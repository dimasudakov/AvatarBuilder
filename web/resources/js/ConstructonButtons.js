let HairButton = 0;
let EyeButton = 0;
let MouthButton = 0;

let lastHair;
let lastEye;
let lastMouth;

let hair_id = "hairStyle";
let eye_id = "eyeStyle";
let mouth_id = "mouthStyle";

let eye_image_id = "selectedEye";
let mouth_image_id = "selectedMouth";
let hair_image_id = "selectedHair";

let hairPath = "/resources/FaceElements/Hairs/";
let eyePath = "/resources/FaceElements/Eyes/";
let mouthPath = "/resources/FaceElements/Mouths/";

function ShowVariants(elem_id){
    if(document.getElementById(elem_id).style.display === ''){
        document.getElementById(elem_id).style.display = 'none';
    } else {
        document.getElementById(elem_id).style.display = '';
    }
}

function validateForm() {
    if(HairButton === 0 || EyeButton === 0 || MouthButton === 0) {
        alert("Выберите все параметры автара");
        return false;
    }
    return true;
}

function HairButtonClick(index, elem_id) {

    if(HairButton !== index) {
        const myButton = document.getElementById(elem_id);
        myButton.style.backgroundColor = "#d24675";
    }

    if(lastHair != null) {
        const lastButton = document.getElementById(lastHair);
        lastButton.style.backgroundColor = "#40C781FF";
    }

    const hair = document.getElementById(hair_id);
    const hairImg = document.getElementById(hair_image_id);

    if (HairButton === index) {
        lastHair = null;
        HairButton = 0;
        hair.value = null;
        hairImg.src = null;
        hairImg.style.display = 'none';
    } else {
        hair.value = index;
        HairButton = index;
        lastHair = elem_id;
        hairImg.src = hairPath + "hair" + index + ".png";
        hairImg.style.display = '';
    }
}
function EyeButtonClick(index, elem_id) {

    if(EyeButton !== index) {
        const myButton = document.getElementById(elem_id);
        myButton.style.backgroundColor = "#d24675";
    }

    if(lastEye != null) {
        const lastButton = document.getElementById(lastEye);
        lastButton.style.backgroundColor = "#40C781FF";
    }

    const eye = document.getElementById(eye_id);
    const eyeImg = document.getElementById(eye_image_id);
    if (EyeButton === index) {
        lastEye = null;
        EyeButton = 0;
        eye.value = null;
        eyeImg.src = null;
        eyeImg.style.display = 'none';
    } else {
        eye.value = index;
        EyeButton = index;
        lastEye = elem_id;
        eyeImg.src = eyePath + "eye" + index + ".png";
        eyeImg.style.display = '';
    }
}
function MouthButtonClick(index, elem_id) {


    if(MouthButton !== index) {
        const myButton = document.getElementById(elem_id);
        myButton.style.backgroundColor = "#d24675";
    }
    if(lastMouth != null) {
        const lastButton = document.getElementById(lastMouth);
        lastButton.style.backgroundColor = "#40C781FF";
    }

    const mouth = document.getElementById(mouth_id);
    const mouthImg = document.getElementById(mouth_image_id);
    if(MouthButton === index) {
        lastMouth = null;
        MouthButton = 0;
        mouth.value = null;
        mouthImg.src = null;
        mouthImg.style.display = 'none';
    } else {
        mouth.value = index;
        MouthButton = index;
        lastMouth = elem_id;
        mouthImg.src = mouthPath + "mouth" + index + ".png";
        mouthImg.style.display = '';
    }

}

function downloadImage() {

}




