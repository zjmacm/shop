Ext.define('Seller.view.order.Order',{
    extend : 'Ext.grid.Panel',
    requires:[
        'Seller.store.OrderStore',
        'Seller.store.OrderSelectedColumnStore'
    ],
    alias: 'widget.order',
    initComponent: function(){
            var orderStore = Ext.create('Seller.store.OrderStore');
            var orderSelectedColumnStore = Ext.create('Seller.store.OrderSelectedColumnStore');
            //初始化标签中的Ext:Qtip属性。
            Ext.QuickTips.init();
            Ext.form.Field.prototype.msgTarget = 'side';/*列模型中渲染的按钮*/
            var detailsRender = function details(value){
                detailsButton='<BUTTON id=ext-gen97 >详情</BUTTON>';
                return detailsButton;
            };
            var replyRender = function reply(value){
                replyButton='<BUTTON id=ext-gen97 >回复</BUTTON>';
                return replyButton;
            };
            var deleteRender = function deleteit(value){
                deleteButton='<BUTTON id=ext-gen97 >删除</BUTTON>';
                return deleteButton;
            };
            //渲染回复状态
            var sexrender = function (value, metaData, record, rowIndex, cloIndex, store) {
                if (value == '0') {
                    metaData.attr = 'style="font-weight:bold";'
                    return '否';
                }
                else
                    return '是';
            }




 /*           var itemform =function() {
alert(Ext.getCmp('message'));
//var record=new Ext.getCmp("message").getSelectionModel().getSelection();
var record = Ext.getCmp("message").getSelectionModel().getSelection();
//var userIds = Ext.getCmp("message").selModel.selections.keys;

//alert(record);
var target ;
Ext.each(record,function(){
alert(this.get("id"))
   target=this.get("id");
});
if(Ext.getCmp('message').getSelectionModel().getCount()>0){
    Ext.Ajax.request({
        url:'/seller/app/data/MessageInfo7.json',
        success: function(response){
           var json = Ext.util.JSON.decode(eval(response.responseText));
            if(json){
                Ext.MessageBox.alert('提示' ,'订单 确认成功') ;
            }
        },
        failure: function(response){
            alert(2) ;
        }
    });
}else{
    alert('请选中一行，再操作');
}

                new Ext.form.Panel({
                    title: '数据表单',
                    renderTo: Ext.getBody(),
                    bodyPadding: 5,
                    width: 350,
                    items: [{
                                xtype:'form',
                               // sm:MessageStore,
                                id:'orderPanelId',
                               // cm:essageModel,
                               // store:essageStore,
                                frame:true,
                                stripeRows : true,
                                width:675,
                                height:270,
                                bbar: new Ext.PagingToolbar({
                                   // pageSize: orderPageSize ,
                                   // store: orderDs,
                                    displayInfo: true,
                                    displayMsg: '当前记录为 {0} - {1} ,总记录 {2}',
                                    emptyMsg: "没有数据",

                                })
                    }],
                    buttons: [{
                        text: 'Submit',
                        handler: function() {
                            // The getForm() method returns the Ext.form.Basic instance:
                            var form = this.up('form').getForm();
                            if (form.isValid()) {
                                // Submit the Ajax request and handle the response
                                form.submit({
                                    success: function(form, action) {
                                       Ext.Msg.alert('Success', action.result.msg);
                                    },
                                    failure: function(form, action) {
                                        Ext.Msg.alert('Failed', action.result ? action.result.msg : 'No response');
                                    }
                                });
                            }
                        }
                    }]
                });
            };
*/








        Ext.apply(this,{
                title:'留言处理',
                height: 400,
                store:orderStore,
               // sm:checkbox,
                plugins: [
                     Ext.create('Ext.grid.plugin.CellEditing', {
                         clicksToEdit: 1
                     }),

                     //复选框咧
                     //var num = new Ext.grid.RowNumberer();
                     //var checkbox = new Ext.grid.CheckboxSelectionModel();

                ],
                columns: [
                    // checkbox,
                     { header: '编号', dataIndex: 'id', sortable: true },
                     { header: '买家名称', dataIndex: 'buyername' },
                     { header: '商品名称', dataIndex: 'itemname'},
                     { header: '留言', dataIndex: 'note' },
                     { header: '是否回复', dataIndex: 'reply', renderer: sexrender },
                     { header: '提问次数', dataIndex: 'number'},
					 { header: '提问时期', dataIndex: 'data', renderer: Ext.util.Format.dateRenderer('Y-m-d') },
                     { header:'详情',width:150,dataIndex:'planedId',renderer:detailsRender,


                       listeners:{
                                         click: function (cb, nv, ov) {
                                            // console.log(cb);
                                                 //Ext.Msg.alert("提示信息", "选中");
                                                 var win = Ext.create('Seller.view.order.OrderSelectedColumn',{
                                                     title: " 编辑",      //标题
                                                     draggable: false,
                                                     height: 350,                          //高度
                                                     width: 800,//宽度
                                                     closable:true,
                                                     layout:'fit',
                                                     modal: true, //是否模态窗口m，默认为false
                                                     resizable: false

                                                 });
                                                 Ext.getCmp("orderSelectedColumnForm").getForm().load({
                                                     url: '/seller/app/data/orderSelectedColumn.json',
                                                     method:'GET',
                                                     renderer: 'data',
                                                     failure: function(form, action) {
                                                         Ext.Msg.alert("Load failed", action.result.errorMessage);
                                                     }
                                                 });
                                                 win.show();

                                         }
                                     }


                     },
                     { header:'回复',width:150,dataIndex:'planedId',renderer:replyRender,
                         listeners:{
                                             click: function () {
                                                  alert('reply works');
                                              }
                         }
                     } ,
                     { header:'删除',width:150,dataIndex:'planedId',renderer:deleteRender
,
                         listeners:{
                                             click: function () {
                                                  alert('delete works');
                                              }
                         }
                     }
                ],
                tbar: [
                    {
                    text:"全部",
                    action:"tbtnall"
                    },
                    {
                    text:"时间",
                    action:"tbtntime"
                    },
                    {
                    text:"次数",
                    action:"tbtnnumber"
                    },
                    {
                    text:"等级",
                    action:"tbtnlevel"
                    }
                ],
// bbar: pager,
// colModel:column,
                dockedItems: [{
                    xtype: 'pagingtoolbar',
                    store: orderStore,   // GridPanel使用相同的数据源
                    dock: 'bottom',
                    //loadPage:1,
                    //load:{params:{start:0,limit:2}},
                    displayInfo: true
                }],
                pageSize: 2, // 每页显示条数

                buttons: [{
                    text: '批量编辑',
                    action: 'btnreset'
                },{
                    text: '批量删除',
                    action: 'btndelete'
                }
                ]



            });
       // console(grid.getStore());
        this.callParent(arguments);
    }

});
