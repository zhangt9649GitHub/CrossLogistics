<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/car/edit.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>
</head>
<body>

<div class="zq-body">
    <form class="layui-form zq-form">
        <input type="hidden" name="carId" value="${carId}">
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                  <@spring.message code="car_Area"/>
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
                <select name="rallyPointId" lay-verify="required" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="car_Carlock"/>
            </div>
            <div class="layui-col-md8">
                <select name="carLockId" lay-verify="required" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
               <@spring.message code="car_Smalltrunklock"/>
            </div>
            <div class="layui-col-md8">
                <select name="carLockBoxId" lay-verify="required" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">

                <@spring.message code="car_Mapdisplay"/>
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