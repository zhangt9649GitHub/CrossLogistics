<#include "../public_header.ftl">
<html lang="zh_CN" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<link rel="stylesheet" type="text/css" href="/static/css/index/index.css">
<script type="text/javascript" src="/static/js/index/index.js"></script>

<body>

	<!-- header start -->
	<div class="container">
		<div class="logo">
			<a href=""><@spring.message code="index_login_title"/></a>
		</div>
		<div class="left-open">
			<i title="<@spring.message code='index_index_Expandclose'/>" class="layui-icon">&#xe668;</i>
		</div>
		<ul class="layui-nav right" lay-filter="">
			<#if  getMessagesOfCarAndGoods??>
            <li class="layui-nav-item" id="remind">
                <a onclick="zq_admin_show('<@spring.message code="index_index_Messagealerts"/>','/admin/index/messageReminder','600','500')"><@spring.message code='index_index_Messagealerts'/><span class="layui-badge"></span></a>
            </li>
			</#if>
			<li class="layui-nav-item">
				<a href="javascript:;" class="zq-username">${(Session.user.userName)!''}</a>
			    <dl class="layui-nav-child"> <!-- 二级菜单 -->
			      <dd><a href="javascript:;" onclick="zq_admin_show('<@spring.message code="index_index_changePassword"/>','/admin/index/changePassword','450','300')"><@spring.message code="index_index_changePassword"/></a></dd>
			      <dd><a href="javascript:;" onclick="zq_logout()"><@spring.message code="index_index_dropout"/></a></dd>
			    </dl>
			</li>
		</ul>
	</div>
	<!-- header end -->

	<!-- 左边导航 start-->
	<div class="layui-side layui-side-menu">
		<div class="layui-side-scroll">
			<ul class="layui-nav layui-nav-tree left-nav" lay-filter="filter">

				<#if  administratorManagement??>
				<li class="layui-nav-item layui-nav-itemed">
				    <a href="javascript:;" data-id="1"><img src="/static/img/admin.png"><@spring.message code="index_index_Administratormanagement"/></a>
				    <dl class="layui-nav-child">
						<#if  getGroupAccessList??>
				      	<dd><a data-href="/admin/jurisdiction/index" data-id="1.1" href="javascript:;"><@spring.message code="index_index_authoritymanagement"/></a></dd>
						</#if>
						<#if getUserTypeList??>
							<dd><a data-href="/admin/administratorType/index" data-id="1.2" href="javascript:;">管理员类型</a></dd>
						</#if>
						<#if getAdminUserList??>
				      	<dd><a data-href="/admin/adminList/index" data-id="1.3" href="javascript:;"><@spring.message code="index_index_Administratorslist"/></a></dd>
						</#if>
				    </dl>
				</li>
				</#if>

				<#if staffManagement??>
				<li class="layui-nav-item">
				    <a href="javascript:;" data-id="2"><img src="/static/img/staff.png"><@spring.message code="index_index_StaffManagement"/></a>
				    <dl class="layui-nav-child">
						<#if selectStaffAll??>
				      	<dd><a data-href="/admin/staffList/index" data-id='2.1' href="javascript:;"><@spring.message code="index_index_Employeelist"/></a></dd>
						</#if>
						<#if staffGroupAll??>
				      	<dd><a data-href="/admin/jurisdiction_1/index" data-id='2.2' href="javascript:;"><@spring.message code="index_index_EmployeePositionpermissionslist"/></a></dd>
						</#if>
				    </dl>
				</li>
				</#if>
				<#if userManagement??>
				<li class="layui-nav-item">
				    <a data-id="3" href="javascript:;"><img src="/static/img/user.png"><@spring.message code="index_index_UserManagement"/></a>
                    <dl class="layui-nav-child">
						<#if selectAppUserAll??>
                        <dd><a data-href="/admin/userList/index" data-id='3.1' href="javascript:;"><@spring.message code="index_index_userlist"/></a></dd>
						</#if>
						<#if getAppUserCertificationList??>
                        <dd><a data-href="/admin/userAuditList/index" data-id='3.2' href="javascript:;"><@spring.message code="index_index_Pendinglist"/></a></dd>
						</#if>
						<#if appUserAddressAll??>
                        <dd><a data-href="/admin/userAddressList/index" data-id='3.3' href="javascript:;"><@spring.message code="index_index_Useraddresslist"/></a></dd>
						</#if>
                    </dl>
				</li>
				</#if>

				<#if getGoodsList??>
				<li class="layui-nav-item">
				    <a href="javascript:;" data-href="/admin/normalGoods/index" data-id='4'><img src="/static/img/goods.png"><@spring.message code="index_index_Cargomanagement"/></a>
				    <#--<dl class="layui-nav-child">-->
						<#--<#if getGoodsList??>-->
				      	<#--<dd><a data-href="/admin/normalGoods/index" data-id='4.1' href="javascript:;"><@spring.message code="index_index_Normalcargomanagement"/></a></dd>-->
						<#--</#if>-->
				      	<#--<#if getGoodsWarningList??>-->
						<#--<dd><a data-href="/admin/abnormalGoods/index" data-id='4.2' href="javascript:;"><@spring.message code="index_index_Abnormalcargomanagement"/></a></dd>-->
						<#--</#if>-->
				    <#--</dl>-->
				</li>
				</#if>
				<#--进程管理 start-->

				<#--进程管理 end-->

				<#if ThreeGoods??>
					<li class="layui-nav-item">
						<a data-id="16" href="javascript:;"><img src="/static/img/sanfang.png">三方货物管理</a>
						<dl class="layui-nav-child">
							<#if getOrdinaryGoodsList??>
								<dd><a data-href="/admin/generalcargo/index" data-id="16.1" href="javascript:;">普通货物</a></dd>
							</#if>
							<#if getCodGoodsList??>
								<dd><a data-href="/admin/codcargo/index" data-id="16.2" href="javascript:;">COD货物</a></dd>
							</#if>
						</dl>
					</li>
				</#if>



				<#if getBagList??>
				<li class="layui-nav-item">
				    <a data-href="/admin/cargoBagList/index" data-id="5" href="javascript:;"><img src="/static/img/gargo-bag.png"><@spring.message code="index_index_Bagmanagement"/></a>
				</li>
				</#if>

				<#if  getwarehouselist??>
				<li class="layui-nav-item">
					<a data-href="/admin/warehouseManagementList/index" data-id="6" href="javascript:;"><img src="/static/img/depot.png"><@spring.message code="index_index_Warehousemanagement"/></a>
				</li>
				</#if>
				<#if getwarehousepositionlist??>
                <li class="layui-nav-item">
                    <a data-href="/admin/warehouseManagement/index" data-id="7" href="javascript:;"><img src="/static/img/cangwei.png"><@spring.message code="index_index_Positionmanagement"/></a>
                </li>
				</#if>

				<#if taskTemplate??>
				<li class="layui-nav-item">
				    <a data-id='8' href="javascript:;"><img src="/static/img/task.png"><@spring.message code="index_index_Tasktemplatemanagement"/></a>
				    <dl class="layui-nav-child">
						<#if carTaskAll??>
				      	<dd><a data-href="/admin/carMission/index" data-id="8.1" href="javascript:;"><@spring.message code="index_index_Cartasktemplate"/></a></dd>
						</#if>

						<#if truckTaskAll??>
				      	<dd><a data-href="/admin/truckMission/index" data-id="8.2" href="javascript:;"><@spring.message code="index_index_Trucktasktemplate"/></a></dd>
						</#if>
				    </dl>
				</li>
				</#if>

				<#if cehicleManagement??>
				<li class="layui-nav-item">
				    <a data-id="9" href="javascript:;"><img src="/static/img/car.png"><@spring.message code="index_index_Vehiclemanagement"/></a>
				    <dl class="layui-nav-child">
						<#if getCarInfoList??>
				      	<dd><a data-href="/admin/car/index" data-id="9.1" href="javascript:;"><@spring.message code="index_index_Carmanagement"/></a></dd>
						</#if>

						<#if getCarMaintenancelist??>
                        <dd><a data-href="/admin/carMaintenance/index" data-id="9.2" href="javascript:;"><@spring.message code="index_index_Carmaintenance"/></a></dd>
						</#if>

						<#if getTruckList??>
				      	<dd><a data-href="/admin/truck/index" data-id="9.3" href="javascript:;"><@spring.message code="index_index_Truckmanagement"/></a></dd>
						</#if>
						<#if getList??>
							<dd><a data-href="/admin/carlock/index" data-id="9.4" href="javascript:;">车锁管理</a></dd>
						</#if>
				    </dl>
				</li>
				</#if>

				<#if getTransshipmentGoodsList??>
				<li class="layui-nav-item">
				    <a data-href="/admin/transportList/index" data-id="10" href="javascript:;"><img src="/static/img/transport.png"><@spring.message code="index_index_Transshipmentmanagement"/></a>
				</li>
				</#if>

				<#if orderManagement??>
				<li class="layui-nav-item">
				    <a data-id="11" href="javascript:;"><img src="/static/img/order.png"><@spring.message code="index_index_Ordermanagement"/></a>
				    <dl class="layui-nav-child">
						<#if taskOrderAll??>
				      	<dd><a data-href="/admin/carOrder/index" data-id="11.1" href="javascript:;"><@spring.message code="index_index_Carorders"/></a></dd>
						</#if>
						<#if appTruckOrderAll??>
				      	<dd><a data-href="/admin/truckOrder/index" data-id="11.2" href="javascript:;"><@spring.message code="index_index_Truckorder"/></a></dd>
						</#if>
				    </dl>
				</li>
				</#if>

				<#if getAppUserCarStatistics??>
					<li class="layui-nav-item">
						<a data-id="12" href="javascript:;" data-href="/admin/carOrderStatistics/index"><img src="/static/img/truckOrderStatistics.png">货物订单统计</a>
					</li>
				</#if>


				<#if financialManagement??>
				<li class="layui-nav-item">
				    <a data-id="13" href="javascript:;"><img src="/static/img/finance.png"><@spring.message code="index_index_FinancialManagement"/></a>
				    <dl class="layui-nav-child">
						<#if getFinanceFlowList??>
				      	<dd><a data-href="/admin/financialFlow/index" data-id="13.1" href="javascript:;"><@spring.message code="index_index_Financialflow"/></a></dd>
						</#if>
						<#if getPayGiveList??>
				      	<#--<dd><a data-href="/admin/wages/index" data-id="13.2" href="javascript:;"><@spring.message code="index_index_salarygiving"/></a></dd>-->
						</#if>
						<#if getWithdrawApplicationList??>
				      	<dd><a data-href="/admin/cashWithdrawal/index" data-id="13.3" href="javascript:;"><@spring.message code="index_index_Withdrawalapplication"/></a></dd>
						</#if>
				    </dl>
				</li>
				</#if>
				<#if getGoodsFromList??>
				<li class="layui-nav-item">
					<a data-id="17" href="javascript:;" data-href="/admin/ManifestManagement/index" ><img src="/static/img/huodan.png">货单管理</a>
				</li>
				</#if>
				<#--<li class="layui-nav-item">-->
				    <#--<a data-id="14" href="javascript:;"><img src="/static/img/report-form.png"><@spring.message code="index_index_Statisticalreport"/></a>-->
				<#--</li>-->

				<#if testSetup??>
				<li class="layui-nav-item">
				    <a data-id="15" href="javascript:;"><img src="/static/img/parameter.png"><@spring.message code="index_index_parametersettings"/></a>
				    <dl class="layui-nav-child">
						<#if getallareaname??>
				      	<dd><a data-href="/admin/domesticAddress/index" data-id="15.1" href="javascript:;"><@spring.message code="index_index_Domesticlogisticsaddressparametersetting"/></a></dd>
						</#if>

						<#if getSGBuildingList??>
				      	<dd><a data-href="/admin/singaporeZipCode/index" data-id="15.2" href="javascript:;"><@spring.message code="index_index_SingaporePostManagement"/></a></dd>
						</#if>
						<#if getSingaporeAreaList??>
				      	<dd><a data-href="/admin/singaporeRegion/index" data-id="15.3" href="javascript:;"><@spring.message code="index_index_SingaporeCustomAreaManagement"/></a></dd>
						</#if>
						<#if getrallypointlist??>
                      	<dd><a data-href="/admin/carRendezvous/index" data-id="15.4"  href="javascript:;"><@spring.message code="index_index_Singaporeassemblymanagement"/></a></dd>
						</#if>
				      	<#--<dd><a href="javascript:;">货袋容量参数设置</a></dd>-->

				      	<#--<dd><a href="javascript:;"><@spring.message code="index_index_Expressorderinformationsheettemplatesettings"/></a></dd>-->
				      	<#--<dd><a href="javascript:;"><@spring.message code="index_index_LogisticsinformationSMStemplatesettings"/></a></dd>-->
				      	<#--<dd><a href="javascript:;"><@spring.message code="index_index_Automaticweighinghardwaredocking"/></a></dd>-->


				      	<#--&lt;#&ndash;<dd><a href="javascript:;">转运运费模板设置</a></dd>&ndash;&gt;-->
				      	<#--<dd><a href="javascript:;">货品分类信息设置</a></dd>-->
				      	<#--<dd><a data-href="/admin/exitAndEntry/index" data-id="15.11" href="javascript:;">出入境物流途径参数设置</a></dd>-->
						<#--<dd><a href="javascript:;"><@spring.message code="index_index_Tasktemplategenerationparametersettings"/></a></dd>-->
						<#--<dd><a href="javascript:;"><@spring.message code="index_index_Orderproductionparametersetting"/></a></dd>-->
						<#--<dd><a href="javascript:;"><@spring.message code="index_index_Paymentmethoddocking"/></a></dd>-->
						<#--<dd><a href="javascript:;"><@spring.message code="index_index_Exchangerateconversiontemplate"/></a></dd>-->
						<#--<dd><a href="javascript:;"><@spring.message code="index_index_Employeesalarypiececountingalgorithmsetting"/></a></dd>-->
						<#if getCopywritingList??>
						<dd><a data-href="/admin/copybookSettings/index" data-id="15.17" href="javascript:;"><@spring.message code="index_index_Copysettings"/></a></dd>
						</#if>
						<#if getBizdictionaryList??>
						<dd><a data-href="/admin/dataDictionary/index" data-id="15.18" href="javascript:;"><@spring.message code="index_index_DataDictionary"/></a></dd>
						</#if>
						<#if getAdminConfiglist??>
						<dd><a data-href="/admin/systemParameterSetting/index" data-id="15.19" href="javascript:;"><@spring.message code="index_index_Systemparametersetting"/></a></dd>
						</#if>
				    </dl>
				</li>
				</#if>



			</ul>
		</div>
	
	</div>
	

	<!-- 左边导航 end-->


	<!-- 主体内容 start-->

	<div class="page-content">
		<div class="layui-tab tab layui-tab-card" lay-filter="xbs-tab" lay-allowclose="false">

            <#-- 操作 -->
            <div class="operating layui-icon layui-icon-down">
                <ul class="layui-nav">
                    <li class="layui-nav-item">
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child"> <!-- 二级菜单 -->
                            <dd class="site-demo-active" data-type="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd class="site-demo-active" data-type="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>

                        </dl>
                    </li>
                </ul>
            </div>
            <#-- 操作 -->
			<ul class="layui-tab-title">
				<li lay-id="0" class="layui-this">首页</li>
			</ul>

			<div class="layui-tab-content">
	            <div class="layui-tab-item layui-show">
	                <iframe src='/admin/index/homepage' frameborder="0" scrolling="yes" class="zq-iframe"></iframe>
	            </div>
          	</div>
		</div>

		
	</div>

	<!-- 主体内容 end -->

</body>
</html>