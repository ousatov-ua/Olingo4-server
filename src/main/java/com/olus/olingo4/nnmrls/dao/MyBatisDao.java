package com.olus.olingo4.nnmrls.dao;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import org.apache.ibatis.session.RowBounds;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getAllParagonRawAgents(int offset, int limit) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawAgentMapper.class);
            if (offset == -1 && limit == -1) {

                // We don't want to return all agents - it will be too hard
                return subscriberMapper.selectParagonRawAgentsLimited();
            }
            var rowBounds = new RowBounds(offset, limit);
            return subscriberMapper.selectParagonRawAgents(rowBounds);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> selectParagonRawAgentById(String id) {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawAgentMapper.class);
            var key = id;
            if (id.length() >= 3) {
                key = id.substring(1, id.length() - 1);
            }
            var result = subscriberMapper.selectParagonRawAgentById(key);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        }
    }
}
