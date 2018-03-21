app_util={
	center_redirect_withid : function(url,paramsCallBack){
		var radioPanel = $('input[name="itemRadio"]:checked');
        var id = radioPanel.val();
        if(id){
        	var allParams;
        	if(paramsCallBack){
        		var pcb = eval(paramsCallBack);
        		allParams = $.extend({"id":id},pcb(id));
        	}else{
        		allParams = {"id":id};
        	}
            Pssystem.refreshCenterPage(url,allParams) ;
        }else{
        	alert('请选择记录!');
        }
	},
	center_redirect_with_form : function(form,url){
		if(jQuery(form).valid()){
            jQuery.ajax({
                type: 'POST',
                url: url ,
                data: jQuery(form).serialize(),
                success: function(d){
                    jQuery('#centerContainer').html(d);
                }
            });
        } ;
	}
}