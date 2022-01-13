package com.design.pattern.structure.bridge.v1;

/*import java.util.List;

public enum NotificationEmergencyLevel {
    SEVERE, URGENCY, NORMAL, TRIVIAL
}

class Notification{
    private List emailAddresses;
    private List telephones;
    private List wechatIds;
    public Notification() {}

    public void setEmailAddress(List emailAddress) {
        this.emailAddresses = emailAddress;
    }
    public void setTelephones(List telephones) {
        this.telephones = telephones;
    }
    public void setWechatIds(List wechatIds) {
        this.wechatIds = wechatIds;
    }

    public void notify(NotificationEmergencyLevel level,String message){
        if (level.equals(NotificationEmergencyLevel.SEVERE)){
            //电话通知
        }else if (level.equals(NotificationEmergencyLevel.URGENCY)){
            //微信
        }else  if (level.equals(NotificationEmergencyLevel.NORMAL)){
            //邮件
        }else if (level.equals(NotificationEmergencyLevel.TRIVIAL)){
            //邮件
        }
    }
}


//客户端如何使用
class ErrorAlertHandler extends AlertHandler {
    public ErrorAlertHandler(AlertRule rule, Notification notification){
        super(rule, notification);
    }
    @Override
    public void check(ApiStatInfo apiStatInfo){
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()){
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}

Notification类有一处明显的问题就是使用的if太多了，另外if里的是发送报警逻辑会很复杂，
我们将不同渠道的发送逻辑剥离出来，形成独立的消息发送类（MsgSender 相关类）。
其中，Notification 类相当于抽象，MsgSender 类相当于实现，两者可以独立开发，通过组合关系（也就是桥梁）任意组合在一起

---------------改进版本-------------

interface MsgSender{
    void send(String message);
}

class TelephoneMsgSender implements MsgSender{
    private List telephones;
    public TelephoneMsgSender(List telephones) {
        this.telephones = telephones;
    }
    @Override
    public void send(String message) {
        //...
    }
}
class EmailMsgSender implements MsgSender {
    // 与TelephoneMsgSender代码结构类似，所以省略...
}
class WechatMsgSender implements MsgSender {
    // 与TelephoneMsgSender代码结构类似，所以省略...
}

abstract class Notification {
    protected MsgSender msgSender;

    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);
}

class SevereNotification extends Notification {
    public SevereNotification(MsgSender msgSender){
        super(msgSender);
    }

    @Override
    public void notify(String message) {
        msgSender.send(message);
    }
}

class UrgencyNotification extends Notification {// 与SevereNotification代码结构类似，所以省略...}

class NormalNotification extends Notification { // 与SevereNotification代码结构类似，所以省略...}

class TrivialNotification extends Notification { // 与SevereNotification代码结构类似，所以省略...}
    */