Ext.define('Seller.store.GoodsTypeStore', {  
  extend : 'Ext.data.Store',  
  storeId : 'goodsTypeStore', 
  fields: ['value', 'type'],
  data : [
          {"value":"new", "type":"全新"},
          {"value":"secondHand", "type":"二手"},
         ]
});  
