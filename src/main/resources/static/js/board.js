let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function(){}을 사용하지않고 ()=> 람다식으로 사용한 이유는 this를 바인딩하기 위함이다.
            this.save();
        });
        $("#btn-delete").on("click", () => { // function(){}을 사용하지않고 ()=> 람다식으로 사용한 이유는 this를 바인딩하기 위함이다.
            this.deleteById();
        });
        $("#btn-update").on("click", () => { // function(){}을 사용하지않고 ()=> 람다식으로 사용한 이유는 this를 바인딩하기 위함이다.
            this.update();
        });
        $("#btn-reply-save").on("click", () => { // function(){}을 사용하지않고 ()=> 람다식으로 사용한 이유는 this를 바인딩하기 위함이다.
            this.replySave();
        });

    },

    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글 작성이 완료되었습니다.");
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("삭제가 완료되었습니다.");
            console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update: function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    replySave: function () {
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

}
index.init();





