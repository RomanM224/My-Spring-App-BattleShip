package com.maistruks.portfolio.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.exception.PaintingException;
import com.maistruks.portfolio.gallery.dao.PainterDao;
import com.maistruks.portfolio.gallery.mapper.PainterMapper;
import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.dto.PainterDto;

@Service
public class PainterService {

    @Autowired
    private PainterDao painterDao;

    @Autowired
    private PaintingService paintingService;
    

    public void create(Painter painter) throws PainterException {
        if (painter.getFirstName() == null || painter.getFirstName().isEmpty()) {
            throw new PainterException("First Name is invalid");
        }
        if (painter.getLastName() == null || painter.getLastName().isEmpty()) {
            throw new PainterException("Last Name is invalid");
        }
        if (painter.getInfo() == null || painter.getInfo().isEmpty()) {
            throw new PainterException("Info is invalid");
        }
        painterDao.create(painter);
    }

    public void update(Painter painter) throws PainterException {
        if(painter.getId() == null) {
            throw new PainterException("Painter not exist");
        }
        if (painter.getFirstName() == null || painter.getFirstName().isEmpty()) {
            throw new PainterException("First Name is invalid");
        }
        if (painter.getLastName() == null || painter.getLastName().isEmpty()) {
            throw new PainterException("Last Name is invalid");
        }
        if (painter.getInfo() == null || painter.getInfo().isEmpty()) {
            throw new PainterException("Info is invalid");
        }
        painterDao.update(painter);
    }

    public List<Painter> getAll() {
        return painterDao.getAll();
    }

    public Painter getById(Integer id) throws PainterException {
        if(id == null) {
            throw new PainterException("Painter not exist");
        }
        return painterDao.getById(id);
    }

    public Painter getByPaintingId(Integer paintingId) {
        return painterDao.getByPaintingId(paintingId);
    }

    public void delete(Integer id) throws PaintingException, PainterException {
        if(id == null) {
            throw new PainterException("Painter not exist");
        }
        List<Painting> paintings = paintingService.getByPainterId(id);
        painterDao.delete(id);
        for (Painting painting : paintings) {
            paintingService.delete(painting.getId());

        }
    }

}
