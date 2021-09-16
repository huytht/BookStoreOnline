package com.aptech.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	Connection conn = null;
	IConnectionBehavior _cb = null;
	public DBManager() {}
	
	public DBManager(IConnectionBehavior conBehavior) {
		this._cb = conBehavior;
	}
	
	public boolean openConnection() {
		try {
			if (_cb == null)
				throw new IllegalArgumentException("Define a connection behavior");
			if (conn != null)
				closeConnection(false);
			conn = _cb.getConnection();	
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		if (conn == null)
			return false;
		return true;
	}
	
	public boolean closeConnection(boolean keepAlive) {
		try {	
			if (conn != null) 
			{
				if (!conn.isClosed())
					conn.close();
			}
			if (!keepAlive) 
				conn = null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean ExecuteNonQuery(String query) {
		try {
			Statement st = conn.createStatement();
			int i = st.executeUpdate(query);
			if (i == -1)
				return false;
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ResultSet ExecuteResultSet(String query) throws SQLException {
		PreparedStatement st = conn.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		return rs;
	}
	
	public boolean isConnected() {
		return conn != null;
	}
	
	
	public Connection getConnection() {
		return conn;
	}
	
	public String getConnectionURL() {
		return _cb.getConnectionURL();
	}
	
	public String getTableSchemaQuery() {
		return _cb.getTableSchemaQuery();
	}
	
}
