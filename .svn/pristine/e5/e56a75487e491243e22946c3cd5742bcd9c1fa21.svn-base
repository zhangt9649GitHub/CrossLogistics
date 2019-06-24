<#include "../public_header.ftl">




	<link rel="stylesheet" type="text/css" href="/static/css/warehouseManagementList/edit.css">
	<script type="text/javascript" src="/static/js/warehouseManagementList/edit.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
              async defer></script>
</head>
<body>

<div class="zq-body">
    <form class="layui-form zq-form">
        <input type="hidden" name="warehouseId" value="${warehouseId}"/>
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                 <@spring.message code="warehouseManagementList_warehousename"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="warehouseName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="warehouseManagementList_WarehouseAddress"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="warehouseAddress" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="zq_contactdetails"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="warehousePhone" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
               <@spring.message code="zq_Latitudeandlongitude"/>
            </div>
            <div class="layui-col-md8">
                <div class="zq-google-map">
                    <div id="map"></div>
                <#-- 显示 -->
                    <div class="zq-google-section">
                        <div class="zq-google-search">
                            <label><@spring.message code="zq_latitude"/>：</label>
                            <input type="text" name="warehouseLat" readonly class="lat">
                            <label><@spring.message code="zq_longitude"/>：</label>
                            <input type="text" name="warehouseLng" readonly class="lng">
                        </div>
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