$(document).ready(function () {
    document.getElementById("productName").value = localStorage.productName;
    document.getElementById("productCounts").value = localStorage.counts;
    document.getElementById("productId").innerText = localStorage.productId;
    var c = parseInt(document.getElementById("productCounts").value);
    var p = parseInt(localStorage.price);
    var price = c*p;
    document.getElementById("price").innerText = price;
})

$("#confirmBuy").click(function () {
    var recevier = document.getElementById("receiver").value;
    var address = document.getElementById("address").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var productId = document.getElementById("productId").innerText;
    var productName = document.getElementById("productName").value;
    var productCounts = document.getElementById("productCounts").value;
    $.ajax({
        async:true,
        type:'post',
        url:'/order/save',
        data:JSON.stringify({
            productId: productId,
            productName: productName,
            receiver: recevier,
            phoneNumber: phoneNumber,
            address:address,
            counts:productCounts,
        }),
        contentType: "application/json;charset=UTF-8",
        dataType:"json",
        success:function(result){
            alert(result.res);
            window.location.href="/order/shopping_record";
        }
    })
})

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