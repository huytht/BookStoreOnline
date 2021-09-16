package com.aptech.helper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.aptech.models.DBManager;
import com.aptech.models.IConnectionBehavior;
import com.aptech.models.MSSQLConnectionBehavior;

@WebListener
public class DBManagerSetup implements ServletContextListener {

	private DBManager dbm;
    public DBManagerSetup() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)  { 
    	if (dbm != null) {
    		if (dbm.isConnected())
    			dbm.closeConnection(false);
    	}
    	dbm = null;
	}

    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	String uid = sce.getServletContext().getInitParameter("dbuserid");
    	String pwd = sce.getServletContext().getInitParameter("dbuserpwd");
		String cat = sce.getServletContext().getInitParameter("dbinitcat");
		
		IConnectionBehavior ic = new MSSQLConnectionBehavior(uid, pwd, cat);

		dbm = new DBManager(ic);
		sce.getServletContext().setAttribute("BookStoreDB", dbm);
		System.out.print("BookStoreDB has been created");
    }
	
}
