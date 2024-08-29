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
        if (!document.getElementById('name').value) { 
            alert('공백을 입력하지 말아주세요.');
            return false;
        }
        if (isNaN(document.getElementById('price').value)) {
                    alert('숫자만 입력해주세요');
                    return false;
        }
        if (!(10 <= document.getElementById('price').value <= 2147483646)) { 
                    return false;
        }
        if (!document.getElementById('description').value) {
                    alert('공백을 입력하지 말아주세요.');
                    return false;
        }
        if (!document.getElementById('item').value) {
                    alert('공백을 입력하지 말아주세요.');
                    return false;
        }
        // === => 변수 유형 고려
        //TODO 자료값 확인;;; 여기에서 체크가 안되는데?
        if (!document.getElementById('image-box').src==="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg") {
                alert('파일을 업로드 해주세요.');
                return false;
        }
        // if(!document.getElementById('image-box').src==="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg"){
        //     //
        //     const fileInput = document.querySelector('#itemImage');
        //     // Create a new File object
        //     myFile = convertURLtoFile(fileInput.src);
        //     // Now let's create a DataTransfer to get a FileList
        //     const dataTransfer = new DataTransfer();
        //     dataTransfer.items.add(myFile);
        //     fileInput.files = dataTransfer.files;
        //     alert("이미지 전송!");
        // }
    };

    // export const convertURLtoFile = async (url: string) => {
    //     const response = await fetch(url);
    //     const data = await response.blob();
    //     const ext = url.split(".").pop(); // url 구조에 맞게 수정할 것
    //     const filename = url.split("/").pop(); // url 구조에 맞게 수정할 것
    //     const metadata = { type: `image/${ext}` };
    //     return new File([data], filename!, metadata);
    // };

