package com.maistruks.portfolio.gallery.service;

import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.painter1;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.painterId1;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.painting1;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.painting2;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.paintingName2;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.paintingWithoutName;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.paintingWithoutYear;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.paintings;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.paintings2;
import static com.maistruks.portfolio.gallery.service.PaintingServiceTest.TestData.style1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.exception.PaintingException;
import com.maistruks.portfolio.gallery.dao.impl.PaintingDaoImpl;
import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.Style;

@ExtendWith(MockitoExtension.class)
public class PaintingServiceTest {
    
    @Mock
    private PaintingDaoImpl paintingDao;
    
    @Mock
    private PainterService painterService;
    
    @InjectMocks
    private PaintingService paintingService;
    
    @Test
    public void givenPaintingAndPainterId_whenCreate_thenCreated() throws PaintingException {
        paintingService.create(painting1, 1);
        
        verify(paintingDao).create(painting1, 1);
    }
    
    @Test
    public void givenPaintingWithoutPainterId_whenCreate_thenNotCreated() {
        
        assertThrows(PaintingException.class, () -> paintingService.create(painting1, null));
    }

    @Test
    public void givenPaintingWithoutNameAndPainterId_whenCreate_thenNotCreated() {
        
        assertThrows(PaintingException.class, () -> paintingService.create(paintingWithoutName, 1));
    }
    
    @Test
    public void givenPaintingWithoutYearAndPainterId_whenCreate_thenNotCreated() {
        
        assertThrows(PaintingException.class, () -> paintingService.create(paintingWithoutYear, 1));
    }
    
    @Test
    public void givenPainting_whenUpdateFullPaintingInfo_thenUpdated() throws PaintingException {
        paintingService.updateFullPaintingInfo(painting1, 2);
        
        verify(paintingDao).updateFullPaintingInfo(painting1, 2);
    }
    
    @Test
    public void givenPaintingWithoutPainterId_whenUpdateFullPaintingInfo_thenNotUpdated() {
        
        assertThrows(PaintingException.class, () -> paintingService.updateFullPaintingInfo(painting1, null));
    }
    
    @Test
    public void givenPaintingWithoutYearAndPainterId_whenUpdateFullPaintingInfo_thenNotUpdated() {
        
        assertThrows(PaintingException.class, () -> paintingService.updateFullPaintingInfo(paintingWithoutYear, 1));
    }
    
    @Test
    public void givenPaintingWithoutNameAndPainterId_whenUpdateFullPaintingInfo_thenNotUpdated() {
        
        assertThrows(PaintingException.class, () -> paintingService.updateFullPaintingInfo(paintingWithoutName, 1));
    }
    
    @Test
    public void givenPainting_whenUpdatePaintingInfo_thenUpdated() throws PaintingException {
        paintingService.updatePaintingInfo(painting1);
        
        verify(paintingDao).updatePaintingInfo(painting1);
    }
    
    @Test
    public void givenPaintingWithoutName_whenUpdatePaintingInfo_thenNotUpdated() {
        
        assertThrows(PaintingException.class, () -> paintingService.updatePaintingInfo(paintingWithoutName));
    }
    
    @Test
    public void givenPaintingWithoutYear_whenUpdatePaintingInfo_thenNotUpdated() {
        
        assertThrows(PaintingException.class, () -> paintingService.updatePaintingInfo(paintingWithoutYear));
    }
    
    @Test
    public void whenGetAll_thenGetAllPaintings()  throws IOException, URISyntaxException {
        InputStream stream1 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_ma_jolie.png").toURI())));
        byte[] image1 = IOUtils.toByteArray(stream1);
        
        InputStream stream2 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_the_three_musicians.png").toURI())));
        byte[] image2 = IOUtils.toByteArray(stream2);
        
        InputStream stream3 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/vincent_van_goghJ_cafe_terrace_at_night.png").toURI())));
        byte[] image3 = IOUtils.toByteArray(stream3);
        
        InputStream stream4 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/vincent_van_goghJ_starry_night.png").toURI())));
        byte[] image4 = IOUtils.toByteArray(stream4);
        
        InputStream stream5 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/leonardo_da_vinci_lady_with_an_ermine.png").toURI())));
        byte[] image5 = IOUtils.toByteArray(stream5);
        
        InputStream stream6 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/leonardo_da_vinci_mona_lisa.png").toURI())));
        byte[] image6 = IOUtils.toByteArray(stream6);
        
        InputStream stream7 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/Johannes_Vermeer_Girl_With_A_Pearl_Earring.png").toURI())));
        byte[] image7 = IOUtils.toByteArray(stream7);
        
        InputStream stream8 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/Jan_Vermeer_The_Art_of_Painting.png").toURI())));
        byte[] image8 = IOUtils.toByteArray(stream8);
        
        paintings.get(0).setImage(image1);
        paintings.get(1).setImage(image2);
        paintings.get(2).setImage(image3);
        paintings.get(3).setImage(image4);
        paintings.get(4).setImage(image5);
        paintings.get(5).setImage(image6);
        paintings.get(6).setImage(image7);
        paintings.get(7).setImage(image8);
        
        when(paintingDao.getAll()).thenReturn(paintings);
        
        List<Painting> actualPaintings = paintingService.getAll();
        
        assertEquals(paintings, actualPaintings);
    }
    
