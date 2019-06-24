
<#include "../kjwl_public_header.ftl">


		

		

		
		<content>
			
			<!-- banner start -->
			
			<div id="banner">
				
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<img src="/static/kjwl/img/banner1.jpg" alt="">
						</div>
						<div class="swiper-slide">
							<img src="/static/kjwl/img/banner2.jpg" alt="">
						</div>
						
					</div>
					<!-- 如果需要分页器 -->
					<div class="swiper-pagination"></div>
					
					
				</div>

				
			</div>
			
			<!-- banner end -->
			
			<!-- 查询 start -->
			<div id="query" class="public">
				<form action="" class="layui-form">
					<p class="title">物流查询</p>
					<div class="flex">
						<div class="types">
							<select>
								<option value="1">转单号</option>
								<option value="2" selected>运单号</option>
							</select>
						</div>
						<input type="text" placeholder="您可以输入快递单号查询" name="deliveryNum" class="layui-input" onkeyup="this.value=this.value.replace(/\s+/g,'')" />
						<div class="submit">
							<i class="layui-icon">&#xe615;</i>
						</div>

					</div>
					<p class="error">
						*&nbsp;&nbsp;快递单号格式错误
					</p>
				</form>
			</div>
			<!-- 查询 end -->
			
			<!-- 关于我们 start-->
			
			<div id="aboutus" class="public">
				<div class="title">
					<h3>关于我们</h3>
				</div>
				
				<div class="content">
					
					<div class="item">
						<div class="left">
							<img src="/static/kjwl/img/page2.jpg" alt="">
						</div>
						<div class="right">
							<div class="wrap">
								<h3 class="title">专业服务</h3>
								<p class="info">
									经过严格培训和考核的认证司机<br>
									99.8%用户好评司机
								</p>
							</div>

						</div>
					</div>
					<div class="item">

						<div class="right">
							<div class="wrap">
								<h3 class="title">低价透明</h3>
								<p class="info">
									低价实惠，比个体司机便宜40%<br>
									按照里程、车型计费，收有标准
								</p>
							</div>
						</div>
						
						<div class="left">
							<img src="/static/kjwl/img/page1.jpg" alt="">
						</div>
					</div>
					<div class="item">
						<div class="left">
							<img src="/static/kjwl/img/page3.jpg" alt="">
						</div>
						<div class="right">
							<div class="wrap">
								<h3 class="title">快捷交易</h3>
								<p class="info">
									支持微信、支付宝<br>
									跨境交易快捷安全
								</p>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
			<!-- 关于我们 end -->
			
			
			
		</content>

<link rel="stylesheet" href="/static/kjwl/css/index.css">
<script src="/static/kjwl/js/index.js"></script>

<#include "../kjwl_public_footer.ftl">