//初始化Markdown编辑器
let contentEditor;
let conditionNumber = 0;
let uid;
let bTitle;
let content;
let sort;
let tags;
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
        content = contentEditor.getHTML();
        sort = $('#blogSortName_sele').val();
        tags = $('#blogTagsName').val();
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
        if ($("#pTitle").text() == "发布博客"){
            $.ajax({
                type: "POST",                      //请求类型
                url: "/blogs/save",           //URL
                data: {
                    publishingUsers: uid
                    , blogTitle: bTitle
                    , blogContentHtml: contentEditor.getHTML()
                    , blogContentMd: contentEditor.getMarkdown()
                    , blogSortName: sort
                    , blogTagsName: tags
                },   //传递的参数
                dataType: "json",                 //返回的数据类型
                success: function (data) {          //data就是返回的json类型的数据
                    if (data.msg == "true") {
                        swal("提交成功", "请确定", "success");
                        //上传成功 清空标题 博文 标签
                        $("#blogName").val("")
                        //因为jquery.editable-select.js 所以改变了 id
                        $('#blogSortName_sele').val("c")
                        $('#blogTagsName').val("")
                        contentEditor.clear()
                    }else {
                        swal("删除失败", "请确定", "error");
                    }
                },
                error: function (data) {
                    swal("提交失败", "请确定", "error");
                }
            })
        }else{

            $.ajax({
                type: "POST",                      //请求类型
                url: "/blogs/modify",           //URL
                data: {
                    blogId : $("#blogId").val(),
                    publishingUsers: uid
                    , blogTitle: bTitle
                    , blogContentHtml: contentEditor.getHTML()
                    , blogContentMd: contentEditor.getMarkdown()
                    , blogSortName: sort
                    , blogTagsName: tags
                },   //传递的参数
                dataType: "json",                 //返回的数据类型
                success: function (data) {          //data就是返回的json类型的数据
                    if (data.msg == "true") {
                        console.log("ok");
                        swal("修改成功", "请确定", "success");
                        //上传成功 清空标题 博文 标签 默认c 为默认分类
                        $("#blogName").val("")
                        $('#blogSortName').val("c")
                        $('#blogTagsName').val("")
                        contentEditor.clear()
                        $("#pTitle").text("发布博客")
                    }
                },
                error: function (data) {
                    swal("修改失败", "请确定", "error");
                }
            })
        }
    };

    //可输入 下拉框
    $('#blogSortName').editableSelect({
        bg_iframe: true,

        case_sensitive: false,
        items_then_scroll: 10 ,
        isFilter:false
    });

});



