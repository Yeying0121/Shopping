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

