console.log( 'update.js' )

function doUpdate(){console.log('doUpdate()');
    //1.
    let nameupdate = document.querySelector('.nameupdate').value;
    let nameupdate = document.querySelector('.nowpw').value;
    let nameupdate = document.querySelector('.pwupdate').value;
    let nameupdate = document.querySelector('.numupdate').value;
//===================================AJAX ======
    $.ajax({
        async : false,
        method : "put",
        url : "/member/update",
        data : {nameupdate:nameupdate,nowpw:nowpw,pwupdate:pwupdate,numupdate:numupdate},
        success : (result)=>{
        if(result){alert('수정되었습니다.');
        location.href="/";
        }
        else {alert('수정 실패')}
    })
}