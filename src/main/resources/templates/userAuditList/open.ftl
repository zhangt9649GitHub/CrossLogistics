<#include "../public_header.ftl">


	<script type="text/javascript" src="/static/js/userAuditList/open.js"></script>

    <link rel="stylesheet" href="/static/css/userAuditList/open.css">

</head>
<body>

<div class="zq-body">
    <input type="hidden" value="${certificationId}" name="certificationId">
    <div class="zq-main">
        <div class="title"><@spring.message code="userAuditList_UserInfo"/></div>
        <hr />
        <div class="content user-info">
            <div class="layui-row">
                <div class="layui-col-md2 number">
                    <label><@spring.message code="userAuditList_userID"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md2 userTrueName">
                    <label>真实姓名：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>

                <div class="layui-col-md8 mobile">
                    <label><@spring.message code="zq_contactdetails"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>

            </div>
            <div class="layui-row truck">
                <div class="layui-col-md2 licensePlate">
                    <label><@spring.message code="userAuditList_Trucklicenseplate"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md2 load">
                    <label><@spring.message code="userAuditList_FreightCarLoad"/>：</label>
                    <div class="inline">
                        <span></span>t
                    </div>
                </div>

                <div class="layui-col-md8 models">
                    <label><@spring.message code="userAuditList_Truckmodels"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>

            </div>



            <div class="layui-row">
                <div class="layui-col-md2 userType">
                    <label><@spring.message code="userAuditList_Audittype"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>
                <div class="layui-col-md2 userCertificationStatus">
                    <label><@spring.message code="userAuditList_Auditstatus"/>：</label>
                    <div class="inline">
                        <span></span>
                    </div>
                </div>


            </div>
        </div>
        <hr/>
        <div class="title"><@spring.message code="userAuditList_Uploadqualificationinformation"/></div>
        <hr/>
        <div class="content images">
        </div>


    </div>

    <#-- 放大图片 -->
    <div class="fixed">
        <div>
            <img src="" alt="">
        </div>
    </div>
</div>

</body>
</html>