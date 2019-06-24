package com.siruiman.crosslogistics.config;

/**
 * @author 张占伟
 * @date 2019/1/4 19:11
 */
public   class   AliPayConfig {
    // 商户appid
    public static final String APPID = "2019010562856142";//2019010562856142
    // 应用私钥
    public static final String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDPXvqAA90M9gNljN+IRdHKdWkr2MQaBB+QunC6oiMXbfH/r89z01XMo163R5VpguLwF6Ry1ikXtV1WrIt4QrtADYCfm5Bwi7AdUvdhRdapKOw6144R/XtE+yQ1hXk4KTvRS+kcxeaI5Pbqg21nN5Gvwcmg47cWWYX+br89Cwq+Obhd/knrlM3PcMTM9/yNeVvofeUJpQTa3Lz1ltlUb4yhZ+akj00SpCvvGuCoS5Kiqkj26sK6in3XlPszsErXLfyrrKHHTTqrfS2fq5yHYrLa3wzNhIYK6ClKgOVsSJ8rSMn5ItWbJUV61WWisfpH1BAcLZQe5ZNfaXkmrwtVgSGdAgMBAAECggEAYlSDK9ipJNAy5CBcjl2tEPeDm0r6EewKm7IMajMfuKhlq/xNvnpjoNmGvBmxe4qY2YgcbmemrJtPWx4ONERQThsY9pXpc7SJGh6RNwrKswO4oF49+8HahecgUFCuHjdPKft42i/tFNSdUK5pXhw3dMPLPAM+edaRdwhEnklyGqigsQrUXra2+dM22XOM1JYSynrJAA9x+/6LuEn/NUeZAy7pJnK17ZNJA9P5IIxMVRJetDPygx/vUdy+eqFqrMBVbiyhEVIWlR3JnWffTqRI/xg/v/gZfLXvqRT4OvZ57tP6J17fP7jvmovOU7RgJ7Z05DAalhqIjAfht9JSDYLUAQKBgQD7YhhNPnb94df8owwA8GeAqK2QIzjdDxQYnUb8iWxjKLdMI6bASAjju85EnAtKJn18cxfm15Yv6ssXV79XBIaCp05KjU1qfezBgdyQ0HLsp7avNxEpFaxyTInn7u8xM9TCvtiANxzA0+7vSMJncKyAro8oceoElkk3h4/1xGKKwQKBgQDTLfSjh6AOM7MSVQCoWBQRvxeklhVHTWCmRAhcppPukHLRF8taE6Zl9n7iuXcbbdoPQOCcO2A7uI9djdjaqbyUnwaY4f4qmnc5k1WdG9ZfIfrNnvQPQG7rGK7qHV9SC8TAUk20ux9b7Z50xg9wVus4nLsyhQlG1Npq8fYLUgKZ3QKBgEY9epzav1ThLgChblE02zbP6u9DaDgcZnkaOSOHIonyboUP0SAZAKLhbxksoNrvNlD3t84Any50ZJr5IbWmxg7rwKyEHwWKegT3zDGKjRIjKqB6uojITY8CY+YylRMicGiZ+tSYkagvj6u8oNVWXlz2aXnbmo/9jie98TOy0/YBAoGATULN8xpGk4uUo0tYDz8SMJ/yHXQPWuPTTWEskgEbtDdC2n7TFiSER1+UcoyaJ/3bvqLkyiPrBE50nSytPurEpLc9qadc3udeYUEnHHC30FxVLu9NqVrQedCxBdQzMIg/toEmDs47Hd3kkCJ6OYaDTlYBIu2LRE/RjTjy5Jdffj0CgYB9m7W6Uf9oF4Whud0q6pzhVFeOHTfb5Qxwg1IwtxKTalm9OreBeKmhl+OrzihLx2RQC581B5BJ/B9c24c1qMsXrB0oxedpzhK9EK5PeaBvbmJyAA7b441HPdmpk6fmnlCdJlBQJ4VGCM5Ii7sXL2IIi8mEi26rH9jPDdJJErfvRg==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String NOTIFY_URL = "http://kjwl.helplove.com.sg/alipay/notifyAliPayResults";
    // 请求网关地址
    public static final String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static final String CHARSET = "UTF-8";
    // 返回格式
    public static final String FORMAT = "json";
    // 支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqiyTM3t3MCWN0VBYUqoC3Qd3ogKx0HaSl0hmMwVEx6nlfErjuv3TDTzvdPJ5BpXQw347/YIlI4arwrLlWra2Y5fm1WqY2644abFjfaI6Xjc4lfJ7KoWAT46FlzVcV4S6BVLLMHsHH0eLhTymgiyXFQd2kJGz8bZSPHK80dbpeapmkl1T0Nko+ZmLvzaIhhy54ViUGwWAZxhnw9maa4hMU4stB0XyCIisAWdAeHk/meKXeVIl1Gwox/7Xe3c/Q2aKjDXuXOl7JirPUO+HwRGuAHDYaf/6pMnL/3wJRpkX4iPe3T3tOyCMn6CDezChGBBYFaEU85v/8gk5DVFafhIOdQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // 签文类型
    public static final String SIGNTYPE = "RSA2";

}
