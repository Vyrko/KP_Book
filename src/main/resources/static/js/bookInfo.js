function updatePrice() {
    var cost = document.getElementById("cost").value;
    console.log(cost);
    var quantity = document.getElementById("quantity").value;
    console.log(quantity);
    var price = quantity * cost;
    document.getElementById("price").innerHTML = price;
}