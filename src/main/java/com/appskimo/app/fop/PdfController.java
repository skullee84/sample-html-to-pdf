package com.appskimo.app.fop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PdfController {

    final PdfService pdfService;

    @GetMapping("/pdf")
    public void pdf(HttpServletResponse response) {
        try {
            pdfService.renderPdf(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
