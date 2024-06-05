var idList = [];
var checkboxes = document.querySelectorAll('.check_id');
var checkAllBox = document.getElementById('checkAll');
var hideInput = document.querySelectorAll('.infoInput');
var divAdd1 = document.getElementById('addressId1');
var divAdd2 = document.getElementById('addressId2');
var addressBr1 = document.getElementById('addressBr1');
var addressBr2 = document.getElementById('addressBr2');

var thFirstWidth = document.querySelector('.display.dataTable th:first-child').offsetWidth;
var thSecondWidth = document.querySelector('.display.dataTable th:nth-child(2)').offsetWidth;
var thirdWidth = thFirstWidth + thSecondWidth;
var elements1 = document.querySelectorAll('.display.dataTable th:nth-child(2)');
var elements2 = document.querySelectorAll('.display.dataTable td:nth-child(2)');
var elements3 = document.querySelectorAll('.display.dataTable th:nth-child(3)');
var elements4 = document.querySelectorAll('.display.dataTable td:nth-child(3)');

elements1.forEach(function(element) {
    element.style.left = thFirstWidth + 'px';
});
elements2.forEach(function(element) {
    element.style.left = thFirstWidth + 'px';
});
elements3.forEach(function(element) {
    element.style.left = thirdWidth + 'px';
});
elements4.forEach(function(element) {
    element.style.left = thirdWidth + 'px';
});

document.querySelector('.dataTables_scrollBody').addEventListener('scroll', function(event) {
    var scrollLeft = event.target.scrollLeft;
    document.querySelector('.dataTables_scrollHead').scrollLeft = scrollLeft;
    document.querySelector('.dataTables_scrollFoot').scrollLeft = scrollLeft;
});

function hideAll() {
    for ( var i = 0; i < hideInput.length; i++ ) {
        hideInput[i].classList.add('hide');
        if (i !== 4) {
            hideInput[i].value = '';
        }
    }
    divAdd1.classList.add('hide');
    divAdd2.classList.add('hide');
    addressBr1.classList.add('hide');
    addressBr2.classList.add('hide');
}

function addList(text) {
    console.log(text + "확인용");
    if (idList.includes(text)) {
        console.log(text + "있습니다");
    } else {
        idList.push(text);
        console.log(text + "추가됨");
    }
}

function removeList(text) {
    var index = idList.indexOf(text);

    if (index !== -1) {
        idList.splice(index, 1);
        console.log("제거됨");
    } else {
        console.log("원래 없음");
    }
}

function updateCheck(event) {
    var checkbox = event.target;
    if (checkbox.checked) {
        console.log(checkbox.id);
        addList(checkbox.id);
        var result = idList.join("/");
        console.log(result);
        document.getElementById('GradeInput1').textContent = result;
        document.getElementById('GradeInput2').value = result;
        document.getElementById('ActivationInput1').textContent = result;
        document.getElementById('ActivationInput2').value = result;
        console.log(idList);
    } else {
        console.log("hell nah");
        removeList(checkbox.id);
        var result = idList.join("/");
        document.getElementById('GradeInput1').textContent = result;
        document.getElementById('GradeInput2').value = result;
        document.getElementById('ActivationInput1').textContent = result;
        document.getElementById('ActivationInput2').value = result;
        console.log(idList);
    }
}

checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('change', updateCheck);
});

checkAllBox.addEventListener('change', function(event) {
    var isChecked = event.target.checked;
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = isChecked;
        checkbox.dispatchEvent(new Event('change'));
    });
});

function GradeUpdate() {
    var isChecked = false;
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            isChecked = true;
        }
    });

    if(!isChecked) {
        alert("사용자를 선택해주세요");
    } else {
        if (confirm("변경하시겠습니까?")) {
            document.Gfrm.submit();
        } else {

        }
    }
}

function ActivationUpdate() {
    var isChecked = false;
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            isChecked = true;
        }
    });

    if(!isChecked) {
        alert("사용자를 선택해주세요");
    } else {
        if (confirm("변경하시겠습니까?")) {
            document.Afrm.submit();
        } else {

        }
    }
}

function nameUpdate(id, name) {
    var title = "이름 변경";
    var content = "사용자의 이름이 변경됩니다";
    var user = name + "님의 변경될 이름";

    hideAll();
    hideInput[0].classList.remove('hide');

    document.getElementById('buyerInfoTitle').innerText = title;
    document.getElementById('buyerInfoContent').innerText = content;
    document.getElementById('buyerInfoUser').innerText = user;
    document.getElementById('buyerInfoInput1').value = "1";
    document.getElementById('buyerInfoInput2').value = id;
    document.getElementById('infoInput1').focus();
}

