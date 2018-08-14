$(function () {
    $(".btn-primary").click(function () {
        if ("用户已经存在" == $("#username").val()) {
            alert("Hello world!");

            if (window.event.keyCode == 13) {
                event.keyCode = 0;//屏蔽回车键
                event.returnValue = false;
                alert("Hello world!");
            }
        }
    })
    //获取鼠标失去光标的事件
    $("#username").blur(function () {
        //获取username的value
        var username = $("#username").val();
        if (username === "") {
            $("#errorName").html("用户名不允许为空");
        } else { //不为空就可以ajax提交到后台数据库
            $.ajax({
                url: "/login?methodName=isusername",
                data: {"username": username},
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data.status == 1) {
                        $('.primary').attr('disabled', "true");
                        $("#errorName").html(data.message);
                    } else {
                        $('.primary').removeAttr("disabled");
                        $("#errorName").html("<h1>可以使用</h1>");
                    }
                }
            });
        }
    });
});