console.log('leave.js')

function doLeave(){ console.log('doLeave()');
//1.
let pwConfirm = document.querySelector('.pwConfirm').value;
console.log(pwConfirm);
//2.
//================ AJAX ======================
$.ajax({
async : false,
method : "delete",
url : "/member/leave",
data : {pwConfirm : pwConfirm},
success : (result)=>{
if(result){
alert('삭제 되었습니다.');
location.href="/";
} else
{alert('삭제 실패');
}
}
})
//==============================================
}