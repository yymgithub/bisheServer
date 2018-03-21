$(document).ready(function(){
	var t = '[data-toggle="query"],[data-toggle="center-redirect-without-params"],[data-toggle="ui-modify"],[data-toggle="delete"],[data-toggle="ui-detail"],[data-toggle="ui-add"],[data-toggle="save"],[data-toggle="enable"]';
	$(document).on('click', t, function(e) {
		var $t = $(this);
		var toggle = $t.attr('data-toggle');
		var fn = $t.attr('data-fn');
		var form = $t.attr('data-form') || '#query-form';
		var url = $t.attr('href');
		var callbackurl = $t.attr('data-callback-url');
		var callback = $t.attr('call-back');
		var paramsCallBack = $t.attr('data-paramsCallBack');
		if(toggle == 'query'){
			if(callback){
				var cbf = eval(callback);
				var cbfr = cbf();
				if(!cbfr)return;
			}
            Pssystem.refreshCenterPage(url , $(form).serialize());
		}else if(toggle == 'center-redirect-without-params'){
            Pssystem.refreshCenterPage(url , {});
		}else if(toggle == 'ui-add'){
            Pssystem.refreshCenterPage(url , {});
		}else if(toggle == 'ui-modify'){
			app_util.center_redirect_withid(url);
		}else if(toggle == 'delete'){
			var radioPanel = $('input[name="itemRadio"]:checked');
	        var id = radioPanel.val();
	        if(id){
	            if(window.confirm('确定删除选中记录吗?')){
	                $.ajax({
	                    type: 'POST',
	                    url: url ,
	                    data: {'id' : id} ,
	                    success: function(d){
                            Pssystem.refreshCenterPage(callbackurl);
	                    }
	                });
	            }
	        } else{
	            alert('请选择记录!');
	        }
		}else if(toggle == 'enable'){
			var radioPanel = $('input[name="itemRadio"]:checked');
			var id = radioPanel.val();
			if(id){
				$.ajax({
                    type: 'POST',
                    url: url ,
                    data: {'id' : id} ,
                    success: function(d){
                        Pssystem.refreshCenterPage(callbackurl);
                    }
                });
			}else{
				alert('请选择记录！');
			}
		}else if(toggle == 'ui-detail'){
			app_util.center_redirect_withid(url,paramsCallBack);
		}else if(toggle == 'save'){
			app_util.center_redirect_with_form(form,url);
		}
	});
});