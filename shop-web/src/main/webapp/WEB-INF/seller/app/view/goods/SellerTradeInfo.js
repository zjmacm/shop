Ext.define('Seller.view.goods.SellerTradeInfo',{
    extend : 'Ext.window.Window',

    alias: 'widget.sellerTradeInfo',
    initComponent: function(){

        Ext.apply(this,{


            frame: true,
            height:500,
            title:'商品线下交易信息',

            bobyPadding: '5 5 0',
            bodyStyle: 'padding:20px 100px;',
            items: [{
             xtype:'form',
             id:'sellerTradeForm',

            fieldDefaults:{
                labelSeparator : ':',
                labelAlign: 'left',
                lableWidth: 75,
                msgTarget: 'qtip'
            },
            defaultType: 'textfield',
            items: [{
                fieldLabel:"手机",
                width:200,


                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必选",

                name:"mobile",

                maxHeight: 200
            },{
                fieldLabel:"邮箱",
                width:200,


                name:"email",
                maxHeight: 200,
                vtype:'email'
            },{
                name:"message",
                hiddenName:"brand",
                xtype:'htmleditor',
                fieldLabel:"附言",
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必填",

            }],


                buttons: [{
                    text: '保存',
                    action:'save',
                    type:'submit'
                },{
                    text: '取消',
                    action: 'cancel'
                }]
            }]}


            );
        this.callParent(arguments);
    }

});