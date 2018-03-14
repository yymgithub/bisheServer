$(function () {
    $('#addnew').click(function () {
        Toplife.refreshCenterPage('/workerManage/toAdd' );
    });
});
(function (d) {
    d['okValue'] = '确定';
    d['cancelValue'] = '取消';
    d['title'] = '提示';
    // [more..]
})(art.dialog.defaults);
function queryPage(page){
    var current =  new Number($("#queryPage").val());
    var tar =  current+new Number(page);
    $("#queryPage").val(tar);
    queryList();
}
function toUpdate(id){
    var url = '/workerManage/toUpdate?id='+id;
    Toplife.refreshCenterPage(url);
}
function queryList(){
    Toplife.refreshCenterPage('/workerManage/list' , jQuery('#queryForm').serialize()) ;
}
function updateState(id,state){
    var url = "/workerManage/updateState?id="+id+"&status="+state;
    art.confirm('确定要修改状态？', function(){
        $.get(url,{},function(r){
            if(r.code == 200){
                queryList();
            }else{
                art.alert(r.message);
            }
        });
    },function(){});
}
function del(id){
    var url = "/workerManage/delete?id="+id;
    art.confirm('确定要删除<'+id+'>？', function(){
        $.get(url,{},function(r){
            if(r.code == 200){
                queryList();
            }else{
                art.alert(r.message);
            }
        });
    },function(){});
}
function execute(name){
    var url = "/workerManage/execute?name="+name;
    art.confirm('确定要手动执行<'+name+'>？', function(){
        $.get(url,{},function(r){
            if(r.code == 200){
                art.alert(r.message);
            }else{
                art.alert(r.message);
            }
        });
    },function(){});
}

