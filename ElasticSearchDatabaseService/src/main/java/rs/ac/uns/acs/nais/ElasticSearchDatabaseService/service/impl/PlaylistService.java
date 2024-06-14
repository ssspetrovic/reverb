package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.PlaylistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IPlaylistService;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Optional<Playlist> findPlaylistById(String id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Iterable<Playlist> findAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public void deletePlaylistById(String id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public void deleteAllPlaylists(){playlistRepository.deleteAll();}

    public Page<Playlist> findAllPlaylistsPage(int page, int size){return playlistRepository.findAll(PageRequest.of(page, size));}

    public long countAllPlaylists(){return playlistRepository.count();}

    public Page<Playlist> findPlaylistsByName(String keyword, Pageable page) {
        return playlistRepository.findPlaylistsByNameContaining(keyword, page);
    }

    public List<Playlist> findPlaylistByGenre(String keyword){
        return playlistRepository.findPlaylistsByGenre(keyword);
    }

    @Override
    public byte[] export(List<Playlist> playlists) throws IOException, DocumentException {
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

        Paragraph title = new Paragraph("PLAYLIST REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = new PdfPTable(2);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Playlist Name", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Subgenre", headerFont));


        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));


        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);


        for (Playlist playlist : playlists) {
            reportTable.addCell(playlist.getName() != null ? playlist.getName() : "N/A");
            reportTable.addCell(playlist.getSubgenre() != null ? playlist.getSubgenre() : "N/A");
        }

        document.add(reportTable);
        document.close();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
