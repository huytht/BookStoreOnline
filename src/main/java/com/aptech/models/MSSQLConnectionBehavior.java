package com.aptech.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class MSSQLConnectionBehavior extends DBUserInfo implements IConnectionBehavior {

	public MSSQLConnectionBehavior(String uid, String pwd, String cat) {
		super(uid, pwd, cat);
	}
	
	@Override
	public Connection getConnection() {
		try	{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();	
			Connection cn = DriverManager.getConnection(getConnectionURL());
			return cn;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String getConnectionURL() {
		return String.format("jdbc:sqlserver://localhost:1433;database=%s;"
				+ "user=sa;password=123456;trustServerCertificate=false;loginTimeout=30;"
				, getCat());
	}

	@Override
	public String getConnectionDetail() {
		return "MSSQL database is connection to " + getCat();
	}

	@Override
	public String getTableSchemaQuery() {
		return "select table_name from information_schema.tables where table_schema = " + getCat();
	}
	
	

}
