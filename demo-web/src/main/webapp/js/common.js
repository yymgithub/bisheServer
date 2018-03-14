Toplife = {
    /****
     * toplife系统创建命名空间的方法,
     * @param str  传入的对象, 以 .分割,
     * 例如  创建toplife管理后台优惠券对象  Toplife.namespace("Man.Coupon")
     * @returns {Toplife}
     */
    namespace : function(str) {
        var parts = str.split("."),object = this,i=0,l=0;

        for(i=0,l=parts.length; i<l;i++){
            if(typeof object[parts[i]] === "undefined"){
                object[parts[i]] = {};
            }
            object = object[parts[i]];
        }
        return object;
    } ,

    /***
     * 公共组件的对象
     */
    Common:{},

    /****
     * 管理后台的组件
     */
    Man:{}

}

Toplife.refreshCenterPage  = function(url ,data) {
    $.ajax({
        type: 'POST',
        url: url ,
        data: data ,
        success: function(d){
            $('#centerContainer').html(d);
        }
    });
};

/****
 *
 * @param ob
 *
 * ob.id : eventId 事件触发的 domId   required
 * ob.url : 模态窗口需要展示的内容     required
 * ob.data :  模态窗口请求的数据
 * ob.getData : function 获取动态数据的参数
 * ob.submit : 提交按钮的事件 required
 * ob.autoShow : 自动弹窗的标志位 , 默认 true .如果传递false ,则需要手动调用 show 方法展示
 * ob.close :
 *
 */
Toplife.modal = function(ob) {
    var objectId = ob.id ;
    var modalId ="modalPanelTemplet"+"-"+objectId ;
    var modalOb = $("#" + modalId) ;
    if(!objectId){
        return ;
    } ;
    if(!(ob.autoShow == false)) {
        $('#'+ob.id).unbind().bind('click', function(){
            showPanel() ;
        });
    }
    //如果没有此对象
    if(modalOb.length ==0 ) {
        //单击的事件,用于弹出窗口

        var modalTemplet = $('#modal-templet').html() ;
        modalTemplet = modalTemplet.replace(/modalPanelTemplet/,"modalPanelTemplet"+"-"+objectId) ;
        modalTemplet = modalTemplet.replace(/modalContainer/,"modalContainer"+"-"+objectId) ;
        modalTemplet = modalTemplet.replace(/modal-submit/,"modal-submit"+"-"+objectId) ;
        modalTemplet = $(modalTemplet) ;
        $("#page-wrapper").append(modalTemplet) ;
        /***
         * 添加样式
         */
        if(ob.css){
            $("#modalPanelTemplet"+"-"+objectId).modal().css(ob.css) ;
        }
        /***
         * 绑定确定事件
         */
        if(ob.submit) {
            $("#modal-submit"+"-"+objectId).bind('click',ob.submit) ;
        }
    }
    function showPanel(){
        var data="";
        if(ob.data){ data = ob.data; }
        else if(ob.getData) { data = ob.getData();}
        $.ajax({
            type: 'POST',
            url: ob.url ,
            data: data ,
            success: function(d){
                $('#modalContainer'+"-"+objectId).html(d);
                $('#modalPanelTemplet'+"-"+objectId).modal('show');
            }
        });
    };

    /***
     * 返回的关闭按钮
     */
    function close() {
        $('#modalPanelTemplet'+"-"+objectId).modal('hide');
    }

    //暴露给创建实例的方法
    return {close:close,show:showPanel}
};
/***
 * 模态窗口关闭的方法
 */
Toplife.modal.closeModal = function () {
    $('#modalPanelTemplet').modal('hide');

}
Toplife.modal.refreshModalPage = function(url ,data) {
    //var currentPanel = $("#page-wrapper").find
    $.ajax({
        type: 'POST',
        url: url ,
        data: data ,
        success: function(d){
            var currentPanelBody = $("#page-wrapper").find('div[aria-hidden="false"]').find(".modal-body");
            currentPanelBody.html(d);
        }
    });
};

Toplife.modal.progress = {
    show:function(){
        $('#modalPanelTempletProgress').modal({
                backdrop: 'static',
                keyboard: false
        });
        $('#modalPanelTempletProgress').modal('show');
    },
    close: function() {
        $('#modalPanelTempletProgress').modal('hide');
    }

}
