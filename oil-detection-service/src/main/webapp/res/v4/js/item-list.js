function initItemList(container,template,paramCallback){
    var container = $('#' + container);
    var page = Wei.pagination({
        getUrl : function(page,count){
            return Wei.up + '/search/data' + Wei.us + '?range=100&start=' + ((page - 1) * count) + '&count=' + count + '&' + paramCallback();
        },
        before : function(){
            container.html('<li class="am-list-item-desced">加载中...</li>');
        },
        callback : function(data){
            container.html('');
            if(data.items.length > 0){
                for(var i = 0;i < data.items.length;i++){
                    var item = data.items[i];
                    var logoUrl = item.itemImgUrl ? item.itemImgUrl : '';
                    if(logoUrl == null || logoUrl == ''){
                        logoUrl = Wei.img.noPic;
                    }
                    var html = template;
                    html = html.replace(/{{id}}/g,item.itemId);
                    html = html.replace(/{{imageUrl}}/g,logoUrl);

                    var shopLogoUrl = item.shopLogoUrl ? item.shopLogoUrl : '';
                    if(shopLogoUrl == null || shopLogoUrl == ''){
                        shopLogoUrl = Wei.img.noPic;
                    }

                    html = html.replace(/{{shopLogoUrl}}/g,shopLogoUrl);

                    var titleAlt = item.itemName;
                    if(titleAlt.length > 20){
                        titleAlt = titleAlt.substring(0,18) + '...';
                    }
                    html = html.replace(/{{updateTime}}/g,item.updateTime);
                    html = html.replace(/{{titleAlt}}/g,titleAlt);
                    html = html.replace(/{{title}}/g,item.itemName);
                    html = html.replace(/{{pirce}}/g,item.minPrice);
                    html = html.replace(/{{shopName}}/g,item.shopName);
                    html = html.replace(/{{itemNum}}/g,item.itemNum);
                    html = html.replace(/{{sd}}/g,((item.sd == null) ? '' : item.sd));
                    html = html.replace(/{{md}}/g,((item.md == null) ? '' : item.md));
                    html = html.replace(/{{wan}}/g,((item.wan == null) ? '' : item.wan));
                    html = html.replace(/{{liu}}/g,((item.liu == null) ? '' : item.liu));
                    html = $(html);
                    container.append(html);
                }
                $("ul[data-liffect] li").each(function (i) {
                    $(this).attr("style", "-webkit-animation-delay:" + i * 100 + "ms;" + "-moz-animation-delay:" + (i * 100) + "ms;"+ "-o-animation-delay:" + (i * 100) + "ms;"+ "animation-delay:" + (i * 100) + "ms;");
                    if (i == $("ul[data-liffect] li").size() -1) {
                        $("ul[data-liffect]").addClass("play");
                    }
                });
            }else{
                container.html('<li class="am-list-item-desced">没有更多的数据.</li>');
            }
        }
    });
    page.init();
    return page;
}