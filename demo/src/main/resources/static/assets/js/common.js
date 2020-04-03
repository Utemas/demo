function logout(){
    window.alert("确定退出登录吗？");
    window.location.href="/logout";
}

function print(result){
    $.each(result,function(index,obj){
        $("#classes").append("<tr><td>"+obj['class_id']+"</td><td>"+obj['class_name']+"</td><td>"+obj['class_teacher']+"</td><td>"+obj['class_score']+"</td><td>"+obj['class_xuefen']+"</td>/tr>");
    })
}

    