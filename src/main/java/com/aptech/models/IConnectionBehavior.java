package com.aptech.models;

import java.sql.Connection;

public interface IConnectionBehavior {
	Connection getConnection();
	String getConnectionURL();
	String getConnectionDetail();
	String getTableSchemaQuery();
}
