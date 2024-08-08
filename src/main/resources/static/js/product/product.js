Productlist();

function Productlist(){ //function start
    //어디에
    let productListBox = document.querySelector('.productListBox')

    //무엇을
    html = ``;

    $.ajax({ //ajax start
        async : false,
        method : 'get',
        url : '/product/productList',
        success : r => { //success start
        console.log(r)
        //여러개 제품 반복문 start
        r.forEach(product => {

        html += `<div class="productBox">`; //제품 1개당 div하나씩 div start

        //여러개 이미지 파일 , 제푸마다 여러개 이미지들
        product.fileNames.forEach(imgName => {
        //업로드된 이미지 파일명을 서버로 요청을 해서 응답 받아 img 마크업 src속성에 대입 /업로드된폴더 다운
        html += `<img style="width:100px" src="/upload/${imgName}" />`
        } );

        html += `</div>`; //제품 1개당 div end

        }) //여러개 제품 반복문 end

        }, //success end
        error : e => {console.log(e)}
    }) //ajax end
    //출력
    productListBox.innerHTML = html;
} //function end