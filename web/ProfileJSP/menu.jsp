<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        button {
            width: 100%;
            height: 50px;
        }
    </style>
</head>
<body>
<table style="width: 40%; margin: 0 auto;">
        <tr>
            <form action="constructor" method="get">
                <td><button style="width: 100%;">Создать аватар</button></td>
            </form>
        </tr>
        <tr>
            <form action="gallery" method="get">
                <td><button style="width: 100%;">Общая галлерея</button></td>
            </form>
        </tr>
        <tr>
                <form action="myGallery" method="get">
                    <td><button style="width: 100%;">Мои аватары</button></td>
                </form>
        </tr>
        <tr>
            <td>
                <button style="width:100%" onclick = "location.href='/'"> Назад  </button> <br />
            </td>
        </tr>
</table>
</body>
</html>