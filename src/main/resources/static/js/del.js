

$(function () {
    $(".btn.btn-danger.del").click(function (){
            let a = $(this)
            $.ajax({
                type: "POST",                      //请求类型
                url: "/blogs/del",           //URL
                data: {
                    id : a.attr("actionId"),
                    action : a.attr("action")
                },   //传递的参数
                dataType: "json",                 //返回的数据类型
                success: function (data) {          //data就是返回的json类型的数据
                    if (data.msg == "true") {
                        swal("删除成功", "请确定", "success");
                        a.parents("tr").remove()
                    }else {
                        swal("删除失败", "请确定", "error");
                    }
                },
                error: function (data) {
                    swal("删除失败", "请确定", "error");
                }
            })
        })
});