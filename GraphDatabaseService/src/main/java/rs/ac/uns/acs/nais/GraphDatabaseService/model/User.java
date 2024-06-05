package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Node("CollectionUser")
public class User {
    @RelationshipId
    private Long userId;
    private String username;
    private List<Song> favoriteSongs = new ArrayList<>();

    public User() {
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", this.userId);
        map.put("username", this.username);
        map.put("favoriteSongs", this.favoriteSongs);
        return map;
    }

    public List<Song> getFavoriteSongs() {
        return new ArrayList<>(favoriteSongs);
    }
}
