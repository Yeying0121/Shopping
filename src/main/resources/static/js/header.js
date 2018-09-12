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