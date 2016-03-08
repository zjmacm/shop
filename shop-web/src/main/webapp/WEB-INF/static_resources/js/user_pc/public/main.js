define(function(require) {
  // var regiStyle= require('../css/style.css#');
  var leftBarAction = require('./left_bar_action');
  var headerAction = require('./header_action');
  var itemAction = require('./item_action');
  var tabsAction = require('../user_personal_page/tabs_action');
  // require('jquery');
	/*seajs.use('../static/register/src/js/calculator', function(main) {
	 alert("use of use");
	});*/
  $(document).ready(function(){
    leftBarAction();
    headerAction();
    itemAction();
    tabsAction();
  });

});