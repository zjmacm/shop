Ext.define('Seller.controller.GoodsAddCtr',{
    extend: 'Ext.app.Controller',
    requires: 'Seller.view.goods.GoodsAdd',
    stores: ['GoodsCategoryStore'],
    models: ['GoodsCategoryModel'],
    views: ['goods.GoodsAdd'],
    init: function(){
        this.control({
            'goodsAddPanel button[action=save]':{
                click:this.save
            },
            'goodsAddPanel button[action=cancel]':{
                click:this.cancel
            }
        });
    },
    save:function(){
    var form =  Ext.getCmp('goodsAddPanel');
    if(form.getForm().isValid()){
        form.getEl().mask('正在上传商品信息','x-mask-loading');
        form.getForm().submit({
            waitMsg: '请稍后',
            method: "post",  //get data  future post
            url: '/seller/goodsManager/addGoods',
            success: function (basicform, action) {
                if (action.result.msg == "success") {
                    Ext.Msg.alert('提示', action.result.msg, function () {
                        document.location='seller.html';
                    });
                } else {
                    Ext.Msg.alert('提示', "上传商品失败", function () {
                        document.location='seller.html';
                    });
                }
            },
            failure: function (basicform, action) {
                switch (action.failureType) {
                    case Ext.form.Action.CLIENT_INVALID:
                        Ext.Msg.alert('Failure', '错误啦啦...');
                        break;
                    case Ext.form.Action.CONNECT_FAILURE:
                        Ext.Msg.alert('连接错误', 'ajax communication failed');
                        break;
                    case Ext.form.Action.SERVER_INVALID:
                        Ext.Msg.alert('验证错误', action.result.msg, function () {
                            document.location = location;
                        });
                    default:
                        Ext.Msg.alert('未知错误', action.result.msg, function () {


                        });

                }
                form.getEl().unmask();
            }
        });
    }else{
        Ext.Msg.alert("提示","你的相关信息填写不符合要求哦!");
        }
    },
    cancel:function(){
        //cancel form
        var form =  Ext.getCmp('goodsAddPanel');
        form.getForm().reset();
        //cancel container file

    }


});
