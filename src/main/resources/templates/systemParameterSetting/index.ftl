<#include "../public_header.ftl">

<script type="text/javascript" src="/static/js/systemParameterSetting/index.js"></script>

<body class="zq-bodybagcolor">
<!-- 刷新 start -->
<div class="zq-nav">
    <a class="layui-btn right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon">&#xe669;</i>
    </a>
</div>
<!-- 刷新 end -->
    <div class="zq-body">

        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li class="layui-this"><@spring.message code="systemParameterSetting_Basicparametersetting"/></li>
                <li><@spring.message code="systemParameterSetting_Shippingsetting"/></li>
                <li><@spring.message code="systemParameterSetting_TaskOrderSettings"/></li>

            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <form class="layui-form zq-form">

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                骑手配送货物金额（每件）（单位：S$）：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="anOrderPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>

                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                货车配送货物金额（每袋）（单位：S$）：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="anBagPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>

                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                客服电话：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="STC" class="layui-input" autocomplete="off" lay-verify="required"/>

                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                异常件扣费（每件）（单位：S$）：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="waringGoodsPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>

                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                汇率换算（新币/人民币）：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="SGDtoCNYExchangeFrate" class="layui-input" autocomplete="off" lay-verify="required|number"/>

                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                汇率换算（美元/人民币）：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="USDtoCNYExchangeFrate" class="layui-input" autocomplete="off" lay-verify="required|number"/>

                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                汇率换算（美元/新币）：
                            </div>
                            <div class="layui-col-md8">

                                <input type="text" name="SGDtoUSDtoExchangeFrate" class="layui-input" autocomplete="off" lay-verify="required|number"/>

                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b style="visibility:hidden">*</b>
                            </div>
                            <div class="layui-col-md8">
                                <#if updateAdminConfig??>
                                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="*"><@spring.message code="zq_save"/></button>
                                <button type="reset" class="layui-btn layui-btn-primary"><@spring.message code="zq_Reset"/></button>
                                </#if>
                            </div>


                        </div>
                    </form>


                </div>
                <div class="layui-tab-item">

                    <form class="layui-form zq-form">
                        <fieldset class="layui-elem-field layui-field-title" >
                            <legend>空运</legend>
                        </fieldset>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                0.5kg以内（普通）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="regularAirPriceOne" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                0.5kg以上每超过0.5kg加收（普通）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="regularAirPriceTwo" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                超过10kg-50kg 每千克（普通）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="regularAirPriceThree" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                超过50kg-100kg 每千克（普通）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="regularAirPriceFour" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                超过100kg 每千克（普通）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="regularAirPriceFive" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                0.5kg以内（敏感）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="sensitiveAirPriceOne" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                0.5kg以上每超过0.5kg加收（敏感）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="sensitiveAirPriceTwo" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                超过10kg-50kg 每千克（敏感）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="sensitiveAirPriceThree" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                超过50kg-100kg 每千克（敏感）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="sensitiveAirPriceFour" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                货物长、宽、高是否有>=150cm 加收（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="unilateralOverlengthPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                货物重量是否有>=40kg（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="overweightPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                货物价值超过交GST税（单位：S$）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="GSTPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <#--<div class="layui-col-md12 zq-col-bottom">-->
                            <#--<div class="layui-col-md2 zq-col-left">-->
                                <#--<b>*</b>-->
                                <#--缴纳GST税描述-->
                            <#--</div>-->
                            <#--<div class="layui-col-md8">-->
                                <#--<input type="text" name="ExceedGSTPrompt" class="layui-input" autocomplete="off" lay-verify="required"/>-->
                            <#--</div>-->
                        <#--</div>-->

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                税率
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="rate" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                permit费用（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="permit" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <fieldset class="layui-elem-field layui-field-title" >
                            <legend>海运</legend>
                        </fieldset>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                1-5立方 每立方（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="marinePriceOne" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                5-10立方 每立方（单位：￥）（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="marinePriceTwo" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                10-20立方 每立方（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="marinePriceThree" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                超过20立方 每立方（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="marinePriceFour" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>

                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                敏感货物每单加收（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="sensitiveMarinePrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>
                        <fieldset class="layui-elem-field layui-field-title" >
                            <legend>空运与海运加急件</legend>
                        </fieldset>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b>*</b>
                                加急件加收（单位：￥）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="emergencyDeliveryPrice" class="layui-input" autocomplete="off" lay-verify="required|number"/>
                            </div>
                        </div>


                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md3 zq-col-left">
                                <b style="visibility:hidden">*</b>
                            </div>
                            <div class="layui-col-md8">
                                 <#if updateAdminConfig??>
                                     <button class="layui-btn layui-btn-normal" lay-submit lay-filter="*"><@spring.message code="zq_save"/></button>
                                     <button type="reset" class="layui-btn layui-btn-primary"><@spring.message code="zq_Reset"/></button>
                                 </#if>
                            </div>


                        </div>
                    </form>
                </div>
                <div class="layui-tab-item">
                    <form class="layui-form zq-form">
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md2 zq-col-left">
                                <b>*</b>
                                生成小车订单时间范围（天）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="generateOrderTimeLimitCar" class="layui-input" autocomplete="off" lay-verify="required|number" />
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md2 zq-col-left">
                                <b>*</b>
                                生成货车订单时间范围（天）
                            </div>
                            <div class="layui-col-md8">
                                <input type="text" name="generateOrderTimeLimitTruck" class="layui-input" autocomplete="off" lay-verify="required|number" />
                            </div>
                        </div>
                        <div class="layui-col-md12 zq-col-bottom">
                            <div class="layui-col-md2 zq-col-left">
                                <b style="visibility:hidden">*</b>
                            </div>
                            <div class="layui-col-md8">
                                 <#if updateAdminConfig??>
                                     <button class="layui-btn layui-btn-normal" lay-submit lay-filter="*"><@spring.message code="zq_save"/></button>
                                     <button type="reset" class="layui-btn layui-btn-primary"><@spring.message code="zq_Reset"/></button>
                                 </#if>
                            </div>


                        </div>
                    </form>
                </div>

            </div>
        </div>






    </div>


</body>
</html>
