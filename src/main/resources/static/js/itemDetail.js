// 수정 기능
// const modifyBtn = document.getElementById('modify-btn');
//
// if (modifyBtn) {
//     modifyBtn.addEventListener('click', event => {
//         let id = document.getElementById('article-id').value;
//
//         fetch(`/api/items/${id}`, {
//             method: 'PUT',
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify({
//                 name: document.getElementById('title').value,
//                 content: document.getElementById('content').value
//             })
//         })
//             .then(() => {
//                 alert('수정이 완료되었습니다.');
//                 location.replace(`/home`);
//             });
//     });
// }
//
// 삭제 기능
// const deleteBtn = document.getElementById('delete-btn');
//
// if (deleteBtn) {
//     deleteBtn.addEventListener('click', event => {
//         const idEl = document.getElementById('item-id');
//         const id = idEl? idEl.value : null;
//         fetch(`/api/items/${id}`, {
//             method: 'DELETE'
//         })
//         .then(() => {
//             alert('삭제가 완료되었습니다.');
//             location.replace(`/items`);
//         });
//     });
// }