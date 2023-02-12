let selectedButton = null;

function showActions(button_id) {
    if(selectedButton != null) {
        const lastButton = document.getElementById(selectedButton);
        lastButton.style.backgroundColor = "#04AA6D";
    }

    if(selectedButton !== button_id) {
        const myButton = document.getElementById(button_id);
        myButton.style.backgroundColor = "#d24675";
        selectedButton = button_id;
    } else {
        selectedButton = null;
    }
}

function updateAva() {
    if(selectedButton == null) {
        alert("Выберите автарку, которую хотите изменить");
        return;
    }


}

function deleteAva() {
    if(selectedButton == null) {
        alert("Выберите автарку, которую хотите изменить");
        return;
    }

    const attrID = document.getElementById("deleteID");
    attrID.value = selectedButton;
}
