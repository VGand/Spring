package com.epam.spring.cinema.pdf;


import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * Created by Andrey_Vaganov on 4/10/2017.
 */
public abstract class AbstractITextPdfView extends AbstractView {

    public AbstractITextPdfView() {
        setContentType("application/pdf");
    }

    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {}

    protected abstract void buildPdfDocument(Map<String, Object> model,
                                             Document document,
                                             PdfWriter writer,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws Exception;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ByteArrayOutputStream out = createTemporaryOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);
        buildPdfMetadata(map, document, httpServletRequest);

        document.open();
        buildPdfDocument(map, document, writer, httpServletRequest, httpServletResponse);
        document.close();
        writeToResponse(httpServletResponse, out);
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }
}
