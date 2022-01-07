
let index = {
    init: function(){
        // on : 첫번째 이벤트, 발생할 일 기재
        $("#btn-save").on("click",()=>{ // function(){}을 사용하지않고 ()=> 람다식으로 사용한 이유는 this를 바인딩하기 위함이다.
            this.save();
        });
    },

    save: function(){
        // alert("user의 save 함수 호출");
        let data ={
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        }

        // console.log(data); 자바스크립트 오브젝트
        // console.log(JSON.stringify(data)); JSON 문자열

        // ajax 호출 시 default가 비동기 호출이다.
        // ajax 통신을 이용해서 3개의 데이터를 제이슨으로 변경하여 insert 요청
        // dataType:"json" == 없어도됨 == ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
        $.ajax({
            // 회원가입 수행 요청
            type:"POST",
            url:"/blog/api/user",
            data:JSON.stringify(data), // HTTP 바디 데이터 => MIME 타입 필요
            contentType:"application/json; charset=utf-8", // 바디데이터가 어떤 타입인지
            dataType:"json" // 요청에 대한 응답이 왔을 때 기본적으로 모든 것이 문자열인데 생긴게 json이라면 자바스크립트로 변경을 해준다.
        }).done(function (resp) { // 정상 시 수행
            alert("회원가입이 완료되었습니다.");
            // alert(resp)
            console.log(resp);
            location.href="/blog";
        }).fail(function(error) { // 실패 시 수행
            alert(JSON.stringify(error));
        });
    }

}
index.init();





