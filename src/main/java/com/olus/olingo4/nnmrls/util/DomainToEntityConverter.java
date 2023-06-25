package com.olus.olingo4.nnmrls.util;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DomainToEntityConverter {

    private DomainToEntityConverter() {

        // Empty
    }

    public static Entity convertParagonRawAgent(Map<String, Object> data) {
        final Entity e = new Entity()
                .addProperty(new Property(null, "User_Code", ValueType.PRIMITIVE, data.get("User_Code")))
                .addProperty(new Property(null, "Active", ValueType.PRIMITIVE, data.get("Active")))
                .addProperty(new Property(null, "Goomzee", ValueType.PRIMITIVE,
                        data.get("Goomzee")));
        e.setId(createId("ParagonRawAgents", data.get("User_Code")));
        return e;
    }

    public static EntityCollection convertParagonRawAgentList(List<Map<String, Object>> list) {
        EntityCollection pragentsCollection = new EntityCollection();
        List<Entity> pragentList = pragentsCollection.getEntities();
        list.forEach(data -> {
            pragentList.add(convertParagonRawAgent(data));
        });
        return pragentsCollection;
    }

    private static URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + id + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }
}
