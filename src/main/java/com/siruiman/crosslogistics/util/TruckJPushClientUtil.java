package com.siruiman.crosslogistics.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TruckJPushClientUtil {
    //别名：测试: TruckDebug+用户编号  发布: TruckRelease+用户编号
    private static final Logger log = LoggerFactory.getLogger(TruckJPushClientUtil.class);
    private static Boolean AF = true; //1.测试 false 2.生产 true
    //极光后台管理系统申请
    //货车端
    private static final String appKey = "9127a9eaa55206944f8646e7";
    private static final String masterSecret = "279c7838728d6b5a42ec1c86";
    public static final int MAX = Integer.MAX_VALUE;
    public static final int MIN = MAX / 2;

    public static JPushClient jPushClient = new JPushClient(masterSecret, appKey);


    /**
     * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
     * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
     * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒)。
     */
    private static long timeToLive = 60 * 60 * 24;

    /**
     * 保持 sendNo 的唯一性是有必要的
     */
    public static int getRandomSendNo() {
        return (int) (MIN + Math.random() * (MAX - MIN));
    }

    /**
     * 推送给设备标识参数的用户
     *
     * @param registrationId     设备标识 一次推送最多 1000 个。客户端集成 SDK 后可获取到该值。
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendTruckToRegistrationId(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam, String extrasparam2) {
        int result = 0;
        try {
            PushPayload pushPayload = TruckJPushClientUtil.buildPushObject_all_registrationId_alertWithTitle(registrationId, notification_title, msg_title, msg_content, extrasparam, extrasparam2);
            log.info("推送给设备标识参数的用户" + pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            log.info("推送结果" + pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();

        } catch (APIRequestException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 推送给设备标识参数的用户
     *
     * @param aliasList   别名或别名组
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToAliasList(JPushClient jPushClient, List<String> aliasList, String msg_content, String extraKey, String extrasparam, String notification_title) {
        int result = 0;
        try {
            PushPayload pushPayload = buildPushObject_all_aliasList_alertWithTitle(aliasList, msg_content, extraKey, extrasparam, notification_title);
            log.info("推送给设备标识参数的用户" + pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            log.info("推送结果" + pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();

        } catch (APIRequestException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 发送给所有安卓用户
     * @param notification_title 通知内容标题
     * @param msg_title 消息内容标题
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0推送失败，1推送成功
     */
    /*public static int sendToAllAndroid( String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload= JPushClientUtil.buildPushObject_android_all_alertWithTitle(notification_title,msg_title,msg_content,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }*/

    /**
     * 发送给所有IOS用户
     * @param notification_title 通知内容标题
     * @param msg_title 消息内容标题
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0推送失败，1推送成功
     */
    /*public static int sendToAllIos(String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload= JPushClientUtil.buildPushObject_ios_all_alertWithTitle(notification_title,msg_title,msg_content,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }*/

    /**
     * 发送给所有用户
     *
     * @param title   标题
     * @param content 内容
     * @return 0推送失败，1推送成功
     */
    public static int sendToAll(String title, String content) {
        int result = 0;
        try {
            PushPayload pushPayload = TruckJPushClientUtil.buildPushObject_android_and_ios(title, content);
            log.info("推送给设备标识参数的用户" + pushPayload);
            PushResult pushResult1 = jPushClient.sendPush(pushPayload);
            log.info("推送结果" + pushResult1);
            if (pushResult1.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    /**
     *  发送给所有用户
     * @param title  标题
     * @param content 内容
     * @param extrasparam 扩展字段
     * @param extrasparam2 扩展字段2
     * @return 0推送失败，1推送成功
     */
   /* public static int sendToAll( String title, String content,String extrasparam, String extrasparam2) {
        int result = 0;
        try {
            PushPayload pushPayload= JPushClientUtil.buildPushObject_android_and_ios(title,content,extrasparam,extrasparam2);
            System.out.println(pushPayload);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }*/

    /**
     * 向所有平台单个或多个指定别名用户推送消息
     *
     * @param aliasList
     * @param msg_content
     * @param extrasparam
     * @return
     */
    private static PushPayload buildPushObject_all_aliasList_alertWithTitle(List<String> aliasList, String msg_content, String extraKey, String extrasparam, String notification_title) {

        log.info("----------向所有平台单个或多个指定别名用户推送消息中......");
        //创建一个IosAlert对象，可指定APNs的alert、title等字段
        //IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();

        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.alias(aliasList))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()

                                .setAlert(msg_content)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra(extraKey, extrasparam)

                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg_content)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("default")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra(extraKey, extrasparam)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                //取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)

                                .build())


                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                /*.setMessage(Message.newBuilder()

                        .setMsgContent(msg_content)

                        .setTitle(msg_title)

                        .addExtra("message extras key",extrasparam)

                        .build())*/

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400)

                        .build())

                .build();

    }

    /**
     * 推送给Tag参数的用户
     *
     * @param tagsList    Tag或Tag组
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToTagList(JPushClient jPushClient, List<String> tagsList, String msg_content, String extra, String extrasparam, String notification_title) {
        int result = 0;
        try {
            PushPayload pushPayload = buildPushObject_all_tagList_alertWithTitle(tagsList, msg_content, extra, extrasparam, notification_title);
            log.info("推送给设备标识参数的用户" + pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            log.info("推送结果" + pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();

        } catch (APIRequestException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static PushPayload buildPushObject_android_and_ios(String title, String content) {
        IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody(title, null, content).build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())//推送平台设置
                .setAudience(Audience.all())//推送设备指定
                .setNotification(Notification.newBuilder()//通知具体内容
                        .setAlert(title)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(content)
                                .setTitle(title)
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(iosAlert)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)

                                .build()
                        )

                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)//消息内容本身
                        .setTitle(title)//消息标题
                        .build())

                .setOptions(Options.newBuilder()
                        //True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。但注意，JPush 服务端 SDK 默认设置为推送 “开发环境”。该字段仅对 iOS 的 Notification 有效。
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        //纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。值为 0 表示该 messageid 无 sendno，所以字段取值范围为非 0 的 int.
                        .setSendno(getRandomSendNo())
                        //推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。该字段对 iOS 的 Notification 消息无效
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(timeToLive)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_android_and_ios(String title, String content, String extrasparam, String extrasparam2) {
        IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody(title, null, content).build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())//推送平台设置
                .setAudience(Audience.all())//推送设备指定
                .setNotification(Notification.newBuilder()//通知具体内容
                        .setAlert(title)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(content)
                                .setTitle(title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("type", extrasparam)
                                .addExtra("id", extrasparam2)
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(iosAlert)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("type", extrasparam)
                                .addExtra("id", extrasparam2)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)

                                .build()
                        )

                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)//消息内容本身
                        .setTitle(title)//消息标题
                        .addExtra("type", extrasparam)//JSON格式的可选参数
                        .addExtra("id", extrasparam2)
                        .build())

                .setOptions(Options.newBuilder()
                        //True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。但注意，JPush 服务端 SDK 默认设置为推送 “开发环境”。该字段仅对 iOS 的 Notification 有效。
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        //纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。值为 0 表示该 messageid 无 sendno，所以字段取值范围为非 0 的 int.
                        .setSendno(getRandomSendNo())
                        //推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。该字段对 iOS 的 Notification 消息无效
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(timeToLive)
                        .build()
                )
                .build();
    }

    private static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam, String extrasparam2) {

        log.info("----------buildPushObject_all_all_alert");
        //创建一个IosAlert对象，可指定APNs的alert、title等字段
        //IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.alias(registrationId))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()

                                .setAlert(notification_title)
                                .setTitle(notification_title)

                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("type", extrasparam)
                                .addExtra("id", extrasparam2)
                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg_title)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("default")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("type", extrasparam)
                                .addExtra("id", extrasparam2)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                //取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)

                                .build())


                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()

                        .setMsgContent(msg_content)

                        .setTitle(msg_title)

                        .addExtra("type", extrasparam)
                        .addExtra("id", extrasparam2)

                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(getRandomSendNo())
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(timeToLive)

                        .build())

                .build();
    }

    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        log.info("----------buildPushObject_android_registrationId_alertWithTitle");
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key", extrasparam)
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("message extras key", extrasparam)
                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(getRandomSendNo())
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(timeToLive)
                        .build())
                .build();
    }

    private static PushPayload buildPushObject_ios_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        log.info("----------buildPushObject_ios_registrationId_alertWithTitle");
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.ios())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notification_title)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("iosNotification extras key", extrasparam)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)

                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("message extras key", extrasparam)
                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(getRandomSendNo())
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(timeToLive)
                        .build())
                .build();
    }


    /**
     * 向所有平台单个或多个指定Tag用户推送消息
     *
     * @param tagsList
     * @param msg_content
     * @param extrasparam
     * @return
     */
    private static PushPayload buildPushObject_all_tagList_alertWithTitle(List<String> tagsList, String msg_content, String extraKey, String extrasparam, String notification_title) {

        log.info("----------向所有平台单个或多个指定Tag用户推送消息中.......");
        //创建一个IosAlert对象，可指定APNs的alert、title等字段
        //IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();

        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.tag(tagsList))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()

                                .setAlert(msg_content)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("from", "jpush")

                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg_content)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("default")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("from", "jpush")
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                //取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)

                                .build())


                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                /*.setMessage(Message.newBuilder()

                        .setMsgContent(msg_content)

                        .setTitle(msg_title)

                        .addExtra("message extras key",extrasparam)

                        .build())*/

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(AF)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400)

                        .build())

                .build();

    }


    /**
     * 消息推送
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     *
     * @param type               推送方式：1、“tag”标签推送，2、“alias”别名推送 3 "all" 推送所有
     * @param notification_title 推送标题
     * @param value              推送的标签或别名值
     * @param alert              推送的内容
     */
    public static void pushTruckMsg(String type, String notification_title, String value, String alert) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        if (AF) {
            StringBuilder sb = new StringBuilder(value);//构造一个StringBuilder对象
            sb.insert(5, "Release");//在指定的位置5，插入指定的字符串
            value = sb.toString();
        } else {
            StringBuilder sb = new StringBuilder(value);//构造一个StringBuilder对象
            sb.insert(5, "Debug");//在指定的位置5，插入指定的字符串
            value = sb.toString();
        }
        if (type.equals("alias")) {
            builder.setAudience(Audience.alias(value));//别名推送
            //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
            builder.setPlatform(Platform.all())
                    //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                    .setNotification(Notification.newBuilder()
                            //指定当前推送的android通知
                            .addPlatformNotification(AndroidNotification.newBuilder()

                                    .setAlert(alert)
                                    .setTitle(notification_title)
                                    /*   //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                       .addExtra(extraKey,extrasparam)*/

                                    .build())
                            //指定当前推送的iOS通知
                            .addPlatformNotification(IosNotification.newBuilder()
                                    //传一个IosAlert对象，指定apns title、title、subtitle等
                                    .setAlert(alert)
                                    //直接传alert
                                    //此项是指定此推送的badge自动加1
                                    .incrBadge(1)
                                    //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                    // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                    .setSound("default")
                                    /* //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                     .addExtra(extraKey,extrasparam)*/
                                    //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                    //取消此注释，消息推送时ios将无法在锁屏情况接收
                                    .setContentAvailable(true)

                                    .build())


                            .build())
                    //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                    // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                    // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                    /*.setMessage(Message.newBuilder()

                            .setMsgContent(msg_content)

                            .setTitle(msg_title)

                            .addExtra("message extras key",extrasparam)

                            .build())*/

                    .setOptions(Options.newBuilder()
                            //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                            .setApnsProduction(AF)
                            //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                            //纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。值为 0 表示该 messageid 无 sendno，所以字段取值范围为非 0 的 int.
                            .setSendno(getRandomSendNo())
                            //推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。该字段对 iOS 的 Notification 消息无效
                            //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                            .setTimeToLive(timeToLive)

                            .build())

                    .build();
        } else if (type.equals("tag")) {
            builder.setAudience(Audience.tag(value));//标签推送
            //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
            builder.setPlatform(Platform.all())
                    //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                    .setAudience(Audience.tag(value))
                    //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                    .setNotification(Notification.newBuilder()
                            //指定当前推送的android通知
                            .addPlatformNotification(AndroidNotification.newBuilder()

                                    .setAlert(alert)
                                    .setTitle(notification_title)
                                    /*     //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                         .addExtra("from","jpush")
     */
                                    .build())
                            //指定当前推送的iOS通知
                            .addPlatformNotification(IosNotification.newBuilder()
                                    //传一个IosAlert对象，指定apns title、title、subtitle等
                                    .setAlert(alert)
                                    //直接传alert
                                    //此项是指定此推送的badge自动加1
                                    .incrBadge(1)
                                    //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                    // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                    .setSound("default")
                                    //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                    /*  .addExtra("from","jpush")*/
                                    //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                    //取消此注释，消息推送时ios将无法在锁屏情况接收
                                    .setContentAvailable(true)

                                    .build())


                            .build())
                    //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                    // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                    // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                    /*.setMessage(Message.newBuilder()

                            .setMsgContent(msg_content)

                            .setTitle(msg_title)

                            .addExtra("message extras key",extrasparam)

                            .build())*/

                    .setOptions(Options.newBuilder()
                            //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                            .setApnsProduction(AF)
                            //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                            //纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。值为 0 表示该 messageid 无 sendno，所以字段取值范围为非 0 的 int.
                            .setSendno(getRandomSendNo())
                            //推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。该字段对 iOS 的 Notification 消息无效
                            //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                            .setTimeToLive(timeToLive)

                            .build())

                    .build();
        } else {
            IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody(notification_title, null, alert).build();
            builder.setAudience(Audience.all());//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
            builder.setPlatform(Platform.android_ios())//推送平台设置
                    .setNotification(Notification.newBuilder()//通知具体内容
                            .setAlert(notification_title)
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .setAlert(alert)
                                    .setTitle(notification_title)
                                    .build()
                            )
                            .addPlatformNotification(IosNotification.newBuilder()
                                    //传一个IosAlert对象，指定apns title、title、subtitle等
                                    .setAlert(iosAlert)
                                    //直接传alert
                                    //此项是指定此推送的badge自动加1
                                    .incrBadge(1)
                                    //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                    // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                    .setSound("default")
                                    //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                    .setContentAvailable(true)

                                    .build()
                            )

                            .build()
                    )
                    //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                    // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                    // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                    .setMessage(Message.newBuilder()
                            .setMsgContent(alert)//消息内容本身
                            .setTitle(notification_title)//消息标题
                            .build())

                    .setOptions(Options.newBuilder()
                            //True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。但注意，JPush 服务端 SDK 默认设置为推送 “开发环境”。该字段仅对 iOS 的 Notification 有效。
                            //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                            .setApnsProduction(AF)
                            //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                            //纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。值为 0 表示该 messageid 无 sendno，所以字段取值范围为非 0 的 int.
                            .setSendno(getRandomSendNo())
                            //推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。该字段对 iOS 的 Notification 消息无效
                            //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                            .setTimeToLive(timeToLive)
                            .build()
                    )
                    .build();
        }
       /* Message.Builder newBuilder=Message.newBuilder();

        newBuilder.setMsgContent(alert);//消息内容
        Message message=newBuilder.build();
        builder.setMessage(message);*/
        PushPayload pushPayload = builder.build();
        log.info("推送给设备标识参数的用户" + pushPayload);
        int result = 0;
        try {
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            log.info("推送结果" + pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();

        } catch (APIRequestException e) {
            e.printStackTrace();
        }


    }


}
