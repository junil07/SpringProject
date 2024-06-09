var error1 = document.getElementById('error1');
var error2 = document.getElementById('error2');
var test = document.getElementById('reg-log');
var checkTest = document.getElementById('checkTest').value;
var idFindDisplay = document.querySelector('.idFindDisplay');
var findSellerName1 = document.getElementById('findSellerName1');
var findSellerEmail1 = document.getElementById('findSellerEmail1');
var idFoundDisplay = document.querySelector('.idFoundDisplay');
var foundSellerId1 = document.getElementById('foundSellerId1');
var pwdFindDisplay = document.querySelector('.pwdFindDisplay');
var idForReset1 = document.getElementById('idForReset1');
var emailForReset1 = document.getElementById('emailForReset1');
var sellerPwdResetBtn = document.getElementById('sellerPwdResetBtn');
var verificationCodeDisplay = document.querySelector('.verificationCodeDisplay');
var verificationCodeInput = document.getElementById('verificationCodeInput');
var verificationCodeHidden = document.getElementById('verificationCodeHidden');
var pwdResetDisplay = document.querySelector('.pwdResetDisplay');
var newSellerPwd2 = document.getElementById('newSellerPwd2');
var newSellerPwd4 = document.getElementById('newSellerPwd4');
var findAccount = document.getElementById('findAccount');

// 뒤로가기 눌러서 로그인 페이지 진입 시 페이지 새로고침
window.addEventListener('pageshow', function(event) {
  // persisted 속성이 true이면 뒤로가기로 페이지에 진입한 것
  if (event.persisted) {
    // 새로고침
    window.location.reload();
  }
});

// seller/login url요청으로 들어왔을 때 판매자 로그인창이 보인다.
if (checkTest === "2") {
    test.checked = true;
}

if (error1 !== null) {
    test.checked = false;
}

if (error2 !== null) {
    test.checked = true;
}

$(function() {
    $("#buyerBtn").click(function() {
        findAccount.textContent = "구매자 계정 찾기 및 비밀번호 재설정";
        findAccount.classList.remove('colorBaby');
    });
    $("#sellerBtn").click(function() {
        findAccount.textContent = "판매자 계정 찾기 및 비밀번호 재설정";
        findAccount.classList.add('colorBaby');
    });
});

function letsFindId() {
    findSellerName1.value = "";
    findSellerEmail1.value = "";
    foundSellerId1.textContent = "";
    idFindDisplay.classList.remove('hide');
    idFoundDisplay.classList.add('hide');
    pwdFindDisplay.classList.add('hide');
    verificationCodeDisplay.classList.add('hide');
    pwdResetDisplay.classList.add('hide');
}

function letsResetPwd() {
    idForReset1.value = "";
    emailForReset1.value = "";
    verificationCodeInput.value = "";
    newSellerPwd2.value = "";
    newSellerPwd4.value = "";
    idFindDisplay.classList.add('hide');
    idFoundDisplay.classList.add('hide');
    pwdFindDisplay.classList.remove('hide');
    verificationCodeDisplay.classList.add('hide');
    pwdResetDisplay.classList.add('hide');
}

function verificationCheck() {
    if (verificationCodeInput.value === verificationCodeHidden.value) {
        alert("인증 성공");
        verificationCodeDisplay.classList.add('hide');
        pwdResetDisplay.classList.remove('hide');
    } else {
        alert("인증번호가 다릅니다.");
    }
}

function buyerLogin() {
    var buyerId = document.getElementById('buyerId').value;
    var buyerPwd = document.getElementById('buyerPwd').value;

    document.getElementById('buyer1').value = buyerId;
    document.getElementById('buyer2').value = buyerPwd;

    document.buyerFrm.submit();
}

function sellerLogin() {
    var sellerId = document.getElementById('sellerId').value;
    var sellerPwd = document.getElementById('sellerPwd').value;

    document.getElementById('seller1').value = sellerId;
    document.getElementById('seller2').value = sellerPwd;

    document.sellerFrm.submit();
}

