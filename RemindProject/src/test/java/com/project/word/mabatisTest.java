package com.project.word;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class mabatisTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    @DisplayName("DataSourceTest")
    public void datasource() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("sqlSessonFactoryTest")
    public void sqlSessionTest() {
        try{
            SqlSession session = sqlSessionFactory.openSession();
            System.out.println(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
