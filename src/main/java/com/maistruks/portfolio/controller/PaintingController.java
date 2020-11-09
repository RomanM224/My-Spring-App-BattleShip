package com.maistruks.portfolio.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.maistruks.portfolio.model.gallery.Painter;
import com.maistruks.portfolio.model.gallery.Painting;
import com.maistruks.portfolio.model.gallery.Style;
import com.maistruks.portfolio.model.gallery.dto.PaintingDto;
import com.maistruks.portfolio.service.gallery.PainterService;
import com.maistruks.portfolio.service.gallery.PaintingService;

@Controller
@RequestMapping("/painting")
public class PaintingController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private PainterService painterService;

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
            ModelAndView modelAndView = new ModelAndView("galery/info");
            paintingService.create(paintingDto, Integer.valueOf(painterId));
            return modelAndView.addObject("info", "Painter created");
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
            ModelAndView modelAndView = new ModelAndView("galery/info");
            paintingService.updateFullPaintingInfo(paintingDto, painterId);
            return modelAndView.addObject("info", "Painter created");
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
            paintingService.updatePaintingInfo(paintingDto);
            return modelAndView.addObject("info", "Painter created");
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
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAll();
        Map<Integer, String> painterNames = getPainterNames(paintings);
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
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByNameAsc")
    public ModelAndView sortByNameAsc() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByNameAsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByNameDsc")
    public ModelAndView sortByNameDsc() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByNameDsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByYearAsc")
    public ModelAndView sortByYearAsc() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByYearAsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }

    @GetMapping("/sortByYearDsc")
    public ModelAndView sortByYearDsc() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByYearDsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
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

    public Map<Integer, String> getPainterNames(List<Painting> paintings) {
        Map<Integer, String> painterNames = new HashMap<>();
        for (Painting painting : paintings) {
            Painter painter = painterService.getByPaintingId(painting.getId());
            painterNames.put(painting.getId(), painter.getFullName());
        }
        return painterNames;
    }
}
