$(function () {
    var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
    $("#baseinformation").click(function(){
        $("#informationTable").removeClass("hidden");
        $("#baseinformation").addClass("active");
        $("#studentinformation").removeClass("active");
        $("#studentTable").addClass("hidden");
        $("#class_info").addClass("hidden");
        $("#ba").removeClass("hidden");
        $("#xj").addClass("hidden");
    });
    $("#studentinformation").click(function(){
        $("#baseinformation").removeClass("active");
        $("#studentinformation").addClass("active");
        $("#informationTable").addClass("hidden")
        $("#studentTable").removeClass("hidden");
        $("#class_info").addClass("hidden");
        $("#ba").addClass("hidden");
        $("#xj").removeClass("hidden");
    });

    $("#classinformation").click(function(){
        $("#class_info").removeClass("hidden");
        $("#informationTable").addClass("hidden");
    });

    
})