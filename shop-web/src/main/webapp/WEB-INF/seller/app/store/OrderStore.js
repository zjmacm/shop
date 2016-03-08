Ext.define('Seller.store.OrderStore', {
           extend : 'Ext.data.Store',
           storeId : 'OrderStore',
            /**
             * @requires
             */
            requires: [
                'Seller.model.OrderModel'
            ],
            model:'Seller.model.OrderModel',
            proxy : {
                type : 'ajax',
                url : '/seller/app/data/orderInfo.json', //代表localhost 所以可以相应的文件夹 但是如果你不以/开头的话将会在request.url 的文件下
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


            autoLoad : true
        });
