package app.Servlets;

import app.Dao.UserJDBC;
import app.Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("client_id") != null) {
            resp.sendRedirect("/menu");
            return;
        }

        getServletContext().getRequestDispatcher("/authJSP/SignIn.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            UserJDBC userJDBC = new UserJDBC();
            User user = userJDBC.getUser(username);

            if(user.getName() == null || !user.getPassword().equals(password)) {
                req.setAttribute("message", true);
                doGet(req, resp);
            }
            HttpSession session = req.getSession();
            session.setAttribute("client_id", user.getId());
            resp.sendRedirect("menu");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
