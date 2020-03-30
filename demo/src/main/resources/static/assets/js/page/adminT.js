$(function () {

    $("#initialPage").click(function(){
        
        //切换
        $("#initialPage").addClass("active");
        $("#xuejiInput").removeClass("active");
        //页面切换

    });

    $("#xuejiInput").click(function(){
        $("#xuejiInput").addClass("active");
    });

    // $("#search_btn2").click(function(){
    //     var st_id = $("#Search_student").val();
        
    // });

    
})