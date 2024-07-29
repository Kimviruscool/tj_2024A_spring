console.log( 'header.js' ) //js 연결확인용 console.log

// 1. 로그인상태 요청
doLoginCheck(); //로그인 상태 상시 활성화

function doLoginCheck(){ //function start
    $.ajax({ //ajax start
        async : false, //동기화
        method:'get' ,
        url:"/member/login/check" ,
        success : (result)=>{ console.log( result );
            let html = '';
            if( result != '' ){ //if start
            //로그인 일때
            html += `<li class=""nav-item> ${result.id} 님</li>
                     <li class="nav-item"> <a class="nav-link" href="#" onclick="doLogout() "> 로그아웃 </a></li>
                     <li class="nav-item"> <a class="nav-link" href="/member/mypage"> 내정보 </a> </li>
                     `
            } //if end
            else{ //else start
            //비로그인 일때
            html += `<li class="nav-item"> <a class="nav-link" href="/member/signup"> 회원가입</a> </li>
                     <li class="nav-item"> <a class="nav-link" href="/member/login"> 로그인 </a></li>
                    `

            } //else end
            document.querySelector('#loginMenu').innerHTML = html;
        } //success end
    }) //ajax end
} //function end

// 2. 로그아웃
function doLogout(){ //function start
    $.ajax({ //ajax start
        method : 'get' , url :"/member/logout" ,
        success : ( result ) => {  console.log( result);
            location.href="/";
        } //success ehd
    }) //ajax end
} //function end



