Wei.ListView = function(id,props){
    document.getElementById(id).addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
    props.type = props.type || 'POST';
    props.count = props.count || 10;
    props.animDelay = props.animDelay || 150;
    if(props.anim == undefined){
        props.anim = false;
    }
    var container = $('#' + id).addClass('wei-pagination-wrapper');
    var scroller = $('<div class="wei-pagination-scroller"></div>').appendTo(container);
    var list = $('<ul></ul>').appendTo(scroller);
    var pullUp = $('<div class="wei-pagination-pull-up"><span class="wei-pagination-pull-up-icon"></span><span class="wei-pagination-pull-up-label">上拉加载更多...</span></div>').appendTo(scroller);
    if(props.anim){
        list.attr('data-liffect','page-top');
    }
    var pullUpEl = pullUp.get(0);
    var pullUpOffset = pullUpEl.offsetHeight;
    var lastY = 0;
    var rowCount = 0;
    var init = function(){
        lastY = 0;
        rowCount = 0;
    }
    var loadData = function(){
        if(!pullUp.hasClass('loading')){
            pullUp.addClass('loading');
        }
        pullUp.find('.wei-pagination-pull-up-label').html('加载中...');
        var url = props.url || props.getUrl();
        var data =  props.getData ? props.getData() : {};
        data.start = rowCount;
        data.count = props.count;
        $.ajax({
            url : url,
            type : props.type,
            dataType : 'json',
            data : data,
            success : function(data){
                rowCount += data.items == null ? 0 : data.items.length;
                if(data.items != null && data.items.length > 0){
                    for(var i = 0;i < data.items.length;i++){
                        var item = data.items[i];
                        var html = $(props.getHtml(item,i,data.items,data));
                        list.append(html);
                        //console.log('==============');
                        html.attr("style", "-webkit-animation-delay:" + i * props.animDelay + "ms;" + "-moz-animation-delay:" + (i * props.animDelay) + "ms;"+ "-o-animation-delay:" + (i * props.animDelay) + "ms;"+ "animation-delay:" + (i * props.animDelay) + "ms;");
                        html.addClass("li-play");
                    }
                    /*
                     if(props.anim){
                     $("ul[data-liffect] li").each(function (i) {
                     $(this).attr("style", "-webkit-animation-delay:" + i * props.animDelay + "ms;" + "-moz-animation-delay:" + (i * props.animDelay) + "ms;"+ "-o-animation-delay:" + (i * props.animDelay) + "ms;"+ "animation-delay:" + (i * props.animDelay) + "ms;");
                     if (i == $("ul[data-liffect] li").size() -1) {
                     $("ul[data-liffect]").addClass("play");
                     }
                     });
                     }
                     */
                }
            },
            error : function(){
                if(props.error){
                    props.error();
                }
            },
            complete:function(){
                scroll.refresh();
            }
        });
    }
    var reloadData = function(){
        list.html('');
        init();
        loadData();
    }
    var scroll = new iScroll(id, {
        topOffset: 0,
        hideScrollbar:true,
        onRefresh: function (evt) {
            if(pullUp.hasClass('loading')){
                pullUp.removeClass('loading');
                pullUp.find('.wei-pagination-pull-up-label').html('上拉加载更多...');
            }
        },
        onScrollMove: function (evt) {
            if(this.y > -10){
                if(pullUp.hasClass('flip')){
                    pullUp.removeClass('flip');
                }
                if(pullUp.hasClass('loading')){
                    pullUp.removeClass('loading');
                }
                pullUp.find('.wei-pagination-pull-up-label').html('上拉加载更多...');
            }else{
                if(this.y < (this.maxScrollY - 5) && !pullUp.hasClass('flip')){
                    pullUp.addClass('flip');
                    pullUp.find('.wei-pagination-pull-up-label').html('释放立即刷新...');
                    this.maxScrollY = this.maxScrollY;
                }else if(this.y > (this.maxScrollY + 5) && pullUp.hasClass('flip')){
                    pullUp.removeClass('flip');
                    pullUp.find('.wei-pagination-pull-up-label').html('上拉加载更多...');
                    this.maxScrollY = pullUpOffset;
                }
            }

            lastY = this.y;
        },
        onScrollEnd: function (evt) {
            if(pullUp.hasClass('flip')){
                pullUp.removeClass('flip');
                loadData();
            }
        }
    });
    loadData();
    return {
        load : function(){
            loadData();
        },
        reload : function(){
            reloadData();
        }
    };
}