$(document).ready(function(){
	
	var mySwiper = new Swiper ('.swiper-container', {
		direction: 'horizontal', // 水平切换选项
		loop: true, // 循环模式选项
		
		// 如果需要分页器
		pagination: {
		  el: '.swiper-pagination',
		  dynamicBullets:true,
		  clickable :true,
		},
		grabCursor : true,
		
		autoplay: {
			delay: 3000,//1秒切换一次
			disableOnInteraction: false,
		}
	});


	layui.use('form', function(){
		var form = layui.form;
	});
	
	//  查询
	
	$('.submit').click(function(){
		
		var value = $('input[name="deliveryNum"]').val();

		var types = $('.types select option:selected').val();
		var regu = /^([0-9]+)(-)?([0-9]+)$/;


		if(value.match(regu)){
			location.href= '/logistics?deliveryNum='+value+'&types='+types;

		}else{

			$('#query .error').css('opacity','1')
		}



	})
	
	
})