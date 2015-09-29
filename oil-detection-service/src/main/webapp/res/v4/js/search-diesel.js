var cw = document.body.clientWidth;
var ch = document.body.clientHeight;
var mr = cw > ch ? ch : cw;
var total;
var count = 5;
var currPage = 1;
function next(){
    loadData(currPage + 1);
}
function prev(){
    loadData(currPage - 1);
}
function hidePagination(){
    $('#pagination-prev').hide();
    $('#pagination-next').hide();
}
function showPagination(totalPage){
    $('#pagination-prev').hide();
    $('#pagination-next').hide();
    if(totalPage > currPage){
        $('#pagination-next').show();
    }
    if(currPage > 1){
        $('#pagination-prev').show();
    }
}
var href = window.location.href;
var url;
var anchorStr;
var anchors;

$(".roll-sign").css('height', mr + 'px');
$(".roll-sign").css('width', mr + 'px');
var rot = 0;
var prefix = $('.tire').css('-o-transform') ?
    '-o-transform' : ($('.tire').css('-ms-transform') ?
    '-ms-transform' : ($('.tire').css('-moz-transform') ?
    '-moz-transform' : ($('.tire').css('-webkit-transform') ?
    '-webkit-transform' : 'transform')));
var animation = {
    test : 1600
};
var rotate = function(now,init) { /*该方法将被旋转的轮子调用*/
    rot += 2;
    $('.tire').css(prefix, 'rotate(' + rot + 'deg)');
};
var options = { /*将要被jQuery使用的参数*/
    easing : 'linear', /*指定速度，此处只是线性，即为匀速*/
    complete : function() {
        $('#car').animate(animation, options);
    },
    step : rotate
};

function play(now,init){
    var s = (now / init.end);
    $(init.elem).css(prefix, 'scale(' + s + ',' + s + ')');
}
function getRandomInt(start,end){
    var val = Math.round(Math.random() * end);
    while(val < start){
        val = Math.round(Math.random() * end);
    }
    return val;
}
// 显示查询结果
function drawItem(items,total,ind){
    if(ind < total){
        var hIncr = $("#query-param-view-div").height();
        var x = Math.round(Math.random() * (mr - 100) + 50);
        var y = Math.round(Math.random() * (mr - 100) + 50) + hIncr;
        var item = items[ind];
        var logoUrl = item.shopLogoUrl ? item.shopLogoUrl : '';
        if(logoUrl == null || logoUrl == ''){
            logoUrl = Wei.img.noPic;
        }
        //border-radius:2.5rem;圆角CSS
        var html =
            '<div class="item" onclick="onItemClick(this,' + items[ind].itemId + ')" style="transform: scale(0.1,0.1);width:5rem;;height:6.5rem;left:' + x + 'px;top:' + y + 'px;z-index:1000;">' +
                '<div style="width:5rem;height:5rem;">' +
                '<img src="' + logoUrl + '" style="width:100%;height:100%;"/>' +
                '</div>' +
                '<div style="width:5rem;height:1.5rem;overflow:hidden;font-size:1rem;color:#ffffff;text-align:center;">' + items[ind].shopName + '</div>' +
                '</div>';
        var a = $(html).appendTo($('#search-leida-div'));
        $(a).animate({
                test : 100
            },{
                duration : 1500,
                easing : 'linear',
                step : play
            }
        );
        setTimeout(function(){
            drawItem(items,total,(ind + 1))
        },getRandomInt(100,1000));
    }
}

