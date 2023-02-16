<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="/WEB-INF/tlds/GalleryTag.tld" prefix="mytags" %>

<html>
<head>
    <title>Gallery</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gallery.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/MyGallery.js"></script>
</head>
<body>
    <div class="center">
        <%
            ArrayList<String> avatarNames = (ArrayList<String>) request.getAttribute("avatarNames");
        %>

        <c:if test="${requestScope.avatarsNumber == 0}">

        </c:if>

        <c:if test="${requestScope.avatarsNumber != 0}">
            <mytags:GalleryTag arrayList="<%= avatarNames %>" />
        </c:if>

    </div>

    <div class="actions">
        <button onclick = "location.href='/menu'"> Меню </button>

        <form action="myGallery" style="margin: 0; padding: 0;" method="post">
            <input type="hidden" name="method" value="PUT">
            <input type="hidden" name="updateIndex" id="updateIndex">
            <button type="submit" id="updateButton" disabled onclick="setUpdateIndex()"> Изменить </button>
        </form>

        <a href="download" download id="downloadLink" onclick="setDownloadIndex()">
            <input type="hidden" name="downloadIndex" id="downloadIndex">
            <button id="downloadButton" disabled> Скачать </button>
        </a>

        <form action="myGallery" method="post">
            <input type="hidden" name="method" value="DELETE">
            <input type="hidden" name="deleteIndex" id="deleteIndex">
            <button type="submit" onclick="deleteAva()"> Удалить </button>
        </form>

        <form action="gallery" style="margin: 0; padding: 0;" method="post">
            <input type="hidden" name="postIndex" id="postIndex">
            <button type="submit" id="postButton" disabled onclick="setPostIndex()"> Опубликовать </button>
        </form>

    </div>
</body>
</html>
