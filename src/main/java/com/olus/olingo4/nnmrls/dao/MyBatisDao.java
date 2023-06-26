package com.olus.olingo4.nnmrls.dao;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingFeaturesMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawListingRemarksMapper;
import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawOfficeMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation for {@link IDao}
 *
 * @author Oleksii Usatov
 */
public class MyBatisDao implements IDao {
    private static final int DEFAULT_ROW_LIMIT = 30;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(MyBatisDao.class);
    private final SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor
     */
    public MyBatisDao() {
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(getResource());
    }

    /**
     * Get mybatis resource, created for test purpose to override it
     *
     * @return {@link InputStream}
     */
    protected InputStream getResource() {
        String resource = "mybatis/mybatis-config.xml";
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }

    private static int offset(int offset) {
        return Math.max(offset, 0);
    }

    private static int limit(int limit) {
        if (limit <= 0) {
            return DEFAULT_ROW_LIMIT;
        }
        return limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllParagonRawAgents(int offset, int limit) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawAgentMapper.class);
            return subscriberMapper.selectParagonRawAgents(offset(offset), limit(limit));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllParagonRawOffices(int offset, int limit) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawOfficeMapper.class);
            return subscriberMapper.selectParagonRawOffices(offset(offset), limit(limit));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllParagonRawListings(int offset, int limit) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawListingMapper.class);
            return subscriberMapper.selectParagonRawListings(offset(offset), limit(limit));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectParagonRawListingById(String id) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawListingMapper.class);
            var result = subscriberMapper.selectParagonRawListingById(id);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectParagonRawListingFeaturesById(String id) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawListingFeaturesMapper.class);
            var result = subscriberMapper.selectParagonRawListingFeaturesById(id);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllParagonRawListingFeatures(int offset, int limit) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawListingFeaturesMapper.class);
            return subscriberMapper.selectParagonRawListingFeatures(offset(offset), limit(limit));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectParagonRawAgentById(String id) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawAgentMapper.class);
            var result = subscriberMapper.selectParagonRawAgentById(id);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectParagonRawOfficeById(String id) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawOfficeMapper.class);
            var result = subscriberMapper.selectParagonRawOfficeById(id);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectParagonRawListingRemarksById(String id) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawListingRemarksMapper.class);
            var result = subscriberMapper.selectParagonRawListingRemarksById(id);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> selectAllParagonRawListingRemarks(int offset, int limit) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawListingRemarksMapper.class);
            return subscriberMapper.selectParagonRawListingRemarks(offset(offset), limit(limit));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> insertParagonRawAgent(Map<String, Object> data) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawAgentMapper.class);
            subscriberMapper.insertParagonRawAgent(data);
            session.commit();
            return subscriberMapper.selectParagonRawAgentById(String.valueOf(data.get(ParagonRawAgentMapper.PK_KEY)));
        }
    }
}
