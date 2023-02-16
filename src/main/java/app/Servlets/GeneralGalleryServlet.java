package app.Servlets;

import app.Dao.AvatarJDBC;
import app.Dao.GalleryJDBC;
import app.Entities.Avatar;
import app.Exceptions.AvatarNotFoundException;
import app.utils.AvatarConstructor;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/gallery")
public class GeneralGalleryServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(req.getParameter("showAvatar") != null && req.getParameter("showAvatar").equals("true")) {
            int index = Integer.parseInt(req.getParameter("showIndex"));
            GalleryJDBC galleryJDBC = new GalleryJDBC();
            try {
                Avatar avatar = galleryJDBC.getAvatarByIndex(index);
                BufferedImage image = AvatarConstructor.createImgOfAvatar(avatar, getServletContext());
                resp.setContentType("image/png");
                //resp.setHeader("Content-Disposition", "attachment; filename=\"" + avatar.getName() + ".png\"");

                ImageIO.write(image, "png", resp.getOutputStream());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return;
        }

        GalleryJDBC db = new GalleryJDBC();
        ArrayList<String> avatarNames = new ArrayList<>();
        try {
            avatarNames = db.getAvatarNames();
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }
        req.setAttribute("avatarsNumber", avatarNames.size());
        req.setAttribute("avatarNames", avatarNames);
        getServletContext().getRequestDispatcher("/ProfileJSP/generalGallery.jsp").
                forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            AvatarJDBC avatarJDBC = new AvatarJDBC();
            int index = Integer.parseInt(req.getParameter("postIndex"));
            int client_id = (int) session.getAttribute("client_id");
            int id = avatarJDBC.getIDbyIndex(index, client_id);

            GalleryJDBC galleryJDBC = new GalleryJDBC();
            if(!galleryJDBC.contains(id)) {
                galleryJDBC.addAvatar(id);
            }
        } catch (SQLException | AvatarNotFoundException e) {
            //TODO
            e.printStackTrace();
        }

        doGet(req, resp);
    }

}
