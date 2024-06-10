// JavaScript 파일 (pdList.js)
//해시태그
const textField = document.getElementById('hashText');
const delHashBtn = document.getElementById('delHashBtn');
const addHashBtn = document.getElementById('addHashBtn');
const textBoxContainer = document.getElementById('hashtag-container');
const categorySelect = document.getElementById('category');
const subCategorySelect = document.getElementById('subCategory');
const subSubCategorySelect = document.getElementById('subSubCategory');
const saveButton = document.getElementById('saveButton');


document.addEventListener("DOMContentLoaded", function() {
    // Function to add a hashtag
    addHashBtn.addEventListener('click', function() {
        const inputText = textField.value.trim();
        const existingHashtags = textBoxContainer.querySelectorAll('.hashtag-box');
        let hasDuplicate = false;

        //중복값
        existingHashtags.forEach(hashtagBox => {
        if (hashtagBox.textContent === inputText) {
            hasDuplicate = true;
            // 중복 값이 존재하면 오류 메시지를 띄우고 함수 종료
            alert('이미 등록된 해시태그 입니다.');
            return;
        }
    });

    if (!hasDuplicate && inputText) {
            createHashtagElement(inputText);
            textField.value = ''; // Clear text field after adding
        }
        const hidden = document.getElementById('totalHash');
        totalHash.value = getAllHashtags();
    });

    // Function to delete a specific hashtag
    delHashBtn.addEventListener('click', function() {
        const inputText = textField.value.trim();
        if (inputText) {
            const textBoxes = textBoxContainer.querySelectorAll('.hashtag-box');
            textBoxes.forEach(hashtagBox => {
                if (hashtagBox.textContent === inputText) {
                    hashtagBox.remove();
                }
            });
        }
        const hidden = document.getElementById('totalHash');
        totalHash.value = getAllHashtags();
    });

    // Function to create a hashtag element
    function createHashtagElement(text) {
        const div = document.createElement('div');
        div.className = 'hashtag-box';
        div.textContent = text;
        textBoxContainer.appendChild(div);
    }
});


//해시태그 박스 클릭 시 텍스트 출력
document.addEventListener("DOMContentLoaded", function() {
    const textBoxContainer = document.getElementById('hashtag-container');

    textBoxContainer.addEventListener('click', function(event) {
        if (event.target.classList.contains('hashtag-box')) {
            document.getElementById("hashText").value = event.target.textContent;
        }
    });
});


//텍스트 입력값이 이미 텍스트 박스에 있다면 추가 방지
var hashtagBoxElement = document.getElementById("hashtag-box");

if (hashtagBoxElement !== null) {
    hashtagBoxElement.addEventListener("click", function() {
        var hashtagBoxes = document.querySelectorAll(".hashtag-box");

        hashtagBoxes.forEach(function(element) {
            element.addEventListener("click", function() {
                document.getElementById("hashText").value = element.textContent;
            });
        });
    });
}


// 요소에 대한 참조 가져오기
const addStockBtn = document.getElementById('addStockBtn');
const sizeInput = document.getElementById('addStock-size');
const countInput = document.getElementById('addStock-count');
const stockContainer = document.getElementById('stock-container');

