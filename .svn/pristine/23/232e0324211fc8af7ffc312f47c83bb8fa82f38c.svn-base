
<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/normalGoods/index.js"></script>

</head>
<body class="zq-bodybagcolor">

	<!-- 刷新 start -->
	<div class="zq-nav">
		<a class="layui-btn right" href="javascript:location.replace(location.href);" title="刷新">
			<i class="layui-icon">&#xe669;</i>
		</a>
	</div>
	<!-- 刷新 end -->


	<!-- 内容 start -->

	<div class="zq-body">
        <!-- 查询 start -->
        <div class="zq-query">
            <form class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" placeholder="货物单号" name="tripartiteNumber" autocomplete="off" class="layui-input" />
                        </div>
                    </div>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" placeholder="快递单号" name="deliveryNumber" autocomplete="off" class="layui-input" />
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" placeholder="创建时间" name="seachTime" autocomplete="off" class="layui-input" id="seachTime"/>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <div class="layui-input-inline">

                            <select name="operateResultNumber">
                                <option value="">操作进程</option>
                                <option value="1">货物入中国仓库</option>
                                <option value="2">货物打包 </option>
                                <option value="3">货物出库</option>
                                <option value="4">货物送出</option>
                                <option value="5">货物入新加坡仓库</option>
                                <option value="6">货物出新加坡仓库 </option>
                                <option value="7">货物直接装车（简易）</option>
                                <option value="8">货物配送中</option>
                                <option value="9">货物配送完成</option>
                                <option value="10">货物入库异常</option>
                                <option value="11">货物派送异常（骑手）</option>
                                <option value="12">货物问题件接收成功</option>
                                <option value="13">货物派送异常（货车）</option>
                                <option value="14">货物装配小车</option>
                                <option value="15">合并转运货物</option>
                                <option value="16">货物发起转运</option>
                            </select>

                        </div>
                    </div>

                    <div class="layui-inline">
                        <button class="layui-btn" lay-submit="" lay-filter="*">
                            <i class="layui-icon">&#xe615;</i>
                        </button>

                    </div>


                </div>
            </form>

        </div>
        <!-- 查询 end -->

		<!-- 操作按钮 start-->
		<div class="zq-actionbuttons">
            <#if importExcel??>
			<button class="layui-btn layui-btn-normal " id="import">三方货物导入</button>
            </#if>
            <#if importGoodsSendConditionExce??>
                <button class="layui-btn layui-btn-normal " id="imports">配送结果导入</button>
            </#if>
			<#--<button class="layui-btn"  onclick="zq_query_show('<@spring.message code="zq_Inquire"/>','#zq-query','500','400')"><i class="layui-icon">&#xe615;</i><@spring.message code="zq_Inquire"/></button>-->

            <#if insertTransshipmentGoodsInfo??>
                <button class="layui-btn" onclick="zq_admin_show('转运货物入库','/admin/normalGoods/zhuanyunruku')"><i class="layui-icon">&#xe608;</i>转运货物入库</button>
            </#if>
            <#if getGoodsAndBags??>
            <button class="layui-btn  layui-btn-normal"  onclick="zq_query_show('导出','#zq-query','500','400')">导出</button>
            </#if>
        </div>
		<!-- 操作按钮 end-->

		<!-- 数据表格 start -->

		<table id="idTest" lay-filter="demo"></table>



		<!-- 数据表格 end -->

	</div>

	<!-- 内容 end -->

	<!-- 表格操作 start -->
	<script type="text/html" id="barDemo">
        {{# if(d.status == 1){ }}
            <#if updateThreeGoodsInfo??>
                {{# if(d.from == '三方货单'){}}
                <a class="zq-btn-startusing" onclick="zq_admin_show('三方货物入库','/admin/normalGoods/sanfangruku?goodsId={{d.goodsId}}')">三方货物入库</a>
                {{# } }}
            </#if>
            <#if updateGoodsAndGoodsDetailsInfo??>
                <a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/normalGoods/edit?goodsId={{d.goodsId}}&from={{d.from}}')"><@spring.message code="zq_edit"/></a>
            </#if>
            <#if getGoodsDetailById??>
                <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/normalGoods/open?goodsId={{d.goodsId}}')"><@spring.message code="zq_View"/></a>
            </#if>
            <#if deleteGoodsById??>
                <a class="zq-btn-delete currency"  onclick="delthis('{{d.goodsId}}')"><@spring.message code="zq_delete"/></a>
            </#if>

        {{# }else if(d.status == 2){ }}
            <#if updateGoodsAndGoodsDetailsInfo??>
            <a class="zq-btn-edit" onclick="zq_admin_show('<@spring.message code="zq_edit"/>','/admin/normalGoods/abnormaledit?goodsId={{d.goodsId}}&from={{d.from}}')"><@spring.message code="zq_edit"/></a>
            </#if>
            <#if getGoodsDetailById??>
                <a class="zq-btn-open" onclick="zq_admin_show('<@spring.message code="zq_View"/>','/admin/normalGoods/open?goodsId={{d.goodsId}}')"><@spring.message code="zq_View"/></a>
            </#if>
            <#if deleteGoodsById??>
                <a class="zq-btn-delete currency"  onclick="delthis('{{d.goodsId}}')"><@spring.message code="zq_delete"/></a>
            </#if>
        {{# } }}
	</script>
	<!-- 表格操作 end -->
	<#-- 查询页 -->
    <div id="zq-query" class="zq-query-body" style="display: none">
        <form class="layui-form  layui-form-pane zq-query-form">
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    后台-员工
                </label>
                <div class="layui-input-inline">
                    <select name="adminUid" lay-search>
                        <option value=""></option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    PAD-员工
                </label>
                <div class="layui-input-inline">
                    <select name="staffId" lay-search>
                        <option value=""></option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                   开始时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="startDate" autocomplete="off" class="layui-input" lay-verify="required" id="export-startDate">
                </div>
            </div>
            <div class="layui-form-item">
                <label  for="name" class="layui-form-label">
                    结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="endDate" autocomplete="off" class="layui-input" lay-verify="required" id="export-endDate">
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn  layui-btn-normal" lay-submit="" lay-filter="export">确定</button>
            </div>

        </form>
	</div>

	<#-- 状态 -->
    <script type="text/html" id="status">
        {{# if(d.status == 1){ }}
			正常
		{{# } }}
        {{# if(d.status == 2){ }}
            <a class="layui-btn layui-btn-danger layui-btn-xs">异常</a>
        {{# } }}

    </script>
	<#-- 特殊货物 -->
    <script type="text/html" id="isSpecialGoodsID">
        {{# if(d.isSpecialGoods == 1){ }}
        是
        {{# } else if(d.isSpecialGoods == 2){ }}
        否
        {{# } }}
    </script>
</body>
</html>