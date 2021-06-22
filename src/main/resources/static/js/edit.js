//初始化Markdown编辑器
let contentEditor;
let conditionNumber = 0;
let uid;
let bTitle;
let content;
$(function () {
    contentEditor = editormd("q-content", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/blog/lib/",//依赖的包路径
        saveHTMLToTextarea: true,

    });

    conditionNumber = 0;

    $('#blogSubmit').click(function () {
        uid = $('#uid').val();
        bTitle = $('#blogName').val();
        content = contentEditor.getHTML()
        if (uid == null || uid.length === 0) {
            return alert("请登录");
            conditionNumber++;
        }
        if (bTitle == null || bTitle.length === 0) {
            return alert("必须输入标题" + $('#blogName').val())
            conditionNumber++;
        }
        if (content == null || content.length === 0) {
            return alert("必须输入博文")
            conditionNumber++;
        }
        if (conditionNumber === 0) {
            Upload()
        }
    });

    function Upload() {
        $.ajax({
            type: "POST",                      //请求类型
            url: "/blogs/save",           //URL
            data: {
                publishing_users: uid
                , blogTitle: bTitle
                , blogContent: contentEditor.getHTML()
            },   //传递的参数
            dataType: "json",                 //返回的数据类型
            success: function (data) {          //data就是返回的json类型的数据
                if (data.msg == "true") {
                    console.log("ok")

                } //else if (data.msg == "false") {
                //     alert("对不起, " + obj.attr("username") + " 用户删除失败");
                // } else if (data.msg == "noex") {
                //     alert("对不起,用户 " + obj.attr("username") + " 不存在");
                // }
            },
            error: function (data) {
                alert("删除失败");
            }
        })
    };
});



