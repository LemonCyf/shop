package com.zjitc.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id =
            "2016082100308349";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/fYKn9w2BMkMkX9qanzm5zeaJunQHzNFfnvFgBZ/vvckiXxvIAyS0a939an/DZbsykin9nOyQPyRGq69JOUasoNxwdJrLw0C5jnNrdtQYfw0eMkAsGxGzD72KDKuCfGMxDfL0NLMSJ8qA9IfVQxQKzEQo760CH7T3O9SCvOSxtqDSy6k0AWeGzGA02BD4IrVVpyCsuSWMdZOlnCrz/WSyxLdMDPqMLxFXz7Dq5bFqLPHO5rF2CKNggQSUDrdIsFH4DSwkU26jxU3wBFbkpD+LBl2wjaCZDoye2lplRQnq+aMtSaAs8oeO0bGDggYcjilrME8rHDTWdJHPCwm/0xh3AgMBAAECggEBAL7Hvzq1C0Fi4D4HOfa/GHYjy8IAf8WdrCQGaRlvwS7L+O5Dr5wQdatDpMHsLypv2pfBxBAnHhGAtxWpy1O6us0W30kzA15rQ7sbT6cLlrhpLJqfGgxXU8UAs6gsg1vXrcOb0Ci5sIcwGbNs7T/uOrOfW3EliUsKZ/PCKjGvHHJodk9WBjp3pgwLvgPr7ITEWS7wxDdugX9PMxEbvJthpHoJuNTE7/VvKwj7vXNbYZA1kGM2xrgqBx19sVOKzqiFOqk52cW/wyHexX88+E3MhHBMI6OKzYDCGYJR1n715WTAWezV2K6Ob+FsqUZyjwg9dsV4FLYF0we9Eujn938Cg0ECgYEA5X5vUxXMKEP79ZtVqbE5oBe+qyQ1qE5xnUpQn0B/c3+MkPS6fkBhSa+L01TrVPtMOvDQ+P9rBNu2prEg+doxJWlm4STjX0Mhwt1J2Is0yKBMr3cA39evoB8r4LkbpQT9UHI77EaK+emRhrlYSadF2Ps4rtmPQuWNDOfopV8LKYcCgYEA1ZtnKMjcsQirqcuVzSyLSqEWQ60vbtqZSSM9wPK1c9EDHVZRxOQOlHGzgrD1CHnsBvcGIcs+36zdcRnbiAStQO91S657/8hDmQHkHCXV3XrdXAQn1lAwVC9HHyfL4CPXDNHHafAVlbkGUWeOsGkYTAjfUSidtr1gtNByEUtDlZECgYB9hu3PjWGxOJZtnjx56BV0Cujg5Zpv6BBfD5IPuZC9FSxG8YW8nuxYCKw7uPxL7HSi3KwBNnxMGP3MKaC26XDuXLvINIwlxCuIUdAdoO8LOhsX8sjIp0pKFCLE9Zs4ZQVF5mX+dAt6bRaLkX/IMbWT3L4BbcDdv3+qaoMnSVhqqwKBgHkmRKH7GkBGPKZ20yWlJ/NR3cDQjuB+lwXGORYgEg33855GAOZ8ulAzFK8BEliWXemtXQr5rVVLQ+UL11kT3ffAWHRuJ+HwgXZcfjYAt4WAU+AGc1m37VEx51KiTZ14dlXM/R72jid240B0TZd8Y81sEsyDDsBANIMY3jk0yMYhAoGBAJseKpzIL1E4HMqQxkWhGV9mYKR9zhKlCuJhJ/817qDjC+Q9vD2rVkBFMBitmkhsgv/D41pnpCeEwLo9J1EuqO3pYhKXT5eZmvF1ca620TpRsWeaqrsPfgGmU/oPPrrOWJpKLarjzHX4oD8ZoIQ5B/4Y3N/mAfuZziuu+Y4alAe6";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuI9MlAdvgu1tbOgftbZOUJhUYpDy9+RvTfjnBFlz1JOFoz6164rTRMKG7i7h7doXL6EeR0n85uhBG2j+OUN/8uut3lK1Yo6LEfTaqVOH45r/plV1SZ4A1YK4Pyy8YsUo2EKBotrKndrbY90TO+hDYtNA9Qj9h5I9migYx6djt2IjZQXZInPxfRYcSHAiBCfdK8uGkUbbpAZV0LOap9CrCMafONIcTfy9z+rw00nyLLD38M+Fz0c6CJsA/BqpENPUhqoQTTr+tX5q3ZKycPkk4vIt27idlAWn0086hN03t+Cl4PpGKP8xxCrvsD3PqY2mMOifb1zta58BsYk2AtmlKQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/jsp/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/jsp/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

