var checkboxBoss = document.getElementById('checkBoss')
var checkboxes = document.querySelectorAll('.checkBaby');
var idList = [];

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
        console.log(idList);
    } else {
        console.log("no no no");
        removeList(checkbox.id);
        console.log(idList);
    }
}

checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('change', updateCheck);
});

checkboxBoss.addEventListener('change', function() {
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = checkboxBoss.checked;
    });
});