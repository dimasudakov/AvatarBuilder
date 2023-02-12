package app.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("client_id") == null) {
            resp.sendRedirect("/enter");
            return;
        }

        getServletContext().getRequestDispatcher("/ProfileJSP/menu.jsp")
                .forward(req, resp);
    }

}
