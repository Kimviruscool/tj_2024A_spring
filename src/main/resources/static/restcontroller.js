console.log('restcontroller.js');

//rest3get()
function rest3get(){
    console.log('rest3get');
    //1. ajax 옵션객체 정의
    let option = {
        //http://localhost:8080/example/test6 [ip 와 port는 생략]
        url : "/example/rest3?key=qwe", //통신할 URL 스프링 컨트롤러 매핑 주소
        method : 'get', //통신할 HTTP 메소드 선택
        success : function(response){console.log(response);} //통신 성공시 응답 받은 데이터
    }

    //ajax 메소드에 옵션 넣어서 실행
    $.ajax(option);
}
//rese3post()
function rest3post(){
    console.log('rest3post');
    $.ajax({
        url : "/example/rest3?key=qwe", //HTTP 통신할 경로, URL, 콘트롤러 매핑
        method : 'post',            //HTTP 메소드
        success : function(response){console.log(response)} //HTTP 성공응답, 주로 컨트롤러가 리턴한 RETURN 값

    }) //ajaxend
} //method end

//rest3put()
function rest3put(){

    let value = document.querySelector('#value').value;
    
    console.log('rest3put');
    $.ajax({
        url : "/example/rest3", //data {쿼리스트링 형식}
        method : 'put',
        data : {'key' : value}, //data : {key : value, key : value}
        success : function(response){console.log(response)}
    })
}
//rest3delete()
function rest3delete(){
    
    let value = document.querySelector('#value').value;

    console.log('rest3delete');
    $.ajax({
        method : 'delete',
        url : "/example/rest3",
        data : {'key' : value},
        success : function(response){console.log(response)}
    })
}