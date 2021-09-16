package com.aptech.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.helper.DBWorldQuery;
import com.aptech.models.*;

@WebServlet("/addNewUser.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddNewUser extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	public AddNewUser() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String authLevel = request.getParameter("authLevel");
		
		if (uid == null || uid.equals("")
			|| pwd == null || pwd.equals("")
			|| authLevel == null || authLevel.equals("")) 
		{
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/addUser.jsp");
		} else {
			try {
				WebUser w = new WebUser();
				w.setUid(uid);
				w.setPwd(pwd);
				w.setAuthLevel(Integer.parseInt(authLevel));
				
				if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
					DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
					
					try {
						if (!dbm.isConnected())
						{
							if (!dbm.openConnection())
								throw new IOException("Could not connect to database and open connection");
						}
						String query = DBWorldQuery.insertUser(w);
						
						dbm.ExecuteNonQuery(query);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					HttpSession s = request.getSession();
					s.setAttribute("webUsers", null);
					s.setAttribute("message", "Add new user successful!!");
					response.sendRedirect(getServletContext().getContextPath() + "/Protected/addUser.jsp");
					return;
				} else {
					response.sendRedirect("login.jsp");
				}
			} catch (Exception ex) {
				response.sendRedirect(getServletContext().getContextPath() + "/errorHandler.jsp");
			}
		}
		
	}
}
