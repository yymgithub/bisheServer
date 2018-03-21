PsSystem.namespace("Common.Component") ;
PsSystem.Common.Component.cascadeSelector ={
	init : function(options){
		this.data.initData(options);
		this.data.__proto__ = this;
		this.dom.__proto__ = this.data;
		this.event.__proto__ = this.data;
		_opt = this.data._opt;
	    //初始化填充第一个select
	    this.event.dochange();
        var cs = this;
	    return $(_opt.selector).each(function(index, ci) {
	        //绑定change事件
	        $(this).on('change',{cs:cs,index:index}, cs.event.changeFunc);
	    });
	},
	data:{
		initData:function(options){
			var _opt = {
		        url: options.url || '', //请求地址，由于是级联数据，所以要求url有参数，以“=”结尾,本地数据情况下不用传递
		        start: options.start || '0', //请求参数的初始值，默认为0
		        selector: options.selector || alert('请指定选择器:selector'), //选择器，选择器的排列顺序代表了层级关系，必须传递
		        filter: options.filter, //过滤后台返回的数据的函数，参数：1、当前附加到select的数据项；2、当前附加到的select的层级
		        valueName: options.valueName || 'id', //options的value在返回数据项中的属性定义
		        descName: options.descName || 'name', //options的显示项在返回数据项中的属性定义
		        change:options.change,//select的change事件触发
		        dataSource:options.dataSource || 'local',//数据来源类型，默认本地
		        dataType:options.dataType || 'json',
		        datas:options.datas || [],//本地数组
		        childrenName:options.childrenName||'children',
		        ajaxType:options.ajaxType || 'get',
		        jsonpCallBack:options.jsonpCallBack//jsonp方式的回调，当前只有回传数据的功能，发生在所有数据填充操作之前
		    }
		    var vurl = _opt.url;
		    if(_opt.dataSource == 'remote' && vurl.substring(vurl.length-1,vurl.length) != '='){
		        alert("请求级联数据后台需有参数，请填写正确格式的url！");
		        return false;
		    }
		    this._opt = _opt;
		    //所有的select对象
		    this.items = $(_opt.selector);
		    this.selectedItem;//当前选中项
		    this.filledItem = this.items[0];
		    this.levelSelected = 0;
		    this.levelFilling = 1;
		    this.isInitial = true;
		},
        province : [{id:0,name:'全国'},{id:1,name:"北京"},{id:2,name:"上海"},{id:3,name:"天津"},{id:4,name:"重庆"},{id:5,name:"河北"},{id:6,name:"山西"},{id:7,name:"河南"},{id:8,name:"辽宁"},{id:9,name:"吉林"},{id:10,name:"黑龙江"},{id:11,name:"内蒙古"},{id:12,name:"江苏"},{id:13,name:"山东"},{id:14,name:"安徽"},{id:15,name:"浙江"},{id:16,name:"福建"},{id:17,name:"湖北"},{id:18,name:"湖南"},{id:19,name:"广东"},{id:20,name:"广西"},{id:21,name:"江西"},{id:22,name:"四川"},{id:23,name:"海南"},{id:24,name:"贵州"},{id:25,name:"云南"},{id:26,name:"西藏"},{id:27,name:"陕西"},{id:28,name:"甘肃"},{id:29,name:"青海"},{id:30,name:"宁夏"},{id:31,name:"新疆"},{id:32,name:"台湾"},{id:42,name:"香港"},{id:43,name:"澳门"}],
        /**
         * 数据指向指定的select，如果不指定index，指向下一个
         */
        pointTo:function(index){
            var items = this.items;
            var _opt = this._opt;
            if(index || index == 0){
                if(index < items.length){
                    if($(items[index]).val()=='')index = index - 1;
                    this.levelSelected = index + 1;
                    this.selectedItem = items[index];
                    if($.isFunction(_opt.change)){
                        var cbl = this.levelSelected;
                        if(cbl<=0)cbl = '';
                        var cr = _opt.change(this.selectedItem,cbl);
                        if(cr === false)return false;
                    }
                    if(index != this.items.length-1){
                        this.levelFilling = index + 2;
                        this.filledItem = items[index+1];
                    }else{
                        this.levelFilling = null;
                        this.filledItem = null;
                    }
                    return true;
                }
                return false;
            }else{
                this.selectedItem = items[this.levelSelected++];
                if($.isFunction(_opt.change)){
                	var cr = _opt.change(this.selectedItem,this.levelSelected);
                	if(cr === false)return false;
                }
                if(this.levelSelected < items.length){
                    this.levelFilling = this.levelSelected + 1;
                    this.filledItem = items[this.levelSelected];
                    return true;
                }else{
                    this.levelFilling = null;
                    this.filledItem = null;
                    return false;
                }
            }
        },
		ajax: function() {
            var _opt = this._opt;
            var url = _opt.url;
            if(this.selectedItem){
                url += $(this.selectedItem).val();
            }else{
                url += _opt.start;
            }
	        var options = {
	            url: url, //请求地址
	            type:_opt.ajaxType,
	            dataType: _opt.dataType,
	            async: false,
	            cache: true
	        };
	        if(_opt.dataType == 'json'){
	            options.success = this.dom.doFillSelect;
	        }else if(_opt.dataType == 'jsonp'){
                options.context = this;
                options.success = this.event.ajaxSuccessFunc;
	        }else{
	            alert('当前不支持' + _opt.dataType + '返回数据类型！');
	        }
	        $.ajax(options);
	    }
	},
	event:{
        /**
         * 每一个(除了最后一个)select绑定的change事件：填充它的下一级select标签并向调用方回传数据
         * 如果是最后一个select，仅有回调功能，以向调用方回传当前选择的值和层级，退出
         */
	    changeFunc:function(event) {
            var cs = event.data.cs;
            if(cs.data.isInitial)
                cs.data.isInitial = false;
            var index = event.data.index;
            if(!cs.data.pointTo(index))return;
            //选择第一个select为“请选择”之后清空所有的select，返回
	        if(cs.data.levelSelected==0 && $(cs.data.selectedItem).val()==''){
	            cs.dom.clear();
	            return;
	        }
	        //填充下一级select标签
	        cs.event.dochange();
	    },
        ajaxSuccessFunc:function(results){
            this.data.datas = results;
            this.dom.doFillSelect();
        },
        /**
         * 实际的change实际执行函数
         */
        dochange : function(){
            var _opt = this._opt;
            var items = this.items;
            var levelSelected = this.levelSelected;
            if(_opt.dataSource == 'remote'){//数据源为后台
                if(levelSelected == 0){
                    this.data.datas = this.data.province;
                    this.dom.doFillSelect();
                }else{
                    this.data.ajax();
                }
            }else if(_opt.dataSource == 'local'){//数据源为本地数据
                if(levelSelected == 0){
                    this.data.datas = _opt.datas;
                    this.dom.doFillSelect();
                    return;
                }
                var sdata = _opt.datas;
                //根据各级被选中的option获取当前操作的select包含的children数据
                for(var i=0;i<levelSelected;i++){
                    var item = items[i];
                    sdata = sdata[$(item).val()];
                    if(sdata && sdata.hasOwnProperty(_opt.childrenName)){
                        sdata = sdata[_opt.childrenName];
                    } else{//如果没有children属性清空下一项select及之后的select
                        this.dom.clear();
                        return;
                    }
                }
                //将children数据填充到下一级select中
                this.data.datas = sdata;
                this.dom.doFillSelect();
            }
        }
	},
	dom:{
        /**
         * 清空select
         */
		clear:function(){
	        var _item = $(this.filledItem);
            var items = this.items;
            var levelFilling = this.levelFilling;
	        _item.empty();//清空当前项
	        _item.append('<option value="">请选择</option>');
	        if((/^(\+|-)?\d+$/.test( levelFilling )) && levelFilling>=0){
	            items.each(function(index,select){
	                if(index+1 > levelFilling){//当前项之后的项
	                    var _select = $(select);
	                    _select.empty();
	                    _select.append('<option value="">请选择</option>');
	                }
	            });
	        }
	    },
        /**
         * 向结点添加option
         */
        doFillSelect:function() {
        	var cs = this;
            var results = cs.datas;
            var _opt = cs._opt;
            var levelFilling = this.levelFilling;
            if(_opt.jsonpCallBack){
                try{
                    _opt.jsonpCallBack(results,levelFilling);//回传数据
                }catch(e){
                    console.error(e);
                }
            }
            this.dom.clear();
            var pDom = $(cs.filledItem);
            var isInitial = cs.isInitial;
            $.each(results,function(index,result){
                var filterRet;
                if ($.isFunction(_opt.filter))
                    if (!(filterRet = _opt.filter(result,levelFilling))) return;
                var optValue;
                if(_opt.dataSource == 'local'){
                    if(!result.hasOwnProperty(_opt.valueName))optValue=index;
                    else optValue=result[_opt.valueName];
                }else{
                    optValue = result[_opt.valueName];
                }
                if(!optValue && optValue!=0){
                    console.error('数据错误！请联系管理员！')
                    return;
                }
                if($.isPlainObject(filterRet) && filterRet.hasOwnProperty('checked')
                    && (filterRet.checked==true || filterRet.checked=='checked') && isInitial){
                    pDom.append('<option value="' + optValue + '" selected>' + result[_opt.descName] + '</option>');
                    if(cs.data.pointTo()){
                        cs.event.dochange();
                    }
                }else{
                    pDom.append('<option value="' + optValue + '">' + result[_opt.descName] + '</option>');
                }
            });
        }
	}
}