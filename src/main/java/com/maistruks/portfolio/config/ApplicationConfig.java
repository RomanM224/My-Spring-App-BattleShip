package com.maistruks.portfolio.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.maistruks.portfolio.controller.BattleshipController;
import com.maistruks.portfolio.controller.CvController;
import com.maistruks.portfolio.controller.MainController;
import com.maistruks.portfolio.controller.PainterController;
import com.maistruks.portfolio.controller.PaintingController;
import com.maistruks.portfolio.gallery.dao.PainterDao;
import com.maistruks.portfolio.gallery.dao.PaintingDao;
import com.maistruks.portfolio.gallery.dao.impl.PainterDaoImpl;
import com.maistruks.portfolio.gallery.dao.impl.PaintingDaoImpl;
import com.maistruks.portfolio.gallery.dao.impl.rowMapper.PainterRowMapper;
import com.maistruks.portfolio.gallery.dao.impl.rowMapper.PaintingRowMapper;
import com.maistruks.portfolio.gallery.mapper.PainterMapper;
import com.maistruks.portfolio.gallery.mapper.PaintingMapper;
import com.maistruks.portfolio.service.battleShip.FieldChecker;
import com.maistruks.portfolio.service.battleShip.GamaManager;
import com.maistruks.portfolio.service.battleShip.ShipsGenerator;
import com.maistruks.portfolio.service.battleShip.TableCreater;
import com.maistruks.portfolio.service.battleShip.UserInputChecker;
import com.maistruks.portfolio.service.gallery.PainterService;
import com.maistruks.portfolio.service.gallery.PaintingService;

@Configuration
@ComponentScan({ "com.maistruk.springapp.config" })
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public MainController myController() {
        return new MainController();
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
    
    @Bean
    public PainterDao painterDao() {
        return new PainterDaoImpl();
    }
    
    @Bean 
    public PaintingDao paintingDao() {
        return new PaintingDaoImpl();
    }
    
    @Bean
    public PainterRowMapper painterRowMapper() {
        return new PainterRowMapper();
    }
    
    @Bean
    public PaintingRowMapper paintingRowMapper() {
        return new PaintingRowMapper();
    }
    
    @Bean
    public PainterService painterService() {
        return new PainterService();
    }
    
    @Bean
    public PaintingService paintingService() {
        return new PaintingService();
    }
    
    @Bean
    public PainterController painterController() {
        return new PainterController();
    }
    
    @Bean
    public PaintingController paintingController() {
        return new PaintingController();
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
    
    @Bean
    public PaintingMapper paintingMapper() {
        return new PaintingMapper();
    }
    
    @Bean  
    public PainterMapper painterMapper() {
        return new PainterMapper();
    }

    @Bean
    public CvController cvController() {
        return new CvController();
    }
}