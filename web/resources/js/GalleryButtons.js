let selectedButton = null;
const downloadButtonID = "downloadButton";
const updateButtonID = "updateButton";

function showActions(button_id) {
    const downloadButton = document.getElementById(downloadButtonID);
    const updateButton = document.getElementById(updateButtonID);
    if(selectedButton != null) {
        const lastButton = document.getElementById(selectedButton);
        lastButton.style.backgroundColor = "#04AA6D";
    }

    if(selectedButton !== button_id) {
        const myButton = document.getElementById(button_id);
        myButton.style.backgroundColor = "#d24675";
        selectedButton = button_id;
        downloadButton.disabled = false;
        updateButton.disabled = false;
    } else {
        selectedButton = null;
        downloadButton.disabled = true;
        updateButton.disabled = true;
    }
}


function deleteAva() {
    if(selectedButton == null) {
        alert("Выберите автарку, которую хотите удалить");
        return;
    }
    const attrID = document.getElementById("deleteIndex");
    attrID.value = selectedButton;
}

function setDownloadIndex() {
    const link = document.getElementById("downloadLink");
    const baseUrl = link.href;

    link.setAttribute("href", baseUrl + "?downloadIndex=" + selectedButton);
}

function setUpdateIndex() {
    const index = document.getElementById("updateIndex");
    index.value = selectedButton;
}

