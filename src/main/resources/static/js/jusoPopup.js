// 팝업창으로 실행 주소API로 직접적인 요청을 하게되는 함수
// onload로 실행 => 브라우저가 html을 로드시 동작
function init() {

    // 응답을 받기 위해 현재 주소의 정보를 전달
    let url = location.href;

    // 인증키
    let confmKey = "U01TX0FVVEgyMDIyMDUxODIwNTkzODExMjU4NTc=";

    // 응답받을 데이터의 범위 설정
    let resultType = "4";

    // 주소API로 요청과 원래창으로 콜백을 분기하기 위한 조건변수
    let inputYn = $("#inputYn").val();

    // 응답받을 주소
    let roadFullAddr = $("#roadFullAddr").val();

    if (inputYn != "Y") {
        $("#confmKey").val(confmKey);
        $("#returnUrl").val(url);
        $("#resultType").val(resultType);
        $("#form").attr("action", "https://www.juso.go.kr/addrlink/addrLinkUrl.do");
        $("#form")[0].submit();
    } else {
        opener.jusoCallBack(roadFullAddr);
        window.close();
    }
}