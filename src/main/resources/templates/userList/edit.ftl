password<#include "../public_header.ftl">
	<script type="text/javascript" src="/static/js/userList/edit.js"></script>

</head>
<body>
	<div class="zq-body">
        <form class="layui-form zq-form">
            <input type="hidden" value="${appUserId}" name="appUserId">
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    用户名
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="userName" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_contactdetails"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="mobile" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>


            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_age"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="age" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                </div>
            </div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_sex"/>
                </div>
                <div class="layui-col-md8">
                    <input type="radio" name="sex" value="男" title="<@spring.message code="zq_male"/>">
                    <input type="radio" name="sex" value="女" title="<@spring.message code="zq_Female"/>" checked>
                </div>
            </div>


            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    真实姓名
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="actualName" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    门牌号
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="houseNumber" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>


            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_Zipcode"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="zipCode" class="layui-input" autocomplete="off" lay-verify="required"/>
                </div>
            </div>
            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_mailbox"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="email" class="layui-input" autocomplete="off" lay-verify="required|email"/>
                </div>
            </div>
            <#--<div class="layui-col-md6 zq-col-bottom">-->
                <#--<div class="layui-col-md4 zq-col-left">-->
                    <#--<b>*</b>-->
                    <#--<@spring.message code="zq_Originalpassword"/>-->
                <#--</div>-->
                <#--<div class="layui-col-md8">-->
                    <#--<input type="password" name="password" class="layui-input" autocomplete="off" lay-verify="required"/>-->
                <#--</div>-->
            <#--</div>-->
            <#--<div class="layui-col-md6 zq-col-bottom">-->
                <#--<div class="layui-col-md4 zq-col-left">-->
                    <#--<b>*</b>-->
                    <#--<@spring.message code="zq_newpassword"/>-->
                <#--</div>-->
                <#--<div class="layui-col-md8">-->
                    <#--<input type="password" name="newPassword" class="layui-input" autocomplete="off" lay-verify="required"/>-->
                <#--</div>-->
            <#--</div>-->

            <div class="layui-col-md6 zq-col-bottom">
                <div class="layui-col-md4 zq-col-left">
                    <b>*</b>
                    <@spring.message code="zq_address"/>
                </div>
                <div class="layui-col-md8">
                    <input type="text" name="address" class="layui-input" autocomplete="off" lay-verify="required"/>
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
                            <input class="zq-file" type="file" onchange="preview(this)" />
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