$(function () {
    $('#editVersion').click(function () {
        Toplife.refreshCenterPage('/versionManage/add');
    });
});

$(function () {
    $('#historyList').click(function () {
        Toplife.refreshCenterPage('/versionManage/historyVersion');
    });
});
