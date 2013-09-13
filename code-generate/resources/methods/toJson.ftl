<import>
flexjson.JSONDeserializer,flexjson.JSONSerializer,java.util.ArrayList,java.util.Collection,java.util.List
</import>
<content>
// TODO toJson methods
public String toJson() {
    return new JSONSerializer().exclude("*.class").serialize(this);
}

public static ${domain} fromJsonTo${domain}(String json) {
    return new JSONDeserializer<${domain}>().use(null, ${domain}.class).deserialize(json);
}

public static String toJsonArray(Collection<${domain}> collection) {
    return new JSONSerializer().exclude("*.class").serialize(collection);
}

public static Collection<${domain}> fromJsonArrayTo${domain}s(String json) {
    return new JSONDeserializer<List<${domain}>>().use(null, ArrayList.class).use("values", ${domain}.class).deserialize(json);
}
    
public String toJson(String... fields) {
    return new JSONSerializer().include(fields).exclude("*").serialize(this);
}

public static String toJsonArray(Collection<${domain}> collection, String... fields) {
    return new JSONSerializer().include(fields).exclude("*").serialize(collection);
}
</content>