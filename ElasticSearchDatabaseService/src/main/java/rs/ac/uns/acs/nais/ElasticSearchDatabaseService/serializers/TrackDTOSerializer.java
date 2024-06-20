package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos.TrackDTO;

import java.util.Map;

public class TrackDTOSerializer implements Serializer<TrackDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No-op
    }

    @Override
    public byte[] serialize(String topic, TrackDTO data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing TrackDTO", e);
        }
    }

    @Override
    public void close() {
        // No-op
    }
}
