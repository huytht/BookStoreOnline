package com.hcmue.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcmue.helper.DBWorldQuery;
import com.hcmue.models.*;

@WebServlet("/editUser.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)

public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUser() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession s = request.getSession();
    	String query = "";
    	String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String authLevel = request.getParameter("authLevel");
		
		if (uid == null || uid.equals("")
				|| pwd == null || pwd.equals("")
				|| authLevel == null || authLevel.equals("")) 
		{
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/editUser.jsp?uid=" + uid);
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
						query = DBWorldQuery.updateUser(w, uid);
						dbm.ExecuteNonQuery(query);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					s.setAttribute("userDataEdit", null);
					s.setAttribute("webUsers", null);
					s.setAttribute("message", "Edit successful!!");
					response.sendRedirect(getServletContext().getContextPath() + "/Protected/editUser.jsp?uid=" + uid);
					return;
				} else {
					response.sendRedirect("login.jsp");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
    }

}
