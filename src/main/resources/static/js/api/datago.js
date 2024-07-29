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
        success : r=>{console.log(r)}
    })
}