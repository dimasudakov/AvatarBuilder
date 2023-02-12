<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Sem</title>
    <link rel="stylesheet" href="authStyle.css">
</head>
<body>
    <div>
        <h1><div style="text-align: center;">Создатель Автара</div></h1>
    </div>

    <div style="
        text-align: center;
        background-color: #04AA6D;
        color: white;
        padding: 14px 20px;
        margin: 10px 0 8px;
        border: none;
        cursor: pointer;
        position: relative;
        left: 50%;
        transform: translate(-50%, 0);
        width: 40%;">
        <c:if test="${not empty sessionScope.client_id}">
            <button onclick = "location.href='/menu'"> Мой профиль  </button> <br />
            <form action="logout" method="get">
                    <button style="width: 100%;">Выйти из аккаунта</button><br />
            </form>
        </c:if>
        <c:if test="${empty sessionScope.client_id}">
            <button onclick = "location.href='/login'"> Войти </button> <br />
            <button onclick = "location.href='/register'"> Регистрация </button>
        </c:if>
    </div>

</body>
</html>
