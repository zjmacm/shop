package com.shop.email;

import org.apache.commons.lang.StringUtils;

/**
 * Created by ldz on 21/11/14.
 */
public class EmailTemplate {
    private String from; // 发件人地址
    private String subject; // 邮件标题
    private String plainTextTemplate; // 纯文本格式的邮件模板
    private String htmlTemplate; // html格式的邮件模板
    /**
     * 该封邮件是否是html格式
     */
    public boolean isHtmlMail() {
        return StringUtils.isNotBlank(htmlTemplate);
    }

    /**
     * 该封邮件是否是文本格式
     */
    public boolean isPlainTextMail() {
        return StringUtils.isNotBlank(plainTextTemplate);
    }

    /**
     * 是否是两种格式都有的
     */
    public boolean isAlternativeMail() {
        return isPlainTextMail() && isHtmlMail();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPlainTextTemplate() {
        return plainTextTemplate;
    }

    public void setPlainTextTemplate(String plainTextTemplate) {
        this.plainTextTemplate = plainTextTemplate;
    }

    public String getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }
}
