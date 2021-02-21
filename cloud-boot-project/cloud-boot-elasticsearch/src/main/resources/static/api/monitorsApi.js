layui.define(function (exports) {
    exports('updeteMonitors', function(data){
       return layui.request("PUT",data, "/monitor");
    });

    exports('deleteMonitors', function(data){
        return layui.request("DELETE",data, "/monitor");
    });

    exports('groupDate', function(){
        return layui.request("GET",null,"group/date");
    });
});