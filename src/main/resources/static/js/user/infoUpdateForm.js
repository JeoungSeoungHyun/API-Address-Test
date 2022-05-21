// 수정 버튼 클릭 리스너
$("#btn-update-info").click(() => {
    infoUpdate();
});

// 주소 찾기 버튼 클릭 리스너
$("#btn-find").click(() => {
    goPopup();
});

// 주소를 직접 입력하지 않고 버튼이 눌려지도록 하는 함수
$("#address").focusin(() => {
    $("#btn-find").click();
});


// 수정 요청
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

// 팝업창으로부터 주소를 받기 위한 콜백함수(!! let으로 만드는 경우 동작하지 않는다.)
function jusoCallBack(roadFullAddr) {
    $("#address").val(roadFullAddr);
};

// 팝업창을 열기 위한 함수
function goPopup() {
    let pop = window.open(`/popup`, "주소찾기", "width=570,height=420,scrollbars=yes,resizeable=yes")
}