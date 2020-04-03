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
        $.ajax({
            url: "/selectContextInfo",
            type: "post",
            success:function(cinfo){
                $("#jg").text(cinfo['customer_jiguan']);
                $("#customer_jiguan").val(cinfo['customer_jiguan'])
                $("#studentTable").removeClass("hidden");
            }
        });
    });

    $("#classinformation").click(function(){
        var chaxunYear = $("#selectYear option:selected").val();
        $("#classes").html("");
        $.ajax({
            url: "/changeYear",
            type: "post",
            dataType: "json",
            data:{
                year: chaxunYear
            },
　　　　　　 success: function(clist){
                $("#class_info").removeClass("hidden");
                $("#informationTable").addClass("hidden");
                $("#studentTable").addClass("hidden");
                $("#classinformation").addClass("active");
                if($.isEmptyObject(clist) ){
                    $("#classes").html("");
                    return false;
                }
                print(clist);
            }
        });
        
    });

    $("#xuejiInput").click(function(){
        $("#xuejiInput").addClass("active");
    });

    $("#changeTheContact").click(function(){
        $("#ContactFormModal").modal("show");
    });

    $("#selectYear").change(function(){
        $("#classes").html("");
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

    //更新个人联系名片
    $("#updateContext").click(function(){
        var jg = $("#customer_jiguan").val();
        $.ajax({
            url:"/updateContextInfo",
            type:"POST",
            data:{
                ji_guan : jg
            },
            dataType: "json",
            success:function(cinfo){
                $("#jg").text(cinfo['customer_jiguan']);
                $('#myModal').modal("hide");
            }
        })
    });


    
})