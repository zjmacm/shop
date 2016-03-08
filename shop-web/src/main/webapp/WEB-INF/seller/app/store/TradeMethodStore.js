Ext.define('Seller.store.TradeMethodStore', {  
  extend : 'Ext.data.Store',  
  storeId : 'tradeMethodStore', 
  fields: ['value', 'type'],
  data : [
          {"value":"offline", "type":"线下"},
          {"value":"online", "type":"线上"},
         ]
});  
