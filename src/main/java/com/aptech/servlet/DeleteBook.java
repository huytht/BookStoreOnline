package com.aptech.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

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
@WebServlet("/deleteBook.do")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			HttpSession s = request.getSession();
			String imageFile = "";
			if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
				DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
				
				try {
					if (!dbm.isConnected())
					{
						if (!dbm.openConnection())
							throw new IOException("Could not connect to database and open connection");
					}
					String query = DBWorldQuery.deleteBook(id);
					String queryGetBook = DBWorldQuery.getBookFromId(id);
					ResultSet rs = dbm.ExecuteResultSet(queryGetBook);
					while (rs.next()) {
						imageFile = rs.getString("image");
						break;
					}
					//Delete file image
				    File image = new File(System.getProperty("user.dir") + "/" + getServletContext().getContextPath() + "/src/main/webapp/image/" + imageFile); 
				    if (image.delete()) { 
				      System.out.println("Deleted the file: " + image.getName());
				    } else {
				      System.out.println("Failed to delete the file.");
				    } 

					dbm.ExecuteNonQuery(query);
					s.setAttribute("bookData", null);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				response.sendRedirect(getServletContext().getContextPath() + "/Protected/listBooks.jsp");
				return;
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

}
