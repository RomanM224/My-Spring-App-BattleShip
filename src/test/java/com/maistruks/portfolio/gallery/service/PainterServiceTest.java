package com.maistruks.portfolio.gallery.service;

import static com.maistruks.portfolio.gallery.service.PainterServiceTest.TestData.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.gallery.dao.impl.PainterDaoImpl;
import com.maistruks.portfolio.gallery.model.Painter;

@ExtendWith(MockitoExtension.class)
public class PainterServiceTest {
    
    @Mock
    private PainterDaoImpl painterDao;
    
    @InjectMocks
    private PainterService painterService;
    
    @Test
    public void givenPainter_whenCreate_thenCreated() throws PainterException {
        painterService.create(painter1);
        
        verify(painterDao).create(painter1);
    }
    
    @Test
    public void givenPainterWithoutFirstName_whenCreate_thenNotCreated() {
        
        assertThrows(PainterException.class, () -> painterService.create(painterWithoutFirstName));
    }
    
    @Test
    public void givenPainterWithoutLastName_whenCreate_thenNotCreated() {
        
        assertThrows(PainterException.class, () -> painterService.create(painterWithoutLastName));
    }
    
    @Test
    public void givenPainterWithoutInfo_whenCreate_thenNotCreated() {
        
        assertThrows(PainterException.class, () -> painterService.create(painterWithoutInfo));
    }
    
    @Test
    public void givenPainter_whenUpdate_thenUpdated() throws PainterException {
        painterService.update(painter1);
        
        verify(painterDao).update(painter1);
    }
    
    @Test
    public void givenPainterWithoutFirstName_whenUpdate_thenNotUpdated() {
        
        assertThrows(PainterException.class, () -> painterService.update(painterWithoutFirstName));
    }
    
    @Test
    public void givenPainterWithoutLastName_whenUpdate_thenNotUpdated() {
        
        assertThrows(PainterException.class, () -> painterService.update(painterWithoutLastName));
    }
    
    @Test
    public void givenPainterWithoutInfo_whenUpdate_thenNotUpdated() {
        
        assertThrows(PainterException.class, () -> painterService.update(painterWithoutInfo));
    }
    
    @Test
    public void whenGetAll_thenGetAllPainters() {
        when(painterDao.getAll()).thenReturn(painters);
        
        List<Painter> actualPainters = painterService.getAll();
        
        assertEquals(painters, actualPainters);
    }
    
    @Test
    public void givenId_whenGetById_thenGetPainting() throws PainterException {
        when(painterDao.getById(painterId2)).thenReturn(painter2);
        
        Painter actualPainter = painterService.getById(painterId2);
        
        assertEquals(painter2, actualPainter);
    }
    
    
    interface TestData {
        Integer painterId1 = 1;
        Integer painterId2 = 2;
        Integer painterId3 = 3;
        Integer painterId4 = 4;
        
        String firstName1 = "Pablo";
        String firstName2 = "Vincent";
        String firstName3 = "Leonardo";
        String firstName4 = "Johannes";
        
        String lastName1 = "Picasso";
        String lastName2 = "van Gogh";
        String lastName3 = "da Vinci";
        String lastName4 = "Vermeer";
        
        String info1 = "Pablo Picasso info";
        String info2 = "Vincent van Gogh inf";
        String info3 = "Leonardo da Vinci info";
        String info4 = "Johannes Vermeer info";
        
        Painter painter1 = new Painter(painterId1, firstName1, lastName1, info1); 
        Painter painter2 = new Painter(painterId2, firstName2, lastName2, info2);
        Painter painter3 = new Painter(painterId3, firstName3, lastName3, info3);
        Painter painter4 = new Painter(painterId4, firstName4, lastName4, info4);
        Painter notExistPainter = null;
        Painter painterWithoutFirstName = new Painter(painterId1, null, lastName1, info1);
        Painter painterWithoutLastName = new Painter(painterId1, firstName1, null, info1);
        Painter painterWithoutInfo = new Painter(painterId1, firstName1, lastName1, null);

        List<Painter> painters = Arrays.asList(painter1, painter2, painter3, painter4);
    }

}
