$("#register").click(function () {
    var userName = document.getElementById("inputUserName").value;
    var email = document.getElementById("inputEmail").value;
    var password = document.getElementById("inputPassword").value;
    var passwordConfirm = document.getElementById("passwordConfirm").value;
    var sex = $("input[name='sex'][checked]").value;
    if(password==passwordConfirm){
        $.ajax({
            async:true,
            type:'post',
            url:'/users/doRegister',
            data:JSON.stringify({
                username:userName,
                password:password,
                email:email,
                sex:sex,
            }),
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (result) {
                if(result.flag==1) {
                    window.location.href='/users/index';
                }else {
                    alert(result.res);
                }
            }
        })
    }else {
        alert("您两次输入的密码不一致，请重新输入！");
    }

})