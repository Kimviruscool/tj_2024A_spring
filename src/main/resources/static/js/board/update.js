function doBoardUpdate(){
let newTitle = document.querySelector(".newTitle").value;
let newContent = document.querySelector(".newContent").value;
let category = document.querySelector(".category").value;
let urlParams = new URL (location.href).searchParams.get('bno');

$.ajax({
    method : "put",
    url : "/board/update",
    data : JSON.stringify({btitle : newTitle, bcontent:newContent , bcno : category, bno : urlParams}),
    contentType : "application/json",
    success : r => {
        console.log(r);
        if(r){alert("수정 완료"); location.href="/board/board"}
        else {alert("수정 실패")}
        }
    })
}
