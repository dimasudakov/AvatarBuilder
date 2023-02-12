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

@WebServlet("/register")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/authJSP/SignUp.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(username, password, email);

        try {
            UserJDBC userJDBC = new UserJDBC();

            if(userJDBC.UserNameExist(username)) {
                req.setAttribute("errorMessage", "Пользователь с таким именем уже существует");
                doGet(req, resp);
                return;
            }
            if(userJDBC.EmailExist(email)) {
                req.setAttribute("errorMessage", "Пользователь с таким Email уже существует");
                doGet(req, resp);
                return;
            }
            userJDBC.addUser(user);

            HttpSession session = req.getSession();
            session.setAttribute("client_id", user.getId());
            resp.sendRedirect("menu");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
