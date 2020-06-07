$(function () {

    $("#initialPage").click(function(){
        
        //切换
        $("#initialPage").addClass("active");
        $("#xuejiInput").removeClass("active");
        //页面切换
        $("#xuejiSearch").removeClass("hidden");
        $("#xuejiInputPage").addClass("hidden");
        $("#classInfoPage").addClass("hidden");
        $("#XuInputPage").addClass("hidden");

    });

    $("#xuejiInput").click(function(){
        $("#initialPage").removeClass("active");
        $("#xuejiInput").addClass("active");
        //页面切换
        $("#xuejiInputPage").removeClass("hidden");
        $("#XuInputPage").removeClass("hidden");
        $("#xuejiSearch").addClass("hidden");
        $("#classInfoPage").addClass("hidden");
    });

    $("#classUpload").click(function(){
        $("#classManage").removeClass("active");
        $("#classUpload").addClass("active");
        
        $("#xuejiSearch").addClass("hidden");

        $("#XuInputPage").addClass("hidden");
        $("#xuejiInputPage").addClass("hidden");
        $("#classInfoPage").removeClass("hidden");
    });

    $("#classManage").click(function(){
        $("#classUpload").removeClass("active");
        $("#classManage").addClass("active");
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

    $("#uploadXueJiInfo").click(function(){
        var file = $("#Xuejifile")[0].files[0];
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            type:'post',
            url:'/uploadXueJiInfo',
            processData: false,
            contentType:false,
            data:formData,
            success:function (msg) {
                alert(msg);
            }
        })
    })


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
    });

    $('#AllStudent').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false
      });

    
});

