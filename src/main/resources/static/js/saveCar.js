$(document).ready(
    function () {
        $("#carForm").submit(function (event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            let formData = {
                id: $("#inputIdCar").val(),
                model: $("#inputCarModel").val(),
                horsepower: $("#inputHorsePower").val(),
                owner: $("#inputOwnerId").val()
            }

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "car",
                data: JSON.stringify(formData),
                success: function (result) {
                    if (result === "OK") {
                        $("#postResultDiv").html(
                            "Car was successfully added <br>");
                    } else {
                        $("#postResultDiv").html("<strong>Validation error</strong>");
                    }
                    console.log(result);
                },
                error: function (result) {
                    $("#postResultDiv").html(
                        result.status + " " + result.statusText + "<br>");
                    console.log("ERROR: ", result);
                }
            });

        }

    })