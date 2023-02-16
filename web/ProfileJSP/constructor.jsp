<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/constructor.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Constructor.js"></script>
    <meta charset="UTF-8">
    <title>Заголовок страницы</title>
</head>


<body onload="loadImage()">
<form action="myGallery" method="post" onsubmit="return validateForm()">
    <div class="options">
        <div class="avatar_name">
            <label>Имя аватара:
                <input type="text" required="required" minlength="4" maxlength="14" id="avatar_name"
                       name="avatar_name"><br />
            </label>
        </div>
        <div class="elements">
            <div class="elem_div">
                <button class="elem_name" type="button" onclick="ShowVariants('hair')">Причёска</button>
            </div>
            <div style="display: none;" id="hair" class="variants">
                <button type="button" id="hair1" onclick="HairButtonClick(1, id)" class="first"> 1 </button>
                <button type="button" id="hair2" onclick="HairButtonClick(2, id)"> 2 </button>
                <button type="button" id="hair3" onclick="HairButtonClick(3, id)"> 3 </button>
                <button type="button" id="hair4" onclick="HairButtonClick(4, id)"> 4 </button>
            </div>
        </div>
        <div class="elements">
            <div class="elem_div">
                <button class="elem_name" type="button" onclick="ShowVariants('eyes')">Глаза</button>
            </div>
            <div style="display: none;" id="eyes" class="variants">
                <button type="button" id="eye1" onclick="EyeButtonClick(1, id)" class="first"> 1 </button>
                <button type="button" id="eye2" onclick="EyeButtonClick(2, id)"> 2 </button>
                <button type="button" id="eye3" onclick="EyeButtonClick(3, id)"> 3 </button>
                <button type="button" id="eye4" onclick="EyeButtonClick(4, id)"> 4 </button>
            </div>
        </div>
        <div class="elements">
            <div class="elem_div">
                <button class="elem_name" type="button" onclick="ShowVariants('mouth')">Рот</button>
            </div>
            <div style="display: none;" id="mouth" class="variants">
                <button type="button" id="mouth1" onclick="MouthButtonClick(1, id)" class="first"> 1 </button>
                <button type="button" id="mouth2" onclick="MouthButtonClick(2, id)"> 2 </button>
                <button type="button" id="mouth3" onclick="MouthButtonClick(3, id)"> 3 </button>
                <button type="button" id="mouth4" onclick="MouthButtonClick(4, id)"> 4 </button>
            </div>
        </div>

        <input type="hidden" name="avatarID" id="avatarID">
        <input type="hidden" name="updateIndex" id="updateIndex">
        <input type="hidden" name="hair" id="hairStyle">
        <input type="hidden" name="eye" id="eyeStyle">
        <input type="hidden" name="mouth" id="mouthStyle">

        <div class="save">
            <button type="button" onclick="history.back()"> назад </button>

            <button type="submit">Сохранить</button>

        </div>
    </div>
</form>

    <div class="image-container">
        <img src="${pageContext.request.contextPath}/resources/FaceElements/StandartFace.png">
        <img id="selectedEye" src="" class="eye" style="display: none;">
        <img id="selectedHair" src="" class="hair" style="display: none;">
        <img id="selectedMouth" src="" class="mouth" style="display: none;">
    </div>

</body>
</html>