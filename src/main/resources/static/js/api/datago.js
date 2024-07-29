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
            //4. 카카오지도 출력 함수에 데이터 매개변수 전달
            api3(r.data);
        } //success end
    }) //ajax end
}

//3. 카카오 지도 API
/*
1. 카카오개발자센터 : https://developers.kakao.com/
2. 로그인
3. 플랫폼 신청
    1. 상단메뉴 > 내 애플리케이션 > 애플리케이션추가
4. 애플리케이션 선택/클릭
    사이드바 메뉴
    [앱키] : 카카오API 사용할때 사용되는 인증키
    javascript 키 확인, 카카오지도 활용
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d95353c1b8109114d56af55ffb98178">
    [플랫폼] : 카카오API 사용할 web url 등록
    Web 플랫폼 등록
        http://192.168.30.11:8080

*/

function api3(data){ //api3 start //매개변수 : api2에서 전달받은 약국정보리스트
//1. 지도를 담을 영역의 DOM 레퍼런스
var mapContainer = document.querySelector('#map'); //지도를 담을 영역의 DOM 레퍼런스
//2. 지도를 생성할 때 필요한 기본 옵션
var options = { //지도를 생성할 때 필요한 기본 옵션
    //지도의 중심좌표 위도 , 경도
	center: new kakao.maps.LatLng(37.4562557, 126.7052062), //지도의 중심좌표.
	 //지도의 레벨(확대, 축소 정도) 0(최대확대)~14(최대축소)
	 level: 7
};
//3. 지도 생성 및 객체 리턴
var map = new kakao.maps.Map(mapContainer, options); //지도 생성 및 객체 리턴

//4. 마커를 표시할 위치와 title 객체 배열입니다.
//data  는 api2 함수에서는 공공데이터 포털의 인천 동구 약국 정보를 매개변수로 전달 받은 객체
var positions = data.map (d=>{
    //1. 객체 생성
    let location = {title : d.약국명, latlng : new kakao.maps.LatLng(d.위도,d.경도)}
    //2. 객체 리턴
    return location; //반복하면서
})
console.log(positions);

//5. 마커 이미지의 이미지 주소입니다.
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

//6. 반복문을 이용한 여러개 마커 생성
for(var i = 0; i<positions.length ; i++){

//마커 이미지의 이미지 크기 입니다.
var imageSize = new kakao.maps.Size(24, 35);

//마커 이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

//마커를 생성합니다.
var marker = new kakao.maps.Marker({
    map:map,
    position : positions[i].latlng,
    title : positions[i].title,
    image : markerImage
});
}
} //api3 end