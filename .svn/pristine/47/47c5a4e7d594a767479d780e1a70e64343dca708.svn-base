<#include "../public_header.ftl">


    <script type="text/javascript" src="/static/js/singaporeZipCode/edit.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>

</head>
<body>
<div class="zq-body">
    <form class="layui-form zq-form">

        <input type="hidden" value="${saBuildingId}" name="saBuildingId">
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="singaporeZipCode_name"/>
            </div>
            <div class="layui-col-md10">
                <input type="text" name="saBuildingName" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                 <@spring.message code="zq_address"/>
            </div>
            <div class="layui-col-md10">
                <input type="text" name="saBuildingAddress" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>


        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                 <@spring.message code="zq_Zipcode"/>
            </div>
            <div class="layui-col-md10">
                <input type="text" name="saZipCode" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                 <@spring.message code="cargoBagList_Area"/>
            </div>
            <div class="layui-col-md10">
                <select name="saId" lay-verify="required" lay-filter="quyu" lay-search>
                    <option value=""><@spring.message code="zq_pleasechoose"/></option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                集结地
            </div>
            <div class="layui-col-md10">
                <select name="rallyPointId" lay-verify="required" lay-search>
                    <option value="">请选择</option>
                </select>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="zq_Latitudeandlongitude"/>
            </div>
            <div class="layui-col-md10">
                <div class="zq-google-map">
                    <div id="map"></div>
                <#-- 显示 -->
                    <div class="zq-google-section">
                        <div class="zq-google-search">

                            <label><@spring.message code="zq_latitude"/>：</label>
                            <input type="text" name="saBuildingLat" readonly class="lat">
                            <label><@spring.message code="zq_longitude"/>：</label>
                            <input type="text" name="saBuildingLng" readonly class="lng">
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