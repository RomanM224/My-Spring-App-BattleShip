package com.maistruks.portfolio.controller;

import static com.maistruks.portfolio.controller.PaintingControllerTest.TestData.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.maistruks.portfolio.config.TestConfig;
import com.maistruks.portfolio.exception.PaintingException;
import com.maistruks.portfolio.gallery.mapper.PaintingMapper;
import com.maistruks.portfolio.gallery.model.MyMultipartFile;
import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.Style;
import com.maistruks.portfolio.gallery.model.dto.PaintingDto;
import com.maistruks.portfolio.gallery.service.PainterService;
import com.maistruks.portfolio.gallery.service.PaintingService;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@ContextConfiguration(classes = TestConfig.class)
public class PaintingControllerTest {

    @Mock
    private PaintingService paintingService;
    @Mock
    private PainterService painterService;
    @Mock
    private View mockView;
    @Mock
    private PaintingMapper paintingMapper;
    @InjectMocks
    private PaintingController paintingController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(paintingController).setSingleView(mockView).build();
    }

    @Test
    public void givenCreateURI_whenMockMvc_thenSuccess() throws Exception {
        List<Style> styles = Arrays.asList(Style.values());
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.get("/painting/create"))
                .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
                .andExpect(MockMvcResultMatchers.model().attribute("styles", styles))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("galery/painting/create"));
    }

    @Test
    public void givenPainting_whenCreate_thenSuccess() throws Exception {
        InputStream stream1 = new BufferedInputStream(Files.newInputStream(
                Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_ma_jolie.png").toURI())));
        byte[] image1 = IOUtils.toByteArray(stream1);
        MockMultipartFile mockImageFile = new MockMultipartFile("image", "pablo_picaso_ma_jolie.png", "image/jpeg",
                image1);

        MyMultipartFile myMultipartFile = new MyMultipartFile(image1);
        paintingWithoutId.setImage(image1);
        paintingDtoWithoutId.setImage(myMultipartFile);
        Mockito.when(paintingMapper.mapPaintingDtoToPainting(paintingDtoWithoutId)).thenReturn(paintingWithoutId);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/painting/create").file(mockImageFile)
                .param("name", paintingName1).param("style", style1.toString()).param("year", year1.toString())
                .param("painterId", painterId1.toString()))
                .andExpect(MockMvcResultMatchers.model().attribute("info", "Painting created"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("galery/info"));
        Mockito.verify(paintingService).create(paintingWithoutId, painterId1);
    }

    @Test
    public void givenWrongPaintig_whenCreate_thenThrowException() throws Exception {
        Mockito.when(paintingMapper.mapPaintingDtoToPainting(paintingDtoWithoutIdAndName))
                .thenReturn(paintingWithoutIdAndName);
        Mockito.when(painterService.getAll()).thenReturn(painters);
        Mockito.doThrow(PaintingException.class).when(paintingService).create(paintingWithoutIdAndName, painterId1);
        mockMvc.perform(MockMvcRequestBuilders.post("/painting/create").param("style", style1.toString())
                .param("year", year1.toString()).param("painterId", painterId1.toString()))
                .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("galery/painting/create"));
    }

    @Test
    public void givenUpdateURI_whenMockMvc_thenSuccess() throws Exception {
        Mockito.when(painterService.getAll()).thenReturn(painters);
        Mockito.when(paintingService.getAll()).thenReturn(paintings);
        mockMvc.perform(MockMvcRequestBuilders.get("/painting/updateFullPaintingInfo"))
                .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
                .andExpect(MockMvcResultMatchers.model().attribute("paintings", paintings))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("galery/painting/updateFullPaintingInfo"));
    }

    @Test
    public void givenPainting_whenMockMvc_thenSuccess() throws Exception, URISyntaxException {
        InputStream stream1 = new BufferedInputStream(Files.newInputStream(
                Paths.get(getClass().getClassLoader().getResource("test_images/pablo_picaso_ma_jolie.png").toURI())));
        byte[] image1 = IOUtils.toByteArray(stream1);
        MockMultipartFile mockImageFile = new MockMultipartFile("image", "pablo_picaso_ma_jolie.png", "image/jpeg",
                image1);

        MyMultipartFile myMultipartFile = new MyMultipartFile(image1);
        painting1.setImage(image1);
        paintingDto.setImage(myMultipartFile);
        Mockito.lenient().when(paintingMapper.mapPaintingDtoToPainting(paintingDto)).thenReturn(painting1);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/painting/updateFullPaintingInfo").file(mockImageFile)
                .param("id", paintingId1.toString()).param("name", paintingName1).param("style", style1.toString())
                .param("year", year1.toString()).param("painterId", painterId1.toString()))
                .andExpect(MockMvcResultMatchers.model().attribute("info", "Painting Updated"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("galery/info"));
        Mockito.verify(paintingService).updateFullPaintingInfo(painting1, painterId1);
    }

    @Test
    public void givenWrongPainting_whenMockMvc_thenThrowException() throws Exception {
        Mockito.when(painterService.getAll()).thenReturn(painters);
        Mockito.when(paintingService.getAll()).thenReturn(paintings);
        Mockito.when(paintingMapper.mapPaintingDtoToPainting(paintingDtoWithoutName)).thenReturn(paintingWithoutName);
        Mockito.doThrow(PaintingException.class).when(paintingService).updateFullPaintingInfo(paintingWithoutName,
                painterId1);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/painting/updateFullPaintingInfo")
                .param("id", paintingId1.toString()).param("style", style1.toString()).param("year", year1.toString())
                .param("painterId", painterId1.toString()))
                .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
                .andExpect(MockMvcResultMatchers.model().attribute("paintings", paintings))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("galery/painting/updateFullPaintingInfo"));
    }

    @Test
    public void givenShowAllURI_whenGetAll_thenSuccess() throws Exception {
        Mockito.when(paintingService.getPaintingsInRange(0, 12)).thenReturn(paintings);
        Mockito.when(paintingService.getRowsAmount()).thenReturn(paintings.size());
        Mockito.when(paintingService.getPainterNames(paintings)).thenReturn(painterNames);
        mockMvc.perform(MockMvcRequestBuilders.get("/painting/showAll"))
            .andExpect(MockMvcResultMatchers.model().attribute("pageId", 1))
            .andExpect(MockMvcResultMatchers.model().attribute("rowsAmount", paintings.size()))
            .andExpect(MockMvcResultMatchers.model().attribute("pages", 1))
            .andExpect(MockMvcResultMatchers.model().attribute("urlName", "showAll"))
            .andExpect(MockMvcResultMatchers.model().attribute("painterNames", painterNames))
            .andExpect(MockMvcResultMatchers.model().attribute("paintings", paintings));
        Mockito.verify(paintingService).getPainterNames(paintings);    
    }
    
    @Test
    public void givenDeleteURI_whenMockMvc_thenSuccess() throws Exception {
        Mockito.when(paintingService.getAll()).thenReturn(paintings);
        mockMvc.perform(MockMvcRequestBuilders.get("/painting/delete"))
            .andExpect(MockMvcResultMatchers.model().attribute("paintings", paintings))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painting/delete"));
    }
    
    @Test
    public void givenPaintingId_whenDelete_thenSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/painting/delete").param("id", paintingId1.toString()))
            .andExpect(MockMvcResultMatchers.model().attribute("info", "Painter deleted"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/info"));
    }
    
    interface TestData {
        
        Integer paintingId1 = 1, paintingId2 = 2, paintingId3 = 3, paintingId4 = 4, paintingId5 = 5, paintingId6 = 6,
                paintingId7 = 7, paintingId8 = 8;

        Integer year1 = 1911, year2 = 1921, year3 = 1888, year4 = 1889, year5 = 1489, year6 = 1503, year7 = 1665,
                year8 = 1668;

        String paintingName1 = "Ma Jolie", paintingName2 = "The Three Musicians",
                paintingName3 = "Cafe Terrace At Night", paintingName4 = "Starry Night",
                paintingName5 = "Lady with an Ermine\"", paintingName6 = "Mona Lisa",
                paintingName7 = "Girl with a Pearl Earring", paintingName8 = "The Art of Painting";

        Style style1 = Style.CUBISM, style2 = Style.CUBISM, style3 = Style.POST_IMPRESSIONISM,
                style4 = Style.POST_IMPRESSIONISM, style5 = Style.HIGH_RENAISSANCE, style6 = Style.RENAISSANE,
                style7 = Style.TRONIE, style8 = Style.BAROQUE;

        List<Style> styles = Arrays.asList(style1, style2, style3, style4, style5, style6, style7, style8);

        Painting painting1 = new Painting(paintingId1, year1, paintingName1, style1);
        Painting painting2 = new Painting(paintingId2, year2, paintingName2, style2);
        Painting painting3 = new Painting(paintingId3, year3, paintingName3, style3);
        Painting painting4 = new Painting(paintingId4, year4, paintingName4, style4);
        Painting painting5 = new Painting(paintingId5, year5, paintingName5, style5);
        Painting painting6 = new Painting(paintingId6, year6, paintingName6, style6);
        Painting painting7 = new Painting(paintingId7, year7, paintingName7, style7);
        Painting painting8 = new Painting(paintingId8, year8, paintingName8, style8);
        Painting paintingWithoutId = new Painting(null, year1, paintingName1, style1);
        Painting paintingWithoutIdAndName = new Painting(null, year1, null, style1);
        PaintingDto paintingDtoWithoutId = new PaintingDto(null, paintingName1, style1, year1);
        PaintingDto paintingDto = new PaintingDto(paintingId1, paintingName1, style1, year1);
        PaintingDto paintingDtoWithoutIdAndName = new PaintingDto(null, null, style1, year1);
        PaintingDto paintingDtoWithoutName = new PaintingDto(paintingId1, null, style1, year1);
        Painting paintingWithoutYear = new Painting(paintingId1, null, paintingName1, style1);
        Painting paintingWithoutName = new Painting(paintingId1, year1, null, style1);

        List<Painting> paintings = Arrays.asList(painting1, painting2, painting3, painting4, painting5, painting6,
                painting7, painting8);
        List<Painting> paintings2 = Arrays.asList(painting1, painting2);

        Integer painterId1 = 1, painterId2 = 2, painterId3 = 3, painterId4 = 4;

        String firstName1 = "Pablo", firstName2 = "Vincent", firstName3 = "Leonardo", firstName4 = "Johannes";

        String lastName1 = "Picasso", lastName2 = "van Gogh", lastName3 = "da Vinci", lastName4 = "Vermeer";

        String info1 = "Pablo Picasso info", info2 = "Vincent van Gogh inf", info3 = "Leonardo da Vinci info",
                info4 = "Johannes Vermeer info";

        Painter painter1 = new Painter(painterId1, firstName1, lastName1, info1);
        Painter painter2 = new Painter(painterId2, firstName2, lastName2, info2);
        Painter painter3 = new Painter(painterId3, firstName3, lastName3, info3);
        Painter painter4 = new Painter(painterId4, firstName4, lastName4, info4);
        List<Painter> painters = Arrays.asList(painter1, painter2, painter3, painter4);
        
        Map<Integer, String> painterNames = new HashMap<Integer, String>() {
            {
                put(paintingId1, painter1.getFullName());
                put(paintingId2, painter1.getFullName());
                put(paintingId3, painter2.getFullName());
                put(paintingId4, painter2.getFullName());
                put(paintingId5, painter3.getFullName());
                put(paintingId6, painter3.getFullName());
                put(paintingId7, painter4.getFullName());
                put(paintingId8, painter4.getFullName());
            }
        };
    }

}
