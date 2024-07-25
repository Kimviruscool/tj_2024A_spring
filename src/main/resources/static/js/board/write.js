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