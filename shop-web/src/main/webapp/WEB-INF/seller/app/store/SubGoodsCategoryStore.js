Ext.define('Seller.store.SubGoodsCategoryStore', {
    extend : 'Seller.store.GoodsCategoryStore',
    storeId : 'subGoodsCategoryStore',
    proxy : {
        type : 'ajax',
        url : '/seller/app/data/subGoodsCategory.json', //代表localhost 所以可以相应的文件夹 但是如果你不以/开头的话将会在request.url 的文件下
        reader: {
            type: 'json',
            successProperty: 'success',
            root:'data'
        }
    },
    autoLoad: true
});
