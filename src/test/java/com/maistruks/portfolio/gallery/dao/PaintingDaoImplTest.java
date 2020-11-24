package com.maistruks.portfolio.gallery.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
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
import com.maistruks.portfolio.gallery.dao.impl.PaintingDaoImpl;
import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.Style;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class PaintingDaoImplTest {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    PaintingDaoImpl paintingDao;
    
    @Test
    public void givenNewPainter_whenCreate_thenChangedRowsInDatabase() {
       Integer rowsBeforeCreatingInPainters = JdbcTestUtils.countRowsInTable(jdbcTemplate, "paintings");
       Integer rowsBeforeCreatingInPainters_Paintings = JdbcTestUtils.countRowsInTable(jdbcTemplate, "painters_paintings");
       Painting painting = new Painting(1, 1890,  "Church at Auvers", Style.POST_IMPRESSIONISM);
       
       paintingDao.create(painting, 2);
       
       Integer rowsAfterCreationInPainters = JdbcTestUtils.countRowsInTable(jdbcTemplate, "paintings");
       Integer rowsAfterCreationInPainters_Paintings = JdbcTestUtils.countRowsInTable(jdbcTemplate, "painters_paintings");
       assertEquals(rowsBeforeCreatingInPainters + 1, rowsAfterCreationInPainters);
       assertEquals(rowsBeforeCreatingInPainters_Paintings + 1, rowsAfterCreationInPainters_Paintings);
    }
    
    @Test
    public void givenPainter_whenUpdateFullPaintingInfo_thenUpdated() {
        Painting painting = new Painting(2, 1890,  "Church at Auvers", Style.POST_IMPRESSIONISM);
        
        paintingDao.updateFullPaintingInfo(painting, 4);
        
        Integer rowsInTablePaintings = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "paintings", "id = 2 and year = 1890 and name = 'Church at Auvers' and style = 'POST_IMPRESSIONISM'");
        Integer rowsInTablePainters_Paintings = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "painters_paintings", "painter_id = 2 and painting_id = 4");
        assertEquals(rowsInTablePaintings, 1);
        assertEquals(rowsInTablePainters_Paintings, 1);
    }
    
    @Test
    public void givenPainter_whenUpdatePaintingInfo_thenUpdated() {
        Painting painting = new Painting(2, 1890,  "Church at Auvers", Style.POST_IMPRESSIONISM);
        
        paintingDao.updatePaintingInfo(painting);
        
        Integer rowsInTablePaintings = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "paintings", "id = 2 and year = 1890 and name = 'Church at Auvers' and style = 'POST_IMPRESSIONISM'");
        assertEquals(rowsInTablePaintings, 1);
    }
    
    @Test
    public void whenGetAll_thenGetAllPainters() {
        Painting painting1 = new Painting(1, 1911,  "Ma Jolie", Style.CUBISM);
        Painting painting2 = new Painting(2, 1921,  "The Three Musicians", Style.CUBISM);
        Painting painting3 = new Painting(3, 1888,  "Cafe Terrace At Night", Style.POST_IMPRESSIONISM);
        Painting painting4 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        Painting painting5 = new Painting(5, 1489,  "Lady with an Ermine", Style.HIGH_RENAISSANCE);
        Painting painting6 = new Painting(6, 1503,  "Mona Lisa", Style.RENAISSANE);
        Painting painting7 = new Painting(7, 1665,  "Girl with a Pearl Earring", Style.TRONIE);
        Painting painting8 = new Painting(8, 1668,  "The Art of Painting", Style.BAROQUE);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2, painting3, painting4, painting5, painting6, painting7, painting8);
        
        List<Painting> actualPaintings = paintingDao.getAll();

        assertEquals(expectedPaintings, actualPaintings);
    }
    
    @Test
    public void givenPaintingId_whenDelete_thenDeleted() {
        Integer rowsBeforeDeleting = JdbcTestUtils.countRowsInTable(jdbcTemplate, "paintings");
        
        paintingDao.delete(3);
        
        Integer rowsAfterDeletion = JdbcTestUtils.countRowsInTable(jdbcTemplate, "paintings");
        assertEquals(rowsBeforeDeleting - 1, rowsAfterDeletion);
    }
    
    @Test
    public void givenPainter_whenGetByPainter_thenGetPaintings() {
        Painting painting1 = new Painting(3, 1888,  "Cafe Terrace At Night", Style.POST_IMPRESSIONISM);
        Painting painting2 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        List<Painting> expetedPaintings = Arrays.asList(painting1, painting2);
        Painter painter = new Painter(2, "Vincent", "van Gogh", "Vincent van Gogh info");
        
        List<Painting> actualPaintings = paintingDao.getByPainter(painter);
        
        assertEquals(expetedPaintings, actualPaintings);
    }
    
    @Test
    public void givenPaintingName_whenGetByName_thenGetPainting() {
        Painting expectedPainting = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        
        Painting actualPainting = paintingDao.getByName("Starry Night");
        
        assertEquals(expectedPainting, actualPainting);
    }
    
    @Test
    public void givenStyle_whenGetByStyle_thenGetPaintings() {
        Painting painting1 = new Painting(3, 1888,  "Cafe Terrace At Night", Style.POST_IMPRESSIONISM);
        Painting painting2 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2);
        
        List<Painting> actualPaintings = paintingDao.getByStyle(Style.POST_IMPRESSIONISM);
        
        assertEquals(expectedPaintings, actualPaintings);
    }
    
    @Test
    public void whenGetRowsAmount_thenGetRowsAmount() {
        Integer expectedRows = 8;
        
        Integer actualRows = paintingDao.getRowsAmount();
        
        assertEquals(expectedRows, actualRows);
    }
    
    @Test
    public void givenOffsetAndLimit_whenGetPaintingsInRange_thenGetPaintings() {
        Painting painting1 = new Painting(5, 1489,  "Lady with an Ermine", Style.HIGH_RENAISSANCE);
        Painting painting2 = new Painting(6, 1503,  "Mona Lisa", Style.RENAISSANE);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2);
        
        List<Painting> actualPaintings = paintingDao.getPaintingsInRange(4, 2);
        
        assertEquals(expectedPaintings, actualPaintings);
    }
    
    @Test
    public void givenOffsetAndLimit_whenGetSortedByNameAsc_thenGetPaintings() {
        Painting painting1 = new Painting(5, 1489,  "Lady with an Ermine", Style.HIGH_RENAISSANCE);
        Painting painting2 = new Painting(1, 1911,  "Ma Jolie", Style.CUBISM);
        Painting painting3 = new Painting(6, 1503,  "Mona Lisa", Style.RENAISSANE);
        Painting painting4 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2, painting3, painting4);
        
        List<Painting> actualPaintings = paintingDao.getSortedByNameAsc(2, 4);
        
        assertEquals(expectedPaintings, actualPaintings);
    }
    
    @Test
    public void givenOffsetAndLimit_whenGetSortedByNameDesc_thenGetPaintings() {
        Painting painting1 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        Painting painting2 = new Painting(6, 1503,  "Mona Lisa", Style.RENAISSANE);
        Painting painting3 = new Painting(1, 1911,  "Ma Jolie", Style.CUBISM);
        Painting painting4 = new Painting(5, 1489,  "Lady with an Ermine", Style.HIGH_RENAISSANCE);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2, painting3, painting4);
        
        List<Painting> actualPaintings = paintingDao.getSortedByNameDesc(2, 4);
        
        assertEquals(expectedPaintings, actualPaintings);
    }
    
    @Test
    public void givenOffsetAndLimit_whenGetSortedByYearAsc_thenGetPaintings() {
        Painting painting1 = new Painting(7, 1665,  "Girl with a Pearl Earring", Style.TRONIE);
        Painting painting2 = new Painting(8, 1668,  "The Art of Painting", Style.BAROQUE);
        Painting painting3 = new Painting(3, 1888,  "Cafe Terrace At Night", Style.POST_IMPRESSIONISM);
        Painting painting4 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2, painting3, painting4);
        
        List<Painting> actualPaintings = paintingDao.getSortedByYearAsc(2, 4);
        
        assertEquals(expectedPaintings, actualPaintings);
    }
    
    @Test
    public void givenOffsetAndLimit_whenGetSortedByYearDesc_thenGetPaintings() {
        Painting painting1 = new Painting(4, 1889,  "Starry Night", Style.POST_IMPRESSIONISM);
        Painting painting2 = new Painting(3, 1888,  "Cafe Terrace At Night", Style.POST_IMPRESSIONISM);
        Painting painting3 = new Painting(8, 1668,  "The Art of Painting", Style.BAROQUE);
        Painting painting4 = new Painting(7, 1665,  "Girl with a Pearl Earring", Style.TRONIE);
        List<Painting> expectedPaintings = Arrays.asList(painting1, painting2, painting3, painting4);
        
        List<Painting> actualPaintings = paintingDao.getSortedByYearDesc(2, 4);
        
        assertEquals(expectedPaintings, actualPaintings);
    }
}
