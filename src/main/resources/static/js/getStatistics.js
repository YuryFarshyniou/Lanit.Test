$(document).ready(
    function () {
        $("#callStatistics").submit(function (event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "statistics",
                dataType: 'json',
                success: function (result) {
                    console.log(result)
                    $("#postResultDiv").html(
                        JSON.stringify(result) + "<br>");
                },
                error: function (result) {
                    $("#postResultDiv").html(
                        result + "<br>");
                    console.log("ERROR: ", result);
                }
            });

        }

    })