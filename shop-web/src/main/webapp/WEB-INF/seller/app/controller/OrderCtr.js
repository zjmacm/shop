//多选框，分页
Ext.define('Seller.controller.OrderCtr',{
	extend : 'Ext.app.Controller',
    requires:['Seller.view.order.Order'],
	stores :['OrderStore'],
	models :['OrderModel'],
	views :['index.Center'],   //文件夹的写法
    init: function(){
        this.control({
            'order button[action=btnreset]':{
                click:this.btnreset
            },
            'order button[action=btndelete]':{
                click:this.btndelete
            },
            'order button[action=tbtnall]':{
                click:this.tbtnall
            },
            'order button[action=tbtntime]':{
                click:this.tbtntime
            },
            'order button[action=tbtnnumber]':{
                click:this.tbtnnumber
            },
            'order button[action=tbtnleval]':{
                click:this.tbtnleval
            }


        });



    },
    btnreset:function(){
    var order =  Ext.getCmp('order');
    alert(order);
    },
    btndelete:function(){


    },
    tbtnall:function(){

        console.log('all...');
        var store = Ext.data.StoreManager.lookup("OrderStore");//找到你的store
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
        var store = Ext.data.StoreManager.lookup("OrderStore");//找到你的store
        store.sort('data', 'DESC');
    },
    tbtnnumber:function(){
            var store = Ext.data.StoreManager.lookup("OrderStore");//找到你的store
            store.sort('number', 'DESC');
    },
    tbtnleval:function(){
        var store = Ext.data.StoreManager.lookup("OrderStore");//找到你的store
        store.sort('id', 'DESC');
    }
});
