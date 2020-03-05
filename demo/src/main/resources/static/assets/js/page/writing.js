$(function(){
    $("#saveDraft").click(function(){
        var article = $("#compose-textarea").val();
        var title = $("#title").val();
        var author = $("#author").val()
        console.log(title);
        $.ajax({
            type:"POST",
			url:"/draft",
            data : {"article" : article,"title":title,"author":author},
			success: function(result){
				if(result == true){
					alert("已经存放在草稿箱");
				}else{
					alert("登录失败");
				}
			}
        });
        
    });

    $("#changeToDraft").on("click",function(){
        $.ajax({
            type:"POST",
            url:"/DisplayDaft",
            success: function(resultlist){
                
                $("#writing_an_article").addClass("hidden");
                $("#draft").removeClass("hidden");

            }
        })
    });
})