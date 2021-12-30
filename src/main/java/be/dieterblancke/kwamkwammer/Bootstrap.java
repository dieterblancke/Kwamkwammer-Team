package be.dieterblancke.kwamkwammer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(
        exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class }
)
public class Bootstrap
{
    public static void main( final String[] args )
    {
        SpringApplication.run( Bootstrap.class, args );
    }
}