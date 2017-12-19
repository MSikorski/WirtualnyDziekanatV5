package org.mateuszsikorski.wirtualnydziekanat.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.mateuszsikorski.wirtualnydziekanat.*")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Config extends WebMvcConfigurerAdapter{
	
	
	private final String dbUrl = "jdbc:mysql://localhost:3306/dziennikmobilny?useSSL=false";
	private final String dbUser = "springstudent";
	private final String dbPass = "springstudent";
	
	/*
	private final String dbUrl = "jdbc:mysql://localhost:3306/mateuszs_WirtualnyDziekanat?useSSL=false";
	private final String dbUser = "mateuszs_root";
	private final String dbPass = "Polki12345";
	*/

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);

		return viewResolver;
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource myDataSource() {

		DriverManagerDataSource myDataSource = new DriverManagerDataSource();
		
		myDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		myDataSource.setUrl(dbUrl);
		myDataSource.setUsername(dbUser);
		myDataSource.setPassword(dbPass);


		return myDataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory mySessionFactory(DriverManagerDataSource myDataSource) throws IOException {

		LocalSessionFactoryBean mySessionFactory = new LocalSessionFactoryBean();
		
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.put("hibernate.show_sql", true);
		
		mySessionFactory.setDataSource(myDataSource);
		mySessionFactory.setPackagesToScan("org.mateuszsikorski.wirtualnydziekanat.entity");
		mySessionFactory.setHibernateProperties(properties);
		mySessionFactory.afterPropertiesSet(); // !!!
		
		return mySessionFactory.getObject();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager myTransactionManager(SessionFactory sessionFactory){
		
		HibernateTransactionManager myTransactionManager = new HibernateTransactionManager();
		
		myTransactionManager.setSessionFactory(sessionFactory);
		
		return myTransactionManager;
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
