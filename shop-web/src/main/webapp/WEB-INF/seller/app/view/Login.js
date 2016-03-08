Ext.define('Seller.view.Login', {
    extend:'Ext.window.Window',
    alias:'widget.login',
    id:'loginwindow',  // 方便查找
    cls:'form-login-dialog',
    iconCls:'form-login-icon-title',
    width:420,
    height:210,
    resizable:false,
    closable:false,
    draggable:false,
    modal:true,
    closeAction:'hide',
    layout:'border',
    title:'Login',

    initComponent:function () {

        Ext.apply(this, {
            items:[
                {
                    itemId:'headerPanel',
                    xtype:'panel',
                    cls:'form-login-header',
                    baseCls:'x-plain',
                    html:'卖客管理系统',
                    region:'north',
                    height:60
                },{
                    xtype:'form',
                    id:'loginform',  //方便查找表单的值
                    bodyPadding:10,
                    header:false,
                    region:'center',
                    border:false,
                    waitMsgTarget:true,
                    layout:{
                        type:'vbox',
                        align:'stretch'
                    },
                    defaults:{
                        labelWidth:85
                    },
                    items:[
                        {
                            itemId:'userName',
                            xtype:'textfield',
                            fieldLabel:'Username',
                            name:'userName',
                            allowBlank:false,
                            anchor:'100%',
                            validateOnBlur:false
                        },{
                            xtype:'textfield',
                            fieldLabel:'Password',
                            name:'password',
                            allowBlank:false,
                            inputType:'password',
                            anchor:'100%',
                            validateOnBlur:false,
                            enableKeyEvents:true,
                            listeners:{
                                render:{
                                    fn:function (field, eOpts) {
                                        field.capsWarningTooltip = Ext.create('Ext.tip.ToolTip', {
                                            target:field.bodyEl,
                                            anchor:'top',
                                            width:305,
                                            html:'Caps lock warning'
                                        });

                                        // disable to tooltip from showing on mouseover
                                        field.capsWarningTooltip.disable();
                                    },
                                    scope:this
                                },

                                keypress:{
                                    fn:function (field, e, eOpts) {
                                        var charCode = e.getCharCode();
                                        if ((e.shiftKey && charCode >= 97 && charCode <= 122) ||
                                            (!e.shiftKey && charCode >= 65 && charCode <= 90)) {

                                            field.capsWarningTooltip.enable();
                                            field.capsWarningTooltip.show();
                                        }
                                        else {
                                            if (field.capsWarningTooltip.hidden === false) {
                                                field.capsWarningTooltip.disable();
                                                field.capsWarningTooltip.hide();
                                            }
                                        }
                                    },
                                    scope:this
                                },

                                blur:function (field) {
                                    if (field.capsWarningTooltip.hidden === false) {
                                        field.capsWarningTooltip.hide();
                                    }
                                }
                            }
                        }
                    ]
                }
            ],
            buttons:[
                {
                    id:'loginButton',
                    type:"submit",
                    action:"login",
                    formBind:true,
                    text:'Login',
                    //ref:'../loginAction',
                    handler:submit,
                    iconCls:'form-login-icon-login',
                    scale:'medium',
                    width:90
                }
            ]
        });
        this.callParent(arguments);
    },
    defaultFocus:'userName'
});

var submit = function(){
    var loginForm = Ext.getCmp('loginform');
    if(loginForm.getForm().isValid()){
        loginForm.getEl().mask('登录验证中', 'x-mask-loading');
        loginForm.getForm().submit({
        waitTitle:'正在登录',
        waitMsg:'正在向服务器申请登录验证，请稍后...',
        method:'get',
        url : '/seller/app/data/loginSuccess.json',
        success:function(basicform, action){
            loginForm.getEl().unmask();
            console.log(action.result);

            if (action.result.msg=='success') {
                document.location='seller.html';
            } else {
                    Ext.Msg.alert('登陆错误',action.result.msg,function(){
                        document.location=location;
                        });
                                
                    }
        },
        failure: function(basicform, action) {
            switch (action.failureType) {
                    case Ext.form.Action.CLIENT_INVALID:
                        Ext.Msg.alert('Failure', '请输入正确用户信息或验证码！');
                        break;
                    case Ext.form.Action.CONNECT_FAILURE:
                        Ext.Msg.alert('连接错误', 'Ajax communication failed');
                        break;
                    case Ext.form.Action.SERVER_INVALID:
                        Ext.Msg.alert('验证错误', action.result.msg,function(){
                            document.location=location;
                        });
                                   
            }
            loginForm.getEl().unmask();
        }
    });
    }else{
        Ext.Msg.alert('提示','登录信息错误,请核对！');
    }
}
