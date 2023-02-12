package app.TagSupport;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class GalleryTag extends SimpleTagSupport {
    private ArrayList<String> avatarNames;

    public void setArrayList(ArrayList<String> arrayList) {
        this.avatarNames = arrayList;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<table>");

        for(int i = 0; i < avatarNames.size(); i++) {
            if(i % 3 == 0) {
                out.println("<tr>");
            }
            out.println("<td><button id=\"" + (i + 1) + "\" onclick=\"showActions(id)\">"
                    + avatarNames.get(i) +
                    "</button></td>");
            if((i + 1) % 3 == 0) {
                out.println("</tr>");
            }
        }

        out.println("</table>");
    }
}