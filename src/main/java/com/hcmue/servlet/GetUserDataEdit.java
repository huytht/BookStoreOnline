package com.hcmue.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcmue.helper.DBWorldQuery;
import com.hcmue.models.*;

@WebServlet("/getUserDataEdit.do")
public class GetUserDataEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUserDataEdit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("uid") != "" && request.getParameter("uid") != null) {
			String uid = request.getParameter("uid");
			HttpSession s = request.getSession();
			
			if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
				DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
				
				try {
					if (!dbm.isConnected())
					{
						if (!dbm.openConnection())
							throw new IOException("Could not connect to database and open connection");
					}
					
					String query = DBWorldQuery.getWebUserByUID(uid);
					
					ResultSet rs = dbm.ExecuteResultSet(query);
					
					if (rs.next())
					{
						WebUser w = new WebUser();
						w.setUid(rs.getString("uid"));
						w.setPwd(rs.getString("password"));
						w.setAuthLevel(rs.getInt("authLevel"));
						s.setAttribute("userDataEdit", w);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				response.sendRedirect(getServletContext().getContextPath() + "/Protected/editUser.jsp?uid=" + uid);
				return;
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
