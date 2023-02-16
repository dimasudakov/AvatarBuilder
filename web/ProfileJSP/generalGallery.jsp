<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="/WEB-INF/tlds/GalleryTag.tld" prefix="mytags" %>

<html>
<head>
    <title>Gallery</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gallery.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/GeneralGallery.js"></script>
</head>
<body>
<div class="center">
    <%
        ArrayList<String> avatarNames = (ArrayList<String>) request.getAttribute("avatarNames");
    %>

    <c:if test="${requestScope.avatarsNumber == 0}">
        <%--TODO--%>
    </c:if>

    <c:if test="${requestScope.avatarsNumber != 0}">
        <mytags:GalleryTag arrayList="<%= avatarNames %>" />
    </c:if>

</div>

<div class="actions">
    <button onclick = "location.href='/menu'"> Меню </button>

    <form action="gallery" method="get">
        <input type="hidden" name="showAvatar" value="true">
        <input type="hidden" name="showIndex" id="showIndex">
        <button id="showButton" disabled onclick="setShowIndex()"> Посмотреть </button>
    </form>


</div>
</body>
</html>