function birthUpdate(id, name) {
    var title = "생년월일 변경";
    var content = "사용자의 생년월일이 변경됩니다";
    var user = name + "님의 변경될 생년월일";

    hideAll();
    hideInput[1].classList.remove('hide');

    document.getElementById('buyerInfoTitle').innerText = title;
    document.getElementById('buyerInfoContent').innerText = content;
    document.getElementById('buyerInfoUser').innerText = user;
    document.getElementById('buyerInfoInput1').value = "2";
    document.getElementById('buyerInfoInput2').value = id;
}

function emailUpdate(id, name) {
    var title = "이메일 변경";
    var content = "사용자의 이메일이 변경됩니다";
    var user = name + "님의 변경될 이메일";

    hideAll();
    hideInput[2].classList.remove('hide');

    document.getElementById('buyerInfoTitle').innerText = title;
    document.getElementById('buyerInfoContent').innerText = content;
    document.getElementById('buyerInfoUser').innerText = user;
    document.getElementById('buyerInfoInput1').value = "3";
    document.getElementById('buyerInfoInput2').value = id;
}

function addressUpdate(id, name) {
    var title = "주소 변경";
    var content = "사용자의 주소가 변경됩니다";
    var user = name + "님의 변경될 주소";

    hideAll();
    for (var i = 3; i < 8; i++) {
        hideInput[i].classList.remove('hide');

        if (i !== 4) {
            hideInput[i].value = '';
        }
    }
    divAdd1.classList.remove('hide');
    divAdd2.classList.remove('hide');
    addressBr1.classList.remove('hide');
    addressBr2.classList.remove('hide');

    document.getElementById('buyerInfoTitle').innerText = title;
    document.getElementById('buyerInfoContent').innerText = content;
    document.getElementById('buyerInfoUser').innerText = user;
    document.getElementById('buyerInfoInput1').value = "4";
    document.getElementById('buyerInfoInput2').value = id;
}

function phoneNumUpdate(id, name) {
    var title = "전화번호 변경";
    var content = "사용자의 전화번호가 변경됩니다";
    var user = name + "님의 변경될 전화번호";

    hideAll();
    hideInput[8].classList.remove('hide');

    document.getElementById('buyerInfoTitle').innerText = title;
    document.getElementById('buyerInfoContent').innerText = content;
    document.getElementById('buyerInfoUser').innerText = user;
    document.getElementById('buyerInfoInput1').value = "5";
    document.getElementById('buyerInfoInput2').value = id;
}

function buyerInfoUpdate() {

    if (document.getElementById('buyerInfoInput1').value === "1" &&
                     document.getElementById('infoInput1').value.trim() === "") {
        alert('이름을 입력해주세요');
        return;
     } else if (document.getElementById('buyerInfoInput1').value === "2" &&
                 document.getElementById('infoInput2').value.trim() === "") {
        alert("생년월일을 입력해주세요");
        return;
    } else if (document.getElementById('buyerInfoInput1').value === "3" &&
                 document.getElementById('infoInput3').value.trim() === "") {
        alert("이메일을 입력해주세요");
        return;
    } else if (document.getElementById('buyerInfoInput1').value === "4" &&
                 (document.getElementById('sample6_postcode').value.trim() === "" ||
                  document.getElementById('sample6_address').value.trim() === "" ||
                  document.getElementById('sample6_detailAddress').value.trim() === "")) {
        if (document.getElementById('sample6_postcode').value.trim() === "") {
            alert("우편번호가 입력되지 않았습니다");
            return;
        } else if (document.getElementById('sample6_address').value.trim() === "") {
            alert("주소가 입력되지 않았습니다");
            return;
        } else if (document.getElementById('sample6_detailAddress').value.trim() === "") {
            alert("상세주소가 입력되지 않았습니다");
            return;
        }
    } else if (document.getElementById('buyerInfoInput1').value === "5" &&
                 document.getElementById('infoInput4').value.trim() === "") {
        alert("전화번호를 입력해주세요");
        return;
    }

    if (confirm("변경하시겠습니까?")) {
        var a1 = document.getElementById('addressForm1').value;
        var a2 = document.getElementById('sample6_detailAddress').value;
        var a3 = document.getElementById('sample6_extraAddress').value;
        document.getElementById('addressForm').value = a1 + a2 + a3;

        document.infoFrm.action = "/admin/buyer/buyerInfoUpdate";
        document.infoFrm.submit();
    } else {

    }
}

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수
            var baby

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
                document.getElementById("sample6_extraAddress").value = extraAddr;
                baby = extraAddr;

            } else {
                document.getElementById("sample6_extraAddress").value = '';
                baby = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;

            var address = "(" + data.zonecode + ") " + addr + " ";
            console.log(address);

            document.getElementById('addressForm1').value = address;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

document.addEventListener('DOMContentLoaded', (event) => {
    const toInput = document.querySelectorAll('.infoInput');
    const buttonBaby = document.getElementById('saveBtn');

    toInput.forEach(input => {
        input.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                e.preventDefault();
                buttonBaby.click();
            }
        });
    });

});



