package be.dieterblancke.kwamkwammer.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@AllArgsConstructor
public class FlywayConfig
{

    @Bean
    public Flyway flyway( final DataSource dataSource )
    {
        log.info( "Migrating default schema " );
        final Flyway flyway = Flyway.configure()
                .table( "migrations" )
                .locations( "db/migration" )
                .dataSource( dataSource )
                .schemas( "kwamkwammer" )
                .load();
        flyway.migrate();
        return flyway;
    }
}