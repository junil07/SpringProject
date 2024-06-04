var sellerId = document.getElementById('sellerId');
var sellerPwd = document.getElementById('sellerPwd');
var sellerPwd1 = document.getElementById('sellerPwd1');
var sellerName = document.getElementById('sellerName');
var sellerBirth = document.getElementById('sellerBirth');
var sellerEmail = document.getElementById('sellerEmail');
var sellerAddress = document.getElementById('sellerAddress');
var sellerPhone = document.getElementById('sellerPhone');
var sellerBNum = document.getElementById('sellerBNum');

// 뒤로가기 눌러서 로그인 페이지 진입 시 페이지 새로고침
window.addEventListener('pageshow', function(event) {
  // persisted 속성이 true면 뒤로가기로 페이지에 진입한 것
  if (event.persisted) {
    // 새로고침
    window.location.reload();
  }
});

// 아이디 중복확인
$(function() {
    $("#idDuplicate").click(function() {

        console.log("중복확인 버튼 눌러짐");
        var sellerIdVal = sellerId.value;
        console.log(sellerIdVal);

        if (sellerIdVal.trim() === "") {
            alert("아이디를 입력해주세요");
        } else {
            $.ajax({
                async: true,
                type: 'POST',
                data: sellerIdVal,
                url: "/seller/idCheck",
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                success : function(data) {
                    console.log("성공 : " + data);
                    if ( data === 0 ) {
                        console.log("중복아니야");
                    } else if ( data === 1 ) {
                        console.log("중복임니다");
                    }
                }
            });
        }
    });
});