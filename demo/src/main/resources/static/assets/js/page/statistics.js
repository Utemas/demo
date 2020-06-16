$(function(){
    $("#st_sex").click(function(){
        $("#xueYuanT").removeClass("active");
        $("#addr_province").removeClass("active");
        $("#st_sex").addClass("active");
        $.ajax({
            type:'post',
            url:'/sexP',
            success: function(slist){
                $("tbody").html("");
                sprint(slist);
            }
        })
    });
    $("#addr_province").click(function(){
        $("#xueYuanT").removeClass("active");
        $("#st_sex").removeClass("active");
        $("#addr_province").addClass("active");
        $.ajax({
            type:'post',
            url:'/sP',
            success: function(slist){
                $("tbody").html("");
                sprint(slist);
            }
        })
    })
})