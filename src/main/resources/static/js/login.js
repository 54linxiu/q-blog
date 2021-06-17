$(
    function (){
        $('#login').click(function (){
            let account = $('#account').val()
            let password = $('#password').val()

            console.log(account)
            console.log(password)
            $.ajax({
                url:'/admin/login',
                type:'get',
                data:{'account':account,'password':password},
                dataType:'json',
                success:function(data){
                    if(data.flag==1){
                        location.href='/admin/index';
                    }else{
                        alert(data.msg);
                    }
                },
                // error:function(){
                //     console.log('请求出错！');
                // }
            })
        })
    }

)