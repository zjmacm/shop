Ext.define('Seller.store.MessageSelectedColumnStore', {
            extend : 'Ext.data.Store',
            storeId : 'messageSelectedColumnStore',
            /**
             * @requires
             */
            requires: [
                'Seller.model.MessageSelectedColumnModel'
            ],
            model:'Seller.model.MessageSelectedColumnModel',
            //this listener to check reader error
            /* listeners: {
                'load': function(store, records, successful) {
                alert(store.getProxy().getReader().rawData.message);
                    }
              }, */


            proxy : {
                type : 'ajax',
                url : '/seller/app/data/messageSelectedColumn.json', //代表localhost 所以可以相应的文件夹 但是如果你不以/开头的话将会在request.url 的文件下
                reader: {
                    type: 'json',
                    successProperty: 'success'
                }
            },
            root : {
                text : 'root',
                id : '-1'
            },
            autoLoad : true,
            sorters : [{
                property : 'text', // 这里需要注意一下,如果排序的属性是id的话，例如7、8、9、10、11，排序后是：10、11、7、8、9
                direction : 'asc' // 它是按照第一数字排序后再按照第二位数字排序，如此类推
            }]


});
