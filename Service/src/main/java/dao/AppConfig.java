package dao;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import model.ticket.Ticket;
import model.users.Client;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
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
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/my_ticket_service_db");
        settings.put(Environment.USER, "postgres");
        settings.put(Environment.PASS, "1111");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Client.class)
                .buildSessionFactory(serviceRegistry);
    }

    @Bean
    @Scope("singleton")
    public HibernateUtil hibernateUtil() {
        return new HibernateUtil(sessionFactory());
    }

    @Bean
    @Scope("singleton")
    public UserDao userDao(){
        return new UserDao(hibernateUtil());
    }

    @Scope("singleton")
    public TicketDao ticketDao(){
        return new TicketDao(hibernateUtil());
    }

}
