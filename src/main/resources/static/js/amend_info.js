
// $(function() {
    $("#save").click(function () {
        var userId = document.getElementById("userId").innerText;
        var userName = document.getElementById("userName").value;
        var email = document.getElementById("email").value;
        var sex = $("input[type='radio']:checked").val();
        $.ajax({
            async:true,
            type:'post',
            url: '/users/save',
            data:JSON.stringify({
                id: userId,
                username: userName,
                email: email,
                sex: sex,
            }),
            contentType:"application/json; charset=UTF-8",
            dataType: "json",
            success: function (result) {
                if (result.flag == 1) {
                    alert(result.res);
                    window.location.href = 'http://localhost:8080/shopping/main';
                } else {
                    alert("修改资料失败！");
                }
            },
            error: function () {
                alert('服务端异常');
            }

        })
    })
// })