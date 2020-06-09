function logout(){
    window.alert("确定退出登录吗？");
    window.location.href="/logout";
}

function print(result){
    $.each(result,function(index,obj){
        $("#classes").append("<tr><td>"+obj['class_id']+"</td><td>"+obj['class_name']+"</td><td>"+obj['class_teacher']+"</td><td>"+obj['class_score']+"</td><td>"+obj['class_xuefen']+"</td>/tr>");
    })
}

function sprint(result){
    $.each(result,function(index,obj){
        $("tbody").append("<tr><td>"+obj['label']+"</td><td>"+obj['countNumber']+"</td><td>"+obj['percent']+"</td>");
    })
}

function hed(str1,str2){
    $("#sec").html("");
    $("#sec").html("<h1>"+str1+"<small>"+str2+"</small></h1><ol class='breadcrumb'><li><a href='/login'>登录</a></li><li><a href='#'><i class='fa fa-dashboard'></i>"+str1+"</a></li></ol>");
}

function hed2(str1,str2,last){
    $("#sec").html("");
    $("#sec").html("<h1>"+str1+"<small>"+str2+"</small></h1><ol class='breadcrumb'><li><a href='/admin'>"+last+"</a></li><li><a href='#'><i class='fa fa-dashboard'></i>"+str1+"</a></li></ol>");
}
function hed3(str1,str2,last){
    $("#sec").html("");
    $("#sec").html("<h1>"+str1+"<small>"+str2+"</small></h1><ol class='breadcrumb'><li><a href='/student'>"+last+"</a></li><li><a href='#'><i class='fa fa-dashboard'></i>"+str1+"</a></li></ol>");
}

    