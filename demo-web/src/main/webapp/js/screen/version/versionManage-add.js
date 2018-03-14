(function (d) {
    d['okValue'] = '确定';
    d['cancelValue'] = '取消';
    d['title'] = '提示';
    // [more..]
})(art.dialog.defaults);

function save() {
    Toplife.refreshCenterPage('versionManage/edit', jQuery('#addVersionForm').serialize());
}

function checkVersion() {
    var url = "/versionManage/checkVersion";
    art.confirm('确定要修改版本？', function () {
        $.get(url, jQuery('#addVersionForm').serialize(), function (r) {
            if (r.code == 200) {
                save();
            } else {
                art.alert(r.message);
            }
        });
    }, function () {
    });
}

$(function () {
    $('#backid').click(function () {
        Toplife.refreshCenterPage('/versionManage/getVersion');
    });
});

function toHome(){
    var url = '/versionManage/getVersion?';
    Toplife.refreshCenterPage(url);
}


