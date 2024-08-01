function doBoardUpdate(){
let newTitle = document.querySelector("#newTitle").value;
let newContent = document.querySelector("#newContent").value;
let category = document.querySelector("#category").value;
let urlParams = new URL (location.href).searchParams.get('bno');

let newinfo = {btitle : newTitle, bcontent:newContent , bcno : category, bno : urlParams}

$.ajax({
    method : "put",
    url : "/board/update",
    data : JSON.stringify(newinfo),
    contentType : "application/json",
    success : (r) => {
        console.log(r);
        if(r){alert("수정 완료"); location.href="/board/board"}
        else {alert("수정 실패")}
        }
    });
}

function bCheck(){
    $.ajax({
        async : false,
        method : "get",
        url : "/member/login/check",
        success : r => {
        if(r == ''){
            alert('로그인하고 오세요');
            location.href = "/member/login"}
        else {doBoardUpdate()}
       }
    })
}
