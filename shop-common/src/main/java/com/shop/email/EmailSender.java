package com.shop.email;

import java.util.Map;




/**
 * 邮件发送接口
 * Created by ldz on 20/11/14.
 */
public interface EmailSender {
    /**
     * 模板群发不带附件
     * @param receivers 接受者，可以多个,逗号分割
     * @param subject  主题
     * @param templateKey 指定模板名
     * @param context 邮件模板中的变量参数指定
     * @param asyn 是否异步发送
     */
    public  void sendTemplateMail(String receivers,String subject,String templateKey,Map<String ,Object> context,boolean asyn);

    /**
     * 模板群发带附件
     * @param receivers  接受者，可以多个,逗号分割
     * @param subject   主题
     * @param templateKey 指定模板名
     * @param context 邮件模板中的变量参数指定
     * @param attachments 多个附件名
     * @param asyn 是否异步发送
     */
    public  void sendTemplateMailWithAttach(String  receivers,String subject,String templateKey,Map<String ,Object> context,String[] attachments,boolean asyn);

    /**
     * 普通简单邮件
     * @param receivers 接受者，可以多个,逗号分割
     * @param subject  主题
     * @param content  内容
     * @param asyn  是否异步发送
     */
    public  void sendSimpleMail(String receivers,String subject,String content,boolean asyn);



    //-----常见邮件模板--------//

    /**
     * 发送常见的邮件
     * @param email
     * @param TemplateId
     * @param isAsyn 是否异步
     */
    public void sendMailByTemplateId(Email email,String TemplateId,boolean isAsyn);
}
