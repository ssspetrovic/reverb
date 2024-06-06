package rs.ac.uns.acs.nais.GraphDatabaseService.report;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ISongService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPlaylistService;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Top50SongsProjection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    private final ISongService songService;
    private final IPlaylistService playlistService; 

    public ReportGenerator(ISongService songService, IPlaylistService playlistService) {
        this.songService = songService;
        this.playlistService = playlistService;
    }

    public byte[] generateReport(String genre, String subgenre, String artist) {
        System.out.println("Starting to generate report...");
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Reverb Song Recommender", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell headerCell;
            headerCell = new PdfPCell(new Phrase("Track Name", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Artist", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Album", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Genre", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Subgenre", headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
            
            List<Song> songs = songService.recommendSongs(genre, subgenre, artist);
            for (Song song : songs) {
                table.addCell(song.getTrackName());
                table.addCell(song.getTrackArtist());
                table.addCell(song.getTrackAlbumName());
                table.addCell(song.getPlaylistGenre());
                table.addCell(song.getPlaylistSubgenre());
            }
            
            document.add(table);

            // Dodavanje tabele za top 50 pesama
            document.add(new Paragraph(" "));
            Paragraph top50Title = new Paragraph("Top 50 Songs", titleFont);
            top50Title.setAlignment(Element.ALIGN_CENTER);
            document.add(top50Title);

            PdfPTable top50Table = new PdfPTable(3);
            top50Table.setWidthPercentage(100);
            
            PdfPCell top50HeaderCell;
            top50HeaderCell = new PdfPCell(new Phrase("Track Name", headerFont));
            top50HeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            top50Table.addCell(top50HeaderCell);
            top50HeaderCell = new PdfPCell(new Phrase("Artist", headerFont));
            top50HeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            top50Table.addCell(top50HeaderCell);
            top50HeaderCell = new PdfPCell(new Phrase("Popularity", headerFont));
            top50HeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            top50Table.addCell(top50HeaderCell);
            
            List<Top50SongsProjection> top50Songs = songService.getTop50Songs();
            for (Top50SongsProjection top50Song : top50Songs) {
                top50Table.addCell(top50Song.getTrackName());
                top50Table.addCell(top50Song.getTrackArtist());
                top50Table.addCell(String.valueOf(top50Song.getTrackPopularity()));
            }
            
            document.add(top50Table);
            System.out.println("Report generated successfully!");
        } catch (DocumentException e) {
            System.out.println("Error: Document exception: " + e.getMessage());
        } finally {
            document.close();
        }

        return outputStream.toByteArray();
    }
}
