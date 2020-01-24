$(function(){
    $("#displayUpdate").click(function(){
        $("#information_article").addClass("hidden");
        $("#updatePage").removeClass("hidden");
    });

    $("#update").click(function(){
        
    });

    $("#back").click(function(){
        // 需要将更改的值全部制空
        $("#information_article").removeClass("hidden");
        $("#updatePage").addClass("hidden")
    });
})