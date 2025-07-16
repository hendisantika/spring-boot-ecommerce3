// Function to add toast message
function showToast(content) {
    $("#toast").addClass("display");
    $("#toast").html(content);
    setTimeout(() => {
        $("#toast").removeClass("display");
    }, 2000);
}

// Function to update cart count
function updateCart() {
    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);
    if (cart == null || cart.length == 0) {
        $(".cart-items").html("(0)");
        $(".cart-body").html("<h3>Cart is empty</h3>");
        $(".checkout-btn").attr('disabled', true);
    } else {
        $(".cart-items").html(`(${cart.length})`);
        let table = `
            <table class='table'>
                <thead class='thead-light'>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
        `;

        let totalPrice = 0;
        cart.map((item) => {
            table += `
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.productPrice}</td>
                    <td>${item.productQuantity}</td>
                    <td>${item.productQuantity * item.productPrice}</td>
                    <td><button onclick='deleteItemFromCart(${item.productId})' class='btn btn-danger btn-sm'>Remove</button></td>
                </tr>
            `;
            totalPrice += item.productPrice * item.productQuantity;
        });

        table = table + `
            <tr>
                <td colspan='5' class='text-right font-weight-bold'>Total Price: ${totalPrice}</td>
            </tr>
        </table>`;
        $(".cart-body").html(table);
        $(".checkout-btn").attr('disabled', false);
    }
}

// Function to delete item from cart
function deleteItemFromCart(pid) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    let newcart = cart.filter((item) => item.productId != pid);
    localStorage.setItem('cart', JSON.stringify(newcart));
    updateCart();
    showToast("Item removed from cart");
}

// Function to add item to cart
function addToCart(pid, pname, price) {
    let cart = localStorage.getItem("cart");
    if (cart == null) {
        // No cart yet
        let products = [];
        let product = {
            productId: pid,
            productName: pname,
            productQuantity: 1,
            productPrice: price
        };
        products.push(product);
        localStorage.setItem("cart", JSON.stringify(products));
        showToast("Item added to cart");
    } else {
        // Cart already exists
        let pcart = JSON.parse(cart);
        let oldProduct = pcart.find((item) => item.productId == pid);

        if (oldProduct) {
            // Product already exists in cart, increase quantity
            oldProduct.productQuantity = oldProduct.productQuantity + 1;
            pcart.map((item) => {
                if (item.productId == oldProduct.productId) {
                    item.productQuantity = oldProduct.productQuantity;
                }
            });
            localStorage.setItem("cart", JSON.stringify(pcart));
            showToast("Item quantity increased");
        } else {
            // Add new product to cart
            let product = {
                productId: pid,
                productName: pname,
                productQuantity: 1,
                productPrice: price
            };
            pcart.push(product);
            localStorage.setItem("cart", JSON.stringify(pcart));
            showToast("Item added to cart");
        }
    }
    updateCart();
}

// Function to go to checkout page
function goToCheckout() {
    window.location = "checkout";
}

// Document ready function
$(document).ready(function () {
    updateCart();

    // Add toast element to body if it doesn't exist
    if ($("#toast").length === 0) {
        $("body").append("<div id='toast'></div>");
    }
});