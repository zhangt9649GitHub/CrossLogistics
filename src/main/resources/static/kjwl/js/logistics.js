$(function(){
	layui.use('form', function(){
		var form = layui.form;
	});
	
	
	//  查询
	$('.submit').click(function(){
		
		var value = ($('input[name="deliveryNum"]').val()).trim();
		var regu = /^([0-9]+)(-)?([0-9]+)$/;

		var types = $('.types select option:selected').val();
		if(value.match(regu)){

			// 判断调用那个接口
			if(types == 1){
				active.submit(value);
			}else if(types == 2){
				active.sanfang(value);
			}


		}else{
			$('#list .error').show();
			$('#list .error').html('*&nbsp;&nbsp;快递单号格式错误');
		}

	});
	
	// 页面传参
	function getQueryString(name) {
		var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return unescape(r[2]);
			//return decodeURI(r[2]); //解决中文乱码问题
		}
	}

	
	var active = {
        // 快递单号查询
		submit : function(value){


			$('#list .error').hide();
			$('#list').html('<p class="error"></p>');
			
			var url = 'http://121.41.60.180';
			$.ajax({
				url:'/Logistic/getLogisticInfoCross?deliveryNum='+value,
				jsonp:'callback',
				jsonpCallback:"successCallback",
				type:'get',
				success: function(data){
					// 成功
					if(data.code == 0){
						
						// 判断是否为空
                        var res = data.data;
						if(res){
                            active.single(res);
						}else{
							$('.layui-timeline').append('<span class="layui-text">货物尚未到仓！</span>')
							
						}
						
						
					}else if(data.code == 30052){
						$('#list .error').show();
						$('#list .error').html('*&nbsp;&nbsp;快递单号错误');
					}
				},
				error: function(){
					
				}
			})
		},
        // 三方快递单号查询
		sanfang: function (value) {

			$('#list .error').hide();
			$('#list').html('<p class="error"></p>');

			$.ajax({
				url:'/Logistic/getLogisticInfoCrossByThreeNum?tripartiteNumber='+value,
				jsonp:'callback',
				jsonpCallback:"successCallback",
				type:'get',
				success: function(data){
					// 成功
					console.log(data);
					if(data.code == 0){
                        var res = data.data;
						// 判断是否为空
						if(res){
						    if(res[0].numOfBoxes != null){
                                active.multiple(res);
                            }else{

                                active.single(res);
                            }




						}else{
							$('.layui-timeline').append('<span class="layui-text">货物尚未到仓！</span>')

						}


					}else if(data.code == 30052){
						$('#list .error').show();
						$('#list .error').html('*&nbsp;&nbsp;快递单号错误');
					}
				},
				error: function(){

				}
			})

		},
        // 不分箱
        single : function(data){

            $.each(data,function(i,item){
                if(i == 0){
                    var list = document.createElement('ul');
                    list.className = 'layui-timeline';

                    list.innerHTML = '<li class="layui-timeline-item">' +
                        '<i class="layui-icon layui-timeline-axis">&#xe643;</i>'+
                        '<div class="layui-timeline-content layui-text">'+
                        '<h3 class="layui-timeline-title">'+ item.createTime +'</h3>'+
                        '<p>'+ item.operateResult +'</p>'+
                        '</div>'+
                        '</li>';

                    $('#list').append(list);
                }else{
                    $('.layui-timeline').append('<li class="layui-timeline-item">' +
                        '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'+
                        '<div class="layui-timeline-content layui-text">'+
                        '<h3 class="layui-timeline-title">'+ item.createTime +'</h3>'+
                        '<p>'+ item.operateResult +'</p>'+
                        '</div>'+
                        '</li>');
                }

            })
        },
        // 分箱
        multiple : function (data) {
            var numbers = 0;
            $.each(data,function(i,item){

                if(item.numOfBoxes != numbers){
                    numbers = item.numOfBoxes;

                    var className = 'item'+numbers;

                    var list = document.createElement('ul');
                    list.className = 'layui-timeline';
                    list.className += ' '+className;

                    list.innerHTML = '<h1 class="title">第'+item.numOfBoxes+'箱</h1>'+
                        '<li class="layui-timeline-item">' +
                        '<i class="layui-icon layui-timeline-axis">&#xe643;</i>'+
                        '<div class="layui-timeline-content layui-text">'+
                        '<h3 class="layui-timeline-title">'+ item.createTime +'</h3>'+
                        '<p>'+ item.operateResult +'</p>'+
                        '</div>'+
                        '</li>';

                    $('#list').append(list)

                }else{


                    $('.item'+ numbers).append('<li class="layui-timeline-item">' +
                        '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'+
                        '<div class="layui-timeline-content layui-text">'+
                        '<h3 class="layui-timeline-title">'+ item.createTime +'</h3>'+
                        '<p>'+ item.operateResult +'</p>'+
                        '</div>'+
                        '</li>');
                }


            })


        }



		
	};


	// 不分箱货物


	
	// 页面传参调用
	if(getQueryString("deliveryNum")){
		var getParams = getQueryString("deliveryNum");
		$('input[name="deliveryNum"]').val(getParams);
		var types = getQueryString("types");

        $('.types option').each(function (i,item) {
           console.log()
            if($(item).val() == types){
                $(item).prop('selected',true);
            }
        });

		// 判断调用那个接口
		if(types == 1){
			active.submit(getParams);
		}else if(types == 2){
			active.sanfang(getParams);
		}

	}

})