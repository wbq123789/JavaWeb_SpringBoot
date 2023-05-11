function AskVerifyCode() {
    $.get('/api/register/verify-code',{
        email:$("#input-email").val()
    },function (data){
        alert(data.reason)
        }
    )
}