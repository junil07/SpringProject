var checkboxBoss = document.getElementById('checkBoss')
var checkboxes = document.querySelectorAll('.checkBaby');
var hiddenInput = document.getElementById('hiddenInput');
var thTest = document.querySelectorAll('.thTest');
var tdTest = document.querySelectorAll('.tdTest');
var idList = [];

function resizeAll() {
    for ( var i = 0; i < thTest.length; i++ ) {
        var resizedTd = window.getComputedStyle(tdTest[i]).getPropertyValue('width');
        console.log(thTest[i].style.width);
        thTest[i].style.width = resizedTd;
        console.log(thTest[i].style.width);
    }
}

resizeAll();

function letsStop() {
    if (confirm("판매 중지하시겠습니까?")) {
        document.pmFrm.submit();
    } else {

    }
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
        var result = idList.join('/');
        console.log(idList);
        console.log("result : " + result);
        hiddenInput.value = result;
    } else {
        console.log("no no no");
        removeList(checkbox.id);
        var result = idList.join('/');
        console.log(idList);
        console.log("result : " + result);
        hiddenInput.value = result;
    }
}

checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('change', updateCheck);
});

checkboxBoss.addEventListener('change', function(event) {
    var isChecked = event.target.checked;
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = isChecked;
        checkbox.dispatchEvent(new Event('change'));
    });
});