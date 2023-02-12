package app.Servlets;

import app.Dao.AvatarJDBC;
import app.Entities.Avatar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AvatarJDBC db = new AvatarJDBC();

        ArrayList<String> avatarNames = new ArrayList<>();
        try {
            avatarNames = db.getAvatarNamesByID((Integer) session.getAttribute("client_id"));
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }
        req.setAttribute("avatarsNumber", avatarNames.size());
        req.setAttribute("avatarNames", avatarNames);
        getServletContext().getRequestDispatcher("/ProfileJSP/gallery.jsp").
                forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String avatar_name = req.getParameter("avatar_name");
        int hair = Integer.parseInt(req.getParameter("hair"));
        int eye = Integer.parseInt(req.getParameter("eye"));
        int mouth = Integer.parseInt(req.getParameter("mouth"));

        HttpSession session = req.getSession();
        Avatar avatar = new Avatar(avatar_name, hair, eye, mouth, (Integer) session.getAttribute("client_id"));

        try {
            AvatarJDBC avatarJDBC = new AvatarJDBC();
            avatarJDBC.addAvatar(avatar);
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }

        doGet(req, resp);
    }
}
