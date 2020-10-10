package com.maistruk.springapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.maistruk.springapp.controller.BattleshipController;
import com.maistruk.springapp.controller.MainController;
import com.maistruk.springapp.controller.StudentController;
import com.maistruk.springapp.dao.StudentDao;
import com.maistruk.springapp.dao.StudentDaoImpl;
import com.maistruk.springapp.dao.StudentRowMapper;
import com.maistruk.springapp.service.StudentService;
import com.maistruk.springapp.service.battleShip.FieldChecker;
import com.maistruk.springapp.service.battleShip.GamaManager;
import com.maistruk.springapp.service.battleShip.ShipsGenerator;
import com.maistruk.springapp.service.battleShip.TableCreater;
import com.maistruk.springapp.service.battleShip.UserInputChecker;

@Configuration
@ComponentScan({ "com.maistruk.springapp.config" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public MainController myController() {
        return new MainController();
    }
    
    @Bean
    public StudentController studentController() {
        return new StudentController();
    }
    
    @Bean
    public StudentService studentService() {
        return new StudentService();
    }
    
    @Bean 
    public StudentDao studentDao() {
        return new StudentDaoImpl(); 
    }
    
    @Bean
    public StudentRowMapper studentRowMapper() {
        return new StudentRowMapper();
    }

    /**
     * Required to inject properties using the 'Value' annotation.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driver}")
    private String driver;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setDriverClassName(driver);
        return driverManagerDataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public BattleshipController battleshipController() {
        return new BattleshipController();
    }
    
    @Bean
    public GamaManager gamaManager() {
         return new GamaManager();
    }
    
    @Bean
    public FieldChecker fieldChecker() {
         return new FieldChecker();
    }
    
    @Bean
    public TableCreater tableCreater() {
         return new TableCreater();
    }
    
    @Bean UserInputChecker userInputChecker() {
        return new UserInputChecker();
    }
    
    @Bean
    public ShipsGenerator shipsGenerator() {
        return new ShipsGenerator();
    }

}