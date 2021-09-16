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

@WebServlet("/addNewCategory.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddNewCategory extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	public AddNewCategory() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("categoryName");
		
		if (name == null || name.equals("")) 
		{
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/addCategory.jsp");
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
						String query = DBWorldQuery.insertCategory(c);
						
						dbm.ExecuteNonQuery(query);


					} catch (Exception ex) {
						ex.printStackTrace();
					}
					HttpSession s = request.getSession();
					s.setAttribute("categories", null);
					s.setAttribute("message", "Add new category successful!!");
					response.sendRedirect(getServletContext().getContextPath() + "/Protected/addCategory.jsp");
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
