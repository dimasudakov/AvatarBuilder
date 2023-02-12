<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Gallery</title>
    <link rel="stylesheet" href="../buttonStyle.css">
</head>
<body>
    <div class="center">
        <%
            ArrayList<String> avatarNames = (ArrayList<String>) request.getAttribute("avatarNames");
        %>

        <c:if test="${requestScope.avatarsNumber == 0}">
            asking gasbags
        </c:if>

        <c:if test="${requestScope.avatarsNumber != 0}">

        <table>

            <%
                for(int i = 0; i < avatarNames.size(); i++) {
                    if(i % 3 == 0) {
            %>
                        <tr>
                <%  } %>
                    <td><button><%= avatarNames.get(i) %></button></td>

                    <% if((i + 1) % 3 == 0) { %>
                        </tr>
                    <%}
                }
            %>
        </table>

        </c:if>
    </div>
</body>
</html>
