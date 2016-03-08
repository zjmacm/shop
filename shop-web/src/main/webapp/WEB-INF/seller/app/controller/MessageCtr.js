//多选框，分页
Ext.define('Seller.controller.MessageCtr',{
	extend : 'Ext.app.Controller',
    requires:['Seller.view.message.Message'],
	stores :['MessageStore'],
	models :['MessageModel'],
	views :['index.Center'],   //文件夹的写法
    init: function(){
        this.control({
            'message button[action=btnreset]':{
                click:this.btnreset
            },
            'message button[action=btndelete]':{
                click:this.btndelete
            },
            'message button[action=tbtnall]':{
                click:this.tbtnall
            },
            'message button[action=tbtntime]':{
                click:this.tbtntime
            },
            'message button[action=tbtnnumber]':{
                click:this.tbtnnumber
            },
            'message button[action=tbtnleval]':{
                click:this.tbtnleval
            }


        });



    },
    btnreset:function(){
    var message =  Ext.getCmp('message');
    alert(message);
    },
    btndelete:function(){


    },
    tbtnall:function(){

        console.log('all...');
        var store = Ext.data.StoreManager.lookup("MessageStore");//找到你的store
        var sorters = store.getSorters();//获取排序方式
        alert(sorters.length);
        //store.filter('buyername', '四');
        store.sort('id', 'DESC');
        for(var i=0;i<sorters.length;i++){
            // sorters.getAt(i);
            // alert(sorters.getAt(i));//Object [object Array] has no method 'getAt'
        }//读到排序方式 like  {property: "field", direction: "ASC"}

    },
    tbtntime:function(){
        var store = Ext.data.StoreManager.lookup("MessageStore");//找到你的store
        store.sort('data', 'DESC');
    },
    tbtnnumber:function(){
            var store = Ext.data.StoreManager.lookup("MessageStore");//找到你的store
            store.sort('number', 'DESC');
    },
    tbtnleval:function(){
        var store = Ext.data.StoreManager.lookup("MessageStore");//找到你的store
        store.sort('id', 'DESC');
    }
});
