$(function(){
    $("#addr_province").click(function(){
        $("#xueYuanT").removeClass("active");
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