// 이름 이메일로 아이디 찾기
$(function() {
    $("#findSellerIdBtn").click(function() {
        console.log("버튼을 눌렸다.");
        var findSellerName1Val = findSellerName1.value;
        var findSellerEmail1Val = findSellerEmail1.value;
        var page = "";
        var dataToSend = {};

        // 판매자
        if (test.checked) {
            dataToSend = {
                sellerName: findSellerName1Val,
                sellerEmail: findSellerEmail1Val
            };
            page = "/seller/idFind";
        } else { // 구매자
            dataToSend = {
                buyerName: findSellerName1Val,
                buyerEmail: findSellerEmail1Val
            };
            page = "/buyer/idFind";
        }

        if (findSellerName1Val.trim() === "" || findSellerEmail1Val.trim() === "") {
            alert("이름 혹은 이메일을 입력해주세요");
        } else {
            $.ajax({
                async: true,
                type: 'POST',
                data: JSON.stringify(dataToSend),
                url: page,
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                success : function(data) {

                    if (data) {

                        if (test.checked) {
                            foundSellerId1.textContent = data.sellerName + "님의 아이디는 " + data.sellerId + " 입니다";
                            idFindDisplay.classList.add('hide');
                            idFoundDisplay.classList.remove('hide');
                        } else {
                            foundSellerId1.textContent = data.buyerName + "님의 아이디는 " + data.buyerId + " 입니다";
                            idFindDisplay.classList.add('hide');
                            idFoundDisplay.classList.remove('hide');
                        }

                    }

                },
                error: function(xhr, status, error) {
                    alert("아이디를 찾을 수 없습니다.");
                }
            });
        }
    });
});

// 아이디 이메일로 있는지 확인
$(function() {
    $("#sellerPwdResetBtn").click(function() {
        var idForReset1Val = idForReset1.value;
        var emailForReset1Val = emailForReset1.value;
        var page = "";
        var dataToSend = {};

        // 판매자
        if (test.checked) {
            dataToSend = {
                sellerId: idForReset1Val,
                sellerEmail: emailForReset1Val
            };
            page = "/seller/sellerFind";
        } else { // 구매자
            dataToSend = {
                buyerId: idForReset1Val,
                buyerEmail: emailForReset1Val
            };
            page = "/buyer/buyerFind";
        }

        if (idForReset1Val.trim() === "" || emailForReset1Val.trim() === "") {
            alert("아이디 혹은 이메일을 입력해주세요");
        } else {
            $.ajax({
                async: true,
                type: 'POST',
                data: JSON.stringify(dataToSend),
                url: page,
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                success : function(data) {
                    if (data === "") {
                        alert("사용자를 찾을 수 없습니다");
                    } else {
                        console.log("데이터 : " + data);
                        document.getElementById('verificationCodeHidden').value = data.verificationCode;
                        pwdFindDisplay.classList.add('hide');
                        verificationCodeDisplay.classList.remove('hide');
                    }
                },
                error: function(xhr, status, error) {
                    console.log("상태: " + status);
                    console.log("오류: " + error);
                    console.log("응답 텍스트: " + xhr.responseText);
                    alert("사용자를 찾을 수 없습니다.");
                }
            });
        }
    });
});

// 새 비밀번호 설정
$(function() {
    $("#getNewPwd").click(function() {
        var newPassword = newSellerPwd2.value;
        var newPassword1 = newSellerPwd4.value;
        var page = "";
        var dataToSend = {};

        // 판매자
        if (test.checked) {
            dataToSend = {
                sellerId: idForReset1.value,
                sellerPassword: newPassword
            };
            page = "/seller/newPwd";
        } else if (!test.checked) { // 구매자
            dataToSend = {
                buyerId: idForReset1.value,
                buyerPassword: newPassword
            };
            page = "/buyer/newPwd";
        }

        if (newPassword.trim() === "") {
            alert('공백이 존재합니다.');
        } else if (newPassword !== newPassword1) {
            alert('비밀번호가 일치하지 않습니다.');
        } else if (confirm("변경하시겠습니까?")) {
            $.ajax({
                async: true,
                type: 'POST',
                data: JSON.stringify(dataToSend),
                url : page,
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                success : function(data) {
                    if (data === 0) {
                        alert('오류 발생');
                    } else {
                        alert('새 비밀번호가 설정되었습니다.');
                        location.reload();
                    }
                }
            });
        } else {

        }
    });
});









