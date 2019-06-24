<#include "../public_header.ftl">
    <script type="text/javascript" src="/static/wangEditor/release/wangEditor.js"></script>
	<script type="text/javascript" src="/static/js/copybookSettings/edit.js"></script>

</head>
<body>

<div class="zq-body">

    <form action="" class="layui-form zq-form">
        <input type="hidden" value="${copyWriterId}" name="cwId">
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="singaporeZipCode_name"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="cwName" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="copybookSettings_Language"/>
            </div>
            <div class="layui-col-md8">
                <select name="language" lay-verify="required" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                    <option value="zh">zh</option>
                    <option value="en">en</option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="copybookSettings_type"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="type" id="HandoverCompany" class="layui-input" style="position:absolute;z-index:2;width:90%;" lay-verify="required" value="" onkeyup="search()" autocomplete="off">
                <select type="text" id="hc_select" lay-filter="hc_select" autocomplete="off"  class="layui-select" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <#--<div class="layui-col-md12 zq-col-bottom">-->
            <#--<div class="layui-col-md2 zq-col-left">-->
                <#--<b>*</b>-->
                <#--类型-->
            <#--</div>-->
            <#--<div class="layui-col-md8">-->
                <#--<select name="type" lay-verify="required" lay-filter="type" lay-search>-->
                    <#--<option value=""></option>-->
                    <#--<option value="1">关于我们</option>-->
                    <#--<option value="2">帮助与反馈</option>-->
                    <#--<option value="3">转运须知</option>-->
                    <#--<option value="4">转运公司介绍</option>-->
                <#--</select>-->
            <#--</div>-->
        <#--</div>-->

        <div class="layui-col-md12 zq-col-bottom tubiao">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="copybookSettings_icon"/>
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

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="copybookSettings_content"/>
            </div>
            <div class="layui-col-md8">
                <div id="editor"></div>
            </div>
        </div>

        <div class="zq-submit">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="add"><@spring.message code="zq_save"/></button>
        </div>

    </form>

</div>

</body>
</html>