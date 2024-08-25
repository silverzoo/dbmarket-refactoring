const deleteCommentBtn = document.getElementById('delete-comment-btn');

if (deleteCommentBtn) {
    deleteCommentBtn.addEventListener('click', event => {
        let commentId = document.getElementById('comment-id').value;
        let userId = document.getElementById('user-id').value;


        fetch(`/api/comments/detail/${commentId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace(`/comments/${userId}`);
            });
    });
}

