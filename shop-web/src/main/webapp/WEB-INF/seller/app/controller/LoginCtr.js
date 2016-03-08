Ext.define('Seller.controller.LoginCtr',{
    extend: 'Ext.app.Controller',
    requires: ['Seller.view.Login'],
    init:function() {
        this.control({
            'button[action=login]':{
                
                click:this.login
            },
            '#loginwindow textfield':{
                specialkey:this.keyenter
            },
            'button[action=logout]':{
                click:this.logout
            }
        });
    },
    views:['Login','Viewport'],
    refs:[{
        ref:'viewport',      //create getViewport()
        selector:'viewport'  //Components alias

    },{
        ref:'loginwindow',
        selector:'loginwindow'
    }],
    
/*    index:function() {
        console.log('index...');
        var loginwin = Ext.create('Seller.view.Login');
        loginwin.show();
    },*/
    login: function() {
        console.log('login...');
        var win = Ext.getCmp('loginwindow');
        var form = Ext.getCmp('loginform');
        var values = form.getValues();
        var lay = this.getViewport().getLayout();  //登录之后跳转到主页面
        if (values.userName == 'admin') {  //如果成功
            var lay = this.getViewport().getLayout();
            //lay.setActiveItem(1);
            win.hide();
            Ext.util.Cookies.set('username', values.userName);
            Ext.util.Cookies.set('token', 'some token');
            Ext.getCmp('loggedin').update('Logged in as: ' + ' <b>' + values.userName + '</b>');
                                                                                            
        }

    },
    keyenter:function (item, event) {
        if (event.getKey() == event.ENTER) {
            this.login();

        }
    },
    logout:function (button) {
        Ext.log('Logout user')
        var lay = this.getViewport().getLayout();
        lay.setActiveItem(0);
        var win = Ext.getCmp('loginwindow');
        win.show();

        Ext.util.Cookies.clear('username');
        Ext.util.Cookies.clear('token');
                                                                    
    }

});
