   // 프로필 변경 버튼 클릭 리스너
   $("#btn-profile-img").click(() => {
       $("#profile-img-input").click();
   });
   // 프로필 이미지 클릭 리스너
   $("#profile-img").click(() => {
       $("#profile-img-input").click();
   });
   // 파일 input 태그 변화 감지 리스너
   $("#profile-img-input").change((event) => {
       profileImgUpdate(event);
   });

   // 프로필 이미지 수정 요청 함수
   async function profileImgUpdate(event) {
       let file = event.target.files[0];

       if (!file.type.match("image.*")) {
           alert("이미지를 선택해주세요");
           return;
       }

       let userId = $("#principalId").val();
       let fileForm = $("#fileForm")[0];
       let formData = new FormData(fileForm);

       let response = await fetch(`/s/api/user/${userId}/img`, {
           method: "PUT",
           body: formData
       })

       if (response.status != 200) {
           alert("경고!! 올바르지 않은 요청입니다.");
           return;
       }

       let responseParse = await response.json();

       if (responseParse === true) {
           alert("정보가 수정되었습니다.");
           imgPreview(event, file);
       } else {
           alert("정보 수정 도중 문제가 발생하였습니다.");
       }
   }
   // 프로필 이미지 변경 함수
   function imgPreview(event, file) {
       let reader = new FileReader();

       reader.onload = (event) => {
           $("#profile-img").attr("src", event.target.result);
       }
       reader.readAsDataURL(file);
   }