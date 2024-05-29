var checkboxBoss = document.getElementById('checkBoss')
var checkboxes = document.querySelectorAll('.checkBaby');

checkboxBoss.addEventListener('change', function() {
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = checkboxBoss.checked;
    });
});