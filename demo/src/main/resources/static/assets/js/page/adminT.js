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
        $("#st_info").addClass("hidden");
    });

    $("#xuejiInput").click(function(){
        $("#initialPage").removeClass("active");
        $("#xuejiInput").addClass("active");
        //页面切换
        $("#xuejiInputPage").removeClass("hidden");
        $("#st_info").removeClass("hidden");
        $("#XuInputPage").removeClass("hidden");
        $("#xuejiSearch").addClass("hidden");
        $("#classInfoPage").addClass("hidden");
    });

    $("#classUpload").click(function(){
        $("#classManage").removeClass("active");
        $("#classUpload").addClass("active");
        
        $("#xuejiSearch").addClass("hidden");
        $("#st_info").addClass("hidden");
        $("#XuInputPage").addClass("hidden");
        $("#xuejiInputPage").addClass("hidden");
        $("#classInfoPage").removeClass("hidden");
    });

    

    $("#classManage").click(function(){
        $("#classUpload").removeClass("active");
        $("#classManage").addClass("active");
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

    $("#uploadSt").click(function(){
        var file = $("#STFile")[0].files[0];
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            type:'post',
            url:'/uploadStudentInfo',
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

