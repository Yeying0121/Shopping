$(document).ready(function(){
    // var url3 = 'http://localhost:8080/logout';
    // $('#user-logout').attr('href',url3);
    $.ajax({
        type:'post',
        url:'/users/identity',
        success:function(result){
            var userName = result.username;
            var email = result.email;
            var sex = result.sex;
            var id = result.id;
            var url = 'http://localhost:8080/shoppingCar?userId='+id;
            var url2 = 'http://localhost:8080/order/shopping_record?userId='+id;
            $('#shoppingCar').attr('href',url);
            document.getElementById("userId").innerText = id;
            $('#shopping_record').attr('href',url2);
            // document.getElementById("userName").value = userName;
            // document.getElementById("email").value = email;
            // if(sex=='男'){
            //     document.getElementById("man").checked = true;
            // }else {
            //     document.getElementById("woman").checked = true;
            // }
        }
    })

    $('[data-toggle="popover"]').popover({
        html: true,
        title: "个人信息",
        content: function () {
            return $('#user-avatar-content').html()
        }
    })

        // var li = $("#nav").children("li");
        //
        // for(var i=0;i<li.length;i++){
        //     var url = window.location.pathname;
        //     var url = url.replace("/","");
        //     if(url.indexOf(li[i].id)!=-1){
        //         li[i].firstChild.className = "active";
        //     }
        //     else {
        //         li[i].firstChild.className = "";
        //     }
        // }
    var liobj = $("#nav li");
    liobj.each(function () {
        $(this).click(function () {
            liobj.removeClass("active")
            $(this).addClass("active");
        })
    })


    // $("#nav li").click(function () {
    //     $("#nav li").removeClass("active");
    //         $(this).addClass("active");
    //
    // })
})
