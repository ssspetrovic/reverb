package rs.ac.uns.acs.nais.OrchestratorService.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TrackDeserializer extends JsonDeserializer<TrackDTO> {
    @Override
    public TrackDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String id = node.get("id").asText();
        String name = node.get("name").asText();
        int popularity = node.get("popularity").asInt();
        double danceability = node.get("danceability").asDouble();
        double energy = node.get("energy").asDouble();
        int key = node.get("key").asInt();
        double loudness = node.get("loudness").asDouble();
        int mode = node.get("mode").asInt();
        double speechiness = node.get("speechiness").asDouble();
        double acousticness = node.get("acousticness").asDouble();
        double instrumentalness = node.get("instrumentalness").asDouble();
        double liveness = node.get("liveness").asDouble();
        double valence = node.get("valence").asDouble();
        double tempo = node.get("tempo").asDouble();
        int duration_ms = node.get("duration_ms").asInt();
        String albumId = node.get("albumId").asText();
        String artistId = node.get("artistId").asText();
        String playlistId = node.get("playlistId").asText();

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(id);
        trackDTO.setName(name);
        trackDTO.setPopularity(popularity);
        trackDTO.setDanceability(danceability);
        trackDTO.setEnergy(energy);
        trackDTO.setKey(key);
        trackDTO.setLoudness(loudness);
        trackDTO.setMode(mode);
        trackDTO.setSpeechiness(speechiness);
        trackDTO.setAcousticness(acousticness);
        trackDTO.setInstrumentalness(instrumentalness);
        trackDTO.setLiveness(liveness);
        trackDTO.setValence(valence);
        trackDTO.setTempo(tempo);
        trackDTO.setDurationMs(duration_ms);
        trackDTO.setAlbumId(albumId);
        trackDTO.setArtistId(artistId);
        trackDTO.setPlaylistId(playlistId);

        return trackDTO;
    }
}
