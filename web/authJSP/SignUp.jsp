<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign Up</title>

    <link rel="stylesheet" href="../resources/css/authStyle.css">

    //TODO
    <script type="text/javascript">
        const regName = /^[a-zA-Z0-9_]{4,14}$/;

        function checkValues(doc){
            const username = doc.getElementById('username');
            const pass = doc.getElementById('password');
            const pass2 = doc.getElementById('password2');
            if(!regName.exec(username.value)) {
                alert("Поле \'Имя пользователя\' может содержать только строчные и заглавные" +
                    "латинские буквы, цифры, а также \'_\'");
                return false
            }
            if(!(pass.value === pass2.value)) {
                alert("Пароли не совпадают");
                return false;
            }
            return true;
        }

    </script>
</head>

<body>
    <c:if test="${not empty errorMessage}">
        <script>
            alert("${errorMessage}");
        </script>
    </c:if>
    <div style="text-align: center;">
        <form name="register_form" method="post"
              onsubmit="return checkValues(document);">


            <label>Имя пользователя:
                <input type="text" required="required" minlength="4" maxlength="14" id="username"
                       name="username"><br />
            </label>

            <label>Пароль:
                <input type="password" required="required" minlength="4" maxlength="14" id="password"
                       name="password"><br />
            </label>
            <label>Введите пароль повторно:
                <input type="password" required="required" minlength="4" maxlength="14" id="password2"
                       name="pass2"><br />
            </label>

            <label>Email:
                <input type="email" required="required" placeholder="alex_frolov@example.com" id="email"
                       name="email"><br />
            </label>
            <button type="submit" >Submit</button>


        </form>
    </div>
</body>
</html>
