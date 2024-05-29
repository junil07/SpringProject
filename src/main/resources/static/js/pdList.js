// JavaScript 파일 (pdList.js)

window.addEventListener('DOMContentLoaded', event => {
    // 테이블 행 마우스오버 이벤트 추가
    const tableRows = document.querySelectorAll("#datatablesSimple tbody tr");

    tableRows.forEach(row => {
        row.addEventListener("mouseover", () => {
            row.classList.add("highlighted-row");
        });

        row.addEventListener("mouseout", () => {
            row.classList.remove("highlighted-row");
        });

        // 행 클릭 시 새 창 열기
        row.addEventListener("click", () => {
            var productId = row.getAttribute('data-product-id');
            if (productId) {
                window.location.href = '/seller/pm/' + productId; //URL을 로드
            }
        });
    });
});
