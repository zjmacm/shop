Ext.define('Seller.store.MessageStore', {
           extend : 'Ext.data.Store',
           storeId : 'MessageStore',
            /**
             * @requires
             */
            requires: [
                'Seller.model.MessageModel'
            ],
            model:'Seller.model.MessageModel',
            proxy : {
                type : 'ajax',
                url : '/seller/app/data/messageInfo.json', //代表localhost 所以可以相应的文件夹 但是如果你不以/开头的话将会在request.url 的文件下
                 reader: {
                    type: 'json',
                    successProperty: 'success',
                    root:'data',
                    totalProperty: 'total'
                  }
            },


             sorters: [{
                 property: 'buyername',
                 direction: 'DESC'
             }, {
                 property: 'number',
                 direction: 'ASC'
             }],

                    pageSize:2, // 每页的条目数量
                    params : {
                        start : 0,
                        limit : 2
                    }  ,

             autoLoad: {start: 0, limit: 2}
            // autoLoad : true
        });
/*
function downLoad(){//为了规范化,还是写个函数吧.
    var down = grid.getSelections();//获得选项.

    var pageCount = ds.getCount();//得到当前页面数据总和
    var allCount  = ds.getTotalCount();//得到所有数据总和

    if (down.length == 0 && selectedRow != null){//这里没有选项的时候才用这个方法,
        if(allCount > pageSize){//这里只是判断一下,总量是否大于分页大小,如果大于分页,才给出选项导出当前页的选项.
            //这里也是为了规范话,页面看上去一致,所以就用了和EXT整体统一的EXT消息框
            var confir1 = Ext.MessageBox.confirm('导出选择提示框','选择"是"则导出所有的数据,选择"否"则导出当前页的数据!',onButton);//选择是则导出选中的行,否则就导出所有行.
        }else{//不大于此的时候就直接全部导出.
            window.location = '<%=webapp%>/servlet/ServerAttr?action=download&down1=svrId&downn=svrId'';
        }
    }else{//有选项的时候,就另外一种判断了.
        var confir2 = Ext.MessageBox.confirm('导出选择提示框','选择"是"则导出所有的数据,选择"否"则导出你所选中的数据!',onButton2);
    }

}
*/