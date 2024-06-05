var sellerId = document.getElementById('sellerId');
var sellerPwd = document.getElementById('sellerPwd');
var sellerPwd1 = document.getElementById('sellerPwd1');
var sellerName = document.getElementById('sellerName');
var sellerBirth = document.getElementById('sellerBirth');
var sellerEmail = document.getElementById('sellerEmail');
var sellerAddress = document.getElementById('sellerAddress');
var sellerPhone = document.getElementById('sellerPhone');
var sellerPhone1 = document.getElementById('sellerPhone1');
var sellerBNum = document.getElementById('sellerBNum');
var idAlert1 = document.querySelector('.idAlert1');
var idAlert2 = document.querySelector('.idAlert2');
var pwdAlert1 = document.querySelector('.pwdAlert1');
var pwdAlert2 = document.querySelector('.pwdAlert2');
var nameAlert1 = document.querySelector('.nameAlert1');
var emailAlert1 = document.querySelector('.emailAlert1');
var phoneAlert1 = document.querySelector('.phoneAlert1');
var BNumAlert1 = document.querySelector('.BNumAlert1');
var idCheck = 0;
var result1 = '';
var result2 = '';
var result3 = '';

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
                    if ( data === 0 ) { // 중복아닐 때
                        sellerId.classList.remove('borderRed');
                        idAlert1.classList.add('hide');
                        idAlert2.classList.remove('hide');
                        idCheck = 1;
                    } else if ( data === 1 ) { // 중복일 때
                        sellerId.classList.add('borderRed');
                        idAlert1.classList.remove('hide');
                        idAlert2.classList.add('hide');
                        sellerId.focus();
                        idCheck = 2;
                    }
                }
            });
        }
    });
});

// 비밀번호 유효성 검사
function checkPwd() {
    if (sellerPwd.value.length !== 0) {
        if (sellerPwd.value.includes(" ")) {
            sellerPwd.classList.add('borderRed');
            sellerPwd1.classList.add('borderRed');
            pwdAlert1.classList.remove('hide');
        } else {
            sellerPwd.classList.remove('borderRed');
            sellerPwd1.classList.remove('borderRed');
            pwdAlert1.classList.add('hide');
        }
    }
    if (sellerPwd.value.length !== 0 && sellerPwd1.value.length !== 0) {
        if (sellerPwd.value !== sellerPwd1.value) {
            sellerPwd.classList.add('borderRed');
            sellerPwd1.classList.add('borderRed');
            pwdAlert2.classList.remove('hide');
        } else {
            sellerPwd.classList.remove('borderRed');
            sellerPwd1.classList.remove('borderRed');
            pwdAlert2.classList.add('hide');
        }
    }
}
sellerPwd.onkeyup = checkPwd;
sellerPwd1.onkeyup = checkPwd;

// 이름 유효성 검사
function koreanCheck(input) {
    var koreanRegex = /^[가-힣]+$/;
    return koreanRegex.test(input);
}

sellerName.onkeyup = function() {
    var isKoreanOnly = koreanCheck(sellerName.value);
    if (!isKoreanOnly) {
        nameAlert1.classList.remove('hide');
        sellerName.classList.add('borderRed');
    } else {
        nameAlert1.classList.add('hide');
        sellerName.classList.remove('borderRed');
    }
}
sellerName.addEventListener('blur', () =>{
    var isKoreanOnly = koreanCheck(sellerName.value);
    if (!isKoreanOnly) {
        nameAlert1.classList.remove('hide');
        sellerName.classList.add('borderRed');
    } else {
        nameAlert1.classList.add('hide');
        sellerName.classList.remove('borderRed');
    }
});

// 이메일 유효성 검사
function emailCheck(email_address) {
    email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
    if (!email_regex.test(email_address)){
        return false;
    } else {
        return true;
    }
}

sellerEmail.onkeyup = function() {
    var test = emailCheck(sellerEmail.value);
    if (!test) {
        emailAlert1.classList.remove('hide');
        sellerEmail.classList.add('borderRed');
    } else {
        emailAlert1.classList.add('hide');
        sellerEmail.classList.remove('borderRed');
    }
}

// 주소지 설정
function sample6_execDaumPostcode() {

    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                result1 = extraAddr;

            } else {
                result1 = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            result2 = data.zonecode;
            result3 = addr;
            document.getElementById('sellerAddress').value = "(" + result2 + ")" + " " + result3
                                                 + " " + result1;
            document.getElementById('sellerAddress1').focus();
        }
    }).open();
}

