package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public interface LongestSongInEveryAlbumProjection {
    String getTrackAlbumName();
    String getTrackName();
    int getDurationMs();
}
