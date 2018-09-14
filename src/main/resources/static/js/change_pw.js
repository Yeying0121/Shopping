$("#save").click(function () {
    var prePassword = document.getElementById("prePassword").value;
    var newPassword = document.getElementById("newPassword").value;
    var passwordConfirm = document.getElementById("passwordConfirm").value;
    var data_change = {
        'prePassword':prePassword,
        'newPassword':newPassword,
        'passwordConfirm':passwordConfirm
    };
    $.ajax({
        async:true,
        type:'post',
        url:'/users/savePassword',
        data:data_change,
        contentType:"application/x-www-form-urlencoded",
        dataType:"json",
        success:function (result) {
            if(result.flag==1) {
                alert(result.res);
                window.location.href='/users/login'
            }else {
                alert(result.res);
            }
        }
    })
})