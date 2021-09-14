$(document).ready(
    function () {
        $("#sendIdForm").submit(function (event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "personWithCars?personId=" + $("#inputPersonId").val(),
                dataType: 'json',
                success: function (result) {
                    console.log(result)
                    $("#postResultDiv").html(
                        JSON.stringify(result) + "<br>");
                },
                error: function (result) {
                    $("#postResultDiv").html(
                        result.status + " " + result.statusText + "<br>");
                    console.log("ERROR: ", result);
                }
            });

        }

    })