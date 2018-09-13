$("#buy").click(function () {
    if($('#check').is(':checked')) {
        var productName = document.getElementById("productName").innerText;
        var counts = document.getElementById("productCounts").innerText;
        var price = document.getElementById("price").innerText;
        var productId = document.getElementById("productId").innerText;
        localStorage.productName = productName;
        localStorage.counts = counts;
        localStorage.price = price;
        localStorage.productId = productId;
        window.location.href="http://localhost:8080/order/new";
    }
})