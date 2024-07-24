console.log( 'update.js' )

function doUpdate(){console.log('doUpdate()');
    //1.
    let nameupdate = document.querySelector('.nameupdate').value;
    let nowpw = document.querySelector('.nowpw').value;
    let pwupdate = document.querySelector('.pwupdate').value;
    let numupdate = document.querySelector('.numupdate').value;

    //2. 객채화
    let info = {nameupdate:nameupdate, nowpw:nowpw,pwupdate:pwupdate,numupdate,numupdate};
//===================================AJAX ======
    $.ajax({
        async : false,
        method : "put",
        url : "/member/update",
        data : JSON.stringify(info),
        contentType : "application/json",
        success : (result)=>{
        if(result){alert('수정되었습니다.');
        location.href="/member/mypage";
        }
        else {alert('수정 실패')}
        }
//        error : e=>{console.log(e);}
});
}