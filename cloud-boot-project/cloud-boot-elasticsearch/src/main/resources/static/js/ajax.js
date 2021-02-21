layui.define(["jquery"], function(exports) {
    var $ = layui.jquery;
    exports('request', function(method,param,url){
        var data;
        $.ajax(
            {
                async : false ,
                type : method ,
                url  : url ,
                data : param,
                dataType : "json" ,
                contentType: 'application/json',
                success : function (data1) {
                    data =  data1
                },
                error(xhr,status,error) {
                    alert(status+":"+error);
                },
                timeout : 6000
            }
        );
        return data;
    });
    exports('asyncRequest', function(method,param,url){
        $.ajax(
            {
                async : true ,
                type : method ,
                url  : url ,
                data : param,
                dataType : "json" ,
                contentType: 'application/json',
                success : function (data1) {
                    return data1.code;
                },
                error(xhr,status,error) {
                    alert(status+":"+error);
                },
                timeout : 6000
            }
        )
    });

    exports('get', function(method,url){
        $.ajax(
            {
                async : false ,
                type : method ,
                url  : url ,
                dataType : "json" ,
                contentType: 'application/json',
                success : function (data1) {
                    data =  data1
                },
                error(xhr,status,error) {
                    alert(status+":"+error);
                },
                timeout : 6000
            }
        );
        return data;
    });
});