//选择栏目 zhangji
function selectEntry(id,mid)
{ 
    $(".menu_list").removeClass("leftbar_color");
    $("#list"+id).addClass("leftbar_color");
    
    $.ajax({
        type:"post",
        url:"/info/entry/select",
        data:{"id":id,"mid":mid},
        success:function(data){             
            $(".right_content").html(data);
        }       
    });
}
