package com.olus.olingo4.nnmrls.storage;

import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import com.olus.olingo4.nnmrls.service.NnmrlsEdmProvider;
import com.olus.olingo4.nnmrls.util.DomainToEntityConverter;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.server.api.uri.UriParameter;

import java.util.List;
import java.util.Optional;

public class Olingo4Storage {

    private final MyBatisDao mybatisDao;

    public Olingo4Storage() {
        this.mybatisDao = new MyBatisDao();
    }

    /**
     * Helper method for providing some sample data
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public EntityCollection getData(EdmEntitySet edmEntitySet, int offset, int limit) {

        var pragentsCollection = new EntityCollection();

        // Check for which EdmEntitySet the data is requested
        if (NnmrlsEdmProvider.ES_PRAGENT_NAME.equals(edmEntitySet.getName())) {
            return DomainToEntityConverter.convertEntityList("ParagonRawAgents", "User_Code",
                    mybatisDao.getAllParagonRawAgents(offset, limit));
        }

        return pragentsCollection;
    }

    public Optional<Entity> getDataByParams(EdmEntitySet edmEntitySet, List<UriParameter> keyParams) {
        var edmEntityType = edmEntitySet.getEntityType();

        // This is only required if we have more than one Entity Type
        if (edmEntityType.getName().equals(NnmrlsEdmProvider.ET_PRAGENT_NAME)) {
            for (final UriParameter key : keyParams) {
                var keyText = key.getText();
                var result = mybatisDao.selectParagonRawAgentById(keyText);
                if (result.isPresent()) {
                    return Optional.of(DomainToEntityConverter.convertEntity("ParagonRawAgents",
                            "User_Code", result.get()));
                }
            }
        }
        return Optional.empty();
    }
}
