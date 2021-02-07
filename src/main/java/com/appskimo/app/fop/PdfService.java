package com.appskimo.app.fop;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfService implements InitializingBean {

    final TemplateEngine templateEngine;
    private ClassPathResource classPathResource;

    @Override
    public void afterPropertiesSet() {
        classPathResource = new ClassPathResource("/static/font/NanumBarunGothic.ttf");
    }

    @SneakyThrows({DocumentException.class, IOException.class})
    public void renderPdf(ServletOutputStream outputStream) {
        final ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont(
            classPathResource.getURL().toString(),
            BaseFont.IDENTITY_H,
            BaseFont.EMBEDDED
        );

        renderer.setDocumentFromString(parseThymeleafTemplate());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
    }

    private String parseThymeleafTemplate() {
        Context context = new Context();
        context.setVariable("name", "ㅇㅣ재경");

        return templateEngine.process("/contract/full_time.html", context);
    }

}