function loadMyData(page){
    var standard = $('#q_standard').val();
    var model = $('#q_model').val();
    var position = $('#q_position').val();
    var queryData = {
        standard : standard,
        model : model,
        position : position,
        type : 'cy',
        range : 1000
    }
    queryData.count = count;
    currPage = page;
    queryData.start = page ? (page - 1) * count : 0;
    $.ajax({
        url : Wei.up + '/search/data' + Wei.us,
        data : queryData,
        dataType : 'json',
        beforeSend:function(){
            $('.item').remove();
            options.complete();
            hidePagination();
            $('#item-list-ul').html('<li class="am-list-item-desced">加载中...</li>');
        },
        success  : function(data){
            showList()
            if(!data.items || data.items.length == 0){
                $('#item-list-ul').html('<li class="am-list-item-desced">没有更多的数据.</li>');
            }else{
                listItem(data.items,data.numRows);
            }
        },
        error    : function(){
            $('#item-list-ul').html('<li class="am-list-item-desced">加载数据出错！</li>');
        }
    });
}
//查询
function loadData(page){

    var standard = $('#q_standard').val();
    var model = $('#q_model').val();
    var position = $('#q_position').val();
    var queryData = {
        standard : standard,
        model : model,
        type : 'cy',
        position : position,
        range : 1000
    }
    queryData.count = count;
    currPage = page;
    queryData.start = page ? (page - 1) * count : 0;
    $.ajax({
        url : Wei.up + '/search/data' + Wei.us,
        data : queryData,
        dataType : 'json',
        beforeSend:function(){
            $('.item').remove();
            options.complete();
            hidePagination();
            $('#item-list-ul').html('<li class="am-list-item-desced">加载中...</li>');
        },
        success  : function(data){
            drawItem(data.items,data.items.length,0);
            setTimeout(function(){
                $("#car").stop();
                showList();
            },3000);
            if(!data.items || data.items.length == 0){
                $('#item-list-ul').html('<li class="am-list-item-desced">没有更多的数据.</li>');
            }else{
                listItem(data.items,data.numRows);
            }
        },
        error    : function(){
            $('#item-list-ul').html('<li class="am-list-item-desced">加载数据出错！</li>');
        }
    });
}
function getLocation(){
    $('#q_position').val('获取中');

    var geo = new jQuery.AMUI.Geolocation({
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 60000
    });
    geo.get().then(function(pos){
        getAddressByLL(pos);
    }, function(error) {
        getAddressByIp();
    });
}
var getAddressByLL = function(pos){
    $.ajax({
        url : Wei.up + '/geo/l2a' + Wei.us + '?lat=' + pos.coords.latitude + "&lng=" + pos.coords.longitude,
        dataType : 'json',
        success  : function(data){
            if(data.success){
                if($('#q_position').val()=='获取中')
                    $('#q_position').val(data.result.province + "-" + data.result.city);
            }else{
                if($('#q_position').val()=='获取中')
                    $('#q_position').val('读取失败');
            }
        },
        error    : function(){
            if($('#q_position').val()=='获取中')
                $('#q_position').val('读取失败');
        }
    });
}
var getAddressByIp = function(){
    $.ajax({
        url : Wei.up + '/geo/li2a' + Wei.us,
        dataType : 'json',
        success  : function(data){
            var d = eval("(" + data + ")");
            if(data.code == 0){
                if($('#q_position').val()=='获取中')
                    $('#q_position').val(data.data.city);
            }else{
                if($('#q_position').val()=='获取中')
                    $('#q_position').val('读取失败');
            }
        },
        error    : function(){
            if($('#q_position').val()=='获取中')
                $('#q_position').val('读取失败');
        }
    });
}

function initMyData(){
    var standard = $('#q_standard').val();
    var model = $('#q_model').val();
    var position = $('#q_position').val();
    if(standard == ''){
        messageAlert('请选择质量标准！');
        return;
    }
    if(model == ''){
        messageAlert('请选择产品型号！');
        return;
    }
    if(position == '' || position == '读取失败' || position == '获取中'){
        messageAlert('请选择收货地址！');
        return;
    }

    $('#search-form-div').hide();
    $('#search-leida-div').hide();
    $('body').addClass('black-bg');
    var queryParam = "";
    queryParam += $("#q_standard").find("option:selected").html() + ";";
    queryParam += $("#q_model").find("option:selected").html() + ";";
    queryParam += $("#q_position").val();
    $('#queryParamView').html(queryParam);
    loadMyData(1);
}
//注册 开始寻油 按钮点击事件
$('#search-btn').click(function(){


    var standard = $('#q_standard').val();
    var model = $('#q_model').val();
    var position = $('#q_position').val();
    if(standard == ''){
        messageAlert('请选择质量标准！');
        return;
    }
    if(model == ''){
        messageAlert('请选择产品型号！');
        return;
    }
    if(position == '' || position == '读取失败' || position == '获取中'){
        messageAlert('请选择收货地址！');
        return;
    }

    $('#search-form-div').hide();
    $('#search-leida-div').show();
    $('body').addClass('black-bg');
    var queryParam = "";
    queryParam += $("#q_standard").find("option:selected").html() + ";";
    queryParam += $("#q_model").find("option:selected").html() + ";";
    queryParam += $("#q_position").val();
    $('#queryParamView').html(queryParam);
    loadData(1);
});
//注册 开始寻油 按钮点击事件
$('#getLocationBtn').click(function(){
    getLocation();
});
$('#select-area-btn').click(function(){
    selectArea(function(str,area){
        $('#q_position').val(str);
    });
});
function onItemClick(node,id){
    window.location.href =  Wei.up + 'item/' + id + Wei.us;
}
var htmlTemplate =
    '<li class="am-list-item-thumb-left list-item card" >' +
        '<div class="am-g">' +
        '<div class="am-u-sm-3 am-list-thumb"  >' +
        '<a href="' + Wei.up + '/item-{{id}}' + Wei.us + '" class=""><img src="{{imageUrl}}" alt="{{titleAlt}}"/></a>' +
        '</div>' +
        '<div class=" am-u-sm-9 am-list-main"  >' +
        '<div class="am-list-item-hd">' +
        '<a href="' + Wei.up + '/item-{{id}}' + Wei.us + '" class=""><div class="am-u-sm-12 text-overflow-ellipsis">{{title}}</div></a>' +
        '</div>' +
        '<div class="am-list-item-text" >' +
        '<div class="am-u-sm-7" style="color:#e87823;font-size:1.5rem;">￥{{pirce}}</div>' +
        '<div class="am-u-sm-5" style="color:#333333;text-align:right;">{{updateTime}}</div>' +
        '<div class="am-u-sm-12" style="">{{shopName}}</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="am-g"  >' +
        '<div class="am-u-sm-4" style="padding-left:0.5rem;padding-right:0;">闪点：{{sd}}</div>' +
        '<div class="am-u-sm-4" style="padding-left:0.5rem;padding-right:0;">密度：{{md}}</div>' +
        '<div class="am-u-sm-4" style="padding-left:0.5rem;padding-right:0;">十六烷值：{{wan}}</div>' +
        '</div>' +
        '</li>';
