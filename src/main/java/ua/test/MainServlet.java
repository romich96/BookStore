package ua.test;

import ua.test.model.Author;
import ua.test.model.Book;
import ua.test.dao.BooksDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Рома on 05.01.2017.
 */
public class MainServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int answer = checkAction(req);
        if(answer==1){
            String findAuthor=req.getParameter("author");
            List<Book> books=BooksDAO.initBooks(BooksDAO.getBooksByAuthor(findAuthor));
            req.setAttribute("bookList",books);
            getServletContext().getRequestDispatcher("/books.jsp").forward(req,resp);
        }
        if (answer==2){
            String findBook=req.getParameter("book");
            List<Author> authors=BooksDAO.initAuthors(BooksDAO.getAuthorsByBook(findBook));
            req.setAttribute("authorList",authors);
            getServletContext().getRequestDispatcher("/authors.jsp").forward(req,resp);

        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    public int checkAction(HttpServletRequest request){
        if(request.getParameter("getBooks")!=null) {return 1;}
        if (request.getParameter("getAuthors")!=null) return 2;
        return 0;

    }
}
