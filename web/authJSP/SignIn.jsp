<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sem</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="../resources/css/authStyle.css">

    <script type="text/javascript">
        function ValidateUsername(username)
        {
            var nameformat = /^[a-zA-Z0-9_]{4,14}$/;
            if(username.value.match(nameformat))
            {
                return true;
            }
            else
            {
                alert("Введено некорректное имя пользователя");
                return false;
            }
        }

        function showMsg(wrongTry) {
            if(wrongTry) {
                alert("Неправильные имя пользователя или пароль");
            }
        }

    </script>

    <!-- End of client side validations that need to be handled
         in javascript, it can be handled in separate file or in same jsp -->
</head>
<body onload="showMsg(<%= request.getAttribute("message") %>)">

<!-- We should have a servlet in order to process the form in
      server side and proceed further -->
<form action="login" method="post" onsubmit="ValidateUsername(document.getElementById('username'))">
    <div class="container">

        <label>Имя пользователя:
            <input type="text" required="required" minlength="4" maxlength="14" id="username"
                   name="username"><br />
        </label>

        <label>Пароль:
            <input type="password" required="required" minlength="4" maxlength="14" id="password"
                   name="password"><br />
        </label>

        <button type="submit">Login</button>
    </div>
</form>
</body>
</html>
