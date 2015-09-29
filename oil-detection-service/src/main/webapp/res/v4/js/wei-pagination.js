Wei.pagination = function(props){
    var styleBase = 'position: fixed;bottom:50px;z-index: 1000;min-height: 32px;min-height: 32px;border-radius: 0;';
    var opt = props;
    return {
        prev : null,
        next : null,
        msg  : null,
        pageSize : 10,
        currPage : 1,
        loadingTemplate: 'loading...',
        loadErrorMsg : '<span style="color:red;">加载数据出错.</span>',
        pageTemplate : '<span style="border-radius:1.5rem;padding-left:1.5rem;padding-right:1.5rem;padding-top:0.5rem;padding-bottom:0.5rem;background-color:#efefef;">{0}/{1}</span>',
        init : function(){
            this.prev = $('<div style="' + styleBase + 'left:10px;background-color:#efefef;padding-left:1rem;padding-right:1rem;padding-top:0.5rem;padding-bottom:0.5rem;" id="__wei-pagination-prev"><a href="javascript:void(0);" class="">上一页</a></div>');
            this.next = $('<div style="' + styleBase + 'right:10px;background-color:#efefef;padding-left:1rem;padding-right:1rem;padding-top:0.5rem;padding-bottom:0.5rem;" id="__wei-pagination-next"><a href="javascript:void(0);" class="">下一页</a></div>');
            this.msg = $('<div style="' + styleBase + 'left:100px;right:100px;text-align:center;"></div>');
            var _this = this;
            $(this.prev).on('click',function(){
                console.log(_this.currPage);
                _this.load(_this.currPage - 1);
            });
            $(this.next).on('click',function(){
                _this.load(_this.currPage + 1);
            });
            $('body').append(this.prev);
            $('body').append(this.next);
            $('body').append(this.msg);
            $(this.prev).hide();
            $(this.next).hide();
        },
        _onLoading : function(){
            $(this.prev).hide();
            $(this.next).hide();
            $(this.msg).html(this.loadingTemplate);
        },
        _onError : function(){
            $(this.msg).html(this.loadErrorMsg);
        },
        load : function(page){
            var _this = this;
            var url = props.url || props.getUrl(page,_this.pageSize);
            var data = props.getData ? props.getData(page,_this.pageSize) : {};
            var type = props.type || 'GET';
            if(!data){data = {};}
            var param = {
                url : url,
                type : type,
                dataType : 'json',
                data : data,
                beforeSend : function(){
                    _this._onLoading();
                    if(props.before){
                        props.before();
                    }
                    $(_this.prev).hide();
                    $(_this.next).hide();
                },
                success : function(data){
                    props.callback(data);
                    var currPage = _this.currPage = page;
                    var start = page ? (page - 1) * count : 0;
                    var count = _this.pageSize;
                    var total = props.getTotal ? props.getTotal(data) : data.numRows;
                    var num = props.getNum ? props.getNum(data) : (data.items ? data.items.length : 0);
                    var end = start + num;
                    var pageNum  = Math.ceil(total / count);
                    var pageMsg = _this.pageTemplate;
                    pageMsg = pageMsg.replace('{0}',currPage);
                    pageMsg = pageMsg.replace('{1}',pageNum);
                    pageMsg = pageMsg.replace('{2}',start);
                    pageMsg = pageMsg.replace('{3}',end);
                    pageMsg = pageMsg.replace('{4}',total);
                    $(_this.msg).html(pageMsg);
                    if(currPage >1){
                        $(_this.prev).show();
                    }
                    if(currPage < pageNum){
                        $(_this.next).show();
                    }
                },
                complete : function(){
                    if(props.complete){
                        props.complete();
                    }
                },
                error : function(){
                    _this._onError;
                }
            };
            $.ajax(param);
        }
    }
}