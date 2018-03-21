//创建地区的公共组件
PsSystem.namespace("Common.Component") ;

/*****
 * 地址信息
 * @param 需要传递的
 * @constructor
 */

$.extend(PsSystem.Common.Component ,{
    setJsonpData : function (data) {
        PsSystem.Common.Component.JsonpData = data ;
    },
    getJsonpData: function () {
        return PsSystem.Common.Component.JsonpData ;
    },
    setCurrentAreaSelId : function (selId) {
        PsSystem.Common.Component.selId = selId ;
    },
    getCurrentAreaSelId : function () {
        return PsSystem.Common.Component.selId ;
    }
})


/*****
 *
 * @param ob
 *    ob.domId
 * @returns {{getArea: getArea}}
 * @constructor
 */
PsSystem.Common.Component.Area =  function(ob){

    var dd = new PsSystem.Common.Component.Area.createAreaComponent(ob.domId) ;


    function getArea (domId) {

        var returnOb = {} ;
        var selArray = $("#"+domId).find("select");

        for(var i=0 ; i < selArray.length ; i++) {
            var selOb = $(selArray[i]);

            if(selOb.data("level") ==1) {
                returnOb.provinceId = selOb.val() ;
            }else if(selOb.data("level") == 2) {
                returnOb.cityId = selOb.val() ;
            }else if(selOb.data("level") == 3) {
                returnOb.countyId = selOb.val() ;
            }
        }

        return returnOb ;

    }

    return {getArea : getArea}



}

/****
 * 根据divId 创建
 * @param domId
 */
PsSystem.Common.Component.Area.createAreaComponent = function(domId) {

    //如果domId 存在
    if(domId) {
        var area1Id = "PsSystem-Area-"+domId+"-1",area2Id = "PsSystem-Area-"+domId+"-2",area3Id = "PsSystem-Area-"+domId+"-3",area4Id = "PsSystem-Area-"+domId+"-4";
        //
        var domOb = $("#"+domId) ;

        //创建一级的区域的下拉框
        //var id = (this.areaData)["area1Id"] ;
        //domOb.append(this.Dom.createSelect(id))

        //设置一级的下拉框
        var selectProvince = this.Dom.createSelect(area1Id , 1);
        selectProvince.bind("change" ,{areaOb:this}, this.Event.childAreaEvent ) ;
        domOb.append(selectProvince) ;
        this.Dom.createOption(area1Id , this.Data.province , area1Id );

        //设置二级下拉框
        var selectCity = this.Dom.createSelect(area2Id , 2);
        selectCity.bind("change" ,{areaOb:this}, this.Event.childAreaEvent ) ;
        domOb.append(selectCity) ;

        //设置三级下拉框
        var selectCounty = this.Dom.createSelect(area3Id , 3);
        selectCounty.bind("change" ,{areaOb:this}, this.Event.childAreaEvent ) ;
        domOb.append(selectCounty) ;
//        //设置四级下拉框
//        var selectTown = this.Dom.createSelect(area4Id);
//        selectTown.bind("change" ,{areaOb:this}, this.Event.childAreaEvent ) ;
//        domOb.append(selectTown) ;
    }


}





/****
 * 事件
 * @type {{}}
 */
PsSystem.Common.Component.Area.createAreaComponent.prototype.Event ={
    childAreaEvent : function(a) {
        var areaOb = a.data.areaOb ;
        var selectOb = $(this)

        if(selectOb.data("level") <=3 ) {
            var ob = areaOb.Data.getDataByFid( selectOb) ;

            PsSystem.Common.Component.setCurrentAreaSelId(selectOb.attr("id")) ; //设置id

            selectOb.bind("jsonpCallback" , function(){
                areaOb.Dom.createOption(selectOb.next().attr("id") ,
                    PsSystem.Common.Component.getJsonpData());
            })
        }
    }
}

PsSystem.Common.Component.Area.createAreaComponent.prototype.Dom ={
    /***
     * 创建下拉框
     * @param  选择下拉框的id
     */
    createSelect : function (selectId , level) {
        var pSel = $("<select>") ;
        pSel.attr("id" , selectId); //设置id
        pSel.data("level",level) ;//设置级别
        pSel.css({
            "margin-right":"10px"
        });
        return pSel;
    },

    /****
     * 创建下拉框 选项
     * @param selectId
     * @param data   [{id:1,name:"北京"} , {id:2,name:"上海"}]
     */
    createOption : function(selectId , data , v){
        var pDom = $("#" + selectId) ;
        pDom.empty();
        pDom.append('<option value="">请选择</option>');
        for(var i = 0 ; i < data.length ; i++) {
            if(v){
                if(v == data[i].id) {
                    pDom.append('<option selected value="'+data[i].id+'">'+data[i].name+'</option>');
                    continue;
                }
            }
            pDom.append('<option  value="'+data[i].id+'">'+data[i].name+'</option>');
        }

    }
}


/****
 * 数据
 * @type {{}}
 */
PsSystem.Common.Component.Area.createAreaComponent.prototype.Data = {

    //省市死数据
    province : [{id:1,name:"北京"},{id:2,name:"上海"},{id:3,name:"天津"},{id:4,name:"重庆"},{id:5,name:"河北"},{id:6,name:"山西"},{id:7,name:"河南"},{id:8,name:"辽宁"},{id:9,name:"吉林"},{id:10,name:"黑龙江"},{id:11,name:"内蒙古"},{id:12,name:"江苏"},{id:13,name:"山东"},{id:14,name:"安徽"},{id:15,name:"浙江"},{id:16,name:"福建"},{id:17,name:"湖北"},{id:18,name:"湖南"},{id:19,name:"广东"},{id:20,name:"广西"},{id:21,name:"江西"},{id:22,name:"四川"},{id:23,name:"海南"},{id:24,name:"贵州"},{id:25,name:"云南"},{id:26,name:"西藏"},{id:27,name:"陕西"},{id:28,name:"甘肃"},{id:29,name:"青海"},{id:30,name:"宁夏"},{id:31,name:"新疆"},{id:32,name:"台湾"},{id:42,name:"香港"},{id:43,name:"澳门"}],

    /***
     * 根据 上级id 获取信息
     * @param fid
     */
    getDataByFid : function (selOb ){
        $.ajax({
            url: "http://d.jd.com/area/get?fid="+selOb.val(),
            dataType: 'jsonp',
            jsonpCallback: "PsSystem.Common.Component.JsonpCallBack",
            cache: false ,
            success : function() {
                debugger
            }

        });
    }
}

/****
 * 公共组件设置jsonp全局变量
 * @param result
 * @constructor
 */
PsSystem.Common.Component.JsonpCallBack  = function(result) {
    PsSystem.Common.Component.setJsonpData(result) ;
    $("#"+PsSystem.Common.Component.getCurrentAreaSelId()).trigger("jsonpCallback") ;

}
