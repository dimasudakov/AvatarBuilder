package app.utils;

import app.Entities.Avatar;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;


public class AvatarConstructor {

    private final static int imgWidth = 2048;
    private final static int imgHeight = 2200;

    private final static int eyeWidth = 1430; // imgWidth * 0.7
    private final static int mouthWidth = 760; // imgWidth * 0.37

    private final static int eyeMarginLeft = 266;
    private final static int eyeMarginTop = 792;

    private final static int mouthMarginLeft = 573;
    private final static int mouthMarginTop = 1430;

    private final static String prefix = "resources/FaceElements/";

    public static BufferedImage createImgOfAvatar(Avatar avatar, ServletContext context) throws Exception {
        String prefix = context.getRealPath("/resources/FaceElements/");

        BufferedImage face = ImageIO.read(
                new File(prefix + "StandartFace.png"));
        BufferedImage hair = ImageIO.read(
                new File(prefix + "Hairs/hair" + avatar.getHair_id() + ".png"));
        BufferedImage eye = ImageIO.read(
                new File(prefix + "Eyes/eye" + avatar.getEye_id() + ".png"));
        BufferedImage mouth = ImageIO.read(
                new File(prefix + "Mouths/mouth" + avatar.getMouth_id() + ".png"));

        BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imgWidth, imgHeight);

        eye = imgResize(eye, eyeWidth, -1);
        hair = imgResize(hair, imgWidth, imgHeight);
        mouth = imgResize(mouth, mouthWidth, -1);

        g.drawImage(face, 0, 0, null);
        g.drawImage(eye, eyeMarginLeft, eyeMarginTop, null);
        g.drawImage(hair, 0, 0, null);
        g.drawImage(mouth, mouthMarginLeft, mouthMarginTop, null);

        return image;
    }

    public static BufferedImage imgResize(BufferedImage img, int width, int height) {
        if(height == -1) {
            height = img.getHeight() * width / img.getWidth();
        }
        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

        return resizedImage;
    }

}