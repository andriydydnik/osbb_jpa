package org.example.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mybatis.data.Order;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

public class Example {
    void doMyBatis() throws IOException {
        Reader reader = getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                session.delete("Orders.deleteById", 1);

                List<Order> orders = session.selectList("Orders.getAll");
                for (Order order : orders)
                    System.out.println(order);

                session.commit();
            } catch (Exception e) {
                session.rollback();
            }
        }
    }
}
