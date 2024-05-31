package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.List;

@Node
public class Album {

    @RelationshipId @GeneratedValue
    private Long id;
    private String name;

    @Relationship(type = "INCLUDED_IN", direction = Direction.INCOMING)
    private List<Song> songs;

    public Album (){
    }
}