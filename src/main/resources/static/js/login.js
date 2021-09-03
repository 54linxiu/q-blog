$(
    function (){
        $('#login').click(function (){
            let account = $('#account').val()
            let password = $('#password').val()

            // console.log(account)
            // console.log(password)
            $.ajax({
                url:'/admin/login',
                type:'POST',
                data:{'account':account,'password':password},
                dataType:'json',
                success:function(data){
                    if(data.flag==1){
                        location.href='/admin/index';
                    }else{
                        // alert(data.msg);
                        $('#msg').text(data.msg).css('color',"#f7acbc").css('opacity',1)
                    }
                },
                error:function(){
                    console.log('请求出错！');
                }
            })
        })
    }

)