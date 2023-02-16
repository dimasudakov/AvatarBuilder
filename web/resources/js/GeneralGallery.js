let selectedButton = null;
const showButtonID = "showButton";

function showActions(button_id) {
    const showButton = document.getElementById(showButtonID);
    if(selectedButton != null) {
        const lastButton = document.getElementById(selectedButton);
        lastButton.style.backgroundColor = "#04AA6D";
    }

    if(selectedButton !== button_id) {
        const myButton = document.getElementById(button_id);
        myButton.style.backgroundColor = "#d24675";
        selectedButton = button_id;
        showButton.disabled = false;
    } else {
        selectedButton = null;
        showButton.disabled = true;
    }
}

function setShowIndex() {
    const index = document.getElementById("showIndex");
    index.value = selectedButton;
}

