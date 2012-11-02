var stringify_aoData = function (aoData) {
    var o = {};
    var modifiers = ['mDataProp_', 'sSearch_', 'iSortCol_', 'bSortable_', 'bRegex_', 'bSearchable_', 'sSortDir_'];
    jQuery.each(aoData, function(idx,obj) {
        if (obj.name) {
            for (var i=0; i < modifiers.length; i++) {
                if (obj.name.substring(0, modifiers[i].length) == modifiers[i]) {
                    var index = parseInt(obj.name.substring(modifiers[i].length));
                    var key = 'a' + modifiers[i].substring(0, modifiers[i].length-1);
                    if (!o[key]) {
                        o[key] = [];
                    }
                    /*console.log('index=' + index);*/
                    o[key][index] = obj.value;
                    //console.log(key + ".push(" + obj.value + ")");
                    return;
                }
            }
            //console.log(obj.name+"=" + obj.value);
            o[obj.name] = obj.value;
        }
        else {
            o[idx] = obj;
        }
    });
    return JSON.stringify(o);
};