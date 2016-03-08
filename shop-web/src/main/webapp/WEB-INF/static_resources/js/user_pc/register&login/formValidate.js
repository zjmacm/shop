define(function(require, exports, module) {

	function formValidate(){
 
                var ok1=false;
                var ok2=false;
                var ok3=false;
                var ok4=false;
/*		$("form :input.required").each(function(){
            var $required = $("<strong class='high'> *</strong>"); //创建元素
            $(this).parent().append($required); //然后将它追加到文档中
        });*/
                // 验证用户名
                $('input[name="username"]').focus(function(){
                    $(this).next().text('用户名应在3-20位之间').removeClass('state1').addClass('state2');
                }).blur(function(){
                    if($(this).val().length >= 3 && $(this).val().length <=12 && $(this).val()!=''){
                        $(this).next().text('输入成功').removeClass('state1').addClass('state4');
                        ok1=true;
						dataHanding(usename);
                    }else{
                        $(this).next().text('用户名应在3-20位之间').removeClass('state1').addClass('state3');
                    }
                     
                });
 
                //验证密码
                $('input[name="password"]').focus(function(){
                    $(this).next().text('密码应在6-20位之间').removeClass('state1').addClass('state2');
                }).blur(function(){
                    if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!=''){
                        $(this).next().text('输入成功').removeClass('state1').addClass('state4');
                        ok2=true;
                    }else{
                        $(this).next().text('密码应该为6-20位之间').removeClass('state1').addClass('state3');
                    }
                     
                });
 
                //验证确认密码
                    $('input[name="repassword"]').focus(function(){
                    $(this).next().text('输入的确认密码要和上面的密码一致').removeClass('state1').addClass('state2');
                }).blur(function(){
                    if($(this).val().length >= 6 && $(this).val().length <=20 && $(this).val()!='' && $(this).val() == $('input[name="password"]').val()){
                        $(this).next().text('输入成功').removeClass('state1').addClass('state4');
                        ok3=true;
                    }else{
                        $(this).next().text('输入的确认密码要和上面的密码一致').removeClass('state1').addClass('state3');
                    }
                     
                });
 
                //验证邮箱
                $('input[name="emailAddress"]').focus(function(){
                    $(this).next().text('非法的EMAIL格式').removeClass('state1').addClass('state2');
                }).blur(function(){
                    if($(this).val().search(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/)==-1){
                        $(this).next().text('非法的EMAIL格式').removeClass('state1').addClass('state3');
                    }else{                  
                        $(this).next().text('输入成功').removeClass('state1').addClass('state4');
                        ok4=true;
                    }
                     
                });
 
                //提交按钮,所有验证通过方可提交
 
                $('.submit').click(function(){
                    if(ok1 && ok2 && ok3 && ok4){
                        $('form').submit();
                    }else{
                        return false;
                    }
                });
                 

	}
	  module.exports = formValidate;
});