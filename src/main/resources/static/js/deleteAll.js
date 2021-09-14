$(document).ready(
    function () {
        $("#deleteAll").submit(function (event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "clear",
                success: function (result) {
                    console.log(result)
                    $("#postResultDiv").html(
                        result + "<br>");
                },
                error: function (result) {
                    $("#postResultDiv").html(
                        result.status + " " + result.statusText + "<br>");
                    console.log("Error" + result)
                }
            });

        }

    })