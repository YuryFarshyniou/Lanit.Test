$(document).ready(
    function () {
        $("#personForm").submit(function (event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            let birthdate = $("#inputPersonBirthdate").val().split("-")
            let newDate = birthdate[2] + '.' + birthdate[1] + '.' + birthdate[0];

            let formData = {
                id: $("#inputId").val(),
                name: $("#inputPersonName").val(),
                birthdate: newDate
            }

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "person",
                data: JSON.stringify(formData),
                success: function (result) {
                    if (result === "OK") {
                        $("#postResultDiv").html(
                            "User successfully added <br>");
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