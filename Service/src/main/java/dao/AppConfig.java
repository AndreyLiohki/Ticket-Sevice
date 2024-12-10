package dao;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import model.ticket.Ticket;
import model.users.Client;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "dao")
public class AppConfig {

    @Bean
    @Scope("singleton")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        dataSource.setUser("postgres");
        dataSource.setPassword("1111");
        return dataSource;

    }

    @Bean
    @Scope("singleton")
    public SessionFactory sessionFactory() {
        DataSource dataSource = dataSource();
        Map<String, Object> settings = new HashMap<>();
        settings.put(Environment.DATASOURCE, dataSource);
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "org.springframework.orm.hibernate5.SpringSessionContext");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Client.class)
                .buildSessionFactory(serviceRegistry);
    }

    @Bean
    @Scope("singleton")

    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }
}