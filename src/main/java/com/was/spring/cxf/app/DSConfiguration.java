package com.was.spring.cxf.app;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EntityScan("com.was.spring.cxf.dto")
@EnableJpaRepositories(
      entityManagerFactoryRef = "entityManagerFactory", 
      basePackages = { "com.was.spring.cxf.repository" })
@Order(1)
public class DSConfiguration {
	
    private Logger logger = LoggerFactory.getLogger(DSConfiguration.class);

	@Autowired
	private Environment environment;
	
	@Bean
	public JndiObjectFactoryBean dataSourceFactoryBean() {

		final JndiObjectFactoryBean jofb = new JndiObjectFactoryBean();
		jofb.setJndiName("jdbc/ccbIntDataSource");

		return jofb;
	}

	@Bean
	@Primary
	public DataSource dataSource() {

		final Boolean initDB = false;
		final DataSource dataSource = (DataSource) dataSourceFactoryBean().getObject();
		//final Boolean initDB = environment.getProperty("init.db", Boolean.class, Boolean.FALSE);
		logger.debug("Is initializing DB from sql scripts enabled? init.db={}", initDB);

		if (initDB) {
			logger.debug("Start initializing DB with scripts:");
//			for (final Resource initScript : dbInitScripts) {
//				logger.debug(initScript.getDescription());
//			}

			final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			//populator.addScripts(dbInitScripts);
		    populator.setSqlScriptEncoding("UTF-8");
			DatabasePopulatorUtils.execute(populator, dataSource);
			logger.debug("End initializing DB");
		}

		return dataSource;
	}

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