function anmiPlay(){
    $("ul[data-liffect] li").each(function (i) {
        $(this).attr("style", "-webkit-animation-delay:" + i * 100 + "ms;" + "-moz-animation-delay:" + (i * 100) + "ms;"+ "-o-animation-delay:" + (i * 100) + "ms;"+ "animation-delay:" + (i * 100) + "ms;");
        if (i == $("ul[data-liffect] li").size() -1) {
            $("ul[data-liffect]").addClass("play");
        }
    });
}
/*
 var htmlTemplate =
 '<li class="am-list-item-thumb-left list-item" >' +
 '<a href="' + Wei.up + '/item-{{id}}' + Wei.us + '" class="">' +
 '<div class="am-g">' +
 '<div class="am-u-sm-3 am-list-thumb"  >' +
 '<img src="{{imageUrl}}" alt="{{titleAlt}}"/>' +
 '</div>' +
 '<div class=" am-u-sm-9 am-list-main"  >' +
 '<div class="am-list-item-hd">' +
 '<div class="am-u-sm-12 text-overflow-ellipsis">{{title}}</div>' +
 '</div>' +
 '<div class="am-list-item-text" >' +
 '<div class="am-u-sm-7" style="color:#e87823;font-size:1.5rem;">￥{{pirce}}</div>' +
 '<div class="am-u-sm-5" style="color:#333333;text-align:right;">{{updateTime}}</div>' +
 '<div class="am-u-sm-12" style="">{{shopName}}</div>' +
 '</div>' +
 '</div>' +
 '</div>' +
 '<div class="am-g"  >' +
 '<div class="am-u-sm-4" style="padding-left:0.5rem;padding-right:0;">闪点：{{sd}}</div>' +
 '<div class="am-u-sm-4" style="padding-left:0.5rem;padding-right:0;">密度：{{md}}</div>' +
 '<div class="am-u-sm-4" style="padding-left:0.5rem;padding-right:0;">十六烷值：{{wan}}</div>' +
 '</div>' +
 '</a>' +
 '</li>';
 */
function listItem(items,total){
    $('#item-list-ul').html('');
    for(var i = 0;i < items.length;i++){
        var item = items[i];
        var html = htmlTemplate.replace(/{{id}}/g,item.itemId);
        var imageUrl = item.shopLogoUrl;
        if(imageUrl == null || imageUrl == ''){
            imageUrl = Wei.img.noPic;
        }
        html = html.replace(/{{imageUrl}}/g,imageUrl);
        var titleAlt = item.itemName;
        if(titleAlt.length > 20){
            titleAlt = titleAlt.substring(0,18) + '...';
        }
        html = html.replace(/{{updateTime}}/g,item.updateTime);
        html = html.replace(/{{titleAlt}}/g,titleAlt);
        html = html.replace(/{{title}}/g,titleAlt);
        html = html.replace(/{{pirce}}/g,item.minPrice);
        html = html.replace(/{{shopName}}/g,item.shopName);
        html = html.replace(/{{sd}}/g,((item.sd == null) ? '' : item.sd));
        html = html.replace(/{{md}}/g,((item.md == null) ? '' : item.md));
        html = html.replace(/{{wan}}/g,((item.wan == null) ? '' : item.wan));
        html = html.replace(/{{liu}}/g,((item.liu == null) ? '' : item.liu));
        $('#item-list-ul').append(html);
    }
    totalPage = Math.ceil(total / count);//total % count == 0 ? total / count : total / count + 1;
    showPagination(totalPage);
}
function showSearch(){
    $('#search-form-div').slideDown(500);
    $('#search-list-div').hide();
    $('body').removeClass('black-bg')
}
function showLeida(){
    $('#search-leida-div').slideDown(500);
    $('#search-list-div').hide();
    $('body').addClass('black-bg')
}
function showList(){
    $('#search-leida-div').fadeOut(500,function(){
        anmiPlay();
    });
    $('#search-list-div').show();
    $('body').removeClass('black-bg')
    window.location.href=window.location.href + '#result';
}
if(href.indexOf('#') > 0){
    url = href.substring(0,href.indexOf('#'));
    var anchorStr = href.substr(href.indexOf('#') + 1);
    var anchors = anchorStr.split('#');
    if(anchorStr.indexOf('result')==-1){
        //查询 当前位置
        getLocation();
    }else{
        initMyData();
    }
}else{
    getLocation();
}