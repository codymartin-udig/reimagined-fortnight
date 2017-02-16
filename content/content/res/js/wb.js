$("#general-get-btn").click(function () {
    $.getJSON("app-api/api/wb", function(data) {
        var rows = "";
        data.map(function (d)  {
            rows += "<tr>" +
            "<td>" + d.id + "</td>" +
            "<td>" + d.manufacturer + "</td>" +
            "<td>" + d.size + "</td>" +
            "<td>" + d.price + "</td>" +
            "<td>" + d.quantity + "</td>" +
            "<td>" + d.sold + "</td>"
            + "</tr>";
            console.log(d);
        });
        $("#general-get-table").after(rows);
    });
});

$("#general-post-btn").click(function () {
    var data = {manufacturer : $("#manufacturer").val(), size : $("#size").val(), price : $("#price").val(), quantity : $("#quantity").val()};

    $.ajax({
        url: "app-api/api/wb",
        method: "POST",
        data: JSON.stringify(data),
        contentType: "application/json"
    });
})
