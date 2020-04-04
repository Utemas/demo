$(function () {
    $("#baseinformation").click(function(){
        hed("个人信息","此信息为身份证件上的信息");
        $("#informationTable").removeClass("hidden");
        $("#baseinformation").addClass("active");
        $("#studentinformation").removeClass("active");
        $("#xue_jiSearch").removeClass("active");
        $("#studentTable").addClass("hidden");
        $("#class_info").addClass("hidden");
        $("#xue_j").addClass("hidden");
    });
    $("#studentinformation").click(function(){
        hed("个人联系信息","此信息做为学校联系的重要参照");
        //显示切换部分
        $("#baseinformation").removeClass("active");
        $("#studentinformation").addClass("active");
        $("#xue_jiSearch").removeClass("active");
        $("#informationTable").addClass("hidden")
        $("#studentTable").removeClass("hidden");
        $("#class_info").addClass("hidden");
        $("#xue_j").addClass("hidden");
        $.ajax({
            url: "/selectContextInfo",
            type: "post",
            success:function(cinfo){
                //籍贯显示
                $("#jg").text(cinfo['customer_jiguan']);
                $("#customer_jiguan").val(cinfo['customer_jiguan']);

                //显示联系电话
                $("#customer_tel").val(cinfo['customer_tel']);
                $("#ct").text(cinfo['customer_tel']);

                //显示邮箱
                $("#customer_email").val(cinfo['customer_email']);
                $("#el").text(cinfo['customer_email']);

                //显示邮编
                $("#customer_youzheng").val(cinfo['customer_youzheng']);
                $("#yb").text(cinfo['customer_youzheng']);

                //显示起始站
                $("#customer_start_station").val(cinfo['customer_start_station']);
                //显示终点站
                $("#customer_end_station").val(cinfo['customer_end_station']);

                $("#customer_end_station").val(cinfo['customer_end_station']);
                $("#sst").text(cinfo['customer_start_station'] + "  至  "+cinfo['customer_end_station']);


                $("#studentTable").removeClass("hidden");
            }
        });
    });

    $("#classinformation").click(function(){
        hed("成绩信息","该信息为在校期间的各科成绩");
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
                $("#xue_j").addClass("hidden");
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

    //学籍信息表显示
    $("#xue_jiSearch").click(function(){

        $("#xue_j").removeClass("hidden");

        $("#xue_jiSearch").addClass("active");
        $("#studentinformation").removeClass("active");
        $("#baseinformation").removeClass("active");

        $("#class_info").addClass("hidden");
        $("#informationTable").addClass("hidden");
        $("#studentTable").addClass("hidden");
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
        //获取值部分
        //籍贯
        var jg = $("#customer_jiguan").val();
        
        //联系电话
        var ct = $("#customer_tel").val();
        //获取邮箱
        var el = $("#customer_email").val();
        //获取邮政编码
        var yb = $("#customer_youzheng").val();
        //获取起始站
        var sst = $("#customer_start_station").val();
        
        var end = $("#customer_end_station").val();
        $.ajax({
            url:"/updateContextInfo",
            type:"POST",
            data:{
                ji_guan : jg,
                customer_tel : ct,
                customer_email : el,
                customer_youzheng : yb,
                customer_start_station : sst,
                customer_end_station: end
            },
            dataType: "json",
            success:function(cinfo){
                $("#jg").text(cinfo['customer_jiguan']);
                $("#ct").text(cinfo['customer_tel']);
                $("#el").text(cinfo['customer_email']);
                $("#yb").text(cinfo['customer_youzheng']);
                $("#sst").text(cinfo['customer_start_station'] + "  至  "+cinfo['customer_end_station']);
                $('#ContactFormModal').modal("hide");
            }
        })
    });


    
})