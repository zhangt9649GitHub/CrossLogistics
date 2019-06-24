$(document).ready(function(){
    // 富文本编辑器初始化
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.customConfig.uploadImgShowBase64 = true;   // 使用 base64 保存图片
    // 隐藏“网络图片”tab
    editor.customConfig.showLinkImg = false;
    editor.create();


    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table = layui.table;
        
        $.ajax({
            url: '/copyWriter/getCopywritingTypeList',
            dataType: 'JSON',
            success: function (data) {
                console.log(data);
                if(data.data != null){
                    $.each(data.data,function(i,n){
                        $('#hc_select').append(' <option value="'+ n.type +'">'+n.type+'</option>');
                    });

                }
                form.render();
            }
        });

        // 下拉框输入 start
        form.on('select(hc_select)', function (data) {   //选择移交单位 赋值给input框
            console.log(data.value);
            $("#HandoverCompany").val(data.value);
            $("#hc_select").next().find("dl").css({ "display": "none" });

            if(data.value == '转运须知' || data.value == '转运公司介绍'){
                $('.tubiao b').text('');
            }else{
                $('.tubiao b').text('*');
            }
            form.render();
        });

        // $("#HandoverCompany").blur(function(){
        //     form.render();
        // });

        window.search = function () {
            var value = $("#HandoverCompany").val();
            $("#hc_select").val(value);
            form.render();
            $("#hc_select").next().find("dl").css({ "display": "block" });
            var dl = $("#hc_select").next().find("dl").children();
            var j = -1;
            for (var i = 0; i < dl.length; i++) {
                if (dl[i].innerHTML.indexOf(value) <= -1) {
                    dl[i].style.display = "none";
                    j++;
                }
                if (j == dl.length-1) {
                    $("#hc_select").next().find("dl").css({ "display": "none" });
                }
            }

        };
        // 下拉框输入 end

        // 监听表单提交
        form.on('submit(add)', function(data){

            var that = this;


            // 获取图标
            if(data.field.type == '转运须知' || data.field.type == '转运公司介绍'){
                data.field.icon =$('#preview img').attr('src');
                if(data.field.icon =='/static/image/dj.jpg' ){
                    data.field.icon = '';
                }
            }else{
                data.field.icon =$('#preview img').attr('src');
                if(data.field.icon == '/static/image/dj.jpg'){
                    layer.msg('请添加图标');
                    return false;
                }
            }

            //获取特殊内容
            data.field.content = editor.txt.html();                // 获取富文本编辑器内容
            if(data.field.content  == '<p><br></p>'){
                layer.msg('请添写内容');return false;
            }

            // 判断中英文
            if(data.field.language == 'en'){
                if(escape(data.field.cwName).indexOf( "%u" )>=0){
                    layer.msg('名称请输入英文');return false;
                }
            }
            if(data.field.language == 'zh'){
                if(escape(data.field.cwName).indexOf( "%u" )<0){
                    layer.msg('名称请输入中文');return false;
                }
            }

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/copyWriter/insertCopyWriter", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.msg);
                    if (response.data.msg == '添加成功') {
                        setTimeout(function () {
                            window.parent.location.reload(); //刷新父页面
                        }, 1000);
                    }
                    // 取消禁止点击
                    else {
                        $(that).removeClass('zq-submit-disabled');
                    }
                });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


    });


});

