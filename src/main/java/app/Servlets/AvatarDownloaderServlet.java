package app.Servlets;

import app.Dao.AvatarJDBC;
import app.Entities.Avatar;
import app.utils.AvatarConstructor;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;


@WebServlet("/download")
public class AvatarDownloaderServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int client_id = (Integer) session.getAttribute("client_id");
        int downloadIndex = Integer.parseInt(req.getParameter("downloadIndex"));
        try {
            AvatarJDBC avatarJDBC = new AvatarJDBC();
            Avatar avatar = avatarJDBC.getAvatarByIndex(downloadIndex, client_id);
            BufferedImage image = AvatarConstructor.createImgOfAvatar(avatar, getServletContext());

            resp.setContentType("image/png");
            resp.setHeader("Content-Disposition", "attachment; filename=\"image.png\"");

            // Записываем изображение в OutputStream
            ImageIO.write(image, "png", resp.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


