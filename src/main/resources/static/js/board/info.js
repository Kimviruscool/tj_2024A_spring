console.log('info.js');

info();
function info(){ console.log('info()');
    let urlParams = new URL( location.href ).searchParams.get('bno');

    $.ajax({
        method : "get",
        url : "/board/boardinfo",
        data : {bno , urlParams},
        success : (r)=>{console.log(r)
            //어디에
            let infoBox = document.querySelector('.infoBox');
            //무엇을
            let html = ` <div> ${r.bcname} </div>
                         <div> ${r.bno} </div>
                         <div> ${r.id} , ${r.bview} , ${r.bdate} </div>
                         <div> ${r.btitle} </div>
                         <div> ${r.bcontent} </div>
                         <div>
                         <button type="button" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>
                         <button type="button" onclick="doBoardDelete${urlParams}">삭제</button>
                         </div>`;
            //출력
            infoBox.innerHTML=html;

        }

    })
}