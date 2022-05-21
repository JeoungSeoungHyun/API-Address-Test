async function infoUpdate() {
    let userId = $("#principalId").val();
    let updateDto = {
        address: $("#address").val()
    }

    let response = await fetch(`/s/api/user/${userId}/info`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(updateDto)
    })

    if (response.status != 200) {
        alert("경고!! 올바르지 않은 요청입니다.");
        return;
    }

    let responseParse = await response.json();

    if (responseParse === true) {
        alert("정보가 수정되었습니다.");
        location.href = `/s/user/${userId}`;
    } else {
        alert("정보 수정 도중 문제가 발생하였습니다.");
    }
}

$("#btn-update-info").click(() => {
    infoUpdate();
});

function jusoCallBack(roadFullAddr) {
    $("#address").val(roadFullAddr);
};

function goPopup() {
    let pop = window.open(`/addrFind`, "주소찾기", "width=570,height=420,scrollbars=yes,resizeable=yes")

}

$("#btn-find").click(() => {
    goPopup();
});

$("#address").focusin(() => {
    $("#btn-find").click();
});