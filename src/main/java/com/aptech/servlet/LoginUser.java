package com.aptech.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.helper.DBWorldQuery;
import com.aptech.models.DBManager;
import com.aptech.models.WebUser;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/loginUser.do")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		WebUser wu = (WebUser)s.getAttribute("authorized_user");
		
		if (wu == null || wu.getUid().equals("") || wu.getUid() == null || wu.getAuthLevel() < 1) {
			String uid = "";
			String pwd = "";
			
			if (request.getParameter("uid") != null) 
				uid = request.getParameter("uid");
			
			if (request.getParameter("pwd") != null) 
				pwd = request.getParameter("pwd");
			
			if (wu == null || wu.getUid().equals("") || wu.getUid() == null || wu.getAuthLevel() < 1
					&& uid != "" && pwd != "") {
				if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
					DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
					
					try {
						if (!dbm.isConnected())
						{
							if (!dbm.openConnection())
								throw new IOException("Could not connect to database and open connection");
						}
						
						String query = DBWorldQuery.getWebUserByUsernameAndPassword(uid, pwd);

						ResultSet rs = dbm.ExecuteResultSet(query);
						
						while (rs.next())
						{
							wu = new WebUser();
							
							wu.setUid(rs.getString("uid"));
							wu.setPwd(rs.getString("password"));
							wu.setAuthLevel(rs.getInt("authlevel"));
							
							s.setAttribute("authorized_user", wu);
							
							if (request.getParameter("rememberMe") != null) {
								String rememberMe = request.getParameter("rememberMe");
								if (rememberMe.equalsIgnoreCase("ON")) {
									int cookieLife = 3600 * 24 * 7;
									
									Cookie uidCookie = new Cookie("credentials_uid", uid);
									Cookie pwdCookie = new Cookie("credentials_pwd", pwd);
									
									uidCookie.setMaxAge(cookieLife);
									pwdCookie.setMaxAge(cookieLife);

									response.addCookie(uidCookie);
									response.addCookie(pwdCookie);
								}
							}
						}
					} catch (Exception ex) {
						System.out.print("Exception: " + ex.getMessage());
						response.sendRedirect(getServletContext().getContextPath() + "/loginError.jsp");
						return;
					}
				} else {
					response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
				}
			}
			if (wu == null || wu.getUid().equals("") || wu.getUid() == null || wu.getAuthLevel() < 1) {
				response.sendRedirect(getServletContext().getContextPath() + "/loginError.jsp");
				return;
			}
		}
		String target = (request.getParameter("dest") == null || request.getParameter("dest") == "" ) 
				? "index.jsp" 
				: request.getParameter("dest") + ".jsp";
		response.sendRedirect(target);
	}
}
