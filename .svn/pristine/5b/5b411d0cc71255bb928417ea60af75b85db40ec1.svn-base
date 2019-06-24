<#include "../public_header.ftl">



<script type="text/javascript" src="/static/js/truckMission/edit.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
        async defer></script>

</head>
<body>

<div class="zq-body">
    <form class="layui-form zq-form">
        <input type="hidden" value="${truckTaskId}" name="truckTaskId">
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                 <@spring.message code="carMission_Templatename"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="truckTaskName" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="truckMission_truckdriver"/>
            </div>
            <div class="layui-col-md8">
                <select name="truckDriverId" xm-select="truckDriverId" lay-verify="required" class="truckDriverId" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="truckMission_warehouse"/>
            </div>
            <div class="layui-col-md8">
                <select lay-verify="required" name="warehouseId" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                 <@spring.message code="cargoBagList_Area"/>
            </div>
            <div class="layui-col-md8">
                <select name="singaporeAreaId" lay-verify="required" lay-filter="singaporeAreaId" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="carMission_Aggregate"/>
            </div>
            <div class="layui-col-md8">
                <select name="saIds" xm-select="select1" class="saIds"  lay-verify="required">
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="truckMission_Aggregatedisplay"/>
            </div>
            <div class="layui-col-md8">
                <div class="zq-google-map">
                    <div id="map"></div>
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