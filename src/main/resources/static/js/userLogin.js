var error1 = document.getElementById('error1');
var error2 = document.getElementById('error2');
var test = document.getElementById('reg-log');
var checkTest = document.getElementById('checkTest').value;

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