<#include "../public_header.ftl">

	<script type="text/javascript" src="/static/js/singaporeRegion/add.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkZGIP6knat07rkfXefRk3IDQ0DU9oagc&callback=initMap"
            async defer></script>
</head>
<body>

<div class="zq-body">
    <form class="layui-form zq-form">
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="singaporeRegion_Areaname"/>
            </div>
            <div class="layui-col-md8">
                <input type="text" name="singaporeAreaName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md12 zq-col-bottom">
            <div class="layui-col-md2 zq-col-left">
                <b>*</b>
                <@spring.message code="singaporeRegion_Regionaldivision"/>
            </div>
            <div class="layui-col-md8">
                <div class="zq-google-map">
                    <div id="map"></div>
                    <div class="zq-google-section">
                        <div class="zq-google-search" onclick="remove()">
                            <@spring.message code="singaporeRegion_Replanning"/>
                            <input type="hidden" readonly class="lat">
                            <input type="hidden" readonly class="lng">
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