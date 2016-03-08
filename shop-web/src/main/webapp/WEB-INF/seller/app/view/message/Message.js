Ext.define('Seller.view.message.Message',{
    extend : 'Ext.grid.Panel',
    requires: [
        'Seller.store.MessageStore',
        'Seller.store.MessageSelectedColumnStore'
        //'Seller.view.message.MessageSelectedColumn'
    ],
    alias: 'widget.message',
    initComponent: function(){
/*            var cmFunRender = function cmFun(){
                 var cm = new Ext.grid.ColumnModel({
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
                                                 var win = Ext.create('Seller.view.message.MessageSelectedColumn',{
                                                     title: " 编辑",      //标题
                                                     draggable: false,
                                                     height: 350,                          //高度
                                                     width: 800,//宽度
                                                     closable:true,
                                                     layout:'fit',
                                                     modal: true, //是否模态窗口m，默认为false
                                                     resizable: false

                                                 });
                                                 Ext.getCmp("messageSelectedColumnForm").getForm().load({
                                                     url: '/seller/app/data/messageSelectedColumn.json',
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


                 });
                 return cm;
            };
*/
            var messageStore = Ext.create('Seller.store.MessageStore');
            var messageSelectedColumnStore = Ext.create('Seller.store.MessageSelectedColumnStore');
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

                     //复选框咧
                     //var num = new Ext.grid.RowNumberer();
                     //Ext.create('Ext.grid.CheckboxSelectionModel',{});
                    this.cellEditing = new Ext.grid.plugin.CellEditing({
                        clicksToEdit: 1
                    });
                    var num=new Ext.grid.RowNumberer();
/*                    var sm = new Ext.grid.CheckboxSelectionModel();


this. sm = new Ext.grid.CheckboxSelectionModel({
    //只允许用户通过复选框执行选中操作
    handleMouseDown : Ext.emptyFn,
    //true表示只允许选择单行
    singleSelect : true
});

this.wm = new Ext.grid.getSelectionModel();
this.sm = new Ext.grid.CheckboxSelectionModel({
    checkOnly:true,

    selectAll : function(){
        sm.clearSelections();//清除全部的选区
        var storeLength = sm.grid.store.getCount();
        for(var i = 0; i < storeLength; i++){
            var record = sm.grid.store.getAt(i);
            //获取record中selected的值
            var selected = record.data['selected'];
            if(!selected){
                var id = record.get('id');
                //传入一个id，根据id查询缓存里的Record，返回其索引
                var si = store.indexOfId(id);
                sm.selectRow(i, true);
            }
        }
    },

    renderer:function(v, p, record){
        //获取record中selected的值
        var selected = record.data['selected'];
        if(!selected){
            //selected为false时候显示
            return '<div class="x-grid3-row-checker"> </div>';
        }else{
            //selected为true显示空
            return '';
        }
    }
});


Ext.create('Ext.window.Window', {
    title: 'Hello',
    height: 200,
    width: 400,
    html: '这里是你要显示的字符串'
}).show();
*/
        Ext.apply(this,{
                title:'留言处理',
                height: 400,
                store:messageStore,
               // sm:checkbox,
                plugins: [
                     Ext.create('Ext.grid.plugin.CellEditing', {
                         clicksToEdit: 1
                     })



                ],
                //cm:cmFunRender,
                    // checkbox,
               // sm: new Ext.grid.CheckboxSelectionModel(),


                columns: [

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
                                                 var win = Ext.create('Seller.view.message.MessageSelectedColumn',{
                                                     title: " 编辑",      //标题
                                                     draggable: false,
                                                     height: 350,                          //高度
                                                     width: 800,//宽度
                                                     closable:true,
                                                     layout:'fit',
                                                     modal: true, //是否模态窗口m，默认为false
                                                     resizable: false

                                                 });
                                                 Ext.getCmp("messageSelectedColumnForm").getForm().load({
                                                     url: '/seller/app/data/messageSelectedColumn.json',
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
                    store: messageStore,   // GridPanel使用相同的数据源
                    dock: 'bottom',
                    //loadPage:1,
                    //load:{params:{start:0,limit:2}},
                    displayInfo: true,
                    load:{
                        params: {
                            // specify params for the first page load if using paging
                            start: 0,
                            limit: 2,
                            // other params
                            foo:   'bar'
                        }
                    }


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
