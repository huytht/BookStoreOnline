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

@WebServlet("/editCategory.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)

public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCategory() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession s = request.getSession();
    	String query = "";
    	int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("categoryName");
		
		if (name == null || name.equals("")) 
		{
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/editCategory.jsp?id=" + id);
		} else {
			try {
				Category c = new Category();
				c.setName(name);
				
				if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
					DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
					
					try {
						if (!dbm.isConnected())
						{
							if (!dbm.openConnection())
								throw new IOException("Could not connect to database and open connection");
						}
						query = DBWorldQuery.updateCategory(c, id);
						dbm.ExecuteNonQuery(query);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					s.setAttribute("categoryDataEdit", null);
					s.setAttribute("categories", null);
					s.setAttribute("message", "Edit successful!!");
					response.sendRedirect(getServletContext().getContextPath() + "/Protected/editCategory.jsp?id=" + id);
					return;
				} else {
					response.sendRedirect("login.jsp");
				}
			} catch (Exception ex) {
//				response.sendRedirect(getServletContext().getContextPath() + "/errorHandler.jsp");
				ex.printStackTrace();
			}
		}
		
    }

}
