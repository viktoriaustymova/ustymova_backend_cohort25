package de.ait.tp.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailTemplatesUtil {

    private final Configuration freeMarkerConfiguration;

    public String createConfirmationMail(String firstName, String lastName, String link) {

        try {
            Template template = freeMarkerConfiguration.getTemplate("confirm_registration_mail.ftlh");

            Map<String,Object> model = new HashMap<>();
            model.put("firstName", firstName);
            model.put("lastName", lastName);
            model.put("link", link);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
