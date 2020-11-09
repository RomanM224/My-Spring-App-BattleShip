package com.maistruks.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.exception.PaintingException;
import com.maistruks.portfolio.model.gallery.Painter;
import com.maistruks.portfolio.model.gallery.dto.PainterDto;
import com.maistruks.portfolio.service.gallery.PainterService;

@Controller
@RequestMapping("/painter")
public class PainterController {

    @Autowired
    private PainterService painterService;

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("galery/painter/create");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute PainterDto painterDto) {
        try {
            painterService.create(painterDto);
            ModelAndView modelAndView = new ModelAndView("galery/info");
            return modelAndView.addObject("info", "Painter created");
        } catch (PainterException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painter/create");
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/update")
    public ModelAndView update() {
        List<Painter> painters = painterService.getAll();
        ModelAndView modelAndView = new ModelAndView("galery/painter/update");
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute PainterDto painterDto) {
        try {
            painterService.update(painterDto);
            ModelAndView modelAndView = new ModelAndView("galery/info");
            return modelAndView.addObject("info", "Painter created");
        } catch (PainterException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painter/update");
            List<Painter> painters = painterService.getAll();
            modelAndView.addObject("painters", painters);
            return modelAndView.addObject("info", e.getMessage());
        }
    }

    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("galery/painter/showAll");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }

    @GetMapping("/showById")
    public ModelAndView getById() {
        ModelAndView modelAndView = new ModelAndView("galery/painter/showById");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }

    @PostMapping("/showById")
    public ModelAndView getById(@ModelAttribute PainterDto painterDto) {
        List<Painter> painters = new ArrayList<>();
        try {
            Painter painter = painterService.getById(painterDto.getId());
            painters.add(painter);
            ModelAndView modelAndView = new ModelAndView("galery/painter/showAll");
            return modelAndView.addObject("painters", painters);
        } catch (PainterException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painter/showById");
            painters = painterService.getAll();
            modelAndView.addObject("painters", painters);
            return modelAndView.addObject("info", e.getMessage());
        }

    }

    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView modelAndView = new ModelAndView("galery/painter/delete");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute PainterDto painterDto) {
        try {
            painterService.delete(painterDto.getId());
            ModelAndView modelAndView = new ModelAndView("galery/info");
            return modelAndView.addObject("info", "Painter deleted");
        } catch (PaintingException | PainterException e) {
            ModelAndView modelAndView = new ModelAndView("galery/painter/delete");
            List<Painter> painters = painterService.getAll();
            modelAndView.addObject("painters", painters);
            return modelAndView.addObject("info", e.getMessage());
        }
    }
}
