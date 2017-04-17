package com.epam.spring.cinema.pdf;

import com.epam.spring.cinema.domain.Ticket;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey_Vaganov on 4/10/2017.
 */
public class SoldTicketPDFView extends AbstractITextPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Ticket> ticketList = (List<Ticket>)((Map<String, Object>)model.get("model")).get("ticketList");

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{10, 60, 30});

        table.addCell("Seat");
        table.addCell("Vip");
        table.addCell("Selling price");

        for (Ticket ticket : ticketList){
            table.addCell(String.valueOf(ticket.getSeat()));
            table.addCell(ticket.getVip() == true ? "YES" : "NO");

            String ticketPrice = (new Double(0)).equals(ticket.getTicketPrice())
                    ? "lucky"
                    : String.valueOf(ticket.getTicketPrice());

            table.addCell(ticketPrice);
        }

        document.add(table);
    }
}
