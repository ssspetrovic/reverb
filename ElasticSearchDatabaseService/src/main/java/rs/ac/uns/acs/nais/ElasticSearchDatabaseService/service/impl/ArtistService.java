package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IArtistService;

import java.util.List;
import java.util.Optional;


@Service
public class ArtistService implements IArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    // Retrieve an artist by ID
    public Optional<Artist> findArtistById(String id) {
        return artistRepository.findById(id);
    }

    // Retrieve all artists
    public Iterable<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    // Delete an artist by ID
    public void deleteArtistById(String id) {
        artistRepository.deleteById(id);
    }

    // Custom query methods
    public List<Artist> findByArtistNameOrArtistDescription(String name, String description) {
        return artistRepository.findByArtistNameOrArtistDescription(name, description);
    }

    public List<Artist> findByArtistNameContainingOrArtistDescriptionContaining(String name, String description) {
        return artistRepository.findByArtistNameContainingOrArtistDescriptionContaining(name, description);
    }

    public List<Artist> findByCustomQuery(String query) {
        return artistRepository.findByCustomQuery(query);
    }

    public List<Artist> searchByDescriptionPhrase(String phrase) {
        return artistRepository.searchByDescriptionPhrase(phrase);
    }

    public List<Artist> searchByNameOrDescriptionFuzzy(String searchTerm) {
        return artistRepository.searchByNameOrDescriptionFuzzy(searchTerm);
    }

    public List<Artist> findByNameAndDescriptionNotAndOptional(String name, String mustNotTerms, String shouldTerms) {
        return artistRepository.findByNameAndDescriptionNotAndOptional(name, mustNotTerms, shouldTerms);
    }

    public List<Artist> findByNestedAttributeAndAggregate(String attributeName, String attributeValue) {
        return artistRepository.findByNestedAttributeAndAggregate(attributeName, attributeValue);
    }

    public List<Artist> findByFunctionScore(String searchTerm, String boostTerms) {
        return artistRepository.findByFunctionScore(searchTerm, boostTerms);
    }
/* 
    public byte[] export(List<Product> products) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        String filename = "podaci/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("PRODUCTS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Product", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Description", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("ID", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);

        for (Product product : products) {
            reportTable.addCell(product.getName());
            reportTable.addCell(product.getDescription());
            reportTable.addCell(product.getId());
        }

        document.add(reportTable);
        document.close();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }
    */
}