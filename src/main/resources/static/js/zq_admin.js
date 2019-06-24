$(document).ready(function(){

	layui.use(['layer', 'element','upload'], function(){
		var layer = layui.layer
		  ,element = layui.element
		  ,upload = layui.upload;

	  	// 监听导航点击效果
	  	element.on('nav(filter)', function(elem){
		  	console.log(elem); //得到当前点击的DOM对象

		  	// 判断是否还有二级菜单
		  	if(elem.siblings('.layui-nav-child').length != 0){
		  		return false;
		  	}
				
		  	var index = elem.attr('data-id');
		  	var title = elem.text();
		  	var href = elem.attr('data-href');
		  	var iframeLength = $('.zq-iframe').length;
		  	console.log(index)
		  	for(var i = 0; i < iframeLength;i++){
		  		if($('.zq-iframe').eq(i).attr('tab-id') == index){
		  			tab.tabChange(index);
		  			return false;
		  		}
		  	}


		  	tab.tabAdd(title,href,index);
		  	tab.tabChange(index);
		});

	  	// 选项卡触发事件
		var tab = {
			tabAdd: function(title,url,id){
	          //新增一个Tab项
	          element.tabAdd('xbs-tab', {
	            title: title 
	            ,content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="zq-iframe"></iframe>'
	            ,id: id
	          })
	        }
	        ,tabChange: function(id){
	          //切换到指定Tab项
	          element.tabChange('xbs-tab', id); //切换到：用户管理
	        }
		};

		// 监听选项卡切换
		element.on('tab(xbs-tab)', function(data){
		  	// console.log(this); //当前Tab标题所在的原始DOM元素
		  	// console.log(data.index); //得到当前Tab的所在下标
		  	// console.log(data.elem); //得到当前的Tab大容器
		  	var aLength = $('.left-nav a').length;
		  	var layId = this.getAttribute('lay-id');
			
			// 对应左边导航显示
		  	for(let i = 0; i<aLength;i++){

		  		if($('.left-nav a').eq(i).attr('data-id') == layId){
		  		
		  			$('.left-nav dd').removeClass('layui-this');
		  			$('.left-nav li').removeClass('layui-this');
		  			$('.left-nav a').eq(i).parent().addClass('layui-this');
		  		}
		  	}
		});

		// 一键关闭
		$('.site-demo-active').on('click', function(){
			var othis = $(this), type = othis.data('type');
			active[type] ? active[type].call(this, othis) : '';
		});

		var active = {
			closeOtherTabs : function () {

				$('.tab .layui-tab-title li').each(function () {


						if(!($(this).hasClass('layui-this'))){

							element.tabDelete('xbs-tab', $(this).attr('lay-id'));
						}
				});
				// element.tabDelete('xbs-tab', '0');
			},
			closeAllTabs : function () {
				$('.tab .layui-tab-title li').each(function () {

					element.tabDelete('xbs-tab', $(this).attr('lay-id'));

				});
			}
		}
	  
	});



	// 监听左菜单展开
	$('.container .left-open i').click(function(event) {
        if($('.layui-side-menu').css('left')=='0px'){
            $('.layui-side-menu').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
           	$(this).html('&#xe66b;');
        }else{
            $('.layui-side-menu').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            	$(this).html('&#xe668;');
            // if($(window).width()<768){
            //     $('.page-content-bg').show();
            // }
        }

    });

	


});


/*弹出层*/
	/*
	    参数解释：
	    title   标题
	    url     请求的url
	    id      需要操作的数据id
	    w       弹出层宽度（缺省调默认值）
	    h       弹出层高度（缺省调默认值）
	*/

function zq_admin_show(title,url,w,h){

	if (title == null || title == '') {
       title=false;
	};
	if (url == null || url == '') {
	    url="404.html";
	};
	if (w == null || w == '') {
	    w=($(window).width()*0.9);
	};
	if (h == null || h == '') {
	    h=($(window).height()*0.8);
	};

	layer.open({
	   	type: 2		// 0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）

	    ,area: [w + 'px', h + 'px'] 	// 在默认状态下，layer是宽高都自适应的，但当你只想定义宽度时，你可以area: '500px'，高度仍然是自适应的。当你宽高都要定义时，你可以area: ['500px', '300px']

	    ,filed: false 		// 即鼠标滚动时，层是否固定在可视区域。如果不想，设置fixed: false即可

	    ,maxmin: true 		// 默认不显示最大小化按钮。需要显示配置maxmin: true即可

	    ,shadeClose: true 	// 可以设定shadeClose来控制点击弹层外区域关闭

	    ,shade: 0.4			// 默认是0.3透明度的黑色背景（'#000'）。如果你想定义别的颜色，可以shade: [0.8, '#393D49']；如果你不想显示遮罩，可以shade: 0

	    ,title: title		// title :'我是标题'，那么只会改变标题文本；若你还需要自定义标题区域样式，那么你可以title: ['文本', 'font-size:18px;']
	    	
	    ,content: url		// 内容
	})
};

// 弹出查询页面
// 弹出查询
function zq_query_show(title,url,w,h) {
    if (title == null || title == '') {
        title=false;
    };
    if (w == null || w == '') {
        w=($(window).width()*0.9);
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    layer.open({
        type: 1
        ,area: [w + 'px', h + 'px']
        ,filed: false
        ,maxmin: true
        ,shadeClose: true
        ,shade: 0.4
        ,title: title
        ,content: $(url)
    })
}


// 单张图片上传
 function preview(file){
 	var prevDiv = document.getElementById('preview');

 	if (file.files && file.files[0]) {
 		var reader = new FileReader();
		console.log(file.files[0]);
 		//限制大小
		var fileSize =file.files[0].size;
        var size = fileSize / 1024;
        if(size <= 2048){

            var formData = new FormData();
            formData.append("file", file.files[0]);
            $.ajax({
                url: '/multipleFileUpload/importFile',
                data: formData,
                dataType: 'JSON',
                type: 'post',
                /**
                 *必须false才会自动加上正确的Content-Type
                 */
                contentType: false,
                /**
                 * 必须false才会避开jQuery对 formdata 的默认处理
                 * XMLHttpRequest会对 formdata 进行正确的处理
                 */
                processData: false,
                success: function (data) {
                    console.log(data);
                    if(data.code == 10034){
                        prevDiv.innerHTML = '<img src="' + data.data + '" />'
                    }
                }
            });
            // reader.onload = function(evt) {
            //     // prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
            // };
            // reader.readAsDataURL(file.files[0]);
		}else{
        	layer.msg('图片大小不能大于2MB');
		}

 	}

 }



 // 退出登入

 function zq_logout(){
 	layer.confirm(
 		'是否退出系统？',
 		{btn: ['确定', '取消']
 	},function(){

 		$.ajax({
			url: '/adminlogin/logOutAdmin',
			type: 'post',
			success: function (data) {
				if(data.msg == '退出成功'){
                    layer.msg(data.msg, {icon: 1});
                    setTimeout(function(){
                        location.href="/admin/index/login"
                    },500)
				}
            }
		})
 		
 	})
 }