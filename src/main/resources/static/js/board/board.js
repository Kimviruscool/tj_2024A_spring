console.log('board.js');

//2. 검색 버튼을 클릭 했을 때
function onSearch(){

}

ball(1);

function ball(page){ console.log('ball()');
$.ajax({
    method : "get",
    url : "/board/all",
    data : {page : page},
    success : (r)=>{console.log(r);
    //어디에
    let boardBox = document.querySelector('.boardBox');
    //무엇을
    let html = '';

    r.forEach((c)=>{
    html += `<tr>  <th> ${c.bno} </th> <th> <a href="/board/info?bno=${c.bno}">${c.btitle}</a> </th> <th> ${c.id} </th> <th> ${c.bdate} </th> <th> ${c.bview} </th>  </tr>`;
    })
    //출력
    boardBox.innerHTML = html;
    },
    error : (e)=>{console.log(e);}
})

}