//Ext.Ext.ns('shop.goods');

Ext.define('func.GoodsAddView',
    {
        extend : 'Ext.form.Panel',
        initComponent : function() {        //初始化标签中的Ext:Qtip属性。
        Ext.QuickTips.init();
        Ext.form.Field.prototype.msgTarget = 'under';
        //商品类别下拉框store
        var goods_category_data=[{'id':1,'name':'手机'},
            {'id':2,'name':'笔记本'},
            {'id':3,'name':'彩票'},
        ];

        var goodsCategoryStore = new Ext.data.JsonStore({
            data:goods_category_data,
            fields:['id','name']//当是静态数据时，fields可以是['id','name']，
            //如果是通过url动态获取数据是，必须是fields:[{name:'id'},{name:'suit'},{name:'type'}]
        });
        //上传图片区域
        var container1=new Ext.Container({
            layout:{
                type:'hbox',
                padding:'5',//与上下行之间的间隔
                pack:'start'//调整最左边按钮的位置:start,center,end
            },
            defaults : {
                margins : '0 0 0 -4'
            },
            items:[
                {
                    xtype : 'textfield',//此句不可删
                    id : 'goods_photo1',
                    name : 'photo',
                    hiddenName:"photo",
                    inputType : "file"
                },
                { xtype: "button", name: "reset",text : "取消" }
            ]
        });
        var container2=new Ext.Container({
            layout:{
                type:'hbox',
                padding:'5',//与上下行之间的间隔
                pack:'start'//调整最左边按钮的位置:start,center,end
            },
            defaults : {
                margins : '0 0 0 -4'
            },
            items:[
                {
                    xtype : 'textfield',//此句不可删
                    id : 'goods_photo2',
                    name : 'photo',
                    hiddenName:"photo",
                    inputType : "file"
                },
                { xtype: "button", name: "reset",text : "取消" }
            ]
        });
        var container3=new Ext.Container({
            layout:{
                type:'hbox',
                padding:'5',//与上下行之间的间隔
                pack:'start'//调整最左边按钮的位置:start,center,end
            },
            defaults : {
                margins : '0 0 0 -4'
            },
            items:[
                {
                    xtype : 'textfield',//此句不可删
                    id : 'goods_photo3',
                    name : 'photo',
                    hiddenName:"photo",
                    inputType : "file"
                },
                { xtype: "button", name: "reset",text : "取消" }
            ]
        });
        var container4=new Ext.Container({
            layout:{
                type:'hbox',
                padding:'5',//与上下行之间的间隔
                pack:'start'//调整最左边按钮的位置:start,center,end
            },
            defaults : {
                margins : '0 30 0 -4'
            },
            items:[
                {
                    xtype: "button",
                    name: "reset",
                    text : "上传所有图片",
                    handler : function() {
                            if(Ext.getCmp('goods_photo1').getValue() == ''){//只判断第一个
                                Ext.Msg.alert('错误','请选择你要上传的文件');
                                return;
                            }
                            Ext.MessageBox.show({
                                title : '请等待',
                                msg : '文件正在上传...',
                                progressText : '',
                                width : 300,
                                progress : true,
                                closable : false,
                                animEl : 'loding'
                            });
                            form.getForm().submit({
                                url : 'Action/UpdateLoad',
                                method : 'POST',
                                success : function(form, action) {
                                    Ext.Msg.alert('Message',
                                            action.result.success);
                                    win.close();
                                },
                                failure : function() {
                                    Ext.Msg.alert('Error',
                                            'File upload failure.');
                                }
                            })
                        }
                },
                { xtype: "button", name: "reset",text : "取消上传" }
            ]
        });
        //商品上传表单
        var goods_addPanel = new Ext.form.FormPanel({
            id:"goods_upload",
            //frame:true,
            autoWidth:true,
            height:400,
            autoScroll:true,
            //margin:20,
            labelWidth:80,
            labelAlign:"left",
            defaultType:"textfield",
            border:0,
            shadow : false,
            bodyStyle: 'padding:20px 100px;' ,    //上－右－下－左//border:false
            items:[{
                fieldLabel:"选择商品类型",
                width:200,
                xtype: "combo",
                triggerAction: 'all',
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必选",
                store: goodsCategoryStore,
                name:"goods_category",
                hiddenName: "goods_category",//不需提交
                selectOnFocus: true,
                displayField: "name",
                valueField: "id",
                mode: "local",//以后改为remote!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                forceSelection: true,
                maxHeight: 200
            },{
                fieldLabel:"选择分类",
                width:200,
                xtype: "combo",
                triggerAction: 'all',
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必选",
                store: goodsCategoryStore,//以下部分获取remote数据时再修改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                name:"category_id",
                hiddenName: "category_id",
                selectOnFocus: true,
                displayField: "name",
                valueField: "id",
                mode: "local",//以后改为remote!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                forceSelection: true,
                maxHeight: 200
            },{
                name:"name",
                hiddenName:"name",
                fieldLabel:"商品名称",
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必填",
                width:200
            },{
                name:"brand",
                hiddenName:"brand",
                fieldLabel:"商品品牌",
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必填",
                width:200
            },{
                name:"goods_kind",
                hiddenName:"goods_kind",//待定!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                fieldLabel:"商品类别",
                width:200,
                xtype:"panel",
                layout:"column",
                allowBlank: false,
                isFormField:true,
                items:[{
                    columnWidth:.5,
                    xtype:"radio",
                    boxLabel:"全新",
                    name:"kind"
                },{
                    columnWidth:.5,
                    checked:true,
                    xtype:"radio",
                    boxLabel:"二手",
                    name:"kind"
                }]
            },{
                name:"trade_method",
                hiddenName:"trade_method",//待定!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                fieldLabel:"交易方式",
                allowBlank: false,
                blankText:'必选',
                //emptyText:"必填",
                width:200,
                xtype:'checkboxgroup',
                columns:2,//2列
                items:[

                    {
                        boxLabel:'线下交易',
                        name:'offline',
                        listeners:{
                            change: function (cb, nv, ov) {//change
                                if(cb.getValue()==true){
                                    //Ext.Msg.alert("提示信息", "选中");
                                    var win = new Ext.Window({
                                        title: " 编辑",	   //标题
                                        draggable: false,
                                        height: 350,						  //高度
                                        width: 800,//宽度
                                        closable:false,
                                        modal: true, //是否模态窗口，默认为false
                                        resizable: false,
                                        items: {
                                            //html:"<iframe scrolling='auto'   width='100%' height=300 src='seller_info.html'> </iframe>"//frameborder='0'
                                        }
                                    });
                                    //win.show();
                            }
                                }

                            }
                        },{
                        boxLabel:'在线交易',
                        name:'online'
                    }
                ]
            },{
                name:"cost",
                hiddenName:"cost",
                fieldLabel:"成本价（元）",
                allowBlank: false,
                emptyText:"必填",
                blankText:'不能为空',
                width:200
            },{
                name:"sale_price",
                hiddenName:"sale_price",
                fieldLabel:"销售价（元）",
                emptyText:"必填",
                blankText:'不能为空',
                allowBlank: false,
                width:200
            },{
                name:"discount_price",
                hiddenName:"discount_price",
                fieldLabel:"优惠价（元）",
                emptyText:"必填,小于等于销售价",
                allowBlank: false,
                width:200
            },{
                name:"credit",
                hiddenName:"credit",
                fieldLabel:"商品积分",
                width:200
            },{
                id:'goods_quantity',
                name:"quantity",
                hiddenName:"quantity",
                fieldLabel:"商品数量",
                allowBlank: false,
                blankText:'不能为空',
                emptyText:"必填",
                width:200
            },{
                    xtype:'container',
                    id:'photo_container',
                    fieldLabel:"上传商品图片 图片像素比例最好为[3:4]  ",
                    layout:'anchor',
                    items:[container1,container2,container3],//,container4
                    width:100,
                    height:130
                },{
                xtype : 'box',
                name:'image',
                id : 'browseImage',
                fieldLabel : "预览图片",
                autoEl : {
                    width : 100,
                    height : 300,
                    tag : 'img',
                    type : 'image',
                    src : '2.jpg',//默认图片
                    style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
                    complete : 'off',
                    id : 'imageBrowse'
             }
         }
],
       //form事件
     /*  listeners : {
           'render' : function() {
                 this.form.findField('goods_photo1').addListener('render', function() {
                      //通过change事件,图片也动态跟踪选择的图片变化
                      Ext.get('goods_photo1').addListener('change',
                                function(field, newValue, oldValue) {
                     //得到选择的图片路径
                      var f = document.getElementById("goods_photo1")
                      var url =document.getElementById('imageBrowse').src = window.URL.createObjectURL(f.files[0]);
                      Ext.Msg.alert('提示','url='+url);
                      var image = Ext.get('imageBrowse').dom;
                      image.src = url;// 覆盖原来的图片
                      image.filters
                           .item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
                      //Ext.Msg.alert('提示','图片已更换');
                            }, this);
                   }, this);
               this.form.findField('goods_photo2').addListener('render', function() {
                   //通过change事件,图片也动态跟踪选择的图片变化
                   Ext.get('goods_photo2').addListener('change',
                           function(field, newValue, oldValue) {
                               //得到选择的图片路径
                               var f = document.getElementById("goods_photo2")
                               var url =document.getElementById('imageBrowse').src = window.URL.createObjectURL(f.files[0]);
                               Ext.Msg.alert('提示','url='+url);
                               var image = Ext.get('imageBrowse').dom;
                               image.src = url;// 覆盖原来的图片
                               image.filters
                                       .item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
                               //Ext.Msg.alert('提示','图片已更换');
                           }, this);
               }, this);
               this.form.findField('goods_photo3').addListener('render', function() {
                   //通过change事件,图片也动态跟踪选择的图片变化
                   Ext.get('goods_photo3').addListener('change',
                           function(field, newValue, oldValue) {
                               //得到选择的图片路径
                               var f = document.getElementById("goods_photo3")
                               var url =document.getElementById('imageBrowse').src = window.URL.createObjectURL(f.files[0]);
                               Ext.Msg.alert('提示','url='+url);
                               var image = Ext.get('imageBrowse').dom;
                               image.src = url;// 覆盖原来的图片
                               image.filters
                                       .item("DXImageTransform.Microsoft.AlphaImageLoader").src = url;
                               //Ext.Msg.alert('提示','图片已更换');
                           }, this);
               }, this);
                }
           },*/
            buttons : [{
                text : "提交",
                name : "submit",
                handler : submit
            },{
                text : "取消",
                name : "reset",
                handler:function(){
                goods_addPanel.getForm().reset();
               }
    }]
        });

   //上传图片到服务器
     function submit() {
         if (goods_addPanel.getForm().isValid()) {
             Ext.getCmp("goods_addPanel").getForm().submit({

                 url: "uploadAction.action",//此处待定!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                 method: "POST",
                 success: function (form, action) {
                     alert("success");
                 },
                 failure: function (form, action) {
                     alert("failure");
                 }
             });
         }
     }
     Ext.apply(this, {
            
            items : [goods_addPanel]
        });
     this.callParent(arguments);
     
 }

});
