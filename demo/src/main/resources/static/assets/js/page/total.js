$(function () {
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
        $("#studentTable").addClass("hidden");
        $("#classinformation").addClass("active");
    });

    $("#xuejiInput").click(function(){
        $("#xuejiInput").addClass("active");
    });

    $("#changeTheContact").click(function(){
        $("#ContactFormModal").modal("show");
    });

    $("#selectYear").change(function(){
        var year = $(this).children('option:selected').val();
        $.ajax({
            url: "/changeYear",
            type: "post",
            data: {
                year: year
            },
            dataType: "json",//数据类型可以为 text xml json  script  jsonp
　　　　　　 success: function(clist){//返回的参数就是 action里面所有的有get和set方法的参数
                if($.isEmptyObject(clist) ){
                    $("#classes").html("");
                    return false;
                }
                print(clist);
                
            }
        });

    });

    
})