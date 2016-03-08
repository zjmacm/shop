Ext.define('Seller.view.goods.GoodsAdd',{
    extend : 'Ext.form.FormPanel',
    requires: [
        'Seller.store.GoodsCategoryStore',
        'Seller.store.SubGoodsCategoryStore',
        'Seller.store.GoodsTypeStore',
        'Seller.store.TradeMethodStore',
        'Seller.view.goods.SellerTradeInfo'
    ],
    alias: 'widget.goodsAddPanel',
    initComponent: function(){
        var goodsCategoryStore = Ext.create('Seller.store.GoodsCategoryStore');
        var subGoodsCategoryStore = Ext.create('Seller.store.SubGoodsCategoryStore');

        var goodsTypeStore = Ext.create('Seller.store.GoodsTypeStore');
        var tradeMethodStore = Ext.create('Seller.store.TradeMethodStore');
        Ext.apply(this,{
            frame: true,
            title:'商品添加',
            fileUpload : true,
            bobyPadding: '5 5 0',
            bodyStyle: 'padding:20px 100px;' ,

                fieldDefaults:{
                    labelSeparator : ':',
                    labelAlign: 'left',
                    lableWidth: 75,
                    msgTarget: 'qtip',
                    width: 300,
                    blankText:'不能为空'
                },
                defaultType: 'textfield',
                items: [{
                    fieldLabel:"选择商品类别",

                    xtype: "combo",
                    triggerAction: 'all',
                    allowBlank: false,

                    emptyText:"必选",
                    store: goodsCategoryStore,
                    name:"goods_category",
                    hiddenName: "goods_category",//不需提交
                    selectOnFocus: true,
                    displayField: "category",
                    valueField: "id",
                    mode: "local",//以后改为remote
                    forceSelection: true,
                    maxHeight: 200
                },{
                    fieldLabel:"选择分类",

                    xtype: "combo",
                    triggerAction: 'all',
                    allowBlank: false,

                    emptyText:"必选",
                    store: subGoodsCategoryStore,//以下部分获取remote数据时再修改
                    name:"category_id",
                    hiddenName: "category_id",
                    selectOnFocus: true,
                    displayField: "category",
                    valueField: "id",
                    mode: "local",//以后改为remote
                    forceSelection: true,
                    maxHeight: 200
                },{
                    name:"name",
                    hiddenName:"name",
                    fieldLabel:"商品名称",
                    allowBlank: false,

                    emptyText:"必填"

                },{
                    name:"brand",
                    hiddenName:"brand",
                    fieldLabel:"商品品牌",
                    allowBlank: false,

                    emptyText:"必填"

                },{
                    name:"cost",
                    hiddenName:"cost",
                    fieldLabel:"成本价（元）",
                    allowBlank: false,
                    emptyText:"必填"


                },{
                    name:"sale_price",
                    hiddenName:"sale_price",
                    fieldLabel:"销售价（元）",
                    emptyText:"必填",

                    allowBlank: false

                },{
                    name:"discount_price",
                    hiddenName:"discount_price",
                    fieldLabel:"优惠价（元）",
                    emptyText:"一般优惠价都是小于销售价呢哦",
                    allowBlank: false

                },{
                    name:"credit",
                    hiddenName:"credit",
                    fieldLabel:"商品积分"

                },{
                    id:'goods_quantity',
                    name:"quantity",
                    hiddenName:"quantity",
                    fieldLabel:"商品数量",
                    allowBlank: false,

                    emptyText:"必填"
                },{fieldLabel:"商品类型",

                        xtype: "combo",
                        triggerAction: 'all',
                        allowBlank: false,
                        blankText:'不能为空',
                        emptyText:"必选",
                        store: goodsTypeStore,
                        name:"goods_category",
                        hiddenName: "goods_category",//不需提交
                        selectOnFocus: true,
                        displayField: "type",
                        valueField: "value",
                        mode: "local",//以后改为remote
                        forceSelection: true,
                        maxHeight: 200
                },{fieldLabel:"交易方式",

                        xtype: "combo",
                        triggerAction: 'all',
                        allowBlank: false,

                        emptyText:"必选",
                        store: tradeMethodStore,
                        name:"goods_category",
                        hiddenName: "goods_category",
                        selectOnFocus: true,
                        displayField: "type",
                        valueField: "value",
                        mode: "local",//以后改为remote
                        forceSelection: true,
                        maxHeight: 200,
                        listeners:{
                         'select': function (cb, nv, ov) {
                             if(cb.getValue()=="offline"){
                                 //Ext.Msg.alert("提示信息", "选中");
                                 var win = Ext.create('Seller.view.goods.SellerTradeInfo',{
                                     title: " 编辑",
                                     draggable: false,
                                     height: 350,
                                     width: 800,
                                     closable:true,
                                     layout:'fit',
                                     modal: true, //是否模态窗口m，默认为false
                                     resizable: false

                                 });
                                 Ext.getCmp("sellerTradeForm").getForm().load({
                                     url: '/seller/app/data/sellerTradeInfoSucc.json',
                                     method:'GET',
                                     renderer: 'data',
                                     failure: function(form, action) {
                                         Ext.Msg.alert("Load failed", action.result.errorMessage);
                                     }
                                 });
                                 win.show();
                             }else{
                                 Ext.Msg.alert('提示',"功能暂时未开放...")
                             }
                         }
                     }
                    },{
                        xtype:'label',
                        html:"<br>上传商品图片(建议大小比例是4:3):<br>"
                    },{
                        xtype : 'filefield',
                        itemId : 'goodsImg1',
                        fieldLabel : '商品图片1',
                        name: 'files',
                        buttonText: "上传商品"
                    },{
                        xtype : 'filefield',
                        itemId : 'goodsImg2',
                        name:"files",
                        buttonText: "上传商品",
                        fieldLabel : '商品图片2'
                    },{
                        xtype : 'filefield',
                        id: 'goodsImg3',
                        itemId : 'goodsImg3',
                        name : 'files',
                        fieldLabel : '商品图片3',
                        buttonText: "上传商品"

                    },{
                        xtype:'label',
                        html:"<br>预览商品图片:<br>"
                    },{
                        xtype : 'box',
                        name:'image',
                        itemId: 'imageShow_box',
                        id : 'imageBrowse',
                        autoEl : {

                        tag : 'img',
                        type : 'image',
                        src : Ext.BLANK_IMAGE_URL,
                        style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',
                        complete : 'off',
                        id : 'imageBrowse'
                        }
                    }],
             listeners : {
             'render' : function() {
             //老子也是醉了，先就这样写吧，三个...
             //
             var uploadField = Ext.ComponentQuery.query('filefield');
             //var uploadField = this.down('textfield[inputType=file]');
                 var imageBox = this.down('box[itemId=imageShow_box]');

                 //遍历数组  第二个参数执行回调函数 (当前元素,当前索引,当前数组(array)) 指定作用域 返回false 立即返回
             Ext.Array.every(uploadField,function(field,index,array){ //遍历数组  第二个参数执行回调函数 (当前元素,当前索引,当前数组(array)) 指定作用域 返回false 立即返回

                 field.addListener('render', function() {
             //通过change事件,图片也动态跟踪选择的图片变化
                field.addListener('change',
             function(field, newValue, oldValue) {
             //得到上传文件的Field
                var path = field.getValue();
                 Ext.Msg.alert(path);
                 if(path!=null && !Ext.isEmpty(path)) {
                     var url = "file://" + path;
                 }

              if(Ext.isIE){
                  var image = imageBox.getEl().dom;
                  image.src = Ext.BLANK_IMAGE_URL;
                  image.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src=url;
              }else {
                  var file = field.inputEl.dom.files;

                  var image = imageBox.getEl().dom;
                  image.src = window.URL.createObjectURL(url);
              }

             }, this);
             }, this);
                 return true;
             });
             }
             },
        buttons: [{
            text: '保存',
            action:'save',
            type:'submit'
        },{
            text: '取消',
            action: 'cancel'
            }
        ]


    });
        this.callParent(arguments);
    }

});
