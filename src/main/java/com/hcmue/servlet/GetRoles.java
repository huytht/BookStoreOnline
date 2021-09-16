package com.hcmue.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcmue.helper.DBWorldQuery;
import com.hcmue.models.*;

@WebServlet("/getRoles.do")
public class GetRoles extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		
		if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
			DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
			
			try {
				if (!dbm.isConnected())
				{
					if (!dbm.openConnection())
						throw new IOException("Could not connect to database and open connection");
				}
				
				String query = DBWorldQuery.getRoles();
				
				ArrayList<Role> allRole = new ArrayList<Role>();
				
				ResultSet rs = dbm.ExecuteResultSet(query);
				
				while (rs.next())
				{
					Role r = new Role();
					r.setId(rs.getInt("id"));
					r.setName(rs.getString("name"));
					allRole.add(r);
				}
				s.setAttribute("roles", allRole);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			String target = (request.getParameter("dest") == null || request.getParameter("dest") == "" ) 
					? "index.jsp" 
					: request.getParameter("dest") + ".jsp";
			response.sendRedirect(target);
			return;
		} else {
			response.sendRedirect("login.jsp");
		}
    }
}
