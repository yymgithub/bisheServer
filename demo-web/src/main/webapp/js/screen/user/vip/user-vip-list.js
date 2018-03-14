userVip={}
userVip.init=function(){
    //查询按钮
    $('#queryUserVip').bind('click' ,function(){
        Toplife.refreshCenterPage('/user/level/vip/list.action' , $("#userVipForm").serialize()) ;
    });

    //添加按钮
    $('#addUserVip').bind('click' ,function(){
        Toplife.refreshCenterPage('/user/level/vip/add.action' , {}) ;
    });

    //删除按钮事件
    $('#deleteUserVip').bind('click' ,function(){
        var radioPanel = $('input[name="userVipRadio"]:checked');
        var ids = radioPanel.val();
        if(ids){
            if(window.confirm('确定删除此用户吗?')){
                $.ajax({
                    type: 'POST',
                    url: '/user/level/vip/del.action' ,
                    data: {'ids' : ids} ,
                    success: function(d){
                        Toplife.refreshCenterPage('/user/level/vip/list.action' ) ;
                    }
                });
            }
        } else{
            alert('请选择商家!');
        }
    });
}

userVip.init() ;
