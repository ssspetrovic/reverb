package rs.ac.uns.acs.nais.OrchestratorService.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import rs.ac.uns.acs.nais.OrchestratorService.dto.TrackDTO;

import java.util.Map;

public class TrackDTODeserializer implements Deserializer<TrackDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No-op
    }

    @Override
    public TrackDTO deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(data, TrackDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing TrackDTO", e);
        }
    }

    @Override
    public void close() {
        // No-op
    }
}
