console.log('info.js');

info();
function info(){ console.log('info()');

            console.log('r1');

    let urlParams = new URL( location.href ).searchParams.get('bno');
            console.log('r2');
            console.log(urlParams);


    $.ajax({
        method : "get",
        url : "/board/boardinfo",
        data : {bno : urlParams},
        success : r => {

         console.log(r);
                    console.log('r2');
                    //어디에
                    let infoBox = document.querySelector('.infoBox');
                    //무엇을
                    let html = ` <div> 카테고리 ${r.bcname} </div>
                                 <div> 글번호 ${r.bno} </div>
                                 <div> 작성자 ${r.id} , 조회수 ${r.bview} , 작성일 ${r.bdate} </div>
                                 <div> 제목 ${r.btitle} </div>
                                 <div> 내용 ${r.bcontent} </div>
                                 <div> 첨부파일 ${r.bfile == null ? "" : r.bfile} <a href="/file/download?filename=${r.bfile}"> 다운로드 </a> </div>
                                 <div>
                                 <button type="button" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>
                                 <button type="button" onclick="doBoardDelete${urlParams}">삭제</button>
                                 </div>`;
                    //출력
                    infoBox.innerHTML=html;



        }

    })
}