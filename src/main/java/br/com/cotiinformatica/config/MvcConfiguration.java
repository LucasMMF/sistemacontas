package br.com.cotiinformatica.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.cotiinformatica.repositories.ContaRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Configuration
@ComponentScan(basePackages = "br.com.cotiinformatica")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/*
	 * Método para configurar o DATA SOURCE do projeto, ou seja, a conexão com o
	 * banco de dados utilizado pelo projeto
	 */
	@Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/bd_sistemacontas");
		dataSource.setUsername("postgres");
		dataSource.setPassword("ruka");

		return dataSource;

	}

	/*
	 * Configuração para que a classe UsuarioRepository possa receber o DataSource
	 * do projeto (conexão com o banco de dados)
	 */
	@Bean
	public UsuarioRepository getUsuarioRepository() {
		return new UsuarioRepository(getDataSource());
	}

	/*
	 * Configuração para que a classe ContaRepository possa receber o DataSource do
	 * projeto (conexão com o banco de dados)
	 */
	@Bean
	public ContaRepository getContaRepository() {
		return new ContaRepository(getDataSource());
	}
}
