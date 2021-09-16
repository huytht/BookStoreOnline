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
import com.hcmue.models.Category;
import com.hcmue.models.DBManager;

@WebServlet("/getCategoryDataEdit.do")
public class GetCategoryDataEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCategoryDataEdit() {
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
					
					String query = DBWorldQuery.getCategoryFromId(id);
					
					ResultSet rs = dbm.ExecuteResultSet(query);
					
					if (rs.next())
					{
						Category c = new Category();
						c.setName(rs.getString("name"));
						s.setAttribute("categoryDataEdit", c);
					}
				} catch (Exception ex) {
					throw new IOException("Query could not be executed to get data of category");
				}
				response.sendRedirect(getServletContext().getContextPath() + "/Protected/editCategory.jsp?id=" + id);
				return;
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
