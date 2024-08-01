////////////////////////////////////////////////////////////////////////////////////
function boardDelete(){
    let urlParams = new URL(location.href).searchParams.get('bno');

    $.ajax({
        async : false,
        method : "delete",
        url : "/board/delete",
        data : {bno : urlParams},
        success : (r) => {
        console.log(r);
        if(r){alert("삭제 완료"); location.href="/board/board"}
        else {alert("삭제 실패")}
        }
    });
}

function dCheck(){
    $.ajax({
        async : false,
        method : "get",
        url : "/member/login/check",
        success : r => {
        if(r ==''){
            alert('로그인하고 오세요');
            location.href = "/member/login"}
        else {boardDelete()}
        }
    })
}