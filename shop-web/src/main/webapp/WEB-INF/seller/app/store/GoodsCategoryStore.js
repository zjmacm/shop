Ext.define('Seller.store.GoodsCategoryStore', {  
            extend : 'Ext.data.Store',  
            storeId : 'goodsCategoryStore', 
            /**
             * @requires 
             */
            requires: [
                'Seller.model.GoodsCategoryModel'
            ],
            model:'Seller.model.GoodsCategoryModel',
            //this listener to check reader error 
            /* listeners: {
                'load': function(store, records, successful) {
                alert(store.getProxy().getReader().rawData.message);
                    }
              }, */
            proxy : {  
                type : 'ajax',  
                 url : '/seller/app/data/goodsCategory.json', //代表localhost 所以可以相应的文件夹 但是如果你不以/开头的话将会在request.url 的文件下
                 reader: {
                    type: 'json',
                    successProperty: 'success',
                    root:'data'
                 }  
            },  
        autoLoad: true
});  