// 전화번호 유효성 검사
function phoneCheck(phoneNum) {
    pattern = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
    if (!pattern.test(phoneNum)) {
        return false;
    } else {
        return true;
    }
}

sellerPhone.onkeyup = function() {
    var phoneTest = phoneCheck(sellerPhone.value);
    if (!phoneTest) {
        phoneAlert1.classList.remove('hide');
        sellerPhone.classList.add('borderRed');
    } else {
        phoneAlert1.classList.add('hide');
        sellerPhone.classList.remove('borderRed');
    }
}

// 전화번호 사이에 하이픈을 넣는 함수
function phoneNumber(value) {
  value = value.replace(/[^0-9]/g, "");
  return value.replace(
    /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,
    "$1-$2-$3"
  );
}

// 사업자 번호 입력안했을 때
sellerBNum.onkeyup = function() {
    if (sellerBNum.value.trim() === "") {
        sellerBNum.classList.add('borderRed');
        BNumAlert1.classList.remove('hide');
    } else {
        sellerBNum.classList.remove('borderRed');
        BNumAlert1.classList.add('hide');
    }
}

// 회원가입 버튼 누름
function signUp() {
    
    document.getElementById('sellerAddress2'). value = "(" + result2 + ")" + " " + result3 + " "
                                    + document.getElementById('sellerAddress1').value + " " + result1;
    sellerPhone1.value = phoneNumber(sellerPhone.value);
    
    // 빈칸일 때
    if (sellerId.value.trim() === "") {
        alert('아이디를 입력해주세요.');
        sellerId.focus();
        return;
    } else if (sellerPwd.value.trim() === "") {
        alert("비밀번호를 입력해주세요.");
        sellerPwd.focus();
        return;
    } else if (sellerPwd1.value.trim() === "") {
        alert("비밀번호 확인을 입력해주세요.");
        sellerPwd1.focus();
        return;
    } else if (sellerName.value.trim() === "") {
        alert("이름을 입력해주세요.");
        sellerName.focus();
        return;
    } else if (sellerBirth.value.trim() === "") {
        alert("생년월일을 입력해주세요.");
        sellerBirth.focus();
        return;
    } else if (sellerEmail.value.trim() === "") {
        alert("이메일을 입력해주세요.");
        sellerEmail.focus();
        return;
    } else if (sellerAddress.value.trim() === "") {
        alert("주소를 입력해주세요.");
        sellerAddress.focus();
        return;
    } else if (sellerPhone.value.trim() === "") {
        alert("전화번호를 입력해주세요.");
        sellerPhone.focus();
        return;
    } else if (sellerBNum.value.trim() === "") {
        alert("사업자번호를 입력해주세요.");
        sellerBNum.classList.add('borderRed');
        BNumAlert1.classList.remove('hide');
        sellerBNum.focus();
        return;
    }

    // 아이디 중복확인 안됨, 아이디 중복 시
    if (idCheck === 0) {
        alert('중복확인을 해주세요.');
        return;
    } else if (idCheck === 2) {
        alert('이미 존재하는 아이디입니다.');
        return;
    }

    // 비밀번호 공백 존재, 비밀번호 다름
    if (sellerPwd.value.includes(" ")) {
        alert('공백이 존재합니다.');
        sellerPwd.focus();
        return;
    } else if (sellerPwd.value !== sellerPwd1.value) {
        alert('비밀번호 확인이 일치하지 않습니다.');
        sellerPwd1.focus();
        return;
    }

    // 이름이 한글이 아님
    if (!koreanCheck(sellerName.value)) {
        alert('이름은 한글로 입력해주세요.');
        sellerName.focus();
        return;
    }

    // 이메일 형식이 아님
    if (!emailCheck(sellerEmail.value)) {
        alert('이메일 형식을 확인해주세요.');
        sellerEmail.focus();
        return;
    }

    // 전화번호 형식이 틀림
    if (!phoneCheck(sellerPhone.value)) {
        alert('전화번호 형식을 확인해주세요.');
        sellerPhone.focus();
        return;
    }

    if (confirm("회원가입을 진행하시겠습니까?")) {
        document.sellerFrm.submit();
    } else {

    }

}
















