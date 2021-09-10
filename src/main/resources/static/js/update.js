let account;
let nickname;
let userId;
$(function () {

    $("#updateBtn").click(function () {
        infoUser()
        return false;
    })

    function infoUser() {
        account = $("#account").val()
        nickname = $("#nickname").val()
        userId = $("#userId").attr("userId");
        if (account == "" && account.length == 0) {
            alert("请输入账号")

        }
        if (nickname == "" && nickname.length == 0) {
            alert("请输入用户名")
        }

        $.ajax({
            type: "POST",                      //请求类型
            url: "/admin/updateUser",           //URL
            data: {
                userAccount:account,
                userNickName:nickname,
                userId:userId
            },   //传递的参数
            dataType: "json",                 //返回的数据类型
            success: function (data) {          //data就是返回的json类型的数据
                if (data.msg == "true") {
                    swal("提交成功", "请确定", "success");

                    contentEditor.clear()
                }else {
                    swal("删除失败", "请确定", "error");
                }
            },
            error: function (data) {
                swal("提交失败", "请确定", "error");
            }
        })

    }


    $("#photoFile").click(function () {
        $("#profile").click();
    })


    $("#profile").change(function () {
        if ($(this).val() == '') {
            return;
        }
        var formData = new FormData();
        formData.append('photo', document.getElementById('profile').files[0]);
        $.ajax({
            url: "/admin/uploadPhoto",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.type == "success") {
                    $("#photoFile").attr("src", data.filepath + data.filename);
                    swal("头像修改成功", "请确定", "success");
                    // $("#productImg").val(data.filename);
                } else {
                    swal("data.msg", "请确定", "error");
                }
            },
            error: function (data) {
                alert("上传失败")
            }
        });
    })


})