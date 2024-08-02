console.log('info.js');


//let urlParams = new URL( location.href ).searchParams.get('bno');
info();
replyAll();

function info(){ console.log('info()');

    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        async : false,
        method : "get",
        url : "/board/boardinfo",
        data : {bno : urlParams},
        success : r => {

         console.log(r);
                    //어디에
                    let infoBox = document.querySelector('.infoBox');
                    //무엇을
                    let html = ` <div> 카테고리 ${r.bcname} </div>
                                 <div> 글번호 ${r.bno} </div>
                                 <div> 작성자 ${r.id} , 조회수 ${r.bview} , 작성일 ${r.bdate} </div>
                                 <div> 제목 ${r.btitle} </div>
                                 <div> 내용 ${r.bcontent} </div> `;

                                 if(r.bfile == null){
                                 }
                                 else {
                                 html += `<div> 첨부파일 ${r.bfile.split("_")[1]} <a href="/file/download?filename=${r.bfile}"> 다운로드 </a> </div>`;
                                 }

                                 html += `<div>
                                 <button type="button" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>
                                 <button type="button" onclick="dCheck()">삭제</button>
                                 </div>

                                 <div>
                                 <textarea class="brcontent"></textarea>
                                 <button type="button" onclick="onReplyWrite()">댓글등록</button>
                                 </div>

                                 <div> 댓글 출력구역
                                 <div class="replyAll"></div>
                                 </div>`;
                    //출력
                    infoBox.innerHTML=html;



        }

    })
}

function onReplyWrite(){
    console.log('onReplyWrite()');
    let urlParams = new URL( location.href ).searchParams.get('bno');

    let brcontent = document.querySelector('.brcontent').value;
    //let bno = bno; //현재 보고있는 게시물 번호
//    let brindex = 0; // 0 : 댓글

    let info = {brindex : 0 , bno : urlParams, brcontent : brcontent}

    $.ajax({
        async : false,
        method : "post",
        url : "/board/reply/write",
        data : JSON.stringify(info),            //json 문자열 데이터로 변환
        contentType : "application/json", //왜 application/json 을 사용하는지?
            //contentType : "application/x-www-form-urlencoded" : ajax 기본
            //contentType : false , > contentType : multipart/form-data (첨부파일 바이너리)
            //contentType : "application/json" (POST/PUT 이면서 첨부파일이 없을 때 주로 json)
        success : r => {
            console.log(r);
            if(r == true){alert("댓글쓰기 성공");}
            else {alert("댓글쓰기 실패 : 로그인후 사용 가능합니다.");}
        } //success end
    }) //ajax end
} //function end

function replyAll(){
    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        async : false,
        method : "get",
        url : "/board/reply/getAll",
        data : {bno : urlParams},
        success : r => {
                console.log(r);

                //어디에
                let replyAll = document.querySelector('.replyAll');
                //무엇을
                let html = ``;

                r.forEach(e => {
                html += `<div> ${e.brcontent} </div>`
                });

                //출력
                replyAll.innerHTML = html;

        }
    })

}