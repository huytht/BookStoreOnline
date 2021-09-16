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
import com.hcmue.models.Category;
import com.hcmue.models.DBManager;
import com.hcmue.models.WebUser;

@WebServlet("/getWebUsers.do")
public class GetWebUsers extends HttpServlet {
       
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
				
				String query = DBWorldQuery.getWebUsers();
				
				ArrayList<WebUser> allUser = new ArrayList<WebUser>();
				
				ResultSet rs = dbm.ExecuteResultSet(query);
				
				while (rs.next())
				{
					WebUser w = new WebUser();
					w.setUid(rs.getString("uid"));
					w.setAuthLevel(rs.getInt("authLevel"));
					allUser.add(w);
				}
				s.setAttribute("webUsers", allUser);
				s.setAttribute("message", null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/listUser.jsp");
			return;
		} else {
			response.sendRedirect("login.jsp");
		}
    }
}
