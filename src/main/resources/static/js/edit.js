//初始化Markdown编辑器
var contentEditor;
$(function () {
    contentEditor = editormd("q-content", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/blog/lib/",//依赖的包路径
        saveHTMLToTextarea: true,

    });

});

$('#blogSubmit').click(function (){

    $.ajax({
        type: "POST",                      //请求类型
        url: "/blogs/save",           //URL
        data: {blogContent: contentEditor.getHTML()},   //传递的参数
        dataType: "json",                 //返回的数据类型
        success: function (data) {          //data就是返回的json类型的数据
            if (data.mess == "true") {
                alert("ok");

            } else if (data.mess == "false") {
                alert("对不起, " + obj.attr("username") + " 用户删除失败");
            } else if (data.mess == "noex") {
                alert("对不起,用户 " + obj.attr("username") + " 不存在");
            }
        },
        error: function (data) {
            alert("删除失败");
        }
    })
})

$(".fa-close").click(function (){
    console.log("11")
})
