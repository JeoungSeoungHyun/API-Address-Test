alert("됨");
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

// let으로 만들면 window가 init할때 생성을 못하는 것같다.!! 이 부분은 좀 더 알아보깋
function jusoCallBack(roadFullAddr) {
    $("#address").val(roadFullAddr);
};

function goPopup() {
    let pop = window.open(`/addrFind`, "주소찾기", "width=570,height=420,scrollbars=yes,resizeable=yes")

}

$("#btn-find").click(() => {
    goPopup();
});