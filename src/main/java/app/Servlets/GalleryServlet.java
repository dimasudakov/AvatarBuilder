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

        if(req.getParameter("method") != null && req.getParameter("method").equals("DELETE")) {
            doDelete(req, resp);
            return;
        }
        if(req.getParameter("method") != null && req.getParameter("method").equals("PUT")) {
            doPut(req, resp);
            return;
        }

        HttpSession session = req.getSession();

        String avatar_name = req.getParameter("avatar_name");
        int hair = Integer.parseInt(req.getParameter("hair"));
        int eye = Integer.parseInt(req.getParameter("eye"));
        int mouth = Integer.parseInt(req.getParameter("mouth"));
        int client_id = (Integer) session.getAttribute("client_id");

        Avatar avatar = new Avatar(avatar_name, hair, eye, mouth, client_id);

        try {
            AvatarJDBC avatarJDBC = new AvatarJDBC();
            if(req.getParameter("updateIndex") != null && !req.getParameter("updateIndex").equals("")) {
                avatarJDBC.deleteAvatar(Integer.parseInt(req.getParameter("updateIndex")), client_id);
            }
            avatarJDBC.addAvatar(avatar);
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }

        doGet(req, resp);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int client_id = (Integer) session.getAttribute("client_id");
        int id = Integer.parseInt(req.getParameter("deleteIndex"));
        try {
            AvatarJDBC avatarJDBC = new AvatarJDBC();
            avatarJDBC.deleteAvatar(id, client_id);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/gallery");
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if(req.getParameter("updateIndex") != null) {
            AvatarJDBC avatarJDBC = new AvatarJDBC();
            int client_id = (int) session.getAttribute("client_id");
            int updateIndex = Integer.parseInt(req.getParameter("updateIndex"));
            Avatar avatar;

            try {
                avatar = avatarJDBC.getAvatarByIndex(updateIndex, client_id);
                String url = "/constructor?updateIndex=" + updateIndex +
                        "&name=" + avatar.getName() +
                        "&hair=" + avatar.getHair_id() +
                        "&eye=" + avatar.getEye_id() +
                        "&mouth=" + avatar.getMouth_id();
                resp.sendRedirect(url);
            } catch (SQLException | AvatarNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
