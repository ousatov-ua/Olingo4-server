package com.olus.olingo4.nnmrls.dao;

import com.olus.olingo4.nnmrls.dao.mappers.ParagonRawAgentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Implementation for {@link IDao}
 *
 * @author Oleksii Usatov
 */
public class MyBatisDao implements IDao {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(MyBatisDao.class);
    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisDao() {
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(getResource());
    }

    protected InputStream getResource() {
        String resource = "mybatis/mybatis-config.xml";
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }

    @Override
    public List<Map<String, Object>> getAllParagonRawAgents() {
        try (var session = sqlSessionFactory.openSession()) {
            var subscriberMapper = session.getMapper(ParagonRawAgentMapper.class);
            return subscriberMapper.selectParagonRawAgents();
        }
    }
}
