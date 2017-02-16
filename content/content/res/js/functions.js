function checkWBApi () {
    $(function () {
        $.get("app-api/api/wb/status")
            .done(function(data) {
                if(data == true)
                   if($("#internal-api-li").hasClass("dead")) {
                        $("#internal-api-li").removeClass("dead");
                        $("#internal-api-li").addClass("live");
                    } else if (! $("#internal-api-li").hasClass("live")) {
                        $("#internal-api-li").addClass("live");
                    }
            })
            .fail(function (err) {
                if($("#internal-api-li").hasClass("live")) {
                    $("#internal-api-li").removeClass("live");
                    $("#internal-api-li").addClass("dead");
                }
                else if (! $("#internal-api-li").hasClass("dead")) {
                     $("#internal-api-li").addClass("dead");
                }
            })
    })
}

function checkVendorApi () {
    $(function () {
        $.get("vendor-api/api/vendor/status")
            .done(function(data) {
                if(data == true)
                   if($("#external-api-li").hasClass("dead")) {
                        $("#external-api-li").removeClass("dead");
                        $("#external-api-li").addClass("live");
                    } else if (! $("#external-api-li").hasClass("live")) {
                        $("#external-api-li").addClass("live");
                    }
            })
            .fail(function (err) {
                if($("#external-api-li").hasClass("live")) {
                    $("#external-api-li").removeClass("live");
                    $("#external-api-li").addClass("dead");
                }
                else if (! $("#external-api-li").hasClass("dead")) {
                     $("#external-api-li").addClass("dead");
                }
            })
    })
}

setInterval(checkWBApi, 3000);
setInterval(checkVendorApi, 3000);

checkWBApi();
checkVendorApi();
