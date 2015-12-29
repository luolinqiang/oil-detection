$(function(){
    init();
});
function init(){

    $.ajax({
        url : '/watch/ajax/get?productId=' + itemId ,
        type : 'get',
        dataType : 'json',
        success : function(data){
            if(0 == data.code){
                var watchHtml = ''
                if(data.data){
                    $('#btn-watch-add').hide();
                    $('#btn-watch-del').show();
                    $('#btn-watch-del').attr('watch-id',data.data.id);
                }else{
                    $('#btn-watch-add').show();
                    $('#btn-watch-del').hide();
                }
            }
        },
        error : function(){
            console.log('init error.');
        }
    });
}
/*
 function init(){
 $.ajax({
 url : Wei.up + '/watch/' + itemId + Wei.us,
 type : 'get',
 dataType : 'json',
 success : function(data){
 if(data.success){
 var watchHtml = ''
 if(data.isWatched){
 $('#btn-watch-add').hide();
 $('#btn-watch-del').show();
 watchHtml += '<a href="#removeWatch" onclick="removeWatch()"><i class="am-icon-heart"></i>取消关注'
 }else{
 watchHtml += '<a href="#watch" onclick="watch()"><i class="am-icon-heart-o"></i>关注';
 }
 watchHtml += '（<span id="watch-num">' + data.watchedNum + '</span>）</a>';
 $('#watch').html(watchHtml);
 }
 },
 error : function(){
 console.log('init error.');
 }
 });
 }
 function watch(){
 showLoading();
 $.ajax({
 url : Wei.up + '/watch/add/' + itemId + Wei.us,
 type : 'get',
 dataType : 'json',
 success : function(data){
 if(data.success){
 var watchNum = parseInt($('#watch-num').html());
 $('#watch').html('<a href="#removeWatch" onclick="removeWatch()"><i class="am-icon-heart"></i>取消关注（<span id="watch-num">' + (watchNum + 1) + '</span>）');
 }else{
 if(data.msg == 'noLogin'){
 window.location.href= Wei.up + '/login' + Wei.us;
 }else{
 messageAlert('出错了，请联系系统管理员。');
 console.log(data.msg);
 }
 }
 },
 complete : function(){
 hideLoading();
 },
 error : function(){
 console.log('error');
 }
 });
 }
 function removeWatch(){
 showLoading();
 $.ajax({
 url : Wei.up + '/watch/remove/' + itemId + Wei.us,
 type : 'get',
 dataType : 'json',
 success : function(data){
 if(data.success){
 var watchNum = parseInt($('#watch-num').html());
 $('#watch').html('<a href="#removeWatch" onclick="watch()"><i class="am-icon-heart-o"></i>关注（<span id="watch-num">' + (watchNum - 1) + '</span>）');
 }else{
 if(data.msg == 'noLogin'){
 window.location.href= Wei.up + '/login' + Wei.us;
 }else{
 messageAlert('出错了，请联系系统管理员。');
 console.log(data.msg);
 }
 }
 },
 complete : function(){
 hideLoading();
 },
 error : function(){
 console.log('error');
 }
 });
 }
 */

function watch(){
    showLoading();
    $.ajax({
        url : '/watch/ajax/follow',
        type : 'get',
        dataType : 'json',
        data : {
            productId : itemId,
            supplierId : supplierId
        },
        success : function(data){
            if(0 == data.code){
                $('#btn-watch-add').hide();
                $('#btn-watch-del').show();
                $('#btn-watch-del').attr('watch-id',data.data);
            }else{
                if(1010 == data.code){
                    window.location.href= '/u/login?redirectUrl=/p/item-' + itemId;
                }else{
                    messageAlert('出错了，请联系系统管理员。');
                    console.log(data.msg);
                }
            }
        },
        complete : function(){
            hideLoading();
        },
        error : function(){
            console.log('error');
        }
    });
}
function removeWatch(){
    showLoading();
    $.ajax({
        url :  '/watch/ajax/unFollow',
        type : 'get',
        dataType : 'json',
        data : {
            id : $('#btn-watch-del').attr('watch-id')
        },
        success : function(data){
            if(0 == data.code){
                $('#btn-watch-add').show();
                $('#btn-watch-del').hide();
                $('#btn-watch-del').attr('watch-id', '');
            }else{
                if(1010 == data.code){
                    window.location.href= '/u/login?redirectUrl=/p/item-' + itemId;
                }else{
                    messageAlert('出错了，请联系系统管理员。');
                }
            }
        },
        complete : function(){
            hideLoading();
        },
        error : function(){
            console.log('error');
        }
    });
}