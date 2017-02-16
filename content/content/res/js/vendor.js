$("#general-get-btn").click(function () {
    $.getJSON("vendor-api/api/vendor?token=0942175430", function(data) {
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
