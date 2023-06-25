package com.olus.olingo4.nnmrls.storage;

import com.olus.olingo4.nnmrls.dao.MyBatisDao;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider;
import com.olus.olingo4.nnmrls.util.DomainToEntityConverter;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.server.api.uri.UriParameter;

import java.util.List;
import java.util.Optional;

import static com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider.ES_PRAGENT_NAME;

/**
 * Storage class to fetch {@link EntityCollection}
 *
 * @author Oleksii Usatov
 */
public class Olingo4Storage {

    private final MyBatisDao mybatisDao;

    public Olingo4Storage() {
        this.mybatisDao = new MyBatisDao();
    }

    /**
     * Fetch data for specified offset and limit ('skip' and 'top')
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public EntityCollection getData(EdmEntitySet edmEntitySet, int offset, int limit) {

        var pragentsCollection = new EntityCollection();

        // Check for which EdmEntitySet the data is requested
        if (ES_PRAGENT_NAME.equals(edmEntitySet.getName())) {
            return DomainToEntityConverter.convertEntityList(ES_PRAGENT_NAME,
                    ParagonRawAgentMapper.PK_KEY,
                    mybatisDao.getAllParagonRawAgents(offset, limit));
        }

        return pragentsCollection;
    }

    /**
     * Fetch data for specified offset and limit ('skip' and 'top')
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public Optional<Entity> getDataByKeys(EdmEntitySet edmEntitySet, List<UriParameter> keyParams) {
        var edmEntityType = edmEntitySet.getEntityType();

        // This is only required if we have more than one Entity Type
        if (edmEntityType.getName().equals(NnmrlsEdmProvider.ET_PRAGENT_NAME)) {
            for (final UriParameter key : keyParams) {
                var keyText = key.getText();
                var result = mybatisDao.selectParagonRawAgentById(keyText);
                if (result.isPresent()) {
                    return Optional.of(DomainToEntityConverter.convertEntity(ES_PRAGENT_NAME,
                            ParagonRawAgentMapper.PK_KEY, result.get()));
                }
            }
        }
        return Optional.empty();
    }
}
