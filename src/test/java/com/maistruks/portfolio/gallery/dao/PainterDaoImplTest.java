package com.maistruks.portfolio.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.maistruks.portfolio.config.TestConfig;
import com.maistruks.portfolio.gallery.dao.impl.PainterDaoImpl;
import com.maistruks.portfolio.gallery.model.Painter;


@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class PainterDaoImplTest {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    PainterDaoImpl painterDao;
    
    @Test
    public void givenNewPainter_whenCreated_thenChangedRowsInDatabase() {
        Integer rowsInDatabaseBeforeCreating = JdbcTestUtils.countRowsInTable(jdbcTemplate, "painters");
        Painter painter = new Painter(5, "FirstName","LastInfo", "Painter info");
        painterDao.create(painter);
        
        Integer rowsInDatabaseAfterCreation = JdbcTestUtils.countRowsInTable(jdbcTemplate, "painters");
        
        assertEquals(rowsInDatabaseBeforeCreating + 1, rowsInDatabaseAfterCreation);
    }
    
    @Test
    public void givenPainter_whenUpdate_thenUpdated() {
        Painter painter = new Painter(2, "Updated First Name", "Updated Last Name", "Painter info");

        painterDao.update(painter);
        
        Integer rowsInDatabaseExpected = 1;
        Integer rowsInDatabaseActual = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "painters", 
                "first_name = 'Updated First Name' and last_name = 'Updated Last Name' and info = 'Painter info' and id = 2");
        assertEquals(rowsInDatabaseExpected, rowsInDatabaseActual);
    }
    
    @Test
    public void whenGetAll_thenGetAllPainters() {
        Painter painter1 = new Painter(1, "Pablo", "Picasso", "Pablo Picasso info");
        Painter painter2 = new Painter(2, "Vincent", "van Gogh", "Vincent van Gogh info");
        Painter painter3 = new Painter(3, "Leonardo", "da Vinci", "Leonardo da Vinci info");
        Painter painter4 = new Painter(4, "Johannes", "Vermeer", "Johannes Vermeer info");
        List<Painter> expectedPainters = new ArrayList<Painter>();
        expectedPainters.add(painter1);
        expectedPainters.add(painter2);
        expectedPainters.add(painter3);
        expectedPainters.add(painter4);
        List<Painter> actualpainters = painterDao.getAll();
        
        assertEquals(actualpainters, expectedPainters);
    }
    
    @Test
    public void whenDeletePainter_thenReduceAmountOfRows() {
        Integer rowsInDatabaseBeforeDeleting = JdbcTestUtils.countRowsInTable(jdbcTemplate, "painters");
        painterDao.delete(2);
        Integer rowsInDatabaseAfterDeletion = JdbcTestUtils.countRowsInTable(jdbcTemplate, "painters");
        
        assertEquals(rowsInDatabaseBeforeDeleting - 1, rowsInDatabaseAfterDeletion);
    }
    
    @Test
    public void giverPainterId_whenGetById_thenGetPainter(){
        Painter expecterPainter = new Painter(2, "Vincent", "van Gogh", "Vincent van Gogh info");
        
        Painter actualPainter = painterDao.getById(2);
        
        assertEquals(expecterPainter, actualPainter);
    }
    
    @Test
    public void givenPaintingId_whenGetByPaintingId_thenGetPainter() {
        Painter expecterPainter = new Painter(2, "Vincent", "van Gogh", "Vincent van Gogh info");
        
        Painter astualPainter = painterDao.getByPaintingId(4);
        
        assertEquals(expecterPainter, astualPainter);
    }

}
