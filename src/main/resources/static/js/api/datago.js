/*
    -공공데이터 : https://www.data.go.kr
        -공공기관에서 제공하는 정보들(data)
        1. 로그인 (간편로그인)
        2. API검색 [필요로 하는 DATA]
        3. 활용신청


*/

//1. 부평구 주유소 현황
api1();
function api1(){
    $.ajax({
        method : "get",
        url : "https://api.odcloud.kr/api/15102672/v1/uddi:5e2a4b30-28fb-4e8f-bc44-9a6db8a6a8db?page=1&perPage=10&serviceKey=M%2FWxb8l%2F2I0NPE9OGXN6Pd9erV8k8pMuBR4%2F6ToNV2xGy8woDM2Z62LHjS3OGRuGAI2ED3Gy0j768Tb4AdFjNg%3D%3D",
        success : r=>{console.log(r)
            /*
            r : 다양한 속성을 가진 응답 객체
            - r.data : 응답 정보 내용물
            */
            let dataArray = r.data;
            console.log(dataArray);

            let html = ``;
            dataArray.forEach( data => {
            html += `<tr>
                       <th>${data.상호}</th>
                       <th>${data.전화번호}</th>
                       <th>${data.주소}</th>
                      </tr>`
            })
            document.querySelector('.api1Tbody').innerHTML = html;
            }
    })
}

//2. 인천시 동구 약국 현황
api2();
function api2(){
    $.ajax({
        async : false,
        method : "get",
        url : "https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=10&serviceKey=M%2FWxb8l%2F2I0NPE9OGXN6Pd9erV8k8pMuBR4%2F6ToNV2xGy8woDM2Z62LHjS3OGRuGAI2ED3Gy0j768Tb4AdFjNg%3D%3D",
        success : r => {console.log(r);
            //1. 어디에
            let api2Tbody = document.querySelector('.api2Tbody');
            //2. 무엇을
            let html = ``;
            //3. 출력
            r.data.forEach(data => {
                html += `            <tr>
                                     <td>${data.약국명}</td>
                                     <td>${data.전화번호}</td>
                                     <td>${data.소재지도로명주소}</td
                                     </tr>`;
            })
            api2Tbody.innerHTML = html;
        }
    })
}

//3. 카카오 지도 API
/*
1. 카카오개발자센터 : https://developers.kakao.com/
2. 로그인
3. 플랫폼 신청
*/
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