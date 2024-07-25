console.log('write.js');

bcFindAll();
//1. 카테고리 호출 , 실행조건 : JS가 열렸을때 사이트 활성화시 (상시 실행)
function bcFindAll(){ console.log('bcFindAll()');
    $.ajax({
        method : "get",
        url : "/board/category",
        success : (r)=>{ console.log(r);
                //1. 어디에
                let categoryBox = document.querySelector('.categoryBox');

                //2. 무엇을 (AJAX 를 이용한 서버로 부터 받은 데이터를 출력)
                let html = '';
                //서버로부터 응답받은 데이터 타입을 확인했더니 LIST 타입 이므로 반복문을 사용하자
                    // 언어별 화살표 함수 표현 방식 JAVA : -> , JS: =>
                r.forEach((c)=>{
                    html += `<option value="${c.bcno}">${c.bcname}</option>`
                })
                //3. 출력할것인지
                categoryBox.innerHTML = html;
        },
        error : (e)=>{
            console.log(e);
        }
    })

}

/*
//2. 게시물 쓰기 (첨부파일을 전송하지 않는 일반 JSON 타입의 형식)
function doBoardWrite(){
    //1. HTML 입력받은 값 가져오기
        //-select 목록 에서 선택한 option의 value 호출
    let bcno = document.querySelector('.categoryBox').value;
    let btitle = document.querySelector('.btitle').value;
    let bcontent = document.querySelector('.bcontent').value;
    //2. 객체화
    let info={
        bcno : bcno, btitle : btitle, bcontent : bcontent
    };
    console.log(info);
    //3. 배열저장 (AJAX 통신)
    $.ajax({
        method : "post",
        url : "/board/write",
        data : JSON.stringify(info),
        contentType : "application/json",
        success : (r)=>{console.log(r);
            if(r){alert('글쓰기 성공')
            location.href="/board/board";
            }else{
                alert('글쓰기실패');
            }
        },
        error : (e)=>{console.log(e);}
    })
    //4. 통신 결과에 따른 실행문
}
*/

//2. 게시물 쓰기 (첨부 파일을 전송하는 대용량 Form 타입의 통신)
function doBoardWrite(){
    //1. form 가져오기 , form 안에 있는 HTML 모두 한번에 가져오기
    let boardWriteForm = document.querySelector('.boardWriteForm');
    console.log(boardWriteForm);
    //2. form HTML을 바이트(Byte) 로 변환해주는 객체 = new ForData
    let boardWriteFormData = new FormData(boardWriteForm);
    console.log(boardWriteFormData);
    //3. ajax 통신
    $.ajax({
        method : "post",
        url : "/board/write",
        data : boardWriteFormData,
        contentType : false, processData : false,
        success : r =>{console.log(r); },
        error : e=>{console.log(e); }
    })
}