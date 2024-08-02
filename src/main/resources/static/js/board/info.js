console.log('info.js');

info();
function info(){ console.log('info()');

    let urlParams = new URL( location.href ).searchParams.get('bno');
            console.log(urlParams);

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
                                 <button type="button">댓글등록</button>
                                 </div>`;
                    //출력
                    infoBox.innerHTML=html;



        }

    })
}

function onReplyWrite(){
    console.log('onReplyWrite()');
}