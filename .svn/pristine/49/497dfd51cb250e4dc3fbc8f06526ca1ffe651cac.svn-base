package com.siruiman.crosslogistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.siruiman.crosslogistics.mapper.*;
import com.siruiman.crosslogistics.model.*;
import com.siruiman.crosslogistics.model.dto.*;
import com.siruiman.crosslogistics.service.GoodsService;
import com.siruiman.crosslogistics.service.GoodsWarningService;
import com.siruiman.crosslogistics.util.DateUtil;
import com.siruiman.crosslogistics.util.RallyPointIdUtil;
import io.swagger.models.auth.In;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.siruiman.crosslogistics.util.RandomCodeUtil.getRandomCode;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDetailsMapper goodsDetailsMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private GoodsWarningMapper goodsWarningMapper;
    @Autowired
    private WarehousePositionsMapper warehousePositionsMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private LogisticInfoMapper logisticInfoMapper;
    @Autowired
    private SingaporeAreaBuildingMapper singaporeAreaBuildingMapper;
    @Autowired
    private UploadFilesMapper uploadFilesMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private BagMapper bagMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectGoodsList(Goods goods) {
            AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            HashMap<String, Object> map = new HashMap<>();
            map.put("tripartiteNumber", goods.getTripartiteNumber());
            map.put("deliveryNumber", goods.getDeliveryNumber());
            map.put("seachTime", goods.getSeachTime());
            map.put("operateResult", goods.getOperateResult());
            if(user.getWarehouseId()>0){
                map.put("warehouseId",user.getWarehouseId());
            }
            return goodsMapper.selectGoodsList(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCount(Goods goods) {
           AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
            HashMap<String, Object> map = new HashMap<>();
            map.put("tripartiteNumber", goods.getTripartiteNumber());
            map.put("deliveryNumber", goods.getDeliveryNumber());
            map.put("seachTime", goods.getSeachTime());
            map.put("operateResult", goods.getOperateResult());
            if(user.getWarehouseId()>0){
                map.put("warehouseId",user.getWarehouseId());
            }
            return goodsMapper.selectCount(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer selectBagIdByGdId(int goodsId) {
        return goodsMapper.selectBagIdByGdId(goodsId);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertBatchImport(String fileName, MultipartFile file) throws IOException {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return "上传文件格式不正确";
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Sheet sheet = null;
        if (isExcel2003) {
            HSSFWorkbook wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        } else {
            XSSFWorkbook wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            int value = i + 1;
            if (row.getCell(0) == null) {
                return "第" + value + "行的三方物流单号不能为空";
            }else if(row.getCell(0) != null) {
                row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                String tripartiteNumber = row.getCell(0).getStringCellValue();
                List<GoodsDetails> goodsDetailsList = goodsDetailsMapper.selectGoodsDetailByTripartiteNumber(tripartiteNumber);
                if (goodsDetailsList.size() > 0) {
                    return "三方物流单号:" + tripartiteNumber + "为重复数据，如果数据有错误，请先删除旧数据再进行导入";
                }
            }
            String regEx = "^[A-Za-z0-9]+$";
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
             String s1 =  row.getCell(0).getStringCellValue();
            if (row.getCell(0).getStringCellValue().matches(regEx)) {
                if (row.getCell(1) == null) {
                    return "第" + value + "行的邮编不能为空";
                }else if(row.getCell(1) != null){
                    row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String zipCode = row.getCell(1).getStringCellValue();
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if(singaporeAreaBuilding==null){
                        String url = "https://developers.onemap.sg/commonapi/search?searchVal="+zipCode+"&returnGeom=Y&getAddrDetails=Y&pageNum=1";
                        List<Results> list;
                        CloseableHttpResponse response ;
                        try{
                            // 2.设置请求方式和请求信息
                            HttpGet httpGet = new HttpGet(url);
                   /*         // 3.执行请求
                            CloseableHttpResponse response = httpClient.execute(httpGet);*/
                            RequestConfig config = RequestConfig.custom()

                                    .setSocketTimeout(1*1000) // socket套接字超时，毫秒。

                                    .setConnectionRequestTimeout(1*1000) //使用连接池来管理连接时，从连接池获取连接的超时时间，毫秒。

                                    .setConnectTimeout(5*1000) // 连接建立超时，毫秒。

                                    .build();

                            CloseableHttpClient httpClient = HttpClients.custom()

                                    .setDefaultRequestConfig(config) //

                                    .build();

                           response = httpClient.execute(httpGet); // 执行请求
                        }catch (Exception e){
                           return "第" + value + "行的邮编在网站上检索超时，可重新导入数据";
                        }
                        if(response == null){
                            return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                        }
                            // 4.获取返回值
                            String s = null;
                            try {
                                s = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            // 5.截取json数组
                            int a = s.indexOf("results\":");
                            String substring = s.substring(a+9, s.length() - 1);
//        如果截取的字符串为空或者没有内容就跳过
                            if(substring==null || substring.equals("[]")){
                                return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                            }
//        将json数组转化为集合
                            list = JSONObject.parseArray(substring, Results.class);
                            if (list.size()>0){
                                for (Results results1 :list){
                                        if("NIL".equals(results1.getPOSTAL())){
                                            return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                                        }
                                    SingaporeAreaBuilding singaporeAreaBuilding2 = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                                    if(singaporeAreaBuilding2==null) {
                                        SingaporeAreaBuilding singaporeAreaBuilding1 = new SingaporeAreaBuilding();
                                        singaporeAreaBuilding1.setSaId(1);
                                        singaporeAreaBuilding1.setSaBuildingName(results1.getBUILDING());
                                        singaporeAreaBuilding1.setSaBuildingLat(results1.getLATITUDE());
                                        singaporeAreaBuilding1.setSaBuildingLng(results1.getLONGITUDE());
                                        singaporeAreaBuilding1.setSaZipCode(results1.getPOSTAL());
                                        singaporeAreaBuilding1.setSaBuildingAddress(results1.getADDRESS());
                                        int rallyPointId = RallyPointIdUtil.selectRallyPointId(zipCode);
                                        if (rallyPointId > 0) {
                                            singaporeAreaBuilding1.setRallyPointId(rallyPointId);
                                        } else if (rallyPointId == 0) {
                                            return "第" + value + "行的邮编在网站上检索到了，但无对应分区";
                                        }
                                        singaporeAreaBuildingMapper.insert(singaporeAreaBuilding1);
                                    }
                                    }

                                }
                            }

                    }


                if (row.getCell(9) == null) {
                    return "第" + value + "行的收货人地址不能为空";
                }
                if (row.getCell(10) == null) {
                    return "第" + value + "行的收货联系人不能为空";
                }
                if (row.getCell(11) == null) {
                    return "第" + value + "行的收货人联系方式不能为空";
                }
                if (row.getCell(14) == null) {
                    return "第" + value + "行的出境方式不能为空";
                }
                if (row.getCell(17) == null) {
                    return "第" + value + "行的是否货到付款不能为空";
                }
                for (int r = 0; r <= 17; r++) {
                    if (row.getCell(2) == null || row.getCell(3) == null || row.getCell(4) == null || row.getCell(5) == null ) {
                        row.createCell(2).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                        row.createCell(3).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                        row.createCell(4).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                        row.createCell(5).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                    }
                    if(row.getCell(16) == null ){
                        row.createCell(16).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                    }
                    if (row.getCell(r) == null) {
                        row.createCell(r).setCellValue(new HSSFRichTextString(String.valueOf("")));
                    }
                    if (row.getCell(15) == null) {
                        return "第" + value + "行为空，共计箱数必须填写";
                    }
                }
            } else {
                return "第" + value + "行的三方物流单号格式不正确或有空格";
            }
        }
        Set<String> sett = new HashSet<String>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            if (!sett.add(row.getCell(0).getStringCellValue())) {
                int value = i + 1;
                return "第" + value + "行的三方物流单号为重复值，导入失败";
            }
        }

        //循环工作表Sheet
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            int line = r + 1;
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteNumber = row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
            String zipCode = row.getCell(1).getStringCellValue();
            row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteLong = row.getCell(2).getStringCellValue();
            row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteWidth = row.getCell(3).getStringCellValue();
            row.getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteHeight = row.getCell(4).getStringCellValue();
            row.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteWeight = row.getCell(5).getStringCellValue();
            row.getCell(6).setCellType(HSSFCell.CELL_TYPE_STRING);
            String shipAddress = row.getCell(6).getStringCellValue();
            row.getCell(7).setCellType(HSSFCell.CELL_TYPE_STRING);
            String shipContact = row.getCell(7).getStringCellValue();
            row.getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);
            String shipContactMobile = row.getCell(8).getStringCellValue();
            row.getCell(9).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptAddress = row.getCell(9).getStringCellValue();
            row.getCell(10).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptContact = row.getCell(10).getStringCellValue();
            row.getCell(11).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptContactMobile = row.getCell(11).getStringCellValue();
            row.getCell(12).setCellType(HSSFCell.CELL_TYPE_STRING);
            String goodType = row.getCell(12).getStringCellValue();
            row.getCell(13).setCellType(HSSFCell.CELL_TYPE_STRING);
            String shipper = row.getCell(13).getStringCellValue();
            row.getCell(14).setCellType(HSSFCell.CELL_TYPE_STRING);
            String exitWay = row.getCell(14).getStringCellValue();
            row.getCell(15).setCellType(HSSFCell.CELL_TYPE_STRING);
            String totalGoods = row.getCell(15).getStringCellValue();
            row.getCell(16).setCellType(HSSFCell.CELL_TYPE_STRING);
            String itemValue = row.getCell(16).getStringCellValue();
            row.getCell(17).setCellType(HSSFCell.CELL_TYPE_STRING);
            String isArrivalPay = row.getCell(17).getStringCellValue();
            // GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailsByTripartiteNumber(tripartiteNumber);
                if (Integer.parseInt(totalGoods) == 1) {
                    Goods goods = new Goods();
                    goods.setFrom("三方货单");
                    goods.setStatus((byte) 1);
                    goods.setDeliveryNumber("04" + getRandomCode());
                    goods.setTripartiteNumber(tripartiteNumber);
                    goods.setZipCode(zipCode);
                    goods.setGoodType(goodType);
                    goods.setAddTime(new Date());
                    goods.setExitWay(exitWay);
                    goods.setTotalGoods(1);
                    AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                    goods.setAdminUserTypeId(user.getAdminUserTypeId());
                    //新增导入人员所属仓库id
                    goods.setWarehouseId(user.getWarehouseId());
                    goodsMapper.insert(goods);
                    GoodsDetails goodsDetails = new GoodsDetails();
                    goodsDetails.setGoodsId(goods.getGoodsId());
                    if (tripartiteLong != null && !(tripartiteLong.equals(""))) {
                        if (Float.valueOf(tripartiteLong) != 0) {
                            goodsDetails.setTripartiteLong(Float.valueOf(tripartiteLong));
                        }
                    }
                    if (tripartiteWidth != null && !(tripartiteWidth.equals(""))) {
                        if (Float.valueOf(tripartiteWidth) != 0) {
                            goodsDetails.setTripartiteWidth(Float.valueOf(tripartiteWidth));
                        }
                    }
                    if (tripartiteHeight != null && !(tripartiteHeight.equals(""))) {
                        if (Float.valueOf(tripartiteHeight) != 0) {
                            goodsDetails.setTripartiteHeight(Float.valueOf(tripartiteHeight));
                        }
                    }
                    if (tripartiteWeight != null && !(tripartiteWeight.equals(""))) {
                        if (Float.valueOf(tripartiteWeight) != 0) {
                            goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                        }
                    }
                    goodsDetails.setShipAddress(shipAddress);
                    String shipContact1 = shipContact.trim();
                    goodsDetails.setShipContact(shipContact1);
                    goodsDetails.setShipContactMobile(shipContactMobile);
                    goodsDetails.setReceiptAddress(receiptAddress);
                    String receiptContact1 = receiptContact.trim();
                    goodsDetails.setReceiptContact(receiptContact1);
                    goodsDetails.setReceiptContactMobile(receiptContactMobile);
                    goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails.setShipper(shipper);
                    goodsDetails.setWarningState("无异常");
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if (singaporeAreaBuilding.getRallyPointId() != null&& singaporeAreaBuilding.getSaId() != null){
                        goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                        goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    if (Double.valueOf(itemValue) != 0) {
                        goodsDetails.setItemValue(new BigDecimal(Double.valueOf(itemValue)));
                    }
                    if (isArrivalPay.equals("是")) {
                        goodsDetails.setIsArrivalPay(2);
                    } else if (isArrivalPay.equals("否")) {
                        goodsDetails.setIsArrivalPay(1);
                    }
                    goodsDetailsMapper.insert(goodsDetails);
                } else {
                    Goods goods = new Goods();
                    goods.setFrom("三方货单");
                    goods.setStatus((byte) 1);
                    String deliveryNumber = "04" + getRandomCode();
                    goods.setDeliveryNumber(deliveryNumber);
                    goods.setTripartiteNumber(tripartiteNumber);
                    goods.setZipCode(zipCode);
                    goods.setGoodType(goodType);
                    goods.setAddTime(new Date());
                    goods.setExitWay(exitWay);
                    goods.setTotalGoods(Integer.parseInt(totalGoods));
                    AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                    goods.setAdminUserTypeId(user.getAdminUserTypeId());
                    //新增导入人员所属仓库id
                    goods.setWarehouseId(user.getWarehouseId());
                    goodsMapper.insert(goods);
                    GoodsDetails goodsDetails = new GoodsDetails();
                    goodsDetails.setGoodsId(goods.getGoodsId());
                    if (tripartiteLong != null && !(tripartiteLong.equals(""))) {
                        if (Float.valueOf(tripartiteLong) != 0) {
                            goodsDetails.setTripartiteLong(Float.valueOf(tripartiteLong));
                        }
                    }
                    if (tripartiteWidth != null && !(tripartiteWidth.equals(""))) {
                        if (Float.valueOf(tripartiteWidth) != 0) {
                            goodsDetails.setTripartiteWidth(Float.valueOf(tripartiteWidth));
                        }
                    }
                    if (tripartiteHeight != null && !(tripartiteHeight.equals(""))) {
                        if (Float.valueOf(tripartiteHeight) != 0) {
                            goodsDetails.setTripartiteHeight(Float.valueOf(tripartiteHeight));
                        }
                    }
                    if (tripartiteWeight != null && !(tripartiteWeight.equals(""))) {
                        if (Float.valueOf(tripartiteWeight) != 0) {
                            goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                        }
                    }
                    goodsDetails.setShipAddress(shipAddress);
                    String shipContact1 = shipContact.trim();
                    goodsDetails.setShipContact(shipContact1);
                    goodsDetails.setShipContactMobile(shipContactMobile);
                    goodsDetails.setReceiptAddress(receiptAddress);
                    String receiptContact1 = receiptContact.trim();
                    goodsDetails.setReceiptContact(receiptContact1);
                    goodsDetails.setReceiptContactMobile(receiptContactMobile);
                    goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails.setShipper(shipper);
                    goodsDetails.setWarningState("无异常");
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if (singaporeAreaBuilding.getRallyPointId() != null&& singaporeAreaBuilding.getSaId() != null){
                        goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                        goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    if (Double.valueOf(itemValue) != 0) {
                        goodsDetails.setItemValue(new BigDecimal(Double.valueOf(itemValue)));
                    }
                    if (isArrivalPay.equals("是")) {
                        goodsDetails.setIsArrivalPay(2);
                    } else if (isArrivalPay.equals("否")) {
                        goodsDetails.setIsArrivalPay(1);
                    }
                    goodsDetailsMapper.insert(goodsDetails);
                    for (int i = 1; i <= Integer.parseInt(totalGoods); i++) {
                        Goods goods1 = new Goods();
                        goods1.setFrom("三方货单");
                        goods1.setStatus((byte) 1);
                        goods1.setDeliveryNumber(deliveryNumber + "-" + i);
                        goods1.setTripartiteNumber(tripartiteNumber);
                        goods1.setZipCode(zipCode);
                        goods1.setGoodType(goodType);
                        goods1.setAddTime(new Date());
                        goods1.setExitWay(exitWay);
                        goods1.setTotalGoods(1);
                        goods1.setAdminUserTypeId(user.getAdminUserTypeId());
                        //新增导入人员所属仓库id
                        goods1.setWarehouseId(user.getWarehouseId());
                        goodsMapper.insert(goods1);
                        GoodsDetails goodsDetails1 = new GoodsDetails();
                        goodsDetails1.setGoodsId(goods1.getGoodsId());
                        if (tripartiteLong != null && !(tripartiteLong.equals(""))) {
                            if (Float.valueOf(tripartiteLong) != 0) {
                                goodsDetails.setTripartiteLong(Float.valueOf(tripartiteLong));
                            }
                        }
                        if (tripartiteWidth != null && !(tripartiteWidth.equals(""))) {
                            if (Float.valueOf(tripartiteWidth) != 0) {
                                goodsDetails.setTripartiteWidth(Float.valueOf(tripartiteWidth));
                            }
                        }
                        if (tripartiteHeight != null && !(tripartiteHeight.equals(""))) {
                            if (Float.valueOf(tripartiteHeight) != 0) {
                                goodsDetails.setTripartiteHeight(Float.valueOf(tripartiteHeight));
                            }
                        }
                        if (tripartiteWeight != null && !(tripartiteWeight.equals(""))) {
                            if (Float.valueOf(tripartiteWeight) != 0) {
                                goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                            }
                        }
                        goodsDetails1.setShipAddress(shipAddress);
                        String shipContact2 = shipContact.trim();
                        goodsDetails1.setShipContact(shipContact2);
                        goodsDetails1.setShipContactMobile(shipContactMobile);
                        goodsDetails1.setReceiptAddress(receiptAddress);
                        String receiptContact2 = receiptContact.trim();
                        goodsDetails1.setReceiptContact(receiptContact2);
                        goodsDetails1.setReceiptContactMobile(receiptContactMobile);
                        goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                        goodsDetails1.setShipper(shipper);
                        goodsDetails1.setWarningState("无异常");
                        if (singaporeAreaBuilding.getRallyPointId() != null&& singaporeAreaBuilding.getSaId() != null){
                            goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                            goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                        }
                        if (isArrivalPay.equals("是")) {
                            goodsDetails1.setIsArrivalPay(2);
                        } else if (isArrivalPay.equals("否")) {
                            goodsDetails1.setIsArrivalPay(1);
                        }
                        goodsDetailsMapper.insert(goodsDetails1);
                    }
                }

        }
        return "1";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteGoodsById(Integer goodsId) {
        Goods goods = goodsMapper.selectGoodsById(goodsId);
        List<Goods> goodsList = goodsMapper.selectGoodsListByTripartiteNumber(goods.getTripartiteNumber());
        int goodsId1 = 0;
        String deliveryNumber = "";
        int goodsId2 = 0;
        if (goodsList.size() > 1) {
            for (Goods goods1 : goodsList
            ) {
                if (goods1.getTotalGoods() > 1) {
                    goodsId1 = goods1.getGoodsId();
                    deliveryNumber = goods1.getDeliveryNumber();
                    //货值未考虑进去
                    goodsMapper.updateGoodsPoints(goods1.getGoodsId(), goods1.getTotalGoods() - 1);
                } else {
                    String[] str = goods1.getDeliveryNumber().split("-");
                    if (str[1].equals("1")) {
                        goodsId2 = goods1.getGoodsId();
                    }
                }
            }

        }
        goodsMapper.deleteByPrimaryKey(goodsId);
        goodsDetailsMapper.deleteGoodsDetailsByGoodsId(goodsId);
        if (goodsId1 != 0) {
            Goods goods1 = goodsMapper.selectGoodsById(goodsId1);
            if (goods1.getTotalGoods() == 1) {
                GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(goodsId1);
                GoodsDetails goodsDetails2 = goodsDetailsMapper.selectGoodsDetailById(goodsId2);
                goodsMapper.deleteByPrimaryKey(goodsId1);
                goodsDetailsMapper.deleteGoodsDetailsByGoodsId(goodsId1);
                //货值未考虑进去
                goodsMapper.updateGoodsDeliveryNumber(deliveryNumber, goodsId2);
                goodsDetailsMapper.updateGoodsItemValue(goodsDetails2.getGdId(), goodsDetails1.getItemValue());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods selectGoodsById(Integer goodsId) {
            return goodsMapper.selectGoodsById(goodsId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoods(Goods goods) {
        goodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectGoodsListByTruckId(Integer truckId, Integer appUserId) {
            return goodsMapper.selectGoodsListByTruckId(truckId, appUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountGoodsListByTruckId(Integer truckId) {
            return goodsMapper.selectCountGoodsListByTruckId(truckId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getDeliveryNumber() throws IllegalAccessException {
        String code = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String temp = sf.format(new Date());
        String value = goodsMapper.selectDeliveryNumber();
        if ((temp.substring(6, 8) + "999999").equals(value.substring(8, 16))) {
            throw new IllegalAccessException("今日订单编号已超限，请明日在创建");
        } else if (temp.equals(value.substring(2, 10))) {
            code = value.substring(0, 8) + String.valueOf(Long.valueOf(value.substring(8, 16)) + 1);
        } else {
            code = "04" + temp + "000001";
        }
        return code;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods selectGoodsByTripartiteNumber(String tripartiteNumber) {
            return goodsMapper.selectGoodsByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods selectGoodsByDeliveryNumber(String deliveryNumber) {
            return goodsMapper.selectGoodsByDeliveryNumber(deliveryNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertTransshipmentGoods(Goods goods) {
        goodsMapper.insert(goods);
    }

    /**
     * 货物打包绑定货袋
     * 张占伟
     *
     * @param goods
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void putGoodsInBag(Goods goods) {
        goodsMapper.updateGoodsInBag(goods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectByBagId(int bagId) {
        return goodsMapper.selectByBagId(bagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectGoodsByParentId(Integer parentId) {
            return goodsMapper.selectGoodsByParentId(parentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods selectGoodsByGoodsId(Integer goodsId) {
            return goodsMapper.selectByPrimaryKey(goodsId);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsInOutWarehouse(int warehouseId, int wpId, String deliveryNumber) {

        goodsMapper.updateGoodsInOutWarehouse(warehouseId, wpId, DateUtil.getDateTime(), deliveryNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsInToWarehouse(int warehouseId, int wpId, String deliveryNumber) {

        goodsMapper.updateGoodsInToWarehouse(warehouseId, wpId, DateUtil.getDateTime(), deliveryNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectGoodsIdByDeliveryNumber(String deliveryNumber) {
        return goodsMapper.selectGoodsIdByDeliveryNumber(deliveryNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectGoodsId() {
        return goodsMapper.selectGoodsId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods selectGoodsAmountByDeliveryNumber(String deliveryNumber) {
        return goodsMapper.selectGoodsAmountByDeliveryNumber(deliveryNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsCreate(Goods goods) {
        goodsMapper.updateGoodsCreate(goods);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsPrintByGoodsId(int goodsId, int isPrint) {
        goodsMapper.updateGoodsPrintByGoodsId(goodsId, isPrint);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectCarGoodsList(Integer bagId) {
            return goodsMapper.selectCarGoodsList(bagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountCarGoodsList(Integer bagId) {
        return goodsMapper.selectCountCarGoodsList(bagId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public GoodsDetails insertGoods(String tripartiteNumber) {
        Goods goods = new Goods();
        goods.setFrom("转运订单");
        String deliveryNumber = "04" + getRandomCode();
        goods.setDeliveryNumber(deliveryNumber);
        goods.setTripartiteNumber(tripartiteNumber);
        goods.setAddTime(new Date());
        goods.setTotalGoods(1);
        goodsMapper.insert(goods);
        int goodsId = goodsMapper.selectGoodsId();
        GoodsDetails goodsDetails1 = new GoodsDetails();
        goodsDetails1.setGoodsId(goodsId);
        goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
        goodsDetailsMapper.insert(goodsDetails1);
        return goodsDetailsMapper.selectGoodsByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insertGoodsWarning(String tripartiteNumber, String warningType, String warningComment, String number, int staffId) {
        Goods goods1 = new Goods();
        goods1.setFrom("转运订单");
        goods1.setDeliveryNumber("04" + getRandomCode());
        goods1.setTripartiteNumber(tripartiteNumber);
        goods1.setStatus((byte) 2);
        goods1.setAddTime(new Date());
        goods1.setTransportType("普通转运");
        goodsMapper.insert(goods1);
        int goodsId = goodsMapper.selectGoodsId();
        GoodsDetails goodsDetails = new GoodsDetails();
        AppUser appUser = appUserMapper.selectAppUserByNumber(number);
        goodsDetails.setAppUserId(appUser.getAppUserId());
        goodsDetails.setGoodsId(goodsId);
        goodsDetails.setWarningState("异常");
        goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods1.getAddTime()));
        goodsDetailsMapper.insert(goodsDetails);
        GoodsWarning goodsWarning = new GoodsWarning();
        goodsWarning.setGoodsId(goodsId);
        goodsWarning.setWarningComment(warningComment);
        goodsWarning.setWarningType(warningType);
        goodsWarning.setWarningState("异常");
        goodsWarning.setCreateTime(goods1.getAddTime());
        goodsWarning.setDelStatus(1);
        goodsWarningMapper.insert(goodsWarning);
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(goodsId);
        logisticInfo.setCreateTime(new Date());
        logisticInfo.setOperateResult("货物入库异常,异常原因:"+warningComment);
        Staff staff = staffMapper.selectStaffDetail(staffId);
        if (staff != null) {
            logisticInfo.setOperateName(staff.getStaffName());
        }
        logisticInfo.setOperateComment(warningComment);
        logisticInfo.setOperateType("货物入库异常");
        logisticInfo.setStaffId(staffId);
        logisticInfoMapper.insert(logisticInfo);
        return goodsId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsAndDetails(Goods goods, String warningType, String warningComment, int staffId, String number) {
        goods.setStatus((byte) 2);
        goods.setUpdateTime(new Date());

        goodsMapper.updateByPrimaryKey(goods);
        GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goods.getGoodsId());
        if (number != null && !(number.equals(""))) {
            AppUser appUser = appUserMapper.selectAppUserByNumber(number);
            goodsDetails.setAppUserId(appUser.getAppUserId());
        }
        goodsDetails.setWarningState("异常");
        goodsDetails.setAbnormalText(warningComment);
        goodsDetails.setUpdateTime(goods.getUpdateTime());
        if (goodsDetails.getSingaporeArea() != null && goodsDetails.getSingaporeArea().getSingaporeAreaId() != null) {
            goodsDetails.setSingaporeAreaId(goodsDetails.getSingaporeArea().getSingaporeAreaId());
        }
        if (goodsDetails.getRallyPoint() != null && goodsDetails.getRallyPoint().getRallyPointId() != null) {
            goodsDetails.setRallyPointId(goodsDetails.getRallyPoint().getRallyPointId());
        }
        goodsDetailsMapper.updateByPrimaryKey(goodsDetails);
        GoodsWarning goodsWarning1 = goodsWarningMapper.selectGoodsWarningByGoodsId(goods.getGoodsId());
        if (goodsWarning1 == null) {
            GoodsWarning goodsWarning = new GoodsWarning();
            goodsWarning.setGoodsId(goods.getGoodsId());
            goodsWarning.setWarningComment(warningComment);
            goodsWarning.setWarningState("异常");
            goodsWarning.setWarningType(warningType);
            goodsWarning.setCreateTime(goods.getUpdateTime());
            goodsWarning.setDelStatus(1);
            goodsWarningMapper.insert(goodsWarning);
        } else {
            goodsWarning1.setWarningComment(warningComment);
            goodsWarning1.setWarningState("异常");
            goodsWarning1.setWarningType(warningType);
            goodsWarning1.setDelStatus(1);
            goodsWarningMapper.updateByPrimaryKey(goodsWarning1);
        }
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(goods.getGoodsId());
        logisticInfo.setCreateTime(new Date());
        logisticInfo.setOperateResult("货物入库异常,异常原因:"+warningComment);
        Staff staff = staffMapper.selectStaffDetail(staffId);
        if (staff != null) {
            logisticInfo.setOperateName(staff.getStaffName());
        }
        logisticInfo.setOperateComment(warningComment);
        logisticInfo.setOperateType("货物入库异常");
        logisticInfo.setStaffId(staffId);
        logisticInfoMapper.insert(logisticInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateGoodsAndGoodsDetails(PDAGoodsDetailsDto pdaGoodsDetailsDto) {
        Goods goods = goodsMapper.selectGoodsById(pdaGoodsDetailsDto.getGoodsId());
        if (pdaGoodsDetailsDto.getGoodType() != null) {
            goods.setGoodType(pdaGoodsDetailsDto.getGoodType());
        }
        goods.setIsSpecialGoods(pdaGoodsDetailsDto.getIsSpecialGoods());
        if (pdaGoodsDetailsDto.getIntoWpNumber() != null) {
            WarehousePositions warehousePositions = warehousePositionsMapper.selectWarehousePositionsbyWpNumber(pdaGoodsDetailsDto.getIntoWpNumber());
            goods.setIntoWarehouseId(warehousePositions.getWarehouseId());
            goods.setIntoWarehousePositionsId(warehousePositions.getWpId());
        }
           /* if(pdaGoodsDetailsDto.getWarningState()!=null&&pdaGoodsDetailsDto.getWarningState().equals("无异常")){
                goods.setStatus((byte)1);
            }else if (pdaGoodsDetailsDto.getWarningState()!=null&&!(pdaGoodsDetailsDto.getWarningState().equals(""))){
                goods.setStatus((byte)2);
            }*/
        goods.setUpdateTime(new Date());
        goodsMapper.updateByPrimaryKey(goods);
        GoodsDetails goodsDetails1 = goodsDetailsMapper.selectGoodsDetailById(pdaGoodsDetailsDto.getGoodsId());
        if (pdaGoodsDetailsDto.getActualLong() != null && pdaGoodsDetailsDto.getActualLong() > 0 && pdaGoodsDetailsDto.getActualWidth() != null && pdaGoodsDetailsDto.getActualWidth() > 0
                && pdaGoodsDetailsDto.getActualHeight() != null && pdaGoodsDetailsDto.getActualHeight() > 0 && pdaGoodsDetailsDto.getActualWeight() != null && pdaGoodsDetailsDto.getActualWeight() > 0) {
            goodsDetails1.setActualLong(pdaGoodsDetailsDto.getActualLong());
            goodsDetails1.setActualWidth(pdaGoodsDetailsDto.getActualWidth());
            goodsDetails1.setActualHeight(pdaGoodsDetailsDto.getActualHeight());
            goodsDetails1.setActualWeight(pdaGoodsDetailsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        /* goodsDetails1.setWarningState(pdaGoodsDetailsDto.getWarningState());*/
        if (pdaGoodsDetailsDto.getNumber() != null && !(pdaGoodsDetailsDto.getNumber().equals(""))) {
            AppUser appUser = appUserMapper.selectAppUserByNumber(pdaGoodsDetailsDto.getNumber());
            if (appUser != null) {
                goodsDetails1.setAppUserId(appUser.getAppUserId());
            }
        }
          /*  if(pdaGoodsDetailsDto.getWarningState().equals("无异常")&& goods.getFrom().equals("转运订单")){
                goodsDetails1.setGoodState("已到仓");
                goodsDetails1.setAbnormalText("");
            }else if(pdaGoodsDetailsDto.getWarningState().equals("无异常")&& goods.getFrom().equals("三方货单")){
                goodsDetails1.setAbnormalText("");
                goodsDetails1.setGoodState("");
            }*/
           /* if (pdaGoodsDetailsDto.getWarningComment()!=null&&!(pdaGoodsDetailsDto.getWarningComment().equals(""))){
                goodsDetails1.setAbnormalText(pdaGoodsDetailsDto.getWarningComment());
            }else{
                goodsDetails1.setAbnormalText("");
            }*/
        goodsDetails1.setUpdateTime(goods.getUpdateTime());
        if (goodsDetails1.getSingaporeArea() != null && goodsDetails1.getSingaporeArea().getSingaporeAreaId() != null && goodsDetails1.getRallyPoint() != null && goodsDetails1.getRallyPoint().getRallyPointId() != null) {
            goodsDetails1.setSingaporeAreaId(goodsDetails1.getSingaporeArea().getSingaporeAreaId());
            goodsDetails1.setRallyPointId(goodsDetails1.getRallyPoint().getRallyPointId());
        }
        if (pdaGoodsDetailsDto.getCategory() != null && pdaGoodsDetailsDto.getCategory().equals("Sensitive")) {
            goodsDetails1.setCategory("敏感");
        } else if (pdaGoodsDetailsDto.getCategory() != null && pdaGoodsDetailsDto.getCategory().equals("Ordinary")) {
            goodsDetails1.setCategory("普通");
        } else if (pdaGoodsDetailsDto.getCategory() != null && pdaGoodsDetailsDto.getCategory().equals("普通")) {
            goodsDetails1.setCategory("普通");
        } else if (pdaGoodsDetailsDto.getCategory() != null && pdaGoodsDetailsDto.getCategory().equals("敏感")) {
            goodsDetails1.setCategory("敏感");
        }
        goodsDetailsMapper.updateByPrimaryKey(goodsDetails1);
           /* if (pdaGoodsDetailsDto.getWarningState().equals("无异常")){
                GoodsWarning goodsWarning =goodsWarningMapper.selectGoodsWarningByGoodsId(pdaGoodsDetailsDto.getGoodsId());
                if(pdaGoodsDetailsDto.getWarningComment()!=null&&!(pdaGoodsDetailsDto.getWarningComment().equals(""))) {
                    goodsWarning.setWarningComment(pdaGoodsDetailsDto.getWarningComment());
                }
                if (pdaGoodsDetailsDto.getWarningType()!=null&&!(pdaGoodsDetailsDto.getWarningType().equals(""))) {
                    goodsWarning.setWarningType(pdaGoodsDetailsDto.getWarningType());
                }
                if (pdaGoodsDetailsDto.getDealComment()!=null&&!(pdaGoodsDetailsDto.getDealComment().equals(""))) {
                    goodsWarning.setDealComment(pdaGoodsDetailsDto.getDealComment());
                }
                if(pdaGoodsDetailsDto.getWarningState()!=null&&!(pdaGoodsDetailsDto.getWarningState().equals(""))) {
                    goodsWarning.setWarningState(pdaGoodsDetailsDto.getWarningState());
                }
                goodsWarning.setDealTime(new Date());
                goodsWarning.setDelStatus(2);
                goodsWarningMapper.updateByPrimaryKey(goodsWarning);
            }else{*/
              /*  GoodsWarning goodsWarning =goodsWarningMapper.selectGoodsWarningByGoodsId(pdaGoodsDetailsDto.getGoodsId());
                if (goodsWarning ==null){
                    GoodsWarning goodsWarning1 = new GoodsWarning();
                    goodsWarning1.setWarningState(pdaGoodsDetailsDto.getWarningState());
                    if (pdaGoodsDetailsDto.getWarningComment()!=null&&!(pdaGoodsDetailsDto.getWarningComment().equals(""))){
                        goodsWarning1.setWarningComment(pdaGoodsDetailsDto.getWarningComment());
                    }
                    goodsWarning1.setCreateTime(goods.getUpdateTime());
                    goodsWarning1.setGoodsId(pdaGoodsDetailsDto.getGoodsId());
                    if (pdaGoodsDetailsDto.getWarningType()!=null&&!(pdaGoodsDetailsDto.getWarningType().equals(""))) {
                        goodsWarning1.setWarningType(pdaGoodsDetailsDto.getWarningType());
                    }
                   *//* if (pdaGoodsDetailsDto.getDealComment()!=null&&!(pdaGoodsDetailsDto.getDealComment().equals(""))) {
                        goodsWarning1.setDealComment(pdaGoodsDetailsDto.getDealComment());
                    }*//*

                    goodsWarning1.setDelStatus(1);
                    goodsWarningMapper.insert(goodsWarning1);
                }else{
                    goodsWarning.setWarningState(pdaGoodsDetailsDto.getWarningState());
                    if (pdaGoodsDetailsDto.getWarningComment()!=null&&!(pdaGoodsDetailsDto.getWarningComment().equals(""))){
                        goodsWarning.setWarningComment(pdaGoodsDetailsDto.getWarningComment());
                    }
                    if (pdaGoodsDetailsDto.getWarningType()!=null&&!(pdaGoodsDetailsDto.getWarningType().equals(""))) {
                        goodsWarning.setWarningType(pdaGoodsDetailsDto.getWarningType());
                    }
                  *//*  if (pdaGoodsDetailsDto.getDealComment()!=null&&!(pdaGoodsDetailsDto.getDealComment().equals(""))) {
                        goodsWarning.setDealComment(pdaGoodsDetailsDto.getDealComment());
                    }*//*
                    goodsWarningMapper.updateByPrimaryKey(goodsWarning);
                }*/

        // }
        return "1";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectCarGoodsWarningList(Integer bagId) {
            return goodsMapper.selectCarGoodsWarningList(bagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountCarGoodsWarningList(Integer bagId) {
            return goodsMapper.selectCountCarGoodsWarningList(bagId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectGoodsDetailsByParentId(Integer parentId) {

        return goodsMapper.selectGoodsDetailsByParentId(parentId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountGoodsDetailsByParentId(Integer parentId) {

            return goodsMapper.selectCountGoodsByParentId(parentId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateGoodsCommentById(Integer goodsId, String comment) {
        goodsMapper.updateGoodsCommentById(goodsId, comment);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectGoodsListByTripartiteNumber(String tripartiteNumber) {
            return goodsMapper.selectGoodsListByTripartiteNumber(tripartiteNumber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(Goods goods) {
        goodsMapper.insert(goods);
    }

    //后台三方货物入库
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateThreeGoodsInfo(ThreeGoodsDto threeGoodsDto) {
        Goods goods = goodsMapper.selectGoodsById(threeGoodsDto.getGoodsId());
        //  goods.setGoodType(threeGoodsDto.getGoodType());
        goods.setIsSpecialGoods(threeGoodsDto.getIsSpecialGoods());
        AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
        if (threeGoodsDto.getUfSavePath() != null) {
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUfSavePath(threeGoodsDto.getUfSavePath());
            uploadFiles.setType(8);
            uploadFilesMapper.insert(uploadFiles);
            Integer id = uploadFilesMapper.getLastId();
            goods.setGoodsUfId(id);
        }
        goods.setIsPrint(1);
        goods.setUpdateTime(new Date());
        if (threeGoodsDto.getRemark() != null) {
            goods.setRemark(threeGoodsDto.getRemark());
        }
        goodsMapper.updateThreeGoodsInfo(goods);
        GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(threeGoodsDto.getGoodsId());
        if (threeGoodsDto.getActualLong() != null && threeGoodsDto.getActualLong() > 0 && threeGoodsDto.getActualWidth() != null && threeGoodsDto.getActualWidth() > 0
                && threeGoodsDto.getActualHeight() != null && threeGoodsDto.getActualHeight() > 0 && threeGoodsDto.getActualWeight() != null && threeGoodsDto.getActualWeight() > 0) {
            goodsDetails.setActualLong(threeGoodsDto.getActualLong());
            goodsDetails.setActualWidth(threeGoodsDto.getActualWidth());
            goodsDetails.setActualHeight(threeGoodsDto.getActualHeight());
            goodsDetails.setActualWeight(threeGoodsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        goodsDetails.setUpdateTime(goods.getUpdateTime());
        goodsDetailsMapper.updateThreeGoodsDetailsInfo(goodsDetails);
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(threeGoodsDto.getGoodsId());
        logisticInfo.setCreateTime(new Date());

        AdminUserDto adminUserDto = adminUserMapper.selectEditAdminUser(Integer.parseInt(user.getAdminUId()));
        if (adminUserDto != null && adminUserDto.getAdminName() != null) {
            logisticInfo.setOperateName(adminUserDto.getAdminName());
        }
        if(adminUserDto!=null&&adminUserDto.getAttribution()!=null&&adminUserDto.getAttribution().equals("新加坡")){
            logisticInfo.setOperateResult(adminUserDto.getAttribution()+"发货仓：揽收入库成功，待打包");
        }else if(adminUserDto!=null&&adminUserDto.getAttribution()!=null){
            logisticInfo.setOperateResult(adminUserDto.getAttribution()+"：揽收入库成功，待打包");
        }
        logisticInfo.setOperateComment("货物入库成功");
        logisticInfo.setOperateType("货物入库成功");
        logisticInfo.setAdminUid(Integer.parseInt(user.getAdminUId()));
        logisticInfoMapper.insert(logisticInfo);
        return "1";
    }

    //后台转运订单入库
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertTransshipmentGoodsInfo(TransshipmentGoodsDto transshipmentGoodsDto) {
        Goods goods = new Goods();
        goods.setFrom("转运订单");
        goods.setDeliveryNumber("04" + getRandomCode());
        goods.setTripartiteNumber(transshipmentGoodsDto.getTripartiteNumber());
        goods.setGoodType(transshipmentGoodsDto.getGoodType());
        goods.setStatus((byte) 1);
        goods.setGoodType(transshipmentGoodsDto.getGoodType());
        goods.setIsSpecialGoods(transshipmentGoodsDto.getIsSpecialGoods());
        goods.setUpdateTime(new Date());
        WarehousePositions warehousePositions = warehousePositionsMapper.selectWarehousePositionsbyWpNumber(transshipmentGoodsDto.getIntoWpNumber());
        goods.setIntoWarehouseId(warehousePositions.getWarehouseId());
        goods.setIntoWarehousePositionsId(warehousePositions.getWpId());
        goods.setTransportType("普通转运");
        if (transshipmentGoodsDto.getUfSavePath() != null) {
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUfSavePath(transshipmentGoodsDto.getUfSavePath());
            uploadFiles.setType(8);
            uploadFilesMapper.insert(uploadFiles);
            Integer id = uploadFilesMapper.getLastId();
            goods.setGoodsUfId(id);
        }
        goods.setIsPrint(1);
        goods.setAddTime(new Date());
        AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
        goods.setTotalGoods(1);
        if (transshipmentGoodsDto.getRemark() != null) {
            goods.setRemark(transshipmentGoodsDto.getRemark());
        }
        goods.setWarehouseId(warehousePositions.getWarehouseId());
        goodsMapper.insert(goods);
        GoodsDetails goodsDetails = new GoodsDetails();
        int goodsId = goodsMapper.selectGoodsId();
        goodsDetails.setGoodsId(goodsId);
        AppUser appUser = appUserMapper.selectAppUserByNumber(transshipmentGoodsDto.getNumber());
        goodsDetails.setAppUserId(appUser.getAppUserId());
        if (transshipmentGoodsDto.getActualLong() != null && transshipmentGoodsDto.getActualLong() > 0
                && transshipmentGoodsDto.getActualWidth() != null && transshipmentGoodsDto.getActualWidth() > 0
                && transshipmentGoodsDto.getActualHeight() != null && transshipmentGoodsDto.getActualHeight() > 0
                && transshipmentGoodsDto.getActualWeight() != null && transshipmentGoodsDto.getActualWeight() > 0) {
            goodsDetails.setActualLong(transshipmentGoodsDto.getActualLong());
            goodsDetails.setActualWidth(transshipmentGoodsDto.getActualWidth());
            goodsDetails.setActualHeight(transshipmentGoodsDto.getActualHeight());
            goodsDetails.setActualWeight(transshipmentGoodsDto.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        goodsDetails.setWarningState("无异常");
        goodsDetails.setGoodState("已到仓");
        goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
        if (transshipmentGoodsDto.getCategory() != null && transshipmentGoodsDto.getCategory().equals("Sensitive")) {
            goodsDetails.setCategory("敏感");
        } else if (transshipmentGoodsDto.getCategory() != null && transshipmentGoodsDto.getCategory().equals("Ordinary")) {
            goodsDetails.setCategory("普通");
        } else {
            goodsDetails.setCategory(transshipmentGoodsDto.getCategory());
        }
        goodsDetailsMapper.insert(goodsDetails);
        LogisticInfo logisticInfo = new LogisticInfo();
        logisticInfo.setGoodsId(goodsId);
        logisticInfo.setCreateTime(new Date());
        AdminUserDto adminUserDto = adminUserMapper.selectEditAdminUser(Integer.parseInt(user.getAdminUId()));
        if (adminUserDto != null && adminUserDto.getAdminName() != null) {
            logisticInfo.setOperateName(adminUserDto.getAdminName());
        }
        if(adminUserDto!=null&&adminUserDto.getAttribution()!=null&&adminUserDto.getAttribution().equals("新加坡")){
            logisticInfo.setOperateResult(adminUserDto.getAttribution()+"发货仓：揽收入库成功，待打包");
        }else if(adminUserDto!=null&&adminUserDto.getAttribution()!=null){
            logisticInfo.setOperateResult(adminUserDto.getAttribution()+"：揽收入库成功，待打包");
        }
        logisticInfo.setOperateComment("货物入库成功");
        logisticInfo.setOperateType("货物入库成功");
        logisticInfo.setAdminUid(Integer.parseInt(user.getAdminUId()));
        logisticInfoMapper.insert(logisticInfo);
        return "" + goodsId;
    }

    //后台正常货物编辑前查看
    @Override
    @Transactional(rollbackFor = Exception.class)
    public GoodsAndGoodsDetails getGoodsAndGoodsDetailsById(Integer goodsId) {
            GoodsAndGoodsDetails goodsAndGoodsDetails = new GoodsAndGoodsDetails();
            Goods goods = goodsMapper.selectGoodsById(goodsId);
            goodsAndGoodsDetails.setGoodsId(goodsId);
            goodsAndGoodsDetails.setTripartiteNumber(goods.getTripartiteNumber());
            if (goods.getGoodType() != null) {
                goodsAndGoodsDetails.setGoodType(goods.getGoodType());
            }
            if (goods.getZipCode() != null) {
                goodsAndGoodsDetails.setZipCode(goods.getZipCode());
            }
            if (goods.getIsSpecialGoods() != null) {
                goodsAndGoodsDetails.setIsSpecialGoods(goods.getIsSpecialGoods());
            }
            if (goods.getExitWay() != null) {
                goodsAndGoodsDetails.setExitWay(goods.getExitWay());
            }
            if (goods.getIntoWpNumber() != null) {
                goodsAndGoodsDetails.setIntoWpNumber(goods.getIntoWpNumber());
            }
            if (goods.getOutWpNumber() != null) {
                goodsAndGoodsDetails.setOutWpNumber(goods.getOutWpNumber());
            }
            if (goods.getRemark() != null) {
                goodsAndGoodsDetails.setRemark(goods.getRemark());
            }
            if (goods.getBagNumber() != null){
                goodsAndGoodsDetails.setBagNumber(goods.getBagNumber());
            }
            GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goodsId);
            if (goodsDetails.getReceiptContact() != null) {
                goodsAndGoodsDetails.setReceiptContact(goodsDetails.getReceiptContact());
            }
            if (goodsDetails.getReceiptAddress() != null) {
                goodsAndGoodsDetails.setReceiptAddress(goodsDetails.getReceiptAddress());
            }
            if (goodsDetails.getReceiptContactMobile() != null) {
                goodsAndGoodsDetails.setReceiptContactMobile(goodsDetails.getReceiptContactMobile());
            }
            if (goodsDetails.getShipContact() != null) {
                goodsAndGoodsDetails.setShipContact(goodsDetails.getShipContact());
            }
            if (goodsDetails.getShipAddress() != null) {
                goodsAndGoodsDetails.setShipAddress(goodsDetails.getShipAddress());
            }
            if (goodsDetails.getShipContactMobile() != null) {
                goodsAndGoodsDetails.setShipContactMobile(goodsDetails.getShipContactMobile());
            }
            if (goodsDetails.getActualLong() != null) {
                goodsAndGoodsDetails.setActualLong(goodsDetails.getActualLong());
            }
            if (goodsDetails.getActualWidth() != null) {
                goodsAndGoodsDetails.setActualWidth(goodsDetails.getActualWidth());
            }
            if (goodsDetails.getActualHeight() != null) {
                goodsAndGoodsDetails.setActualHeight(goodsDetails.getActualHeight());
            }
            if (goodsDetails.getActualWeight() != null) {
                goodsAndGoodsDetails.setActualWeight(goodsDetails.getActualWeight());
            }
            if (goodsDetails.getAppUserId() > 0) {
                AppUser appUser = appUserMapper.selectAppUserByUserId(goodsDetails.getAppUserId());
                if (appUser != null && appUser.getNumber() != null) {
                    goodsAndGoodsDetails.setNumber(appUser.getNumber());
                }
            }
            if (goodsDetails.getCategory() != null) {
                goodsAndGoodsDetails.setCategory(goodsDetails.getCategory());
            }
            if (goodsDetails.getIsUrgent() != null) {
                goodsAndGoodsDetails.setIsUrgent(goodsDetails.getIsUrgent());
            }
            if (goodsDetails.getItemValue() != null) {
                goodsAndGoodsDetails.setItemValue(goodsDetails.getItemValue());
            }
            return goodsAndGoodsDetails;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateGoodsAndGoodsDetailsInfo(GoodsAndGoodsDetails goodsAndGoodsDetails) {
        String str = "0";
        Goods goods = goodsMapper.selectGoodsById(goodsAndGoodsDetails.getGoodsId());
        GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailById(goodsAndGoodsDetails.getGoodsId());
        List<LogisticInfo> logisticInfoList = logisticInfoMapper.selectGoodsLogisticInfoByGoodsId(goodsAndGoodsDetails.getGoodsId());
        for (LogisticInfo logisticInfo : logisticInfoList
        ) {
            if (logisticInfo != null && logisticInfo.getOperateType() != null && logisticInfo.getOperateType().equals("货物打包成功")) {
                goods.setStatus((byte) 2);
                goodsDetails.setWarningState("异常");
                goodsDetails.setAbnormalText("在货物打包后更改货物信息，导致货物异常");
                GoodsWarning goodsWarning = goodsWarningMapper.selectGoodsWarningByGoodsId(goodsAndGoodsDetails.getGoodsId());
                if (goodsWarning == null) {
                    GoodsWarning goodsWarning1 = new GoodsWarning();
                    goodsWarning1.setGoodsId(goodsAndGoodsDetails.getGoodsId());
                    goodsWarning1.setWarningComment("在货物打包后更改货物信息，导致货物异常");
                    goodsWarning1.setWarningType("货物打包后的信息变更");
                    goodsWarning1.setWarningState("异常");
                    goodsWarning1.setCreateTime(new Date());
                    goodsWarning1.setDelStatus(1);
                    goodsWarningMapper.insert(goodsWarning1);
                } else {
                    goodsWarning.setGoodsId(goodsAndGoodsDetails.getGoodsId());
                    goodsWarning.setWarningComment("在货物打包后更改货物信息，导致货物异常");
                    goodsWarning.setWarningType("货物打包后的信息变更");
                    goodsWarning.setWarningState("异常");
                    goodsWarning.setCreateTime(new Date());
                    goodsWarning.setDelStatus(1);
                    goodsWarningMapper.updateByPrimaryKey(goodsWarning);
                }
                str = "1";
            } else {
                str = "2";
            }
        }
        goods.setTripartiteNumber(goodsAndGoodsDetails.getTripartiteNumber());
        goods.setGoodType(goodsAndGoodsDetails.getGoodType());
        goods.setIsSpecialGoods(goodsAndGoodsDetails.getIsSpecialGoods());
        goods.setExitWay(goodsAndGoodsDetails.getExitWay());
        goods.setRemark(goodsAndGoodsDetails.getRemark());
        goods.setZipCode(goodsAndGoodsDetails.getZipCode());
        goods.setUpdateTime(new Date());
        if (goodsAndGoodsDetails.getIntoWpNumber() != null && !(goodsAndGoodsDetails.getIntoWpNumber().equals(""))) {
            WarehousePositions warehousePositions = warehousePositionsMapper.selectWarehousePositionsbyWpNumber(goodsAndGoodsDetails.getIntoWpNumber());
            if (warehousePositions != null && warehousePositions.getWarehouseId() != null && warehousePositions.getWpId() != null) {
                goods.setIntoWarehouseId(warehousePositions.getWarehouseId());
                goods.setIntoWarehousePositionsId(warehousePositions.getWpId());
            }
        }
        if (goodsAndGoodsDetails.getOutWpNumber() != null && !(goodsAndGoodsDetails.getOutWpNumber().equals(""))) {
            WarehousePositions warehousePositions1 = warehousePositionsMapper.selectWarehousePositionsbyWpNumber(goodsAndGoodsDetails.getIntoWpNumber());
            if (warehousePositions1 != null && warehousePositions1.getWarehouseId() != null && warehousePositions1.getWpId() != null) {
                goods.setOutWarehouseId(warehousePositions1.getWarehouseId());
                goods.setOutWarehousePositionsId(warehousePositions1.getWpId());
            }
        }
        Bag bag = bagMapper.selectBagByNumber(goodsAndGoodsDetails.getBagNumber());
        goods.setBagId(bag.getBagId());
        goodsMapper.updateGoodsInformation(goods);
        SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(goodsAndGoodsDetails.getZipCode());
        if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
            goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
        }
        if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
            goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
        }
        if (goodsAndGoodsDetails.getActualLong() != null && goodsAndGoodsDetails.getActualLong() > 0
                && goodsAndGoodsDetails.getActualWidth() != null && goodsAndGoodsDetails.getActualWidth() > 0
                && goodsAndGoodsDetails.getActualHeight() != null && goodsAndGoodsDetails.getActualHeight() > 0
                && goodsAndGoodsDetails.getActualWeight() != null && goodsAndGoodsDetails.getActualWeight() > 0) {
            goodsDetails.setActualLong(goodsAndGoodsDetails.getActualLong());
            goodsDetails.setActualWidth(goodsAndGoodsDetails.getActualWidth());
            goodsDetails.setActualHeight(goodsAndGoodsDetails.getActualHeight());
            goodsDetails.setActualWeight(goodsAndGoodsDetails.getActualWeight());
        } else {
            return "长宽高重量不能小于等于零或为空";
        }
        if (goodsAndGoodsDetails.getShipAddress() != null && !(goodsAndGoodsDetails.getShipAddress().equals(""))) {
            goodsDetails.setShipAddress(goodsAndGoodsDetails.getShipAddress());
        } else {
            goodsDetails.setShipAddress(null);
        }
        if (goodsAndGoodsDetails.getShipContact() != null && !(goodsAndGoodsDetails.getShipContact().equals(""))) {
            goodsDetails.setShipContact(goodsAndGoodsDetails.getShipContact());
        } else {
            goodsDetails.setShipContact(null);
        }
        if (goodsAndGoodsDetails.getShipContactMobile() != null && !(goodsAndGoodsDetails.getShipContact().equals(""))) {
            goodsDetails.setShipContactMobile(goodsAndGoodsDetails.getShipContactMobile());
        } else {
            goodsDetails.setShipContactMobile(null);
        }
        if (goodsAndGoodsDetails.getReceiptAddress() != null && !(goodsAndGoodsDetails.getReceiptAddress().equals(""))) {
            goodsDetails.setReceiptAddress(goodsAndGoodsDetails.getReceiptAddress());
        } else {
            goodsDetails.setReceiptAddress(null);
        }
        if (goodsAndGoodsDetails.getReceiptContact() != null && !(goodsAndGoodsDetails.getReceiptContact().equals(""))) {
            goodsDetails.setReceiptContact(goodsAndGoodsDetails.getReceiptContact());
        } else {
            goodsDetails.setReceiptContact(null);
        }
        if (goodsAndGoodsDetails.getReceiptContactMobile() != null && !(goodsAndGoodsDetails.getReceiptContactMobile().equals(""))) {
            goodsDetails.setReceiptContactMobile(goodsAndGoodsDetails.getReceiptContactMobile());
        } else {
            goodsDetails.setReceiptContactMobile(null);
        }
        if (goodsAndGoodsDetails.getItemValue() != null) {
            goodsDetails.setItemValue(goodsAndGoodsDetails.getItemValue());
        }
        if (goods.getFrom().equals("转运订单")) {
            if (goodsAndGoodsDetails.getCategory() != null && goodsAndGoodsDetails.getCategory().equals("Sensitive")) {
                goodsDetails.setCategory("敏感");
            } else if (goodsAndGoodsDetails.getCategory() != null && goodsAndGoodsDetails.getCategory().equals("Ordinary")) {
                goodsDetails.setCategory("普通");
            } else {
                goodsDetails.setCategory(goodsAndGoodsDetails.getCategory());
            }
            goodsDetails.setIsUrgent(goodsAndGoodsDetails.getIsUrgent());
            if (goodsAndGoodsDetails.getNumber() != null) {
                //找不到用户编号
                AppUser appUser = appUserMapper.selectAppUserByNumber(goodsAndGoodsDetails.getNumber());
                goodsDetails.setAppUserId(appUser.getAppUserId());
            }
        }
        AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
        goodsDetails.setAdminUid(Integer.parseInt(user.getAdminUId()));
        goodsDetails.setUpdateTime(goods.getUpdateTime());
        goodsDetailsMapper.updateGoodsDetailsInformation(goodsDetails);
        return str;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectThreeGoodsList(int adminUserTypeId, CodGoodsDto codGoodsDto) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tripartiteNumber", codGoodsDto.getTripartiteNumber());
            map.put("deliveryNumber", codGoodsDto.getDeliveryNumber());
            map.put("addTime", codGoodsDto.getAddTime());
            map.put("endTime", codGoodsDto.getEndTime());
            map.put("adminUserTypeId", adminUserTypeId);
            return goodsMapper.selectThreeGoodsList(map);
            //解决输入多快递单号查询，用逗号隔开
           /* List<Goods> goodsList = new ArrayList<>();
            if(codGoodsDto.getDeliveryNumber()!=null){
                String[] deliveryNumber1 = codGoodsDto.getDeliveryNumber().split(",");
                if(deliveryNumber1.length>0){
                    for(int i =0;i<deliveryNumber1.length;i++){
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("tripartiteNumber",codGoodsDto.getTripartiteNumber());
                        map.put("deliveryNumber",deliveryNumber1[i]);
                        map.put("addTime",codGoodsDto.getAddTime());
                        map.put("endTime",codGoodsDto.getEndTime());
                        map.put("adminUserTypeId",adminUserTypeId);
                        List<Goods> goodsList1 = goodsMapper.selectThreeGoodsList(map);
                        for (Goods goods:goodsList1
                        ) {
                            goodsList.add(goods);
                        }
                    }
                }else{
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("tripartiteNumber",codGoodsDto.getTripartiteNumber());
                    map.put("deliveryNumber",codGoodsDto.getDeliveryNumber());
                    map.put("addTime",codGoodsDto.getAddTime());
                    map.put("endTime",codGoodsDto.getEndTime());
                    map.put("adminUserTypeId",adminUserTypeId);
                    return goodsMapper.selectThreeGoodsList(map);
                }
                return goodsList;
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("tripartiteNumber",codGoodsDto.getTripartiteNumber());
            map.put("deliveryNumber",codGoodsDto.getDeliveryNumber());
            map.put("addTime",codGoodsDto.getAddTime());
            map.put("endTime",codGoodsDto.getEndTime());
            map.put("adminUserTypeId",adminUserTypeId);
            return goodsMapper.selectThreeGoodsList(map);*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountThreeGoodsList(int adminUserTypeId, CodGoodsDto codGoodsDto) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tripartiteNumber", codGoodsDto.getTripartiteNumber());
            map.put("deliveryNumber", codGoodsDto.getDeliveryNumber());
            map.put("addTime", codGoodsDto.getAddTime());
            map.put("endTime", codGoodsDto.getEndTime());
            map.put("adminUserTypeId", adminUserTypeId);
            return goodsMapper.selectCountThreeGoodsList(map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertCodGoodsList(String fileName, MultipartFile file) throws IOException {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return "上传文件格式不正确";
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Sheet sheet = null;
        if (isExcel2003) {
            HSSFWorkbook wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        } else {
            XSSFWorkbook wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            int value = i + 1;
            if (row.getCell(0) == null) {
                return "第" + value + "行的三方物流单号不能为空";
            }else if(row.getCell(0) != null) {
                row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                String tripartiteNumber = row.getCell(0).getStringCellValue();
                List<GoodsDetails> goodsDetailsList = goodsDetailsMapper.selectGoodsDetailByTripartiteNumber(tripartiteNumber);
                if (goodsDetailsList.size() > 0) {
                    return "三方物流单号:" + tripartiteNumber + "为重复数据，如果数据有错误，请先删除旧数据再进行导入";
                }
            }
            String regEx = "^[A-Za-z0-9]+$";
            if (row.getCell(0).getStringCellValue().matches(regEx)) {
                if (row.getCell(1) == null) {
                    return "第" + value + "行的邮编不能为空";
                }else if(row.getCell(1) != null){
                    row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String zipCode = row.getCell(1).getStringCellValue();
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if(singaporeAreaBuilding==null){
                        String url = "https://developers.onemap.sg/commonapi/search?searchVal="+zipCode+"&returnGeom=Y&getAddrDetails=Y&pageNum=1";
                        List<Results> list;
                        CloseableHttpResponse response ;
                        try{
                            // 2.设置请求方式和请求信息
                            HttpGet httpGet = new HttpGet(url);
                   /*         // 3.执行请求
                            CloseableHttpResponse response = httpClient.execute(httpGet);*/
                            RequestConfig config = RequestConfig.custom()

                                    .setSocketTimeout(1*1000) // socket套接字超时，毫秒。

                                    .setConnectionRequestTimeout(1*1000) //使用连接池来管理连接时，从连接池获取连接的超时时间，毫秒。

                                    .setConnectTimeout(5*1000) // 连接建立超时，毫秒。

                                    .build();

                            CloseableHttpClient httpClient = HttpClients.custom()

                                    .setDefaultRequestConfig(config) //

                                    .build();

                            response = httpClient.execute(httpGet); // 执行请求
                        }catch (Exception e){
                            return "第" + value + "行的邮编在网站上检索超时，可重新导入数据";
                        }
                        if(response == null){
                            return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                        }
                        // 4.获取返回值
                        String s = null;
                        try {
                            s = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 5.截取json数组
                        int a = s.indexOf("results\":");
                        String substring = s.substring(a+9, s.length() - 1);
//        如果截取的字符串为空或者没有内容就跳过
                        if(substring==null || substring.equals("[]")){
                            return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                        }
//        将json数组转化为集合
                        list = JSONObject.parseArray(substring, Results.class);
                        if (list.size()>0){
                            for (Results results1 :list){
                                if("NIL".equals(results1.getPOSTAL())){
                                    return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                                }
                                SingaporeAreaBuilding singaporeAreaBuilding2 = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                                if(singaporeAreaBuilding2==null) {
                                    SingaporeAreaBuilding singaporeAreaBuilding1 = new SingaporeAreaBuilding();
                                    singaporeAreaBuilding1.setSaId(1);
                                    singaporeAreaBuilding1.setSaBuildingName(results1.getBUILDING());
                                    singaporeAreaBuilding1.setSaBuildingLat(results1.getLATITUDE());
                                    singaporeAreaBuilding1.setSaBuildingLng(results1.getLONGITUDE());
                                    singaporeAreaBuilding1.setSaZipCode(results1.getPOSTAL());
                                    singaporeAreaBuilding1.setSaBuildingAddress(results1.getADDRESS());
                                    int rallyPointId = RallyPointIdUtil.selectRallyPointId(zipCode);
                                    if (rallyPointId > 0) {
                                        singaporeAreaBuilding1.setRallyPointId(rallyPointId);
                                    } else if (rallyPointId == 0) {
                                        return "第" + value + "行的邮编在网站上检索到了，但无对应分区";
                                    }
                                    singaporeAreaBuildingMapper.insert(singaporeAreaBuilding1);
                                }
                            }

                        }
                    }

                }
                if (row.getCell(3) == null) {
                    return "第" + value + "行的收货人地址不能为空";
                }
                if (row.getCell(4) == null) {
                    return "第" + value + "行的收货联系人不能为空";
                }
                if (row.getCell(5) == null) {
                    return "第" + value + "行的收货人联系方式不能为空";
                }
                if (row.getCell(8) == null) {
                    return "第" + value + "行的出境方式不能为空";
                }
                if (row.getCell(11) == null) {
                    return "第" + value + "行的是否货到付款不能为空";
                }else if(row.getCell(11) != null){
                    row.getCell(11).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String isArrivalPay = row.getCell(11).getStringCellValue();
                    if (!(isArrivalPay.equals("是"))) {
                        return "此列表为货物付款货物列表，请导入货到付款的货物";
                    }
                }
                for (int r = 0; r <= 11; r++) {
                    if (row.getCell(2) == null ) {
                        row.createCell(2).setCellValue(new HSSFRichTextString(String.valueOf(0)));

                    }
                    if (row.getCell(10) == null) {
                        row.createCell(10).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                    }
                    if (row.getCell(r) == null) {
                        row.createCell(r).setCellValue(new HSSFRichTextString(String.valueOf("")));
                    }
                    if (row.getCell(9) == null) {
                        return "第" + value + "行为空，共计箱数必须填写";
                    }
                }
            } else {
                return "第" + value + "行的三方物流单号格式不正确或有空格";
            }
        }
        Set<String> sett = new HashSet<String>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            if (!sett.add(row.getCell(0).getStringCellValue())) {
                int value = i + 1;
                return "第" + value + "行的三方物流单号为重复值，导入失败";
            }
        }

        //循环工作表Sheet
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            int line = r + 1;
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteNumber = row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
            String zipCode = row.getCell(1).getStringCellValue();
            row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteWeight = row.getCell(2).getStringCellValue();
            row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptAddress = row.getCell(3).getStringCellValue();
            row.getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptContact = row.getCell(4).getStringCellValue();
            row.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptContactMobile = row.getCell(5).getStringCellValue();
            row.getCell(6).setCellType(HSSFCell.CELL_TYPE_STRING);
            String goodType = row.getCell(6).getStringCellValue();
            row.getCell(7).setCellType(HSSFCell.CELL_TYPE_STRING);
            String shipper = row.getCell(7).getStringCellValue();
            row.getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);
            String exitWay = row.getCell(8).getStringCellValue();
            row.getCell(9).setCellType(HSSFCell.CELL_TYPE_STRING);
            String totalGoods = row.getCell(9).getStringCellValue();
            row.getCell(10).setCellType(HSSFCell.CELL_TYPE_STRING);
            String itemValue = row.getCell(10).getStringCellValue();
            row.getCell(11).setCellType(HSSFCell.CELL_TYPE_STRING);
            String isArrivalPay = row.getCell(11).getStringCellValue();
                if (Integer.parseInt(totalGoods) == 1) {
                    Goods goods = new Goods();
                    goods.setFrom("三方货单");
                    goods.setStatus((byte) 1);
                    goods.setDeliveryNumber("04" + getRandomCode());
                    goods.setTripartiteNumber(tripartiteNumber);
                    goods.setZipCode(zipCode);
                    goods.setGoodType(goodType);
                    goods.setAddTime(new Date());
                    goods.setExitWay(exitWay);
                    goods.setTotalGoods(1);
                    AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                    goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                    goods.setAdminUserTypeId(user.getAdminUserTypeId());
                    //新增导入人员所属仓库id
                    goods.setWarehouseId(user.getWarehouseId());
                    goodsMapper.insert(goods);
                    GoodsDetails goodsDetails = new GoodsDetails();
                    goodsDetails.setGoodsId(goods.getGoodsId());
                    if (Float.valueOf(tripartiteWeight) != 0) {
                        goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                    }
                    goodsDetails.setReceiptAddress(receiptAddress);
                    String receiptContact1 = receiptContact.trim();
                    goodsDetails.setReceiptContact(receiptContact1);
                    goodsDetails.setReceiptContactMobile(receiptContactMobile);
                    goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails.setShipper(shipper);
                    goodsDetails.setWarningState("无异常");
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                        goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                    }
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                        goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    if (Double.valueOf(itemValue) > 0) {
                        goodsDetails.setItemValue(new BigDecimal(Double.valueOf(itemValue)));
                    }
                    if (isArrivalPay.equals("是")) {
                        goodsDetails.setIsArrivalPay(2);
                    }
                    goodsDetailsMapper.insert(goodsDetails);
                } else {
                    Goods goods = new Goods();
                    goods.setFrom("三方货单");
                    goods.setStatus((byte) 1);
                    String deliveryNumber = "04" + getRandomCode();
                    goods.setDeliveryNumber(deliveryNumber);
                    goods.setTripartiteNumber(tripartiteNumber);
                    goods.setZipCode(zipCode);
                    goods.setGoodType(goodType);
                    goods.setAddTime(new Date());
                    goods.setExitWay(exitWay);
                    goods.setTotalGoods(Integer.parseInt(totalGoods));
                    AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                    goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                    goods.setAdminUserTypeId(user.getAdminUserTypeId());
                    //新增导入人员所属仓库id
                    goods.setWarehouseId(user.getWarehouseId());
                    goodsMapper.insert(goods);
                    GoodsDetails goodsDetails = new GoodsDetails();
                    goodsDetails.setGoodsId(goods.getGoodsId());

                    if (Float.valueOf(tripartiteWeight) != 0) {
                        goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                    }
                    goodsDetails.setReceiptAddress(receiptAddress);
                    String receiptContact1 = receiptContact.trim();
                    goodsDetails.setReceiptContact(receiptContact1);
                    goodsDetails.setReceiptContactMobile(receiptContactMobile);
                    goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails.setShipper(shipper);
                    goodsDetails.setWarningState("无异常");
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                        goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                    }
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                        goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    if (Double.valueOf(itemValue) != 0) {
                        goodsDetails.setItemValue(new BigDecimal(Double.valueOf(itemValue)));
                    }
                    if (isArrivalPay.equals("是")) {
                        goodsDetails.setIsArrivalPay(2);
                    }
                    goodsDetailsMapper.insert(goodsDetails);
                    for (int i = 1; i <= Integer.parseInt(totalGoods); i++) {
                        Goods goods1 = new Goods();
                        goods1.setFrom("三方货单");
                        goods1.setStatus((byte) 1);
                        goods1.setDeliveryNumber(deliveryNumber + "-" + i);
                        goods1.setTripartiteNumber(tripartiteNumber);
                        goods1.setZipCode(zipCode);
                        goods1.setGoodType(goodType);
                        goods1.setAddTime(new Date());
                        goods1.setExitWay(exitWay);
                        goods1.setTotalGoods(1);
                        goods1.setAdminUid(Integer.parseInt(user.getAdminUId()));
                        goods1.setAdminUserTypeId(user.getAdminUserTypeId());
                        //新增导入人员所属仓库id
                        goods1.setWarehouseId(user.getWarehouseId());
                        goodsMapper.insert(goods1);
                        GoodsDetails goodsDetails1 = new GoodsDetails();
                        goodsDetails1.setGoodsId(goods1.getGoodsId());
                        if (Float.valueOf(tripartiteWeight) != 0) {
                            goodsDetails1.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                        }
                        goodsDetails1.setReceiptAddress(receiptAddress);
                        String receiptContact2 = receiptContact.trim();
                        goodsDetails1.setReceiptContact(receiptContact2);
                        goodsDetails1.setReceiptContactMobile(receiptContactMobile);
                        goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                        goodsDetails1.setShipper(shipper);
                        goodsDetails1.setWarningState("无异常");
                        if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                            goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                        }
                        if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                            goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                        }
                        if (isArrivalPay.equals("是")) {
                            goodsDetails1.setIsArrivalPay(2);
                        }
                        //未平均分配货值
                        goodsDetailsMapper.insert(goodsDetails1);
                    }
                }

        }
        return "1";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertCodGoods(CodGoods codGoods) {
        List<GoodsDetails> goodsDetailsList = goodsDetailsMapper.selectGoodsDetailByTripartiteNumber(codGoods.getTripartiteNumber());
        if (goodsDetailsList.size() == 0) {
            if (codGoods.getTotalGoods() == 1) {
                Goods goods = new Goods();
                goods.setFrom("三方货单");
                goods.setStatus((byte) 1);
                goods.setDeliveryNumber("04" + getRandomCode());
                goods.setTripartiteNumber(codGoods.getTripartiteNumber());
                goods.setZipCode(codGoods.getZipCode());
                goods.setGoodType(codGoods.getGoodType());
                goods.setAddTime(new Date());
                goods.setExitWay(codGoods.getExitWay());
                goods.setTotalGoods(1);
                AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                goods.setAdminUserTypeId(user.getAdminUserTypeId());
                goodsMapper.insert(goods);
                GoodsDetails goodsDetails = new GoodsDetails();
                goodsDetails.setGoodsId(goods.getGoodsId());
                if (Float.valueOf(codGoods.getTripartiteWeight()) != 0) {
                    goodsDetails.setTripartiteWeight(Float.valueOf(codGoods.getTripartiteWeight()));
                }
                goodsDetails.setReceiptAddress(codGoods.getReceiptAddress());
                String receiptContact1 = codGoods.getReceiptContact().trim();
                goodsDetails.setReceiptContact(receiptContact1);
                goodsDetails.setReceiptContactMobile(codGoods.getReceiptContactMobile());
                goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                goodsDetails.setShipper(codGoods.getShipper());
                goodsDetails.setWarningState("无异常");
                SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(goods.getZipCode());
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                    goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                }
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                    goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                }
                if (codGoods.getItemValue().compareTo(new BigDecimal(0)) > 0) {
                    goodsDetails.setItemValue(codGoods.getItemValue());
                }
                goodsDetails.setIsArrivalPay(2);
                goodsDetailsMapper.insert(goodsDetails);
            } else {
                Goods goods = new Goods();
                goods.setFrom("三方货单");
                goods.setStatus((byte) 1);
                String deliveryNumber = "04" + getRandomCode();
                goods.setDeliveryNumber(deliveryNumber);
                goods.setTripartiteNumber(codGoods.getTripartiteNumber());
                goods.setZipCode(codGoods.getZipCode());
                goods.setGoodType(codGoods.getGoodType());
                goods.setAddTime(new Date());
                goods.setExitWay(codGoods.getExitWay());
                goods.setTotalGoods(codGoods.getTotalGoods());
                AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                goods.setAdminUserTypeId(user.getAdminUserTypeId());
                goodsMapper.insert(goods);
                GoodsDetails goodsDetails = new GoodsDetails();
                goodsDetails.setGoodsId(goods.getGoodsId());

                if (Float.valueOf(codGoods.getTripartiteWeight()) != 0) {
                    goodsDetails.setTripartiteWeight(Float.valueOf(codGoods.getTripartiteWeight()));
                }
                goodsDetails.setReceiptAddress(codGoods.getReceiptAddress());
                String receiptContact1 = codGoods.getReceiptContact().trim();
                goodsDetails.setReceiptContact(receiptContact1);
                goodsDetails.setReceiptContactMobile(codGoods.getReceiptContactMobile());
                goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                goodsDetails.setShipper(codGoods.getShipper());
                goodsDetails.setWarningState("无异常");
                SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(codGoods.getZipCode());
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                    goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                }
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                    goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                }
                if (codGoods.getItemValue().compareTo(new BigDecimal(0)) > 0) {
                    goodsDetails.setItemValue(codGoods.getItemValue());
                }
                goodsDetails.setIsArrivalPay(2);
                goodsDetailsMapper.insert(goodsDetails);
                for (int i = 1; i <= codGoods.getTotalGoods(); i++) {
                    Goods goods1 = new Goods();
                    goods1.setFrom("三方货单");
                    goods1.setStatus((byte) 1);
                    goods1.setDeliveryNumber(deliveryNumber + "-" + i);
                    goods1.setTripartiteNumber(codGoods.getTripartiteNumber());
                    goods1.setZipCode(codGoods.getZipCode());
                    goods1.setGoodType(codGoods.getGoodType());
                    goods1.setAddTime(new Date());
                    goods1.setExitWay(codGoods.getExitWay());
                    goods1.setTotalGoods(1);
                    goods1.setAdminUid(Integer.parseInt(user.getAdminUId()));
                    goods1.setAdminUserTypeId(user.getAdminUserTypeId());
                    goodsMapper.insert(goods1);
                    GoodsDetails goodsDetails1 = new GoodsDetails();
                    goodsDetails1.setGoodsId(goods1.getGoodsId());
                    if (Float.valueOf(codGoods.getTripartiteWeight()) != 0) {
                        goodsDetails1.setTripartiteWeight(Float.valueOf(codGoods.getTripartiteWeight()));
                    }
                    goodsDetails1.setReceiptAddress(codGoods.getReceiptAddress());
                    String receiptContact2 = codGoods.getReceiptContact().trim();
                    goodsDetails1.setReceiptContact(receiptContact2);
                    goodsDetails1.setReceiptContactMobile(codGoods.getReceiptContactMobile());
                    goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails1.setShipper(codGoods.getShipper());
                    goodsDetails1.setWarningState("无异常");
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                        goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                    }
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                        goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    goodsDetails1.setIsArrivalPay(2);
                    //未平均分配货值
                    goodsDetailsMapper.insert(goodsDetails1);
                }
            }

        } else {
            return "三方物流单号:" + codGoods.getTripartiteNumber() + "为重复数据，如果数据有错误，请先删除旧数据再进行导入";
        }
        return "1";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectOrdinaryGoodsList(int adminUserTypeId, OrdinaryGoodsDto ordinaryGoodsDto) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tripartiteNumber", ordinaryGoodsDto.getTripartiteNumber());
            map.put("deliveryNumber", ordinaryGoodsDto.getDeliveryNumber());
            map.put("addTime", ordinaryGoodsDto.getAddTime());
            map.put("endTime", ordinaryGoodsDto.getEndTime());
            map.put("adminUserTypeId", adminUserTypeId);
            return goodsMapper.selectOrdinaryGoodsList(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountOrdinaryGoodsList(int adminUserTypeId, OrdinaryGoodsDto ordinaryGoodsDto) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("tripartiteNumber", ordinaryGoodsDto.getTripartiteNumber());
            map.put("deliveryNumber", ordinaryGoodsDto.getDeliveryNumber());
            map.put("addTime", ordinaryGoodsDto.getAddTime());
            map.put("endTime", ordinaryGoodsDto.getEndTime());
            map.put("adminUserTypeId", adminUserTypeId);
            return goodsMapper.selectCountOrdinaryGoodsList(map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertOrdinaryGoodsList(String fileName, MultipartFile file) throws IOException {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return "上传文件格式不正确";
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Sheet sheet = null;
        if (isExcel2003) {
            HSSFWorkbook wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        } else {
            XSSFWorkbook wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            int value = i + 1;
            if (row.getCell(0) == null) {
                return "第" + value + "行的三方物流单号不能为空";
            }else if(row.getCell(0) != null) {
                row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
                String tripartiteNumber = row.getCell(0).getStringCellValue();
                List<GoodsDetails> goodsDetailsList = goodsDetailsMapper.selectGoodsDetailByTripartiteNumber(tripartiteNumber);
                if (goodsDetailsList.size() > 0) {
                    return "三方物流单号:" + tripartiteNumber + "为重复数据，如果数据有错误，请先删除旧数据再进行导入";
                }
            }
            String regEx = "^[A-Za-z0-9]+$";
            if (row.getCell(0).getStringCellValue().matches(regEx)) {
                if (row.getCell(1) == null) {
                    return "第" + value + "行的邮编不能为空";
                }else if(row.getCell(1) != null){
                    row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String zipCode = row.getCell(1).getStringCellValue();
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if(singaporeAreaBuilding==null){
                        String url = "https://developers.onemap.sg/commonapi/search?searchVal="+zipCode+"&returnGeom=Y&getAddrDetails=Y&pageNum=1";
                        List<Results> list;
                        CloseableHttpResponse response ;
                        try{
                            // 2.设置请求方式和请求信息
                            HttpGet httpGet = new HttpGet(url);
                   /*         // 3.执行请求
                            CloseableHttpResponse response = httpClient.execute(httpGet);*/
                            RequestConfig config = RequestConfig.custom()

                                    .setSocketTimeout(1*1000) // socket套接字超时，毫秒。

                                    .setConnectionRequestTimeout(1*1000) //使用连接池来管理连接时，从连接池获取连接的超时时间，毫秒。

                                    .setConnectTimeout(5*1000) // 连接建立超时，毫秒。

                                    .build();

                            CloseableHttpClient httpClient = HttpClients.custom()

                                    .setDefaultRequestConfig(config) //

                                    .build();

                            response = httpClient.execute(httpGet); // 执行请求
                        }catch (Exception e){
                            return "第" + value + "行的邮编在网站上检索超时，可重新导入数据";
                        }
                        if(response == null){
                            return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                        }
                        // 4.获取返回值
                        String s = null;
                        try {
                            s = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // 5.截取json数组
                        int a = s.indexOf("results\":");
                        String substring = s.substring(a+9, s.length() - 1);
//        如果截取的字符串为空或者没有内容就跳过
                        if(substring==null || substring.equals("[]")){
                            return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                        }
//        将json数组转化为集合
                        list = JSONObject.parseArray(substring, Results.class);
                        if (list.size()>0){
                            for (Results results1 :list){
                                if("NIL".equals(results1.getPOSTAL())){
                                    return "第" + value + "行的邮编在网站上未检索到，无法导入到系统中";
                                }
                                SingaporeAreaBuilding singaporeAreaBuilding2 = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                                if(singaporeAreaBuilding2==null) {
                                    SingaporeAreaBuilding singaporeAreaBuilding1 = new SingaporeAreaBuilding();
                                    singaporeAreaBuilding1.setSaId(1);
                                    singaporeAreaBuilding1.setSaBuildingName(results1.getBUILDING());
                                    singaporeAreaBuilding1.setSaBuildingLat(results1.getLATITUDE());
                                    singaporeAreaBuilding1.setSaBuildingLng(results1.getLONGITUDE());
                                    singaporeAreaBuilding1.setSaZipCode(results1.getPOSTAL());
                                    singaporeAreaBuilding1.setSaBuildingAddress(results1.getADDRESS());
                                    int rallyPointId = RallyPointIdUtil.selectRallyPointId(zipCode);
                                    if (rallyPointId > 0) {
                                        singaporeAreaBuilding1.setRallyPointId(rallyPointId);
                                    } else if (rallyPointId == 0) {
                                        return "第" + value + "行的邮编在网站上检索到了，但无对应分区";
                                    }
                                    singaporeAreaBuildingMapper.insert(singaporeAreaBuilding1);
                                }

                            }

                        }
                    }
                }
                if (row.getCell(3) == null) {
                    return "第" + value + "行的收货人地址不能为空";
                }
                if (row.getCell(4) == null) {
                    return "第" + value + "行的收货联系人不能为空";
                }
                if (row.getCell(5) == null) {
                    return "第" + value + "行的收货人联系方式不能为空";
                }
                if (row.getCell(8) == null) {
                    return "第" + value + "行的出境方式不能为空";
                }
                if (row.getCell(11) == null) {
                    return "第" + value + "行的是否货到付款不能为空";
                }else if(row.getCell(11) != null){
                    row.getCell(11).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String isArrivalPay = row.getCell(11).getStringCellValue();
                    if (!(isArrivalPay.equals("否"))) {
                        return "此列表为普通货物列表，请导入普通货物列表";
                    }
                }
                for (int r = 0; r <= 11; r++) {
                    if (row.getCell(2) == null ) {
                        row.createCell(2).setCellValue(new HSSFRichTextString(String.valueOf(0)));

                    }
                    if (row.getCell(10) == null) {
                        row.createCell(10).setCellValue(new HSSFRichTextString(String.valueOf(0)));
                    }

                    if (row.getCell(r) == null) {
                        row.createCell(r).setCellValue(new HSSFRichTextString(String.valueOf("")));
                    }
                    if (row.getCell(9) == null) {
                        return "第" + value + "行为空，共计箱数必须填写";
                    }
                }
            } else {
                return "第" + value + "行的三方物流单号格式不正确或有空格";
            }
        }
        Set<String> sett = new HashSet<String>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            if (!sett.add(row.getCell(0).getStringCellValue())) {
                int value = i + 1;
                return "第" + value + "行的三方物流单号为重复值，导入失败";
            }
        }

        //循环工作表Sheet
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            int line = r + 1;
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteNumber = row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
            String zipCode = row.getCell(1).getStringCellValue();
            row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
            String tripartiteWeight = row.getCell(2).getStringCellValue();
            row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptAddress = row.getCell(3).getStringCellValue();
            row.getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptContact = row.getCell(4).getStringCellValue();
            row.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
            String receiptContactMobile = row.getCell(5).getStringCellValue();
            row.getCell(6).setCellType(HSSFCell.CELL_TYPE_STRING);
            String goodType = row.getCell(6).getStringCellValue();
            row.getCell(7).setCellType(HSSFCell.CELL_TYPE_STRING);
            String shipper = row.getCell(7).getStringCellValue();
            row.getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);
            String exitWay = row.getCell(8).getStringCellValue();
            row.getCell(9).setCellType(HSSFCell.CELL_TYPE_STRING);
            String totalGoods = row.getCell(9).getStringCellValue();
            row.getCell(10).setCellType(HSSFCell.CELL_TYPE_STRING);
            String itemValue = row.getCell(10).getStringCellValue();
            row.getCell(11).setCellType(HSSFCell.CELL_TYPE_STRING);
            String isArrivalPay = row.getCell(11).getStringCellValue();

                if (Integer.parseInt(totalGoods) == 1) {
                    Goods goods = new Goods();
                    goods.setFrom("三方货单");
                    goods.setStatus((byte) 1);
                    goods.setDeliveryNumber("04" + getRandomCode());
                    goods.setTripartiteNumber(tripartiteNumber);
                    goods.setZipCode(zipCode);
                    goods.setGoodType(goodType);
                    goods.setAddTime(new Date());
                    goods.setExitWay(exitWay);
                    goods.setTotalGoods(1);
                    AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                    goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                    goods.setAdminUserTypeId(user.getAdminUserTypeId());
                    //新增导入人员所属仓库id
                    goods.setWarehouseId(user.getWarehouseId());
                    goodsMapper.insert(goods);
                    GoodsDetails goodsDetails = new GoodsDetails();
                    goodsDetails.setGoodsId(goods.getGoodsId());
                    if (Float.valueOf(tripartiteWeight) != 0) {
                        goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                    }
                    goodsDetails.setReceiptAddress(receiptAddress);
                    String receiptContact1 = receiptContact.trim();
                    goodsDetails.setReceiptContact(receiptContact1);
                    goodsDetails.setReceiptContactMobile(receiptContactMobile);
                    goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails.setShipper(shipper);
                    goodsDetails.setWarningState("无异常");
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                        goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                    }
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                        goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    if (Double.valueOf(itemValue) > 0) {
                        goodsDetails.setItemValue(new BigDecimal(Double.valueOf(itemValue)));
                    }
                    if (isArrivalPay.equals("否")) {
                        goodsDetails.setIsArrivalPay(1);
                    }
                    goodsDetailsMapper.insert(goodsDetails);
                } else {
                    Goods goods = new Goods();
                    goods.setFrom("三方货单");
                    goods.setStatus((byte) 1);
                    String deliveryNumber = "04" + getRandomCode();
                    goods.setDeliveryNumber(deliveryNumber);
                    goods.setTripartiteNumber(tripartiteNumber);
                    goods.setZipCode(zipCode);
                    goods.setGoodType(goodType);
                    goods.setAddTime(new Date());
                    goods.setExitWay(exitWay);
                    goods.setTotalGoods(Integer.parseInt(totalGoods));
                    AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                    goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                    goods.setAdminUserTypeId(user.getAdminUserTypeId());
                    //新增导入人员所属仓库id
                    goods.setWarehouseId(user.getWarehouseId());
                    goodsMapper.insert(goods);
                    GoodsDetails goodsDetails = new GoodsDetails();
                    goodsDetails.setGoodsId(goods.getGoodsId());

                    if (Float.valueOf(tripartiteWeight) != 0) {
                        goodsDetails.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                    }
                    goodsDetails.setReceiptAddress(receiptAddress);
                    String receiptContact1 = receiptContact.trim();
                    goodsDetails.setReceiptContact(receiptContact1);
                    goodsDetails.setReceiptContactMobile(receiptContactMobile);
                    goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails.setShipper(shipper);
                    goodsDetails.setWarningState("无异常");
                    SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(zipCode);
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                        goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                    }
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                        goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    if (Double.valueOf(itemValue) != 0) {
                        goodsDetails.setItemValue(new BigDecimal(Double.valueOf(itemValue)));
                    }
                    if (isArrivalPay.equals("否")) {
                        goodsDetails.setIsArrivalPay(1);
                    }
                    goodsDetailsMapper.insert(goodsDetails);
                    for (int i = 1; i <= Integer.parseInt(totalGoods); i++) {
                        Goods goods1 = new Goods();
                        goods1.setFrom("三方货单");
                        goods1.setStatus((byte) 1);
                        goods1.setDeliveryNumber(deliveryNumber + "-" + i);
                        goods1.setTripartiteNumber(tripartiteNumber);
                        goods1.setZipCode(zipCode);
                        goods1.setGoodType(goodType);
                        goods1.setAddTime(new Date());
                        goods1.setExitWay(exitWay);
                        goods1.setTotalGoods(1);
                        goods1.setAdminUid(Integer.parseInt(user.getAdminUId()));
                        goods1.setAdminUserTypeId(user.getAdminUserTypeId());
                        //新增导入人员所属仓库id
                        goods1.setWarehouseId(user.getWarehouseId());
                        goodsMapper.insert(goods1);
                        GoodsDetails goodsDetails1 = new GoodsDetails();
                        goodsDetails1.setGoodsId(goods1.getGoodsId());
                        if (Float.valueOf(tripartiteWeight) != 0) {
                            goodsDetails1.setTripartiteWeight(Float.valueOf(tripartiteWeight));
                        }
                        goodsDetails1.setReceiptAddress(receiptAddress);
                        String receiptContact2 = receiptContact.trim();
                        goodsDetails1.setReceiptContact(receiptContact2);
                        goodsDetails1.setReceiptContactMobile(receiptContactMobile);
                        goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                        goodsDetails1.setShipper(shipper);
                        goodsDetails1.setWarningState("无异常");
                        if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                            goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                        }
                        if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                            goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                        }
                        if (isArrivalPay.equals("否")) {
                            goodsDetails1.setIsArrivalPay(1);
                        }
                        //未平均分配货值
                        goodsDetailsMapper.insert(goodsDetails1);
                    }
                }

        }
        return "1";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertOrdinaryGoods(OrdinaryGoods ordinaryGoods) {
        List<GoodsDetails> goodsDetailsList = goodsDetailsMapper.selectGoodsDetailByTripartiteNumber(ordinaryGoods.getTripartiteNumber());
        if (goodsDetailsList.size() == 0) {
            if (ordinaryGoods.getTotalGoods() == 1) {
                Goods goods = new Goods();
                goods.setFrom("三方货单");
                goods.setStatus((byte) 1);
                goods.setDeliveryNumber("04" + getRandomCode());
                goods.setTripartiteNumber(ordinaryGoods.getTripartiteNumber());
                goods.setZipCode(ordinaryGoods.getZipCode());
                goods.setGoodType(ordinaryGoods.getGoodType());
                goods.setAddTime(new Date());
                goods.setExitWay(ordinaryGoods.getExitWay());
                goods.setTotalGoods(1);
                AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                goods.setAdminUserTypeId(user.getAdminUserTypeId());
                goodsMapper.insert(goods);
                GoodsDetails goodsDetails = new GoodsDetails();
                goodsDetails.setGoodsId(goods.getGoodsId());
                if (Float.valueOf(ordinaryGoods.getTripartiteWeight()) != 0) {
                    goodsDetails.setTripartiteWeight(Float.valueOf(ordinaryGoods.getTripartiteWeight()));
                }
                goodsDetails.setReceiptAddress(ordinaryGoods.getReceiptAddress());
                String receiptContact1 = ordinaryGoods.getReceiptContact().trim();
                goodsDetails.setReceiptContact(receiptContact1);
                goodsDetails.setReceiptContactMobile(ordinaryGoods.getReceiptContactMobile());
                goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                goodsDetails.setShipper(ordinaryGoods.getShipper());
                goodsDetails.setWarningState("无异常");
                SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(goods.getZipCode());
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                    goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                }
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                    goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                }
                if (ordinaryGoods.getItemValue().compareTo(new BigDecimal(0)) > 0) {
                    goodsDetails.setItemValue(ordinaryGoods.getItemValue());
                }
                goodsDetails.setIsArrivalPay(1);
                goodsDetailsMapper.insert(goodsDetails);
            } else {
                Goods goods = new Goods();
                goods.setFrom("三方货单");
                goods.setStatus((byte) 1);
                String deliveryNumber = "04" + getRandomCode();
                goods.setDeliveryNumber(deliveryNumber);
                goods.setTripartiteNumber(ordinaryGoods.getTripartiteNumber());
                goods.setZipCode(ordinaryGoods.getZipCode());
                goods.setGoodType(ordinaryGoods.getGoodType());
                goods.setAddTime(new Date());
                goods.setExitWay(ordinaryGoods.getExitWay());
                goods.setTotalGoods(ordinaryGoods.getTotalGoods());
                AdminUserLoginDto user = (AdminUserLoginDto) SecurityUtils.getSubject().getSession().getAttribute("user");
                goods.setAdminUid(Integer.parseInt(user.getAdminUId()));
                goods.setAdminUserTypeId(user.getAdminUserTypeId());
                goodsMapper.insert(goods);
                GoodsDetails goodsDetails = new GoodsDetails();
                goodsDetails.setGoodsId(goods.getGoodsId());

                if (Float.valueOf(ordinaryGoods.getTripartiteWeight()) != 0) {
                    goodsDetails.setTripartiteWeight(Float.valueOf(ordinaryGoods.getTripartiteWeight()));
                }
                goodsDetails.setReceiptAddress(ordinaryGoods.getReceiptAddress());
                String receiptContact1 = ordinaryGoods.getReceiptContact().trim();
                goodsDetails.setReceiptContact(receiptContact1);
                goodsDetails.setReceiptContactMobile(ordinaryGoods.getReceiptContactMobile());
                goodsDetails.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                goodsDetails.setShipper(ordinaryGoods.getShipper());
                goodsDetails.setWarningState("无异常");
                SingaporeAreaBuilding singaporeAreaBuilding = singaporeAreaBuildingMapper.selectSGBuildingByZipCode(ordinaryGoods.getZipCode());
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                    goodsDetails.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                }
                if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                    goodsDetails.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                }
                if (ordinaryGoods.getItemValue().compareTo(new BigDecimal(0)) > 0) {
                    goodsDetails.setItemValue(ordinaryGoods.getItemValue());
                }
                goodsDetails.setIsArrivalPay(1);
                goodsDetailsMapper.insert(goodsDetails);
                for (int i = 1; i <= ordinaryGoods.getTotalGoods(); i++) {
                    Goods goods1 = new Goods();
                    goods1.setFrom("三方货单");
                    goods1.setStatus((byte) 1);
                    goods1.setDeliveryNumber(deliveryNumber + "-" + i);
                    goods1.setTripartiteNumber(ordinaryGoods.getTripartiteNumber());
                    goods1.setZipCode(ordinaryGoods.getZipCode());
                    goods1.setGoodType(ordinaryGoods.getGoodType());
                    goods1.setAddTime(new Date());
                    goods1.setExitWay(ordinaryGoods.getExitWay());
                    goods1.setTotalGoods(1);
                    goods1.setAdminUid(Integer.parseInt(user.getAdminUId()));
                    goods1.setAdminUserTypeId(user.getAdminUserTypeId());
                    goodsMapper.insert(goods1);
                    GoodsDetails goodsDetails1 = new GoodsDetails();
                    goodsDetails1.setGoodsId(goods1.getGoodsId());
                    if (Float.valueOf(ordinaryGoods.getTripartiteWeight()) != 0) {
                        goodsDetails1.setTripartiteWeight(Float.valueOf(ordinaryGoods.getTripartiteWeight()));
                    }
                    goodsDetails1.setReceiptAddress(ordinaryGoods.getReceiptAddress());
                    String receiptContact2 = ordinaryGoods.getReceiptContact().trim();
                    goodsDetails1.setReceiptContact(receiptContact2);
                    goodsDetails1.setReceiptContactMobile(ordinaryGoods.getReceiptContactMobile());
                    goodsDetails1.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getAddTime()));
                    goodsDetails1.setShipper(ordinaryGoods.getShipper());
                    goodsDetails1.setWarningState("无异常");
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getSaId() != null) {
                        goodsDetails1.setSingaporeAreaId(singaporeAreaBuilding.getSaId());
                    }
                    if (singaporeAreaBuilding != null && singaporeAreaBuilding.getRallyPointId() != null) {
                        goodsDetails1.setRallyPointId(singaporeAreaBuilding.getRallyPointId());
                    }
                    goodsDetails1.setIsArrivalPay(1);
                    //未平均分配货值
                    goodsDetailsMapper.insert(goodsDetails1);
                }
            }

        } else {
            return "三方物流单号:" + ordinaryGoods.getTripartiteNumber() + "为重复数据，如果数据有错误，请先删除旧数据再进行导入";
        }
        return "1";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ExportGoodsOrder> selectGoodsListByUserName(GoodsOrderDto goodsOrderDto) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("sendPeople", goodsOrderDto.getSendPeople());
            map.put("startDate", goodsOrderDto.getStartDate());
            map.put("endDate", goodsOrderDto.getEndDate());
            List<ExportGoodsOrder> exportGoodsOrderList = goodsMapper.selectGoodsListByUserName(map);
            for (ExportGoodsOrder exportGoodsOrder:exportGoodsOrderList
                 ) {
                if(exportGoodsOrder.getIsArrivalPay()==2){
                    if(exportGoodsOrder.getDeliveryNumber().indexOf("-1") !=-1){
                        String deliveryNumber = exportGoodsOrder.getDeliveryNumber().substring(0,exportGoodsOrder.getDeliveryNumber().indexOf("-"));
                        GoodsDetails goodsDetails = goodsDetailsMapper .selectGoodsDetailsByDeliveryNumber(deliveryNumber);
                        if(goodsDetails!=null&&goodsDetails.getItemValue()!=null){
                            exportGoodsOrder.setItemValue(goodsDetails.getItemValue());
                        }
                    }
                }
            }
            return exportGoodsOrderList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int selectCountGoodsListByUserName(GoodsOrderDto goodsOrderDto) {

            HashMap<String, Object> map = new HashMap<>();
            map.put("sendPeople", goodsOrderDto.getSendPeople());
            map.put("startDate", goodsOrderDto.getStartDate());
            map.put("endDate", goodsOrderDto.getEndDate());
            return goodsMapper.selectCountGoodsListByUserName(map);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ExportGoodsOrder> selectGoodsBySendTime(String nowDate, String nowDate1) {

            return goodsMapper.selectGoodsBySendTime(nowDate, nowDate1);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CodGoods> selectCodGoodsById(int goodsId) {
            return goodsMapper.selectCodGoodsById(goodsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrdinaryGoods> selectOrdinaryGoodsById(int goodsId) {

            return goodsMapper.selectOrdinaryGoodsById(goodsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ExportGoodsOrder> selectThreeGoodsByAdminUserTypeId(int adminUserTypeId) {
            return goodsMapper.selectThreeGoodsByAdminUserTypeId(adminUserTypeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insertGoodsSendCondition(String fileName, MultipartFile file) throws IOException, ParseException {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return "上传文件格式不正确";
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Sheet sheet = null;
        if (isExcel2003) {
            HSSFWorkbook wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        } else {
            XSSFWorkbook wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            int value = i + 1;
            if (row.getCell(0) == null) {
                return "第" + value + "行的三方物流公司名称不能为空";
            }
            if (row.getCell(1) == null) {
                return "第" + value + "行的快递单号不能为空";
            }
            if (row.getCell(2) == null) {
                return "第" + value + "行的货物配送状态不能为空";
            }
            if (row.getCell(4) == null) {
                return "第" + value + "行的是否确认收货不能为空";
            }
            if (row.getCell(5) == null) {
                return "第" + value + "行的货物配送时间不能为空";
            }
            for (int r = 0; r <= 5; r++) {
                if (row.getCell(r) == null) {
                    row.createCell(r).setCellValue(new HSSFRichTextString(String.valueOf("")));
                }

            }

        }
        Set<String> sett = new HashSet<String>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
            if (!sett.add(row.getCell(1).getStringCellValue())) {
                int value = i + 1;
                return "第" + value + "行的快递单号为重复值，导入失败";
            }
        }

        //循环工作表Sheet
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            int line = r + 1;
            row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
            String sendPeople = row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
            String deliveryNumber = row.getCell(1).getStringCellValue();
            row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
            String status = row.getCell(2).getStringCellValue();
            row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
            String abnormalText = row.getCell(3).getStringCellValue();
            row.getCell(4).setCellType(HSSFCell.CELL_TYPE_STRING);
            String isReceiveGoods = row.getCell(4).getStringCellValue();
            row.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
            String sendTime = row.getCell(5).getStringCellValue();
            Goods goods = goodsMapper.selectGoodsByDeliveryNumber(deliveryNumber);
            GoodsDetails goodsDetails = goodsDetailsMapper.selectGoodsDetailsByDeliveryNumber(deliveryNumber);
            if (status.equals("正常")) {
                if (goods.getFrom() != null && goods.getFrom().equals("三方货单")) {
                    if (isReceiveGoods.equals("是")) {
                        goodsDetails.setIsReceiveGoods(1);
                    } else if (isReceiveGoods.equals("否")) {
                        goodsDetails.setIsReceiveGoods(2);
                    }
                    Calendar c = new GregorianCalendar(1900, 0, -1);
                    Date date = c.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String str = sdf.format(date);
                    String endtime = DateUtil.addDay(str, Integer.parseInt(sendTime));
                    goodsDetails.setSendTime(sdf.parse(endtime));
                    goodsDetails.setSendPeople(sendPeople);
                    goodsDetails.setUpdateTime(new Date());
                    goodsDetailsMapper.updateGoodsDetails(goodsDetails);
                } else if (goods.getFrom() != null && goods.getFrom().equals("转运订单")) {
                    if (isReceiveGoods.equals("是")) {
                        goodsDetails.setIsReceiveGoods(1);
                        goodsDetails.setGoodState("已完成");
                    } else if (isReceiveGoods.equals("否")) {
                        goodsDetails.setIsReceiveGoods(2);
                        goodsDetails.setGoodState("转运中");
                    }
                    Calendar c = new GregorianCalendar(1900, 0, -1);
                    Date date = c.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String str = sdf.format(date);
                    String endtime = DateUtil.addDay(str, Integer.parseInt(sendTime));
                    goodsDetails.setSendPeople(sendPeople);
                    goodsDetails.setUpdateTime(new Date());
                    goodsDetailsMapper.updateGoodsDetails(goodsDetails);
                }
                LogisticInfo logisticInfo = new LogisticInfo();
                logisticInfo.setGoodsId(goods.getGoodsId());
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("貨物已簽收，簽收人："+goodsDetails.getReceiptContact());
                logisticInfo.setOperateName(sendPeople);
                logisticInfo.setOperateComment("货物配送完成");
                logisticInfo.setOperateType("货物配送");
                logisticInfoMapper.insert(logisticInfo);
            } else if (status.equals("异常")) {
                if (isReceiveGoods.equals("是")) {
                    return "请核实导入信息，异常件货物如果客户已确认接收，请线下处理";
                } else if (isReceiveGoods.equals("否")) {
                    goodsDetails.setIsReceiveGoods(2);
                }
                goods.setStatus((byte) 2);
                goods.setUpdateTime(new Date());
                goodsMapper.updateGoodsStatus(goods);
                Calendar c = new GregorianCalendar(1900, 0, -1);
                Date date = c.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String str = sdf.format(date);
                String endtime = DateUtil.addDay(str, Integer.parseInt(sendTime));
                goodsDetails.setSendPeople(sendPeople);
                goodsDetails.setUpdateTime(new Date());
                goodsDetails.setWarningState("异常");
                if (abnormalText != null) {
                    goodsDetails.setWarningState(abnormalText);
                } else {
                    return "异常件货物，必须填写配送异常描述";
                }
                goodsDetailsMapper.updateGoodsDetailsState(goodsDetails);
                GoodsWarning goodsWarning = new GoodsWarning();
                goodsWarning.setGoodsId(goods.getGoodsId());
                goodsWarning.setWarningComment(abnormalText);
                goodsWarning.setWarningState("异常");
                goodsWarning.setWarningType(abnormalText);
                goodsWarning.setCreateTime(new Date());
                goodsWarning.setDelStatus(1);
                goodsWarningMapper.insert(goodsWarning);
                LogisticInfo logisticInfo = new LogisticInfo();
                logisticInfo.setGoodsId(goods.getGoodsId());
                logisticInfo.setCreateTime(new Date());
                logisticInfo.setOperateResult("派送失敗，失敗原因："+abnormalText);
                logisticInfo.setOperateName(sendPeople);
                logisticInfo.setOperateComment(abnormalText);
                logisticInfo.setOperateType("货物派送异常");
                logisticInfoMapper.insert(logisticInfo);


            }

        }
        return "1";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Goods> selectByTripartiteNumber(String tripartiteNumber) {
        List<Goods> goods = goodsMapper.selectByTripartiteNumber(tripartiteNumber);
        return goods;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateGoodsPackingCondition(int goodsId) {
        goodsMapper.updateGoodsBagId(goodsId,new Date());
        String OperateType = "货物打包入袋";
        LogisticInfo logisticInfo = logisticInfoMapper.selectGoodsLogisticInfoByIdType(goodsId,OperateType);
        if(logisticInfo!=null&&logisticInfo.getLogisticInfoId()!=null){
            logisticInfoMapper.deleteByPrimaryKey(logisticInfo.getLogisticInfoId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ExportGoodsOrder> selectGoodsListByWarehouseId(int warehouseId) {
        return goodsMapper.selectGoodsListByWarehouseId(warehouseId);
    }


}
