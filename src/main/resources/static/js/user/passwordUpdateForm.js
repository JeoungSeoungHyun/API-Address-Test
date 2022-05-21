// 패스워드 수정 버튼 클릭 리스너
$("#btn-update-password").click(() => {
    passwordSameCheck();
});

// 패스워드 수정 요청 함수
async function passwordUpdate() {
    let userId = $("#principalId").val();
    let updateDto = {
        password: $("#password").val()
    }

    let response = await fetch(`/s/api/user/${userId}/password`, {
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

// 패스워드 일치 확인 함수
function passwordSameCheck() {
    let password = $("#password").val();
    let samePassword = $("#password-check").val();

    if (password === samePassword) {
        passwordUpdate();
    } else {
        $(".my_error_box").text("패스워드가 동일하지 않습니다.");
        $(".my_error_box").removeClass("my_hidden");
    }
}