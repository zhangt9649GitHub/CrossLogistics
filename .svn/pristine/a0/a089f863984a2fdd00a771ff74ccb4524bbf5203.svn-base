<#include "../public_header.ftl">


    <script type="text/javascript" src="/static/js/carRendezvous/edit.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>

</head>
<body>
<div class="zq-body">
    <form class="layui-form zq-form">

        <input type="hidden" value="${rallyPointId}" name="rallyPointId">
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="carRendezvous_Assemblyname"/>
            </div>
            <div class="layui-col-md10">
                <input type="text" name="rallyPointName" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="carRendezvous_Assemblyaddress"/>
            </div>
            <div class="layui-col-md10">
                <input type="text" name="rallyPointAddress" class="layui-input" autocomplete="off" lay-verify="required"/>
            </div>
        </div>

        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="cargoBagList_Area"/>
            </div>
            <div class="layui-col-md10">
                <select name="singaporeAreaId" lay-verify="required"  lay-filter="quyu" lay-search>
                    <option value=""></option>
                </select>
            </div>
        </div>


        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">

                 <@spring.message code="carRendezvous_Assemblydescription"/>
            </div>
            <div class="layui-col-md10">
                <textarea placeholder="请输入内容" name="rallyPointAccount" class="layui-textarea" ></textarea>
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
                            <letb><@spring.message code="zq_latitude"/>：</letb>
                            <input type="text" name="rallyPointLat" readonly>
                            <letb><@spring.message code="zq_longitude"/>：</letb>
                            <input type="text" name="rallyPointLng" readonly>
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