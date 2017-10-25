package nl.webedu.hourregistration.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MongoID {
    private String $oid;

    public MongoID(){
    }

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

    @JsonCreator
    public static String fromJSON(String val) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        MongoID a = mapper.readValue(val,MongoID.class);
        return a.get$oid();
    }
}
