<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/carOrder/open.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
        async defer></script>
</head>
<body>

	<div class="zq-body">
        <input type="hidden" value="${taskOrderId}" name="taskOrderId">
        <div class="zq-main">

            <div class="title">详细信息</div>
            <hr/>
            <div class="content info">
                <div class="layui-row">
                    <div class="layui-col-md3 orderNumber">
                        <label>订单编号：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 totalMoney">
                        <label>订单金额：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 addMoney">
                        <label>订单奖励金额：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 createTime">
                        <label>订单时间：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-md3 name">
                        <label>订单名称：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 totalIntegral">
                        <label>订单积分：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 addIntegral">
                        <label>订单奖励积分：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

                </div>
                <div class="layui-row">
                    <div class="layui-col-md3 state">
                        <label>订单状态：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 singaporeAreaName">
                        <label>订单区域：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>
                    <div class="layui-col-md2 rallyPointAddress">
                        <label>集结地：</label>
                        <div class="inline">
                            <span></span>
                        </div>
                    </div>

                </div>
            </div>
            <hr/>
            <div class="title">货物清单</div>
            <hr/>
            <div class="content">
                <div class="table">
                    <table id="idTest"></table>

                </div>
            </div>
            <hr/>

            <div class="title">地图显示</div>
            <hr/>
            <div class="content zq-google-map">
                <div id="map"></div>
            </div>
        </div>
	</div>


    <script type="text/html" id="barDemo">

        <#if updateGoodsComment??>
        {{# if(d.deductState == 2){}}
        <a class="zq-btn-delete"  onclick="startusing('{{d.goodsId}}')">扣除</a>
         {{# } }}
        </#if>

    </script>
    <script type="text/html" id="status">

        {{# if(d.status == 1){}}
        正常
        {{# } }}
        {{# if(d.status == 2){}}
        异常
        {{# } }}
    </script>

</body>
</html>