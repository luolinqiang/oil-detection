(function(window){
    /*
        yyyy-mm-dd hh:mm:ss  default
        yyyy-mm-dd
        yyyy/mm/dd
        hh:mm:ss
     */
    var formatDate = function(format) {
       function f(s){
           return s.toString().length===1?'0'+s:s;
       }
        var time=[f(this.getHours()),f(this.getMinutes()),f(this.getSeconds())],
            date=[this.getFullYear(),f(this.getMonth()+1),f(this.getDate())];
        switch (format){
            case 'yyyy/mm/dd':
                return date.join('/');
            case 'hh:mm:ss':
                return time.join(':');
            case 'yyyy-mm-dd hh:mm:ss':
                return date.join('-')+' '+time.join(':');
                break;
            case 'yyyy-mm-dd':
            default:
                return date.join('-');
        }
    }
    window.Date.prototype.formatDate = formatDate;

    Request = {
        QueryString : function(item){
            var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)","i"));
            return svalue ? svalue[1] : svalue;
        }
    };

})(window);


