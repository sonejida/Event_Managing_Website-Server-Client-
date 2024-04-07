document.addEventListener("DOMContentLoaded", function () {
        calculateTotal("prices");
    });

    function calculateTotal(targetElementId) {
        var tickets = document.querySelectorAll("table tr:has(td)");

        var subtotal = 0;

        tickets.forEach(function (ticket) {
            var price = parseFloat(ticket.cells[2].textContent);
            subtotal += price;
        });

        var taxRate = 0.13;
        var taxes = subtotal * taxRate;
        var total = subtotal + taxes;

        var totalElement = document.getElementById(targetElementId);
        if (totalElement) {
            totalElement.innerHTML = `
                <h3>Total Price Summary</h3>
                <p>Total: $${subtotal.toFixed(2)}</p>
                <p>Taxes (13%): $${taxes.toFixed(2)}</p>
                <p>Subtotal: $${total.toFixed(2)}</p>
            `;
        }
    }

