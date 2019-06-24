<#include "../public_header.ftl">


<script type="text/javascript" src="/static/js/normalGoods/abnormaledit.js"></script>

</head>
<body>

	<div class="zq-body">
		
		<form class="layui-form zq-form">
            <input type="hidden" value="${goodsId}" name="goodsId">
			<input type="hidden" value="${from}" name="from">
            <input type="hidden" value="3" name="adminUid">
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					<@spring.message code="abnormalGoods_consignee"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="receiptContact" class="layui-input" autocomplete="off" />
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					<@spring.message code="abnormalGoods_Consigneecontactinformation"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="receiptContactMobile" class="layui-input" autocomplete="off" lay-verify="number"/>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					<@spring.message code="normalGoods_Shippingaddress"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="receiptAddress" class="layui-input" autocomplete="off" />
				</div>
			</div>


			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					<@spring.message code="zq_Zipcode"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="zipCode" class="layui-input" autocomplete="off" lay-verify=""/>
				</div>
			</div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					用户编号
				</div>
				<div class="layui-col-md8">
					<input type="text" name="number" class="layui-input" autocomplete="off"/>
				</div>
			</div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">

					<@spring.message code="abnormalGoods_sender"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="shipContact" class="layui-input" autocomplete="off" />
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">

					<@spring.message code="abnormalGoods_Shippercontactinformation"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="shipContactMobile" class="layui-input" autocomplete="off" />
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">

					<@spring.message code="normalGoods_deliveryaddress"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="shipAddress" class="layui-input" autocomplete="off"/>
				</div>
			</div>
			
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					<@spring.message code="normalGoods_Typeofcargo"/>
				</div>
				<div class="layui-col-md8">
                    <input type="text" name="goodType" class="layui-input" autocomplete="off" lay-verify=""/>
				</div>
			</div>

            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">

                    <@spring.message code="normalGoods_Carrier"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="shipper" class="layui-input" autocomplete="off" />
                </div>
            </div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					货物单号
				</div>
				<div class="layui-col-md8">
					<input type="text" name="tripartiteNumber" class="layui-input" autocomplete="off" lay-verify="number"/>
				</div>
			</div>

			

			<#--<div class="layui-col-md6 zq-col-bottom">-->
				<#--<div class="layui-col-md4 zq-col-left">-->
					<#--<b>*</b>-->
					<#--<@spring.message code="normalGoods_Specialitems"/>-->
				<#--</div>-->
				<#--<div class="layui-col-md8">-->
					<#--<select name="isSpecialGoods" lay-verify="required" lay-search>-->
						<#--<option value=""></option>-->
						<#--<option value="1"><@spring.message code="normalGoods_Yes"/></option>-->
						<#--<option value="2"><@spring.message code="normalGoods_No"/></option>-->
					<#--</select>-->
				<#--</div>-->
			<#--</div>-->

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					<@spring.message code="abnormalGoods_Abnormalstate"/>
				</div>
				<div class="layui-col-md8">
                    <select name="warningStateNumber" lay-verify="required" lay-search>
                        <option value=""></option>
                        <option value="2"><@spring.message code="abnormalGoods_abnormal"/></option>
                        <option value="3"><@spring.message code="abnormalGoods_Processing"/></option>
                        <option value="1"><@spring.message code="abnormalGoods_Noabnormality"/></option>
                    </select>
				</div>
			</div>

			<#--<div class="layui-col-md6 zq-col-bottom">-->
				<#--<div class="layui-col-md4 zq-col-left">-->
					<#--<b>*</b>-->
					<#--<@spring.message code="zq_long"/>（<@spring.message code="zq_unit"/>：cm）-->
				<#--</div>-->
				<#--<div class="layui-col-md8">-->
					<#--<input type="text" name="actualLong" class="layui-input" autocomplete="off" lay-verify="required|number"/>-->
				<#--</div>-->
			<#--</div>-->
			<#--<div class="layui-col-md6 zq-col-bottom">-->
				<#--<div class="layui-col-md4 zq-col-left">-->
					<#--<b>*</b>-->
					<#--<@spring.message code="zq_width"/>（<@spring.message code="zq_unit"/>：cm）-->
				<#--</div>-->
				<#--<div class="layui-col-md8">-->
					<#--<input type="text" name="actualWidth" class="layui-input" autocomplete="off" lay-verify="required|number"/>-->
				<#--</div>-->
			<#--</div>-->
			<#--<div class="layui-col-md6 zq-col-bottom">-->
				<#--<div class="layui-col-md4 zq-col-left">-->
					<#--<b>*</b>-->
					<#--<@spring.message code="zq_high"/>（<@spring.message code="zq_unit"/>：cm）-->
				<#--</div>-->
				<#--<div class="layui-col-md8">-->
					<#--<input type="text" name="actualHeight" class="layui-input" autocomplete="off" lay-verify="required|number"/>-->
				<#--</div>-->
			<#--</div>-->
            <#--<div class="layui-col-md6 zq-col-bottom">-->
                <#--<div class="layui-col-md4 zq-col-left">-->
                    <#--<b>*</b>-->
                   <#--<@spring.message code="zq_weight"/>（<@spring.message code="zq_unit"/>：kg）-->
                <#--</div>-->
                <#--<div class="layui-col-md8">-->
                    <#--<input type="text" name="actualWeight" class="layui-input" autocomplete="off" lay-verify="required|number"/>-->
                <#--</div>-->
            <#--</div>-->
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b></b>
                   <@spring.message code="abnormalGoods_Exceptiontype"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="warningType" class="layui-input" autocomplete="off" readonly />
                </div>
            </div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					总箱数
				</div>
				<div class="layui-col-md8">
					<input type="text" name="totalGoods" class="layui-input" autocomplete="off" lay-verify="number" />
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b></b>
					货袋编号
				</div>
				<div class="layui-col-md8">
					<input type="text" name="bagNumber" class="layui-input" autocomplete="off" />
				</div>
			</div>

			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b></b>
					 <@spring.message code="abnormalGoods_Exceptiondescription"/>
				</div>
				<div class="layui-col-md10">
					<textarea name="abnormalText" placeholder="" class="layui-textarea"></textarea>
				</div>
			</div>


			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b></b>
					处理描述
				</div>
				<div class="layui-col-md10">
					<textarea name="dealComment" placeholder="" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="zq-submit">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"> <@spring.message code="zq_save"/></button>
			</div>
		</form>

	</div>

</body>
</html>