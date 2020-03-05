$(function(){
    $("#displayUpdate").click(function(){
        $("#information_article").addClass("hidden");
        $("#updatePage").removeClass("hidden");
    });

    $("#update").click(function(){
        var tel;
        var email;
        var emailZhengze = '[a-zA-Z0-9_-]+';
        if(emailZhengze.exec(email)){
            
        }
        $.ajax({
            type:"POST",
            url:"/updateInformation",
            data:{
                "telphone": tel
            },
            success:function(message){

			},
        })
    });

    $("#back").click(function(){
        // 需要将更改的值全部制空
        $("#information_article").removeClass("hidden");
        $("#updatePage").addClass("hidden")
    });
})