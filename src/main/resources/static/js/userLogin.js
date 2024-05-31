var error1 = document.getElementById('error1');
var error2 = document.getElementById('error2');
var test = document.getElementById('reg-log');

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