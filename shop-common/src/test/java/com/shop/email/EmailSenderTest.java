package com.shop.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/email.xml"})
public class EmailSenderTest {

    @Autowired
    private EmailSender emailSender;


   @Test
    public void testSendEmail() throws Exception {
        Map<String,Object> context= new HashMap<String,Object>();
        context.put("message","donahue");
        String receivers ="ldz2012yn@gmail.com,1285310383@qq.com";
        emailSender.sendTemplateMail(receivers, "Test", "emailtemplates/activeEmail.vm", context, true);
       try {
           Thread.sleep(40000);  //防止spring容器关闭
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
//
//    @Test
//    public void testSendEmailTemplateWithAttachment() throws Exception {
//        Map<String,Object> context= new HashMap<String,Object>();
//        context.put("message","donahue");
//        String receivers ="ldz2012yn@gmail.com,1285310383@qq.com";
//        String [] attachments = {"我是中文.txt"};
//        emailSender.sendTemplateMailWithAttach(receivers, "test", "emailtemplates/activeEmail.vm", context, attachments, true);
//       // emailSender.sendEmailWithAttachment(receivers,"test","emailtemplates/activeEmail.vm", context,attachments,false);
//        try {
//            Thread.sleep(40000);  //防止spring容器关闭
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void testSendMailByTemplateId() {
//        Email email = new Email();
//        email.setSubject("我的donahue");
//        email.setTo("83815870@qq.com");
//        email.setBcc("83815870@qq.com");
//        email.setCc("1285310383@qq.com");
//        Map<String,Object> context = new HashMap<String, Object>();
//        context.put("message","你好");
//        email.setContext(context);
//        emailSender.sendMailByTemplateId(email,"REMIND",true);
//        try {
//            Thread.sleep(30000);  //防止spring容器关闭
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
