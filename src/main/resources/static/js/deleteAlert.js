document.addEventListener('DOMContentLoaded', function() {
    // Thymeleaf에서 삽입된 변수 값을 가져오기
    const message = document.getElementById('deleteMsg').dataset.value;
    const error = document.getElementById('deleteError').dataset.value;

    if (message) {
        alert(message);
    } else if (error) {
        alert(error);
    }
})
