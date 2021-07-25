package be.dieterblancke.kwamkwammer.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@AllArgsConstructor
@EnableJpaRepositories(basePackages = { "be.dieterblancke.kwamkwammer.repository" })
public class DataSourceConfig
{

    private final Environment environment;

    @Bean
    @Primary
    public DataSource dataSource()
    {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize( 10 );
        ds.setMinimumIdle( 3 );
        ds.setDriverClassName( "com.mysql.cj.jdbc.Driver" );
        ds.setJdbcUrl( environment.getProperty( "spring.datasource.url" ) );
        ds.addDataSourceProperty( "user", environment.getProperty( "spring.datasource.username" ) );
        ds.addDataSourceProperty( "password", environment.getProperty( "spring.datasource.password" ) );
        ds.addDataSourceProperty( "cachePrepStmts", true );
        ds.addDataSourceProperty( "prepStmtCacheSize", 250 );
        ds.addDataSourceProperty( "prepStmtCacheSqlLimit", 2048 );
        ds.addDataSourceProperty( "useServerPrepStmts", true );
        ds.addDataSourceProperty( "useSSL", false );
        return ds;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource() );
        em.setPackagesToScan( "be.dieterblancke.kwamkwammer.domain");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter( vendorAdapter );

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put( "hibernate.dialect", environment.getProperty( "spring.jpa.database-platform" ) );
        em.setJpaPropertyMap( properties );

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager()
    {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );
        return transactionManager;
    }
}
