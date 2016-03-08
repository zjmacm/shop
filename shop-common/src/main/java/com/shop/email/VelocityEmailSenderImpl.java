package com.shop.email;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.*;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

/**
 * @author ldz
 * Created by ldz on 20/11/14.
 * 邮件发送类，velocity模板引擎实现
 */
@Component("emailSender")
public class VelocityEmailSenderImpl implements EmailSender{
    private final String DEFAULT_SENDER = "shang_ming_yang@163.com";
    private final String DEFAULT_ENCODING = "UTF-8";
    private String emailFrom = DEFAULT_SENDER;
    private String emailEncoding = DEFAULT_ENCODING;
    //默认是异步发送
    private boolean isAsyn = true;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailTemplateFactory emailTemplateFactory;

    @Autowired
    private VelocityEngine velocityEngine;
    //注入Spring封装的异步执行器
    @Autowired
    private TaskExecutor taskExecutor;

    //日志记录
    private Log log = LogFactory.getLog(getClass());

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailEncoding() {
        return emailEncoding;
    }

    public void setEmailEncoding(String emailEncoding) {
        this.emailEncoding = emailEncoding;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public boolean isAsyn() {
        return isAsyn;
    }

    public void setAsyn(boolean isAsyn) {
        this.isAsyn = isAsyn;
    }

    public EmailTemplateFactory getEmailTemplateFactory() {
        return emailTemplateFactory;
    }

    public void setEmailTemplateFactory(EmailTemplateFactory emailTemplateFactory) {
        this.emailTemplateFactory = emailTemplateFactory;
    }

    @Override
    /**
     * 群发不带附件
     * @param receivers
     * @param subject
     * @param templateKey 指定模板名
     * @param context 邮件模板中的变量参数指定
     */
    public void sendTemplateMail(final String receivers,final  String subject, final String templateKey, final Map<String, Object> context,final boolean isAsyn) {
        sendEmail(receivers, subject, templateKey, context, null,isAsyn);

    }


    @Override
    /**
     * 群发带附件
     * @param receivers
     * @param subject
     * @param templateKey 指定模板名
     * @param context 邮件模板中的变量参数指定
     * @param attachments 多个附件名
     */
    public void sendTemplateMailWithAttach(final String receivers, final String subject, final String templateKey, final Map<String, Object> context,final String []attachments,final boolean isAsyn) {
        sendEmail(receivers, subject, templateKey, context, attachments,isAsyn);

    }

    /**
     * 普通简单邮件
     * @param receivers 接受者，可以多个,逗号分割
     * @param subject  主题
     * @param content  内容
     * @param isAsyn  是否异步发送
     */
    public  void sendSimpleMail(String receivers,String subject,String content,boolean isAsyn){
        final MimeMessage message = mailSender.createMimeMessage();
        Validate.notEmpty(receivers);
        try {
            message.setRecipients(Message.RecipientType.BCC, new InternetAddress().parse(receivers));
            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setText(content);
            //异步发送
            if(isAsyn&&this.isAsyn) {
                taskExecutor.execute(new Runnable() {
                    public void run() {
                        try {
                            mailSender.send(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.debug(e);
                        }
                    }
                });
            } else {
                mailSender.send(message);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.debug("邮件发送错误");
        }

    }

    /**
     * 发送常见的邮件
     * @param email
     * @param TemplateId
     */
    @Override
    public void sendMailByTemplateId(Email email, String TemplateId,boolean isAsyn) {
        EmailTemplate emailTemplate = emailTemplateFactory.create(TemplateId);
        Assert.notNull(emailTemplate);
        try {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.addFrom(InternetAddress.parse(emailTemplate.getFrom()));
            mimeMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCc()));
            mimeMessage.addRecipients(Message.RecipientType.BCC,InternetAddress.parse(email.getBcc()));
            mimeMessage.setSubject(emailTemplate.getSubject());
            MimeMultipart multipart = new MimeMultipart("alternative");
            mimeMessage.setContent(multipart);
            // 处理PlainText的邮件
            if (emailTemplate.isPlainTextMail()) {

                BodyPart plainText = new MimeBodyPart();
                plainText.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplate
                        .getPlainTextTemplate(), getEmailEncoding(), email.getContext()));
                multipart.addBodyPart(plainText);
            }

            // 处理html的邮件
            if (emailTemplate.isHtmlMail()) {

                BodyPart html = new MimeBodyPart();
                html.setContent(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplate
                        .getHtmlTemplate(), getEmailEncoding(), email.getContext()), "text/html;charset=UTF-8");
                multipart.addBodyPart(html);
            }
            if(isAsyn&&this.isAsyn){
                taskExecutor.execute(new Runnable() {
                    public void run() {
                        try {
                            mailSender.send(mimeMessage);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.debug(e);
                        }
                    }
                });
            } else {
                mailSender.send(mimeMessage);

            }

        }catch (Exception e){
            e.printStackTrace();
            log.debug("邮件发送异常");
        }

    }

    private final void sendEmail(final String receivers,final String subject, final String templateKey,
                           final Map<String,Object> context,final String [] attachments ,final boolean isAsyn) {
        Validate.notEmpty(receivers);
        Validate.notEmpty(templateKey);

        StringWriter writer = new StringWriter();
        VelocityEngineUtils.mergeTemplate(velocityEngine, templateKey, getEmailEncoding(), context, writer);

        final String mailText = writer.toString();  //mail content
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {

            InternetAddress[] addressTo = new InternetAddress().parse(receivers);
            mimeMessage.setRecipients(Message.RecipientType.BCC, addressTo);
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
            message.setFrom(new InternetAddress(getEmailFrom()));
            message.setSubject(subject);
            message.setText(mailText, true);
            message.setSentDate(new Date());

            if (attachments != null) {
                for (int i = 0; i < attachments.length; i++) {
                    if (!StringUtils.isBlank(attachments[i])) {
                        //FileSystemResource file = new FileSystemResource(attachmentPath);
                        ClassPathResource file = new ClassPathResource(attachments[i]);
                        //解决乱码
                        message.addAttachment(MimeUtility.encodeWord(file.getFilename()), file);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.debug("邮件发送错误");
        }
        //异步发送
        if(isAsyn&&this.isAsyn) {
            taskExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        mailSender.send(mimeMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.debug(e);
                    }
                }
            });
        } else {
            mailSender.send(mimeMessage);
        }
    }

}

