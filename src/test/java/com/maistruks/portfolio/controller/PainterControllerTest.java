package com.maistruks.portfolio.controller;

import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.firstName1;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.firstName2;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.info1;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.info2;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.lastName1;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.lastName2;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painter1;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painter2;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterDto1;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterDto2;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterDtoWithoutFirstName;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterDtoWithoutFirstNameAndId;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterId1;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterId2;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterWithoutFirstName;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painterWithoutFirstNameAndId;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painters;
import static com.maistruks.portfolio.controller.PainterControllerTest.TestData.painters2;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import com.maistruks.portfolio.config.TestConfig;
import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.gallery.mapper.PainterMapper;
import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.dto.PainterDto;
import com.maistruks.portfolio.gallery.service.PainterService;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = TestConfig.class)
public class PainterControllerTest {
    
    @Mock
    private PainterService painterService;
    @Mock
    private View mockView;
    @Mock
    private PainterMapper painterMapper;
    @InjectMocks
    private PainterController painterController;
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(painterController).setSingleView(mockView).build();
    }
    
    @Test
    public void givenCreateURI_whenMockMvc_thenSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/painter/create"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/create"));
    }
    
    @Test
    public void givePainter_whenCreate_thenSuccess() throws Exception {
        Mockito.lenient().when(painterMapper.mapPainterDtoToPainter(painterDto1)).thenReturn(painter1);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/create")
            .param("firstName", firstName1).param("lastName", lastName1).param("painterInfo", info1))
            .andExpect(MockMvcResultMatchers.model().attribute("info", "Painter created"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("galery/info"));
        Mockito.verify(painterService).create(painter1);
    }
    
    @Test
    public void givenWrongPainter_whenCreate_thenThrowException() throws Exception {
        Mockito.lenient().when(painterMapper.mapPainterDtoToPainter(painterDtoWithoutFirstNameAndId)).thenReturn(painterWithoutFirstNameAndId);
        Mockito.doThrow(PainterException.class).when(painterService).create(painterWithoutFirstNameAndId);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/create").param("lastName", lastName1).param("painterInfo", info1))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/create"));   
    }
    
    @Test
    public void givenUpdateURI_whenMockMVC_thenSuccess() throws Exception {
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.get("/painter/update"))
            .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/update"));     
    }
    
    @Test
    public void givenPainter_whenUpdate_thenSuccess() throws Exception {
        Mockito.when(painterMapper.mapPainterDtoToPainter(painterDto2)).thenReturn(painter2);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/update").param("id", painterId2.toString())
            .param("firstName", firstName2).param("lastName", lastName2).param("painterInfo", info2))
            .andExpect(MockMvcResultMatchers.model().attribute("info", "Painter Updated"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/info"));
        Mockito.verify(painterService).update(painter2);
    }
    
    @Test
    public void givenWrongPainter_whenUpdate_thenThrowException() throws Exception {
        Mockito.when(painterMapper.mapPainterDtoToPainter(painterDtoWithoutFirstName)).thenReturn(painterWithoutFirstName);
        Mockito.doThrow(PainterException.class).when(painterService).update(painterWithoutFirstName);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/update/").param("id", painterId1.toString())
            .param("lastName", lastName1).param("painterInfo", info1))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/update"));
    }
    
    @Test
    public void givenShowAllURI_whenMockMvc_thenSuccess() throws Exception {
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.get("/painter/showAll"))
            .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/showAll"));
    }
    
    @Test
    public void givenShowByIdURI_whenShowAll_thenSuccess() throws Exception {
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.get("/painter/showById"))
        .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.view().name("galery/painter/showById"));
    }
    
    @Test
    public void givenPainterId_whenGetById_thenSuccess() throws Exception {
        Mockito.when(painterService.getById(painterId2)).thenReturn(painter2);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/showById").param("id", "2"))
            .andExpect(MockMvcResultMatchers.model().attribute("painters", painters2))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/showAll"));
    }
    
    @Test
    public void givenNotExistPainterId_whenGetById_thenThrowException() throws Exception {
        Mockito.doThrow(PainterException.class).when(painterService).getById(999);
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/showById").param("id", "999"))
            .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/showById"));
    }
    
    @Test
    public void givenDeleteURI_whenMockMvc_thenSuccess() throws Exception {
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.get("/painter/delete"))
            .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/delete"));
    }
    
    @Test
    public void givenPainterId_whenDelete_thenSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/delete").param("id", "2"))
            .andExpect(MockMvcResultMatchers.model().attribute("info", "Painter deleted"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/info"));
    }
    
    @Test
    public void givenNotExistPainterId_whenDelete_thenThrowException() throws Exception, PainterException {
        Mockito.doThrow(PainterException.class).when(painterService).delete(999);
        Mockito.when(painterService.getAll()).thenReturn(painters);
        mockMvc.perform(MockMvcRequestBuilders.post("/painter/delete").param("id", "999"))
            .andExpect(MockMvcResultMatchers.model().attribute("painters", painters))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.view().name("galery/painter/delete"));
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
        
        Painter painter1 = new Painter(firstName1, lastName1, info1); 
        Painter painter2 = new Painter(painterId2, firstName2, lastName2, info2);
        Painter painter3 = new Painter(painterId3, firstName3, lastName3, info3);
        Painter painter4 = new Painter(painterId4, firstName4, lastName4, info4);
        Painter notExistPainter = null;
        Painter painterWithoutFirstNameAndId = new Painter(null, null, lastName1, info1);
        Painter painterWithoutFirstName = new Painter(painterId1, null, lastName1, info1);
        Painter painterWithoutLastName = new Painter(painterId1, firstName1, null, info1);
        Painter painterWithoutInfo = new Painter(painterId1, firstName1, lastName1, null);
        
        PainterDto painterDto1 = new PainterDto(null, firstName1, lastName1, info1);
        PainterDto painterDto2 = new PainterDto(painterId2, firstName2, lastName2, info2);
        PainterDto painterDtoWithId = new PainterDto(painterId2, null, null, null);

        PainterDto painterDtoWithoutFirstNameAndId = new PainterDto(null, null, lastName1, info1);
        PainterDto painterDtoWithoutFirstName = new PainterDto(painterId1, null, lastName1, info1);
        List<Painter> painters = Arrays.asList(painter1, painter2, painter3, painter4);
        List<Painter> painters2 = Arrays.asList(painter2);
    }
}
