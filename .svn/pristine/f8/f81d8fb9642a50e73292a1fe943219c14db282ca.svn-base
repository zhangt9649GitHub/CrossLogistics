$(document).ready(function(){
	
	// 监听滚动条滚动
	 $(document).scroll(function() {
        var scroH = $(document).scrollTop();  //滚动高度
        var viewH = $(window).height();  //可见高度 
        var contentH = $(document).height();  //内容高度

 
        if(scroH >70){  //距离顶部大于100px时
			$('#totop').show();
			$('#totop').css('position','fixed')
			$('#totop').css('top','100%')
        }
		if(scroH <= 30){
			$('#totop').hide();
		}
        if (contentH - (scroH + viewH) <= 148){  //距离底部高度小于100px
             $('#totop').show();
			 $('#totop').css('position','absolute')
			 $('#totop').css('top','-12px')
        }  
       
 
    });

	// 返回顶部
	$('#totop').click(function(){
		
		$(document).scrollTop(0);
	})

	
	
})