    @Test
    public void givenPaintingName_whenGetByName_thenGetPainting() throws IOException, URISyntaxException, PaintingException {
        InputStream stream2 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_the_three_musicians.png").toURI())));
        byte[] image2 = IOUtils.toByteArray(stream2);
        painting2.setImage(image2);
        when(paintingDao.getByName(paintingName2)).thenReturn(painting2);
        
        Painting actualPainting = paintingService.getByName(paintingName2);
        
        assertEquals(painting2, actualPainting);
    }
    
    @Test
    public void givenPaintingWithEmptyName_whenGetByName_thenThrowException() {
        
        assertThrows(PaintingException.class, () -> paintingService.getByName(""));
    }
    
    @Test
    public void givenPaintingWithoutName_whenGetByName_thenThrowException() {
        
        assertThrows(PaintingException.class, () -> paintingService.getByName(null));
    }
    
    @Test
    public void givenPainterId_whenGetByPainterId_thenGetPaintin() throws PainterException, IOException, URISyntaxException, PaintingException {
        InputStream stream1 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_ma_jolie.png").toURI())));
        byte[] image1 = IOUtils.toByteArray(stream1);
        
        InputStream stream2 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_the_three_musicians.png").toURI())));
        byte[] image2 = IOUtils.toByteArray(stream2);
        paintings2.get(0).setImage(image1);
        paintings2.get(1).setImage(image2);
        when(painterService.getById(painterId1)).thenReturn(painter1);
        when(paintingDao.getByPainter(painter1)).thenReturn(paintings2);
        
        List<Painting> actualPaintings = paintingService.getByPainterId(painterId1);
        
        assertEquals(paintings2, actualPaintings);
    }
    
    @Test
    public void notGivenPainterId_whenGetByPainterId_thenNotGetPaintin() {
        
        assertThrows(PaintingException.class, () -> paintingService.getByPainterId(null));
    }
    
    @Test
    public void givenStyle_whenGetByStyle_thenGetPaintings() throws IOException, URISyntaxException {
        InputStream stream1 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_ma_jolie.png").toURI())));
        byte[] image1 = IOUtils.toByteArray(stream1);
        
        InputStream stream2 = new BufferedInputStream(
                Files.newInputStream(Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_the_three_musicians.png").toURI())));
        byte[] image2 = IOUtils.toByteArray(stream2);
        paintings2.get(0).setImage(image1);
        paintings2.get(1).setImage(image2);
        
        when(paintingDao.getByStyle(style1)).thenReturn(paintings2);
        
        List<Painting> actualPaintings = paintingService.getByStyle(style1);
        
        assertEquals(paintings2, actualPaintings);
    }
  
    interface TestData{
        Integer paintingId1 = 1;
        Integer paintingId2 = 1;
        Integer paintingId3 = 1;
        Integer paintingId4 = 1;
        Integer paintingId5 = 1;
        Integer paintingId6 = 1;
        Integer paintingId7 = 1;
        Integer paintingId8 = 1;
        
        Integer year1 = 1911;
        Integer year2 = 1921;
        Integer year3 = 1888;
        Integer year4 = 1889;
        Integer year5 = 1489;
        Integer year6 = 1503;
        Integer year7 = 1665;
        Integer year8 = 1668;
        
        String paintingName1 = "Ma Jolie";
        String paintingName2 = "The Three Musicians";
        String paintingName3 = "Cafe Terrace At Night";
        String paintingName4 = "Starry Night";
        String paintingName5 = "Lady with an Ermine";
        String paintingName6 = "Mona Lisa";
        String paintingName7 = "Girl with a Pearl Earring";
        String paintingName8 = "The Art of Painting";
        
        Style style1 = Style.CUBISM;
        Style style2 = Style.CUBISM;
        Style style3 = Style.POST_IMPRESSIONISM;
        Style style4 = Style.POST_IMPRESSIONISM;
        Style style5 = Style.HIGH_RENAISSANCE;
        Style style6 = Style.RENAISSANE;
        Style style7 = Style.TRONIE;
        Style style8 = Style.BAROQUE;
        
        Painting painting1 = new Painting(paintingId1, year1, paintingName1, style1);
        Painting painting2 = new Painting(paintingId2, year2, paintingName2, style2);
        Painting painting3 = new Painting(paintingId3, year3, paintingName3, style3);
        Painting painting4 = new Painting(paintingId4, year4, paintingName4, style4);
        Painting painting5 = new Painting(paintingId5, year5, paintingName5, style5);
        Painting painting6 = new Painting(paintingId6, year6, paintingName6, style6);
        Painting painting7 = new Painting(paintingId7, year7, paintingName7, style7);
        Painting painting8 = new Painting(paintingId8, year8, paintingName8, style8);
        Painting paintingWithoutYear = new Painting(paintingId1, null, paintingName1, style1);
        Painting paintingWithoutName = new Painting(paintingId1, year1, null, style1);

        List<Painting> paintings = Arrays.asList(painting1, painting2, painting3, painting4, painting5, painting6,painting7, painting8);
        List<Painting> paintings2 = Arrays.asList(painting1, painting2);

        
        Integer painterId1 = 1;
        String firstName1 = "Pablo";
        String lastName1 = "Picasso";
        String info1 = "Pablo Picasso info";
        Painter painter1 = new Painter(painterId1, firstName1, lastName1, info1); 

    }

}
