package app.Servlets;

import app.Dao.AvatarJDBC;
import app.Entities.Avatar;
import app.Exceptions.AvatarNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/constructor")
public class ConstructorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        getServletContext().getRequestDispatcher("/ProfileJSP/constructor.jsp")
                .forward(req, resp);

    }
}
