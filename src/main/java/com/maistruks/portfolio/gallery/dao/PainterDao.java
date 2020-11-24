package com.maistruks.portfolio.gallery.dao;

import java.util.List;

import com.maistruks.portfolio.gallery.model.Painter;

public interface PainterDao {

    void create(Painter painter);

    void update(Painter painter);

    List<Painter> getAll();

    Painter getById(Integer id);
    
    Painter getByPaintingId(Integer paintingId);

    void delete(Integer id);

}
