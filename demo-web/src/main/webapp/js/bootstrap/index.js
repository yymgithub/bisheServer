$(document).ready(function() {

    //菜单跳转的事件

    $('#side-menu').find('a').bind('click' , function(e){

        var ob = $(this) ;

        var url = ob.attr('link') ;

        if(url && url!="#"){
            Pssystem.refreshCenterPage(url , {}) ;
        }

    })

});