$("#handle").click(function () {
    var orderStatus = $("#orderStatus option:selected").val();
    var id = document.getElementById("id").innerText;
    $.ajax({
        async:true,
        type:'post',
        url:'/order/handle',
        data:JSON.stringify({
            id:id,
            orderStatus:orderStatus,
        }),
        contentType: "application/json;charset=UTF-8",
        dataType:"json",
        success:function (result) {
            alert(result.res);
        }
    })
})