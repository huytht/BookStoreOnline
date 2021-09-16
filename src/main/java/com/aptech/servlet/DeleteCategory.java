package com.aptech.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aptech.helper.DBWorldQuery;
import com.aptech.models.DBManager;

/**
 * Servlet implementation class DeleteBook
 */
@WebServlet("/deleteCategory.do")
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCategory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			HttpSession s = request.getSession();
			if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
				DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
				
				try {
					if (!dbm.isConnected())
					{
						if (!dbm.openConnection())
							throw new IOException("Could not connect to database and open connection");
					}
					String query = DBWorldQuery.deleteCategory(id);
					dbm.ExecuteNonQuery(query);
					s.setAttribute("categories", null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				response.sendRedirect(getServletContext().getContextPath() + "/Protected/listCategory.jsp");
				return;
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

}
