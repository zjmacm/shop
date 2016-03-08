package com.shop.email;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 获取邮件模板
 * Created by ldz on 21/11/14.
 */
@Component
public class EmailTemplateFactory {
    @Resource(name="templateMailContext")
    private Map<String, EmailTemplate> templateMailContext;

    public Map<String, EmailTemplate> getTemplateMailContext() {
        return templateMailContext;
    }

    public void setTemplateMailContext(Map<String, EmailTemplate> templateMailContext) {
        this.templateMailContext = templateMailContext;
    }

    public EmailTemplate create(String templateId) {

        for (String key : templateMailContext.keySet()) {
            System.out.println(key + "[" + templateMailContext.get(key) + "]");
        }
        if (templateMailContext.containsKey(templateId)) {
            return templateMailContext.get(templateId);
        }
        return null;
    }

}
