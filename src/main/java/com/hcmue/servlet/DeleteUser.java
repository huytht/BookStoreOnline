package com.hcmue.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcmue.helper.DBWorldQuery;
import com.hcmue.models.DBManager;

/**
 * Servlet implementation class DeleteBook
 */
@WebServlet("/deleteUser.do")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteUser() {
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
					String query = DBWorldQuery.deleteUser(uid);
					dbm.ExecuteNonQuery(query);
					s.setAttribute("webUsers", null);
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

}
