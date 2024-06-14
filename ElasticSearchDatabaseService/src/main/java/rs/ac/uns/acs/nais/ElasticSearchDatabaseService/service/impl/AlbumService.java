package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.elasticsearch.search.aggregations.metrics.InternalHDRPercentiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IAlbumService;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IArtistService;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void deleteAllAlbums(){albumRepository.deleteAll();}

    @Override
    public Iterable<Album> getAllAlbums(){return albumRepository.findAll();}

    public Page<Album> findAllAlbumsPage(int page, int size){return albumRepository.findAll(PageRequest.of(page, size));}

    public long countAllAlbums(){return albumRepository.count();}

    public Page<Album> findAlbumsByReleaseDate(String date, Pageable page) {
        return albumRepository.findAlbumsByReleaseDate(date, page);
    }
    @Override
    public Optional<Album> findAlbumById(String id) {
        return albumRepository.findById(id);
    }
    @Override
    public Page<Album> findAlbumsByNameInDateRange(String keyword, String startDate, String endDate, Pageable page){
        return albumRepository.findAlbumsByNameInDateRange(keyword, startDate, endDate, page);
    }

    @Override
    public byte[] export(List<Album> albums) throws IOException, DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        String directoryPath = "pdf";
        String filename = directoryPath + "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        };

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("ALBUMS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Album Name", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Release Date", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Artist ID", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);

        for (Album album : albums) {
            reportTable.addCell(album.getName() != null ? album.getName() : "N/A");
            reportTable.addCell(album.getReleaseDate() != null ? album.getReleaseDate() : "N/A");
            reportTable.addCell(album.getArtistId() != null ? album.getArtistId() : "N/A");
        }

        document.add(reportTable);
        document.close();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }

}
