package com.maistruks.portfolio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.exception.PaintingException;
import com.maistruks.portfolio.gallery.mapper.PaintingMapper;
import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.Style;
import com.maistruks.portfolio.gallery.model.dto.PaintingDto;
import com.maistruks.portfolio.gallery.service.PainterService;
import com.maistruks.portfolio.gallery.service.PaintingService;

@Controller
@RequestMapping("/painting")
public class PaintingController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private PainterService painterService;
    
    @Autowired
    private PaintingMapper paintingMapper;

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/create");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute PaintingDto paintingDto,
            @RequestParam(required = false, defaultValue = "") Integer painterId) {
        try {
            Painting painting = paintingMapper.mapPaintingDtoToPainting(paintingDto);
            ModelAndView modelAndView = new ModelAndView("galery/info");
            paintingService.create(painting, Integer.valueOf(painterId));
            return modelAndView.addObject("info", "Painting created");
        } catch (PaintingException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painting/create");
            List<Painter> painters = painterService.getAll();
            modelAndView.addObject("painters", painters);
            List<Style> styles = Arrays.asList(Style.values());
            modelAndView.addObject("styles", styles);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/updateFullPaintingInfo")
    public ModelAndView updateFullPaintingInfo() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/updateFullPaintingInfo");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }

    @PostMapping("/updateFullPaintingInfo")
    public ModelAndView updateFullPaintingInfo(@ModelAttribute PaintingDto paintingDto,
            @RequestParam(required = false, defaultValue = "") Integer painterId) {
        try {
            Painting painting = paintingMapper.mapPaintingDtoToPainting(paintingDto);
            ModelAndView modelAndView = new ModelAndView("galery/info");
            paintingService.updateFullPaintingInfo(painting, painterId);
            return modelAndView.addObject("info", "Painting Updated");
        } catch (PaintingException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painting/updateFullPaintingInfo");
            List<Painter> painters = painterService.getAll();
            modelAndView.addObject("painters", painters);
            List<Painting> paintings = paintingService.getAll();
            modelAndView.addObject("paintings", paintings);
            List<Style> styles = Arrays.asList(Style.values());
            modelAndView.addObject("styles", styles);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/updatePaintingInfo")
    public ModelAndView updatePaintingInfo() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/updatePaintingInfo");
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }

    @PostMapping("/updatePaintingInfo")
    public ModelAndView updatePaintingInfo(@ModelAttribute PaintingDto paintingDto) {
        try {
            ModelAndView modelAndView = new ModelAndView("galery/info");
            Painting painting = paintingMapper.mapPaintingDtoToPainting(paintingDto);
            paintingService.updatePaintingInfo(painting);
            return modelAndView.addObject("info", "Painting Updated");
        } catch (PaintingException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painting/updatePaintingInfo");
            List<Painting> paintings = paintingService.getAll();
            modelAndView.addObject("paintings", paintings);
            List<Style> styles = Arrays.asList(Style.values());
            modelAndView.addObject("styles", styles);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/showAll")
    public ModelAndView showAll(ServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        String pageIdRequest = request.getParameter("pageId");
        Integer pageId = 1;
        if(pageIdRequest != null) {
             pageId = Integer.valueOf(pageIdRequest);
        }
        Integer offset = pageId * 12 - 12;
        List<Painting> paintings = paintingService.getPaintingsInRange(offset, 12);
        Integer rowsAmount = paintingService.getRowsAmount();
        Integer pages;
        if(rowsAmount % 12 == 0) {
            pages = rowsAmount / 12;
        }else {
            pages = rowsAmount / 12 + 1;
        }
        modelAndView.addObject("pageId", pageId);
        modelAndView.addObject("rowsAmount", rowsAmount);
        modelAndView.addObject("pages", pages);
        Map<Integer, String> painterNames = paintingService.getPainterNames(paintings);
        modelAndView.addObject("urlName", "showAll");
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/showPainting")
    public ModelAndView showByName() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showByPainting");
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @PostMapping("/showPainting")
    public ModelAndView showByName(HttpServletRequest request) {
        String paintingName = request.getParameter("paintingName");
        try {
            ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
            Painting painting = paintingService.getByName(paintingName);
            List<Painting> paintings = new ArrayList<>();
            paintings.add(painting);
            Map<Integer, String> painterNames = new HashMap<>();
            Painter painter = painterService.getByPaintingId(painting.getId());
            painterNames.put(painting.getId(), painter.getFullName());
            modelAndView.addObject("painterNames", painterNames);
            modelAndView.addObject("paintings", paintings);
            return modelAndView;
        } catch (PaintingException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painting/showByPainting");
            List<Painting> paintings = paintingService.getAll();
            modelAndView.addObject("paintings", paintings);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/showByPainter")
    public ModelAndView showByPainter() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showByPainter");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }

    @PostMapping("/showByPainter")
    public ModelAndView showByPainter(@RequestParam(required = false, defaultValue = "") Integer painterId) {
        try {
            ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
            List<Painting> paintings;
            paintings = paintingService.getByPainterId(painterId);
            Painter painter = painterService.getById(Integer.valueOf(painterId));
            Map<Integer, String> painterNames = new HashMap<>();
            for (Painting painting : paintings) {
                painterNames.put(painting.getId(), painter.getFullName());
            }
            modelAndView.addObject("painterNames", painterNames);
            modelAndView.addObject("paintings", paintings);
            return modelAndView;
        } catch (PaintingException | PainterException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painting/showByPainter");
            List<Painter> painters = painterService.getAll();
            modelAndView.addObject("painters", painters);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/showByStyle")
    public ModelAndView showByStyle() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showByStyle");
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }

    @PostMapping("/showByStyle")
    public ModelAndView showByStyle(@RequestParam("style") String myStyle) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        Style style = Style.valueOf(myStyle);
        List<Painting> paintings = paintingService.getByStyle(style);
        Map<Integer, String> painterNames = paintingService.getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByNameAsc")
    public ModelAndView sortByNameAsc(ServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        String pageIdRequest = request.getParameter("pageId");
        Integer pageId = 1;
        if(pageIdRequest != null) {
             pageId = Integer.valueOf(pageIdRequest);
        }
        Integer offset = pageId * 12 - 12;
        List<Painting> paintings = paintingService.getSortedByNameAsc(offset, 12);
        Integer rowsAmount = paintingService.getRowsAmount();
        Integer pages;
        if(rowsAmount % 12 == 0) {
            pages = rowsAmount / 12;
        }else {
            pages = rowsAmount / 12 + 1;
        }
        modelAndView.addObject("pageId", pageId);
        modelAndView.addObject("rowsAmount", rowsAmount);
        modelAndView.addObject("pages", pages);
        Map<Integer, String> painterNames = paintingService.getPainterNames(paintings);
        modelAndView.addObject("urlName", "sortByNameAsc");
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByNameDsc")
    public ModelAndView sortByNameDsc(ServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        String pageIdRequest = request.getParameter("pageId");
        Integer pageId = 1;
        if(pageIdRequest != null) {
             pageId = Integer.valueOf(pageIdRequest);
        }
        Integer offset = pageId * 12 - 12;
        List<Painting> paintings = paintingService.getSortedByNameDesc(offset, 12);
        Integer rowsAmount = paintingService.getRowsAmount();
        Integer pages;
        if(rowsAmount % 12 == 0) {
            pages = rowsAmount / 12;
        }else {
            pages = rowsAmount / 12 + 1;
        }
        modelAndView.addObject("pageId", pageId);
        modelAndView.addObject("rowsAmount", rowsAmount);
        modelAndView.addObject("pages", pages);
        Map<Integer, String> painterNames = paintingService.getPainterNames(paintings);
        modelAndView.addObject("urlName", "sortByNameDsc");
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByYearAsc")
    public ModelAndView sortByYearAsc(ServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        String pageIdRequest = request.getParameter("pageId");
        Integer pageId = 1;
        if(pageIdRequest != null) {
             pageId = Integer.valueOf(pageIdRequest);
        }
        Integer offset = pageId * 12 - 12;
        List<Painting> paintings = paintingService.getSortedByYearAsc(offset, 12);
        Integer rowsAmount = paintingService.getRowsAmount();
        Integer pages;
        if(rowsAmount % 12 == 0) {
            pages = rowsAmount / 12;
        }else {
            pages = rowsAmount / 12 + 1;
        }
        modelAndView.addObject("pageId", pageId);
        modelAndView.addObject("rowsAmount", rowsAmount);
        modelAndView.addObject("pages", pages);
        Map<Integer, String> painterNames = paintingService.getPainterNames(paintings);
        modelAndView.addObject("urlName", "sortByYearAsc");
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByYearDsc")
    public ModelAndView sortByYearDsc(ServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        String pageIdRequest = request.getParameter("pageId");
        Integer pageId = 1;
        if(pageIdRequest != null) {
             pageId = Integer.valueOf(pageIdRequest);
        }
        Integer offset = pageId * 12 - 12;
        List<Painting> paintings = paintingService.getSortedByYearDesc(offset, 12);
        Integer rowsAmount = paintingService.getRowsAmount();
        Integer pages;
        if(rowsAmount % 12 == 0) {
            pages = rowsAmount / 12;
        }else {
            pages = rowsAmount / 12 + 1;
        }
        modelAndView.addObject("pageId", pageId);
        modelAndView.addObject("rowsAmount", rowsAmount);
        modelAndView.addObject("pages", pages);
        Map<Integer, String> painterNames = paintingService.getPainterNames(paintings);
        modelAndView.addObject("urlName", "sortByYearDsc");
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/delete");
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute PaintingDto paintingDto) {
        try {
            ModelAndView modelAndView = new ModelAndView("galery/info");
            paintingService.delete(paintingDto.getId());
            return modelAndView.addObject("info", "Painter deleted");
        } catch (PaintingException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painting/delete");
            List<Painting> paintings = paintingService.getAll();
            modelAndView.addObject("paintings", paintings);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    
}
