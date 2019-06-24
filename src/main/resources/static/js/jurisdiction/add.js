$(document).ready(function(){

	layui.use(['layer', 'form','table'], function(){
		var layer = layui.layer
		  	,form = layui.form;
	  	  	

  		form.on('submit(add)', function(data){
  		    var that = this;

  		    var accessIds = new Array();
  		    $('.layui-form-checkbox').each(function(){
                if($(this).hasClass('layui-form-checked')){
                   // $(this).siblings('input').val();
                    accessIds.push($(this).siblings('input').val())
                }
            });
            if(accessIds.length == 0){
                layer.msg('请选择权限！');
                return false;
            }
            data.field.accessIds = accessIds;

            // 禁止点击
            $(that).addClass('zq-submit-disabled');
            axios.post("/admin/groupAccess/addGroupAccess", data.field)
                .then(function (response) {
                    console.log(response)
                    //提示信息并跳转页面
                    layer.msg(response.data.message);
                    if (response.data.message == '添加成功') {
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

  		// 权限列表
        $.ajax({
            url: '/admin/groupAccess/accessTreeAll',
            dataType:"JSON",
            success:function(data){
                console.log(data);
                if(data.message == '获取成功'){

                    var res = data.data;
                    // 一级
                    for(let i in res){

                        var item = document.createElement('div');
                        item.setAttribute('class','item');
                        var one = document.createElement('div');
                        one.setAttribute('class','one');
                        var oneInput = document.createElement('input');
                        oneInput.type = 'checkbox';
                        oneInput.name = 'accessIds';
                        oneInput.setAttribute('lay-skin','primary');
                        oneInput.title = res[i].accessName;
                        oneInput.value = res[i].accessId;
                        one.append(oneInput);


                        var erji = res[i].accesses;
                        var two = document.createElement('div');
                        two.setAttribute('class','two');


                        var there = document.createElement('div');
                        there.setAttribute('class','there');
                        if(erji != null){
                            var twoUl = document.createElement('ul');
                            //二级
                        	for(let a in erji){
                        	    var twoLi = document.createElement('li');
								var twoInput = document.createElement('input');
                                twoInput.type = 'checkbox';
                                twoInput.name = 'accessIds';
                                twoInput.setAttribute('lay-skin','primary');
                                twoInput.title = erji[a].accessName;
                                twoInput.value = erji[a].accessId;
                                twoLi.append(twoInput);
                                twoUl.append(twoLi);
                        		var sanji = erji[a].accesses;

                                var thereUl = document.createElement('ul');
                        		if(sanji != null){

                                    // 三级
                        		    for(let b in sanji){

                                        var thereLi = document.createElement('li');
                                        var thereInput = document.createElement('input');
                                        thereInput.type = 'checkbox';
                                        thereInput.name = 'accessIds';
                                        thereInput.setAttribute('lay-skin','primary');
                                        thereInput.title = sanji[b].accessName;
                                        thereInput.value = sanji[b].accessId;
                                        thereLi.append(thereInput);
                                        thereUl.append(thereLi);
                                    }
                                    there.append(thereUl);
                                }else{
                                    thereUl.style.height = '32px';
                                    there.append(thereUl);
                                }


                        	}
                            two.append(twoUl);
                        }

                        item.append(there);
                        item.prepend(two);
                        item.prepend(one);
                        $('.list').append(item);
                    }
                    form.render('checkbox');
                }
            },
            error:function(data){
                console.log("错误！！");
                // window.clearInterval(timer);
            }
        })


	  
	});



});