document.addEventListener('DOMContentLoaded', function () {
    var datatableElements = document.querySelectorAll('.datatablesSimple');
    datatableElements.forEach(function (datatableElement) {
        new simpleDatatables.DataTable(datatableElement);
    });

    document.getElementById('relationDelBtn').disabled = true;
    document.getElementById('relationBtn').disabled = true;

    var tableCBody = document.querySelector('#c tbody');

    // Event delegation for add-conn-btn
    tableCBody.addEventListener('click', function (event) {
        if (event.target.classList.contains('add-conn-btn')) {
            var btn = event.target;

            // empty 메시지 삭제
            var emptyCell = document.querySelector('td.datatable-empty');
            if (emptyCell) {
                emptyCell.parentNode.removeChild(emptyCell);
            }

            var row = btn.closest('tr');
            var productCode = row.querySelector('td:nth-child(1)').innerText.trim();
            var category = row.querySelector('td:nth-child(2)').innerText.trim();
            var productName = row.querySelector('td:nth-child(3)').innerText.trim();

            var tableARows = document.querySelectorAll('#a tr');
            var result = true;
            tableARows.forEach(function (row) {
                var cell = row.querySelector('td:nth-child(1)');
                if (cell) {
                    var cellValue = cell.textContent.trim();
                    if (cellValue === productCode) {
                        alert("이미 추가된 상품입니다.");
                        result = false;
                    }
                }
            });

            if (result) {
                var tableA = document.getElementById('a').getElementsByTagName('tbody')[0];
                var newRow = tableA.insertRow();

                var cell1 = newRow.insertCell(0);
                var cell2 = newRow.insertCell(1);
                var cell3 = newRow.insertCell(2);
                var cell4 = newRow.insertCell(3);

                cell1.textContent = productCode;
                cell2.textContent = category;
                cell3.textContent = productName;

                console.log('새로운 행이 테이블 A에 추가되었습니다.');

                var tableARows = document.querySelectorAll('#a tr');
                var cellValues = [];
                tableARows.forEach(function (row) {
                    var cell = row.querySelector('td:nth-child(1)');
                    if (cell) {
                        var cellValue = cell.textContent.trim();
                        cellValues.push(cellValue);
                    }
                });

                //삭제버튼 비활성화
                document.getElementById('relationDelBtn').disabled = true;
                // 만약 A 테이블의 행이 1개 이하라면 추가버튼 비활성화
                var relationBtn = document.getElementById('relationBtn');
                if (cellValues.length < 2) {
                    relationBtn.disabled = true;
                } else {
                    relationBtn.disabled = false;
                }

                const relationList = document.getElementById('relationList');
                relationList.value = cellValues.join(",");

                var delBtn = document.createElement('button');
                delBtn.className = "btn btn-secondary btn-sm";
                delBtn.style.height = "25px";
                delBtn.style.fontSize = "10px";
                delBtn.type = "button";
                delBtn.name = "delConnBtn";
                delBtn.textContent = "DEL";

                cell4.appendChild(delBtn);

                delBtn.addEventListener('click', function () {
                    var rowToRemove = this.closest('tr');
                    rowToRemove.parentNode.removeChild(rowToRemove);

                    var tableARows = document.querySelectorAll('#a tr');
                    var cellValues = [];
                    tableARows.forEach(function (row) {
                        var cell = row.querySelector('td:nth-child(1)');
                        if (cell) {
                            var cellValue = cell.textContent.trim();
                            cellValues.push(cellValue);
                        }
                    });
                    relationList.value = cellValues.join(",");

                    var relationBtn = document.getElementById('relationBtn');
                    if (cellValues.length < 2) {
                        relationBtn.disabled = true;
                    } else {
                        relationBtn.disabled = false;
                    }
                });
            }
        }
    });
});


function addListToTable(pk, name) {
    // AJAX를 사용하여 서버로부터 해당하는 List를 비동기적으로 가져옴
    // 예를 들어 jQuery를 사용한다면:
    $.ajax({
        url: '/seller/pm/getRelationProductIdList',
        method: 'GET',
        data: { pk: pk,
                name: name },
        success: function (response) {
            //테이블 비움
            var tableABody = document.querySelector('#a tbody');
            tableABody.innerHTML = '';

            //수정버튼 활성화
            document.getElementById('relationBtn').disabled = false;
            //삭제버튼 활성화
            document.getElementById('relationDelBtn').disabled = false;

            //테이블에 값 추가
            response.forEach(function (item) {
                var newRow = document.createElement('tr');
                newRow.innerHTML = '<td>' + item.productCode + '</td><td>' + item.category.categoryName + '</td><td>' + item.productName +
                                    '</td><td> <button class="btn btn-secondary btn-sm delBtn" style="height: 25px; font-size: 10px;" type="button">DEL</button> </td>';
                document.getElementById('a').getElementsByTagName('tbody')[0].appendChild(newRow);
            });

            var tableARows = document.querySelectorAll('#a tr');
            var cellValues = [];
            tableARows.forEach(function (row) {
                var cell = row.querySelector('td:nth-child(1)');
                if (cell) {
                    var cellValue = cell.textContent.trim();
                    cellValues.push(cellValue);
                }
            });
            const relationList = document.getElementById('relationList');
            const relationName = document.getElementById('relationName');
            relationList.value = cellValues.join(",");
            relationName.value = name;

            // 모든 행을 추가한 후에 이벤트 리스너를 추가
            var delBtns = document.querySelectorAll('.delBtn');
            delBtns.forEach(function(delBtn) {
                delBtn.addEventListener('click', function() {
                    var rowToRemove = this.closest('tr');
                    rowToRemove.parentNode.removeChild(rowToRemove);

                    var tableARows = document.querySelectorAll('#a tr');
                    var cellValues = [];
                    tableARows.forEach(function (row) {
                        var cell = row.querySelector('td:nth-child(1)');
                        if (cell) {
                            var cellValue = cell.textContent.trim();
                            cellValues.push(cellValue);
                        }
                    });
                    relationList.value = cellValues.join(",");

                    // 만약 A 테이블의 행이 1개 이하라면 수정버튼 비활성화
                    var relationBtn = document.getElementById('relationBtn');
                    if (cellValues.length < 2) {
                        relationBtn.disabled = true;
                    } else {
                        relationBtn.disabled = false;
                    }
                });
            });

        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}




