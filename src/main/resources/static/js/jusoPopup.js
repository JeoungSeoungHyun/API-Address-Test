function init() {
    let url = location.href;

    let confmKey = "U01TX0FVVEgyMDIyMDUxODIwNTkzODExMjU4NTc=";
    let resultType = "4";
    let inputYn = $("#inputYn").val();
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