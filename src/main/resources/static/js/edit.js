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
        imageUpload    : true,
        imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/blogs/saveImg",

    });


    $('#blogSubmit').click(function () {
        uid = $('#uid').val();
        bTitle = $('#blogName').val();
        content = contentEditor.getHTML()
        if (uid == null || uid.length === 0) {
             alert("请登录");
            return
        }

        if (bTitle == null || bTitle.length === 0) {
            swal("请输入标题", {
                icon: "error",
            });
            return
        }
        if (content == null || content.length === 0) {
            swal("请输入博文", {
                icon: "error",
            });
            return
        }
        Upload()
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
                }
            },
            error: function (data) {
                alert("删除失败");
            }
        })
    };
});



