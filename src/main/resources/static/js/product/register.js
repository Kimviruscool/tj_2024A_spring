console.log("register.js")

function onRegister(){
    console.log('onRegister()');
    // 대용량 첨부파일 보내기
    //1. 폼가져오기
    const productForm = document.querySelector('#productForm')
    //2. 폼데이터를 바이터로 변환 (2진법) , 첨부파일은 JSON.TEXT (문자,텍스트) 형식으로 보낼수 없다.
    const productFormData = new FormData(productForm);
    //3. ajax 를 이용한 데이터 전송
    $.ajax({ //ajax start
        async : false, //동기화 false
        method : "post", //타입지정
        url : "/product/register", //주소지정
        data : productFormData,
        contentType : false,
        processData : false, // --- ajax 에서 멀티파트타입으로 전송 방법
        success : r=> {console.log(r);},
        error : e => {console.log(e);}
    }) //ajax end
} //function end