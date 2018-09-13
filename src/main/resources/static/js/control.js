$("#addCat").click(function () {
    var catName = document.getElementById("productCat").value;
    $.ajax({
        async:true,
        type:'post',
        url:'/category/save',
        data:JSON.stringify({
            categoryName:catName,
        }),
        contentType:"application/json;charset=UTF-8",
        dataType:"json",
        success:function (result) {
            alert(result.res);
        }
    })
})

$("#addProduct").click(function () {
    var productName = document.getElementById("productName").value;
    var productDescribe = document.getElementById("productDescribe").value;
    var productPrice = document.getElementById("productPrice").value;
    var productCount = document.getElementById("productCount").value;
    var imageUrl = document.getElementById("productImg").value;
    var category = $("#category option:selected").value;
    alert(category);
    $.ajax({
        async:true,
        type:'post',
        url:'/shopping/save',
        data:JSON.stringify({
            productName:productName,
            price:productPrice,
            productStock:productCount,
            desc:productDescribe,
            imageUrl:imageUrl,
            categoryId:{id:category},
        }),
        contentType:"application/json;charset=UTF-8",
        dataType:"json",
        success:function (result) {
            alert(result.res);
            window.location.href="http://localhost:8080/control";
        }
    })
})