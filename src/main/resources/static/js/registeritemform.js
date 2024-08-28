// layout 적용시 이 스크립트 부분이 날라감 << 체크!
const fileDOM = document.querySelector('#itemImage');
const preview = document.querySelector('#image-box');

fileDOM.addEventListener('change', () => {
    const reader = new FileReader();
    reader.onload = ({ target }) => {
    //           const imgElement = document.createElement("img");
    //           imgElement.src = target.result;
    //           preview.appendChild(imgElement);
    preview.src = target.result;
    alert("이미지 업로드");
    };
    reader.readAsDataURL(fileDOM.files[0]);
});

function formValidation(){
//        if (!document.getElementById('itemImage')){} 안됨
        if (!document.getElementById('name').value) { // id 라는 id 를 선택하고 해당 input이 공백일 경우
            alert('공백을 입력하지 말아주세요.');
            return false;
        }
        if (isNaN(document.getElementById('price').value)) { // id 라는 id 를 선택하고 해당 input이 공백일 경우
                    alert('숫자만 입력해주세요');
                    return false;
        }
        if (!(10 <= document.getElementById('price').value <= 2147483646)) { // id 라는 id 를 선택하고 해당 input이 공백일 경우
                    alert('가격 범위는 10~2147483646 입니다');
                    return false;
        }
        if (!document.getElementById('description').value) { // id 라는 id 를 선택하고 해당 input이 공백일 경우
                    alert('공백을 입력하지 말아주세요.');
                    return false;
        }
        if (!document.getElementById('item').value) { // id 라는 id 를 선택하고 해당 input이 공백일 경우
                    alert('공백을 입력하지 말아주세요.');
                    return false;
        }
        // === => 변수 유형 고려
        //TODO 자료값 확인;;; 여기에서 체크가 안되는데?
        if (document.getElementById('itemImage').files.length == 0) { // id 라는 id 를 선택하고 해당 input이 공백일 경우
                alert('파일을 업로드 해주세요.');
                return false;
        }
    };

