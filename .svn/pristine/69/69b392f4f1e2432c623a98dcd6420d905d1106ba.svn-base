package com.siruiman.crosslogistics.service.impl;

import com.siruiman.crosslogistics.mapper.CarLockMapper;
import com.siruiman.crosslogistics.mapper.SysConfigMapper;
import com.siruiman.crosslogistics.model.CarLock;
import com.siruiman.crosslogistics.model.SysConfig;
import com.siruiman.crosslogistics.service.ConfigService;
import com.siruiman.crosslogistics.util.StaticConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 张占伟
 * @date 2019/1/15 14:08
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    private static final Logger logger = LoggerFactory
            .getLogger(ConfigServiceImpl.class);
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private CarLockMapper carLockMapper;

    @Override
    public List<SysConfig> selectAll() {
        return sysConfigMapper.selectAll();
    }

    @Override
    public void findAndUpdateByKey(String key, String value) {
        try {
            SysConfig config = sysConfigMapper.selectByKey(key);
            if (config==null){
                SysConfig data = new SysConfig();
                data.setKey(key);
                data.setValue(value);
                sysConfigMapper.insert(data);
            }
            else {
                if (null == config.getValue() && null != value){
                    config.setValue(value);
                    sysConfigMapper.updateByPrimaryKey(config);
                }else if (!config.getValue().equals(value)){
                    config.setValue(value);
                    sysConfigMapper.updateByPrimaryKey(config);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadConfig() {
        logger.info("开始加载系统参数配置");
        List<SysConfig> list = selectAll();
        for (SysConfig config:list) {

//            生成小车订单的时间
            if ("generateOrderTimeLimitCar".equals(config.getKey())){
                StaticConfigUtil.generateOrderTimeLimitCar=Integer.valueOf(config.getValue());
            }
//          生成货车订单的时间
            if ("generateOrderTimeLimitTruck".equals(config.getKey())){
                StaticConfigUtil.generateOrderTimeLimitTruck=Integer.valueOf(config.getValue());
            }

//            普货：0.5kg
            if ("regularAirPriceOne".equals(config.getKey())){
                StaticConfigUtil.regularAirPriceOne=Integer.valueOf(config.getValue());
            }

//            普货：0.5kg以上每超过0.5kg加收
            if ("regularAirPriceTwo".equals(config.getKey())){
                StaticConfigUtil.regularAirPriceTwo=Double.valueOf(config.getValue());
            }

//          普货：超过10kg-50kg 每千克
            if ("regularAirPriceThree".equals(config.getKey())){
                StaticConfigUtil.regularAirPriceThree=Double.valueOf(config.getValue());
            }

//          普货：超过50kg-100kg
            if ("regularAirPriceFour".equals(config.getKey())){
                StaticConfigUtil.regularAirPriceFour=Double.valueOf(config.getValue());
            }

//          普货：超过100kg
            if ("regularAirPriceFive".equals(config.getKey())){
                StaticConfigUtil.regularAirPriceFive=Double.valueOf(config.getValue());
            }

//          敏感：0.5kg以内
            if ("sensitiveAirPriceOne".equals(config.getKey())){
                StaticConfigUtil.sensitiveAirPriceOne=Double.valueOf(config.getValue());
            }

//          敏感：0.5kg以上每超过0.5kg
            if ("sensitiveAirPriceTwo".equals(config.getKey())){
                StaticConfigUtil.sensitiveAirPriceTwo=Double.valueOf(config.getValue());
            }

//          敏感： 超过10kg-50kg
            if ("sensitiveAirPriceThree".equals(config.getKey())){
                StaticConfigUtil.sensitiveAirPriceThree=Double.valueOf(config.getValue());
            }

//          敏感：  超过10kg-50kg
            if ("sensitiveAirPriceFour".equals(config.getKey())){
                StaticConfigUtil.sensitiveAirPriceFour=Double.valueOf(config.getValue());
            }

//            普货 1-5立方
            if ("marinePriceOne".equals(config.getKey())){
                StaticConfigUtil.marinePriceOne=Double.valueOf(config.getValue());
            }

//          普货  5-10立方
            if ("marinePriceTwo".equals(config.getKey())){
                StaticConfigUtil.marinePriceTwo=Double.valueOf(config.getValue());
            }

//           普货 10-20立方
            if ("marinePriceThree".equals(config.getKey())){
                StaticConfigUtil.marinePriceThree=Double.valueOf(config.getValue());
            }

//            普货 20立方+
            if ("marinePriceFour".equals(config.getKey())){
                StaticConfigUtil.marinePriceFour=Double.valueOf(config.getValue());
            }

//            空运：货物长、宽、高是否有>=150cm >加收
            if ("unilateralOverlengthPrice".equals(config.getKey())){
                StaticConfigUtil.unilateralOverlengthPrice=Double.valueOf(config.getValue());
            }

//            空运：货物重量是否有>=40k >加收
            if ("overweightPrice".equals(config.getKey())){
                StaticConfigUtil.overweightPrice=Double.valueOf(config.getValue());
            }

//            空运和海运 是否紧急加收
            if ("emergencyDeliveryPrice".equals(config.getKey())){
                StaticConfigUtil.emergencyDeliveryPrice=Double.valueOf(config.getValue());
            }

//            空运:货品超过2021RMB（SGD400）交GST税
            if ("GSTPrice".equals(config.getKey())){
                StaticConfigUtil.GSTPrice=Double.valueOf(config.getValue());
            }

//            税率
            if ("rate".equals(config.getKey())){
                StaticConfigUtil.rate=Double.valueOf(config.getValue());
            }

//             证书 加收
            if ("permit".equals(config.getKey())){
                StaticConfigUtil.permit=Double.valueOf(config.getValue());
            }

//          海运敏感货每单加收
            if ("sensitiveMarinePrice".equals(config.getKey())){
                StaticConfigUtil.sensitiveMarinePrice=Double.valueOf(config.getValue());
            }
//            一个货物小车的运费
            if ("anOrderPrice".equals(config.getKey())){
                StaticConfigUtil.anOrderPrice=Double.valueOf(config.getValue());
            }
//              一个货袋货车的运费
            if ("anBagPrice".equals(config.getKey())){
                StaticConfigUtil.anBagPrice=Double.valueOf(config.getValue());
            }
//              一个问题件扣的钱数
            if ("waringGoodsPrice".equals(config.getKey())){
                StaticConfigUtil.waringGoodsPrice=Double.valueOf(config.getValue());
            }
//             新币 与 人民币的汇率 1新币=?元人民币
            if ("SGDtoCNYExchangeFrate".equals(config.getKey())){
                StaticConfigUtil.SGDtoCNYExchangeFrate=new BigDecimal(config.getValue());
            }

//           美元 与 人民币的汇率
            if("USDtoCNYExchangeFrate".equals(config.getKey())){
                StaticConfigUtil.USDtoCNYExchangeFrate = new BigDecimal(config.getValue());
            }

            //           美元 与 新币的汇率
            if("SGDtoUSDtoExchangeFrate".equals(config.getKey())){
                StaticConfigUtil.SGDtoUSDtoExchangeFrate = new BigDecimal(config.getValue());
            }


//            客服联系电话
            if("STC".equals(config.getKey())){
                StaticConfigUtil.STC=config.getValue();
            }


        }
//        车锁状态初始化
        List<CarLock> locks = carLockMapper.selectAllCarLockState();
        for (CarLock ca:locks) {
            StaticConfigUtil.lockState.put(ca.getLockNumber(),ca.getState()+"");
        }
        logger.info("系统参数加载成功");
    }
}
