package com.maistruks.portfolio.gallery.dao;

import java.util.List;

import com.maistruks.portfolio.model.gallery.Painter;
import com.maistruks.portfolio.model.gallery.Painting;
import com.maistruks.portfolio.model.gallery.Style;

public interface PaintingDao {
    
    void create(Painting painting, Integer painterId);
    
    void updateFullPaintingInfo(Painting painting, Integer painterId);
    
    void updatePaintingInfo(Painting painting);
    
    List<Painting> getAll();
    
    List<Painting> getByPainter(Painter painter);
    
    Painting getByName(String name);
    
    List<Painting> getByStyle(Style style);
    
    void delete(Integer id);

}
