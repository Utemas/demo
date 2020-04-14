$(function () {

    $("#initialPage").click(function(){
        
        //切换
        $("#initialPage").addClass("active");
        $("#xuejiInput").removeClass("active");
        //页面切换
        $("#xuejiSearch").removeClass("hidden");
        $("#xuejiInputPage").addClass("hidden");

    });

    $("#xuejiInput").click(function(){
        $("#initialPage").removeClass("active");
        $("#xuejiInput").addClass("active");

        //页面切换
        $("#xuejiInputPage").removeClass("hidden");
        $("#xuejiSearch").addClass("hidden");
    });

    $("#example2").on("click","#deleteStudent",function () {
        if (confirm("要删除数据吗?")) {
            $.ajax({
                url: "/delete",
                type: "post",
                data: {
                    st_id:$(this).val()
                },
                success: function (msg) {
                    window.location = "/admin";
                }
            });
        } else {
            return false;
        }
    });

    $("#uploadFile").click(function(){
        var file = $("#file")[0].files[0];
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            type:'post',
            url:'/upload',
            processData: false,
            contentType:false,
            data:formData,
            success:function (msg) {
                alert(msg);
            }
        })
    });


    $("#uploadClassFile").click(function(){
        var file = $("#classFile")[0].files[0];
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            type:'POST',
            url:'/uploadScore',
            processData: false,
            contentType:false,
            data:formData,
            success:function (msg) {
                alert(msg);
            }
        })
    })
});

