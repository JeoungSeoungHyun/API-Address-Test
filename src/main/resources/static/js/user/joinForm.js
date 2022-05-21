$("#btn-find").click(() => {
    goPopup();
});

// 중복 검사를 위한 오브젝트
let valid = {
    username: {
        state: false,
        msg: ""
    },
    password: {
        state: false,
        msg: ""
    },
}

// 오브젝트를 확인하여 오류메세지를 나타내는 함수(form태그의 onsubmit으로 실행)
function validation() {
    if (valid.username.state && valid.password.state) {
        return true;
    } else {
        let errorMsgs = ``;
        let errorUsername = valid.username.msg;
        let errorPassword = valid.password.msg;

        if (errorUsername != "") {
            errorMsgs += `${valid.username.msg}<br/>`;
        }
        if (errorPassword != "") {
            errorMsgs += `${valid.password.msg}<br/>`;
        }

        $(".my_error_box").html(errorMsgs);
        $(".my_error_box").removeClass("my_hidden");
        return false;
    }
}

// input 태그를 확인하며 동적으로 오브젝트 상태를 변경
$("#username").focusout(() => {
    usernameSameCheck();
});
$("#password").focusout(() => {
    passwordSameCheck();
});

$("#password-check").focusout(() => {
    passwordSameCheck();
});

$("#address").focusin(() => {
    $("#btn-find").click();
});

// 유저네임 중복확인 요청 함수
async function usernameSameCheck() {
    let username = $("#username").val();

    let response = await fetch(`/api/user/username/same-check?username=${username}`);
    let responseParse = await response.json();

    if (response.status === 200) {
        if (!responseParse) {
            valid.username.state = false;
            valid.username.msg = "유저네임이 중복되었습니다.";
        } else {
            valid.username.state = true;
            valid.username.msg = "";
        }
    } else {
        alert(responseParse);
    }
}

// 패스워드 일치 확인 함수
function passwordSameCheck() {
    let password = $("#password").val();
    let samePassword = $("#password-check").val();

    if (password === samePassword) {
        valid.password.state = true;
        valid.password.msg = "";
    } else {
        valid.password.state = false;
        valid.password.msg = "패스워드가 동일하지 않습니다.";
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