<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="/WEB-INF/tlds/GalleryTag.tld" prefix="mytags" %>

<html>
<head>
    <title>Gallery</title>
    <link rel="stylesheet" href="/resources/css/gallery.css">
    <script type="text/javascript" src="/resources/js/GalleryButtons.js"></script>
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
            <mytags:GalleryTag arrayList="<%= avatarNames %>" />
        </c:if>

    </div>

    <div class="actions">
        <button onclick = "location.href='/menu'">Назад</button>

        <button type="button" onclick="updateAva()">Изменить</button>

        <button type="button" onclick="downloadAva()">Скачать</button>

        <form action="gallery" method="post">
            <input type="hidden" name="method" value="DELETE">
            <input type="hidden" name="deleteID" id="deleteID">
            <button type="submit" onclick="deleteAva()"> Удалить </button>
        </form>

    </div>
</body>
</html>
