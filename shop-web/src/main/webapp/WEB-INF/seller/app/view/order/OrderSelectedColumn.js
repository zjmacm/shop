Ext.define('Seller.view.order.OrderSelectedColumn',{
    extend : 'Ext.window.Window',

    alias: 'widget.orderSelectedColumn',
    initComponent: function(){

        Ext.apply(this,{


            frame: true,
            height:500,
            title:'选中信息',
            bobyPadding: '5 5 0',
            bodyStyle: 'padding:20px 100px;',
            items: [{
             xtype:'form',
             id:'orderSelectedColumnForm',

            fieldDefaults:{
                labelSeparator : ':',
                labelAlign: 'left',
                labelWidth: 75,
                msgTarget: 'qtip'
            },
            defaultType: 'textfield',
            items: [{
                fieldLabel:"买家",
                width:200,
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必选",
                name:"buyername",
                maxHeight: 200
            },{
                             fieldLabel:"商品",
                             width:200,
                             allowBlank: false,
                             blankText:'不能为空',
                             emptyText:"必选",
                             name:"itemname",

                             maxHeight: 200
              },{
                                             fieldLabel:"留言",
                                             width:200,
                                             allowBlank: false,
                                             blankText:'不能为空',
                                             emptyText:"必选",
                                             name:"note",
                                             maxHeight: 200
              },{
                                             fieldLabel:"回复",
                                             width:200,
                                             allowBlank: false,
                                             blankText:'不能为空',
                                             emptyText:"必选",
                                             name:"reply",
                                             maxHeight: 200
              },{
                                                             fieldLabel:"提问次数",
                                                             width:200,
                                                             allowBlank: false,
                                                             blankText:'不能为空',
                                                             emptyText:"必选",
                                                             name:"number",
                                                             maxHeight: 200
              },{
                                                             fieldLabel:"时间",
                                                             width:200,
                                                             allowBlank: false,
                                                             blankText:'不能为空',
                                                             emptyText:"必选",
                                                             name:"data",
                                                             maxHeight: 200
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