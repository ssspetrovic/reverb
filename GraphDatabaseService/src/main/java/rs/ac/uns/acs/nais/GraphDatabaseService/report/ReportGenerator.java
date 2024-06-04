package rs.ac.uns.acs.nais.GraphDatabaseService.report;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ISongService;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.LongestSongInEveryAlbumProjection;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;

public class ReportGenerator {
    private final ISongService songService;

    public ReportGenerator(ISongService songService) {
        this.songService = songService;
    }

    public byte[] generateReport() {
        System.out.println("Starting to generate report...");
        Document document = new Document();
        try {
            System.out.println("Creating PDF document...");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(3);
            table.addCell("Album Title");
            table.addCell("Song Title");
            table.addCell("Duration");
            for (LongestSongInEveryAlbumProjection song : songService.getLongestSongInEveryAlbum()) {
                table.addCell(song.getTrackAlbumName());
                table.addCell(song.getTrackName());
                String duration = String.format("%d:%02d", song.getDurationMs() / 60000, (song.getDurationMs() % 60000) / 1000);
                table.addCell(duration);
            }
            System.out.println("Adding table to document...");
            document.add(table);
            System.out.println("Closing document...");
            document.close();
            System.out.println("Report generated successfully!");
            return Files.readAllBytes(Paths.get("report.pdf"));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + e.getMessage());
            return null;
        } catch (DocumentException e) {
            System.out.println("Error: Document exception: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Error: IO exception: " + e.getMessage());
            return null;
        }
    }
}