package com.maistruks.portfolio.gallery.dao;

import java.util.List;

import com.maistruks.portfolio.gallery.model.Painter;
import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.Style;

public interface PaintingDao {
    
    void create(Painting painting, Integer painterId);
    
    void updateFullPaintingInfo(Painting painting, Integer painterId);
    
    void updatePaintingInfo(Painting painting);
    
    List<Painting> getAll();
    
    List<Painting> getByPainter(Painter painter);
    
    Painting getByName(String name);
    
    List<Painting> getByStyle(Style style);
    
    void delete(Integer id);
    
    Integer getRowsAmount();
    
    List<Painting> getPaintingsInRange(Integer offset, Integer limit);
    
    List<Painting> getSortedByNameAsc(Integer offset, Integer limit);
    
    List<Painting> getSortedByNameDesc(Integer offset, Integer limit);
    
    List<Painting> getSortedByYearAsc(Integer offset, Integer limit);
    
    List<Painting> getSortedByYearDesc(Integer offset, Integer limit);

}
