var buyerId = document.getElementById('buyerId');
var buyerPwd = document.getElementById('buyerPwd');
var buyerPwd1 = document.getElementById('buyerPwd1');
var buyerName = document.getElementById('buyerName');
var buyerBirth = document.getElementById('buyerBirth');
var buyerEmail = document.getElementById('buyerEmail');
var buyerAddress = document.getElementById('buyerAddress');
var buyerPhone = document.getElementById('buyerPhone');
var idAlert1 = document.querySelector('.idAlert1');
var idAlert2 = document.querySelector('.idAlert2');
var pwdAlert1 = document.querySelector('.pwdAlert1');
var pwdAlert2 = document.querySelector('.pwdAlert2');
var emailAlert1 = document.querySelector('.emailAlert1');
var idCheck = 0;
var pwdCheck = 0;
var onlyNumbers = /^\d*$/; // 숫자만 확인하는 정규식

// 뒤로가기 눌러서 로그인 페이지 진입 시 페이지 새로고침
window.addEventListener('pageshow', function(event) {
  // persisted 속성이 true이면 뒤로가기로 페이지에 진입한 것
  if (event.persisted) {
    // 새로고침
    window.location.reload();
  }
});

// 비밀번호 유효성 검사
function checkPwd() {
    if (buyerPwd.value.length !== 0) {
        if (buyerPwd.value.includes(" ")) {
            buyerPwd.classList.add('borderRed');
            buyerPwd.classList.add('focusRed');
            buyerPwd1.classList.add('borderRed');
            buyerPwd1.classList.add('focusRed');
            pwdAlert1.classList.remove('hide');
        } else {
            buyerPwd.classList.remove('borderRed');
            buyerPwd.classList.remove('focusRed');
            buyerPwd1.classList.remove('borderRed');
            buyerPwd1.classList.remove('focusRed');
            pwdAlert1.classList.add('hide');
        }
    }
    if (buyerPwd.value.length !== 0 && buyerPwd1.value.length !== 0) {
        if (buyerPwd.value !== buyerPwd1.value) {
            buyerPwd.classList.add('borderRed');
            buyerPwd.classList.add('focusRed');
            buyerPwd1.classList.add('borderRed');
            buyerPwd1.classList.add('focusRed');
            pwdAlert2.classList.remove('hide');
        } else {
            buyerPwd.classList.remove('borderRed');
            buyerPwd.classList.remove('focusRed');
            buyerPwd1.classList.remove('borderRed');
            buyerPwd1.classList.remove('focusRed');
            pwdAlert2.classList.add('hide');
        }
    }
}
buyerPwd.onkeyup = checkPwd;
buyerPwd1.onkeyup = checkPwd;

// 아이디 중복확인
$(function() {
    $("#idDuplicate").click(function() {

        console.log("나 눌렸다");
        var buyerIdVal = buyerId.value;
        console.log(buyerIdVal);

        if (buyerIdVal.trim() === "") {
            alert("아이디를 입력해주세요");
        } else {
            $.ajax({
                async: true,
                type: 'POST',
                data: buyerIdVal,
                url: "/buyer/idCheck",
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                success : function(data) {
                    console.log("성공했다." + data);
                    console.log(typeof data);
                    if ( data === 0 ) { // 중복아님
                        console.log("중복 아님");
                        buyerId.classList.remove('borderRed');
                        buyerId.classList.remove('focusRed');
                        idAlert1.classList.add('hide');
                        idAlert2.classList.remove('hide');
                        idCheck = 1;
                    } else if ( data === 1 ) { // 중복
                        console.log("중복이다");
                        buyerId.classList.add('borderRed');
                        buyerId.classList.add('focusRed');
                        idAlert1.classList.remove('hide');
                        idAlert2.classList.add('hide');
                        buyerId.focus();
                        idCheck = 2;
                    }
                }
            });
        }
    });
});

// 아이디 유효성 검사
buyerName.addEventListener('blur', function() {
    buyerName.classList.add('borderRed');
    buyerName.classList.add('focusRed');
});

// 이메일 유효성 검사
function emailCheck(email_address){
	email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if (!email_regex.test(email_address)){
		return false;
	} else {
		return true;
	}
}

buyerEmail.onkeyup = function() {
    var test = emailCheck(buyerEmail.value);
    if (!test) {
        emailAlert1.classList.remove('hide');
        buyerEmail.classList.add('borderRed');
        buyerEmail.classList.add('focusRed');
    } else {
        emailAlert1.classList.add('hide');
        buyerEmail.classList.remove('borderRed');
        buyerEmail.classList.remove('focusRed');
    }
}

// 주소지 설정


// 회원가입 버튼 눌렀을 시
function signUp() {
    if (buyerId.value.trim() === "") {
        alert('아이디를 입력해주세요.');
        return;
    } else if (buyerPwd.value.trim() === "") {
        alert("비밀번호를 입력해주세요.");
        return;
    } else if (buyerPwd1.value.trim() === "") {
        alert("비밀번호 확인을 입력해주세요.");
        return;
    } else if (buyerName.value.trim() === "") {
        alert("이름을 입력해주세요.");
        return;
    } else if (buyerBirth.value.trim() === "") {
        alert("생년월일을 입력해주세요.");
        return;
    } else if (buyerEmail.value.trim() === "") {
        alert("이메일을 입력해주세요.");
        return;
    } else if (buyerAddress.value.trim() === "") {
        alert("주소를 입력해주세요.");
        return;
    } else if (buyerPhone.value.trim() === "") {
        alert("전화번호를 입력해주세요");
        return;
    }



    console.log("테스트");
}