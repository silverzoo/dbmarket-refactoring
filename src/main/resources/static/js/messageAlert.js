document.addEventListener('DOMContentLoaded', function() {
    // Thymeleaf에서 삽입된 변수 값을 가져오기
    const successEl = document.getElementById('success');
    const errorEl = document.getElementById('error');

    const success = successEl ? successEl.dataset.value : null;
    const error = errorEl ? errorEl.dataset.value : null;


    if (success) {
        alert(success);
    } else if (error) {
        alert(error);
    }
})
