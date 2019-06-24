<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/truckMission/open.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>

</head>
<body>

<div class="zq-body">
    <div class="zq-main">
        <input type="hidden" value="${truckTaskId}" name="truckTaskId">
        <div class="title"><@spring.message code="truckMission_details"/></div>
        <hr />
        <div class="content info">
            <div class="layui-row ">
                <div class="layui-col-md3 truckTaskNumber">
                    <label><@spring.message code="carMission_Templatename"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md3 truckDriverName">
                    <label><@spring.message code="normalGoods_Driversname"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md6 adminName">
                    <label><@spring.message code="truckMission_Operator"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
            </div>
            <div class="layui-row">
                <div class="layui-col-md3 truckTaskName">
                    <label><@spring.message code="carMission_Templatenumber"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md3 status">
                    <label><@spring.message code="truckMission_status"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md6 addTime">
                    <label><@spring.message code="zq_Creationtime"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
            </div>

        </div>
        <hr/>

        <div class="title"><@spring.message code="zq_route"/></div>
        <hr />
        <div class="content zq-google-map">
            <div id="map"></div>
        </div>

    </div>
</div>

</body>
</html>