if (addStockBtn && sizeInput && countInput && stockContainer) {
    // 버튼에 클릭 이벤트 리스너 추가하기
    addStockBtn.addEventListener('click', function() {
        // 입력 필드에서 값 가져오기
        const sizeValue = parseInt(sizeInput.value); // 입력된 size 값을 숫자로 변환
        const countValue = countInput.value;

        // 이미 존재하는 사이즈인지 확인
        const sizeExists = Array.from(stockContainer.children).some(child => {
            const childSize = parseInt(child.querySelector('.input-group-text').textContent);
            return childSize === sizeValue;
        });

        if (sizeExists) {
            alert('이미 존재하는 사이즈입니다. 다른 사이즈를 입력해주세요.');
        } else if (!sizeValue || !countValue) {
            alert('신발 사이즈와 수량을 모두 입력해주세요.');
        } else {
            // 새로운 요소 생성
            const newDiv = document.createElement('div');
            newDiv.className = 'input-group input-group-sm mb-3';
            newDiv.style.float = 'left';
            newDiv.style.width = '33%';

            const newSpan = document.createElement('span');
            newSpan.className = 'input-group-text stockClass';
            newSpan.textContent = sizeValue;

            const newInput = document.createElement('input');
            newInput.setAttribute('type', 'number');
            newInput.className = 'form-control';
            newInput.setAttribute('placeholder', '수량 입력');
            newInput.value = countValue;

            const newButton = document.createElement('button');
            newButton.className = 'btn btn-outline-secondary';
            newButton.setAttribute('type', 'button');
            newButton.textContent = '삭제';
            newButton.addEventListener('click', function() {
                stockContainer.removeChild(newDiv);
                getStockCountList();
            });

            newDiv.appendChild(newSpan);
            newDiv.appendChild(newInput);
            newDiv.appendChild(newButton);

            // size 값을 기준으로 정렬하여 추가
            let inserted = false;
            for (let i = 0; i < stockContainer.children.length; i++) {
                const currentSize = parseInt(stockContainer.children[i].querySelector('.input-group-text').textContent);
                if (sizeValue < currentSize) {
                    stockContainer.insertBefore(newDiv, stockContainer.children[i]);
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                stockContainer.appendChild(newDiv);
            }

            // 입력 필드 초기화
            sizeInput.value = '';
            countInput.value = '';

            getStockCountList();
        }
    });
} else {
    console.error("하나 이상의 요소를 찾을 수 없습니다.");
}

//동적으로 불러온 상자에 삭제 이벤트 추가
document.addEventListener('click', function(e) {
    if (e.target && e.target.id === 'delStockBtn') {
        e.target.closest('.input-group').remove();
        getStockCountList();
    }
});

//사이즈, 재고 수량 이중리스트로 만들어줌
function getStockCountList() {
    const stockList = []; // 이중 리스트 형태로 사이즈와 수량을 저장할 배열
    const stockElements = document.querySelectorAll('#stock-container .input-group');

    stockElements.forEach(element => {
        const sizeElement = element.querySelector('.input-group-text');
        const countElement = element.querySelector('.form-control');

        const sizeValue = parseInt(sizeElement.textContent);
        const countValue = countElement.value;

        const newStock = [sizeValue, countValue];
        stockList.push(newStock);
    });

    const stockHidden = document.getElementById('totalStock');
    totalStock.value = stockList;
}

//초기값 설정
const hidden = document.getElementById('totalHash');
getStockCountList();
totalHash.value = getAllHashtags();

// Function to get all hashtags as an array
function getAllHashtags() {
    const textBoxes = textBoxContainer.querySelectorAll('.hashtag-box');
    return Array.from(textBoxes).map(textBox => textBox.textContent);
}

// 이벤트가 DOM 요소들을 로드한 후 실행되도록 함
document.addEventListener('DOMContentLoaded', function () {
    // B 테이블의 체크박스 요소들을 선택
    var checkboxes = document.querySelectorAll('#b input[name="productCheckbox"]');

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            if (checkbox.checked) {
                // 선택된 체크박스의 부모 행을 복제하여 A 테이블에 추가
                var productRow = checkbox.closest('tr').cloneNode(true);
                document.querySelector('#a tbody').appendChild(productRow);
            } else {
                // 선택 해제된 경우 A 테이블에서 해당 행 삭제
                var productId = checkbox.value;
                var rows = document.querySelectorAll('#a tbody tr');
                rows.forEach(function (row) {
                    if (row.getAttribute('data-product-id') === productId) {
                        row.remove();
                    }
                });
            }
        });
    });
});

//예외처리
saveButton.addEventListener('click', function() {

    // 유효성 검사 함수 추가
    document.getElementById('productForm').addEventListener('submit', function (event) {

        var requiredFields = [
            'productname',
            'productprice',
            'productdiscount',
            'productexplain',
            'productDetailMate',
            'productDetailHeight',
            'productDetailMaker',
            'productDetailCountry',
            'productDetailColor',
            'productDetailYear',
            'productDetailAs',
            'productDetailWarning',
            'productDetailStandard'
        ];
        var isValid = true;

        requiredFields.forEach(function (fieldId) {
            var field = document.getElementById(fieldId);
            if (field && field.value.trim() === '' || field.value == 0) {
                isValid = false;
                field.style.borderColor = 'red'; // 경고 표시
            } else if (field) {
                field.style.borderColor = ''; // 오류가 없을 때 원래 상태로 되돌리기
            }
        });

        if (!isValid ) {
            alert('모든 필수 항목을 입력해주세요.');
            event.preventDefault();
        }
    });

    //재고 하나 이상
    const stockElements = document.querySelectorAll('.stockClass');
    var num = 0;
    stockElements.forEach(element => {
            num++;
        });
    if(num === 0) {
        alert('치수를 하나 이상 추가해주세요.');
        event.preventDefault();
    }

    // 세 번째 카테고리 선택값 확인
    const selectedCategoryId = subSubCategorySelect.value;

    // 세 번째 카테고리 선택값이 없는 경우 경고창 표시
    if (!selectedCategoryId) {
        alert('카테고리를 선택해주세요.');
        event.preventDefault();
    }


});








