$("#subCounts").click(function(){
    var productCounts = document.getElementById("productCounts");
    var counts = parseInt(productCounts.value);
    if(counts>=2)
        counts--;
    document.getElementById("productCounts").value = counts;
})

$("#addCounts").click(function(){
    var productCounts = document.getElementById("productCounts");
        var counts = parseInt(productCounts.value);
            counts++;
    document.getElementById("productCounts").value = counts;
})

$("#addShoppingCar").click(function(){
    var productId = document.getElementById("productId").innerText;
    var counts = document.getElementById("productCounts").value;
    console.log({name: 'shopping-car', productId: productId, counts: counts})
    $.ajax({
        async:true,
        type:'post',
        url:'/shoppingCar/save',
        data:JSON.stringify({
            productId:productId,
            counts:counts,
        }),
        contentType: "application/json;charset=UTF-8",
        dataType:"json",
        success:function(result){
            if(result.flag==1){
                console.log({name: 'shopping-car', counts: counts})
                // alert(result.res);
            }
        }
    })
})
$("#buy").click(function () {
    var productName = document.getElementById("productName").innerText;
    var counts = document.getElementById("productCounts").value;
    var price = document.getElementById("price").innerText;
    var productId = document.getElementById("productId").innerText;
    localStorage.productName = productName;
    localStorage.counts = counts;
    localStorage.price = price;
    localStorage.productId = productId;
    window.location.href="http://localhost:8080/order/new";
})