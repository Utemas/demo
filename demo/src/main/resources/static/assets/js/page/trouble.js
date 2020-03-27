$(function(){
    $("#cancel").click(function(){
        history.go(-1);
    });

    $("#submitTrouble").click(function(){
        var titleText = $("#title").val();
        var text = $("#compose-textarea").val();
        if(titleText == "" || text == ""){
            alert("你需要描述一下问题的所在。");
            if(titleText == ""){
                $("#title").css("border-color","red");
            }
            
            if(text == ""){
                $("#compose-textarea").css("border-color","red");
            }
        }else{
            $.ajax({
                type:"POST",
                url:"/saveTrouble",
                data:{"title": titleText,"text":text},
                success: function(message){
                    alert(message);
                }
            });
        }
    });

    $("#title").change(function(){
        $("#title").css("border-color","");
        $("#compose-textarea").css("border-color","");
    });

    $("#clearTrouble").click(function(){
        window.location.reload();
    });
})