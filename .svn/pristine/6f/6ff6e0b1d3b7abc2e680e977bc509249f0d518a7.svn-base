<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/staffList/add.js"></script>

</head>
<body>
	<div class="zq-body">
		<form class="layui-form zq-form" id="uploadForm" enctype="multipart/form-data">


			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					<@spring.message code="staffList_Employeename"/>
				</div>
				<div class="layui-col-md8">
					<input type="text" name="staffName" class="layui-input" autocomplete="off" lay-verify="required"/>
				</div>
			</div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="staffList_contactdetails"/>
                </div>
                <div class="layui-col-md8 ">
                    <input type="text"  name="mobile" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>

            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="staffList_Jobrole"/>
                </div>
                <div class="layui-col-md8 ">
                    <input type="text"  name="position" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>


            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="staffList_Employeeaccount"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="userName" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_password"/>
                </div>
                <div class="layui-col-md8">
                    <input type="password" name="password" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					<@spring.message code="zq_confirmpassword"/>
				</div>
				<div class="layui-col-md8">
					<input type="password" id="queren-password" class="layui-input" autocomplete="off" lay-verify="required"/>
				</div>
			</div>

            <div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					<@spring.message code="staffList_Employeepermissiongroup"/>
				</div>
				<div class="layui-col-md8">
					<select name="staffGroupId"  lay-verify="required" lay-search>
						<option value=""></option>
					</select>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					<@spring.message code="staffList_Employeegender"/>
				</div>
				<div class="layui-col-md8 ">
					<input type="radio" name="sex" value="男" title='<@spring.message code="zq_male"/>'>
      				<input type="radio" name="sex" value="女" title='<@spring.message code="zq_Female"/>' checked>
				</div>
			</div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					<@spring.message code="zq_dateofbirth"/>
				</div>
				<div class="layui-col-md8 ">
					<input type="text" name="bornYears" class="layui-input" autocomplete="off" lay-verify="required" id="test1"/>
				</div>
			</div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_age"/>

                </div>
                <div class="layui-col-md8 ">
                    <input type="text" name="age" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>

			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					归属地

				</div>
				<div class="layui-col-md8 ">
					<select name="attribution"  lay-verify="required" lay-search>
						<option value=""></option>
					</select>
				</div>
			</div>
			<div class="layui-col-md6 zq-col-bottom">
				<div class="layui-col-md4 zq-col-left">
					<b>*</b>
					所属仓库

				</div>
				<div class="layui-col-md8 ">
					<select name="warehouseId"  lay-verify="required" lay-search>
						<option value=""></option>
					</select>
				</div>
			</div>


			<div class="layui-col-md12 zq-col-bottom">
				<div class="layui-col-md2 zq-col-left">
					<b>*</b>
					<@spring.message code="zq_facialphoto"/>
                    <br/>
                    <p>100*100</p>
				</div>
				<div class="layui-col-md10">
					<div class="zq-div-img">
						<div class="zq-domt">
							<div id="preview">
								<img src="/static/image/dj.jpg">
							</div>
							<input class="zq-file" type="file"  onchange="preview(this)" />
						</div>
					</div>
					

				</div>
			</div>




			<div class="zq-submit">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>

			</div>
		</form>


	</div>

</body>
</html>