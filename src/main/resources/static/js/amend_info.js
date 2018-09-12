$(document).ready(function(){
    $.ajax({
        type:'post',
        url:'/users/identity',
        success:function(result){
            var userName = result.username;
            var email = result.email;
            var sex = result.sex;
            var id = result.id;
            var url = 'http://localhost:8080/shoppingCar?userId='+id;
            $('#shoppingCar').attr('href',url);
            document.getElementById("userId").innerHTML = id;
            document.getElementById("userName").value = userName;
            document.getElementById("email").value = email;
            if(sex=='男'){
                document.getElementById("man").checked = true;
            }else {
                document.getElementById("woman").checked = true;
            }
        }
    })
})

$(function() {
    $("#save").click(function () {
        var userId = document.getElementById("userId").innerText; // header.html
        var userName = document.getElementById("userName").value; //
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
})