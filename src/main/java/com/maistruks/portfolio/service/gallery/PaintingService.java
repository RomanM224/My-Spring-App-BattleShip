package com.maistruks.portfolio.service.gallery;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruks.portfolio.exception.PainterException;
import com.maistruks.portfolio.exception.PaintingException;
import com.maistruks.portfolio.gallery.dao.PaintingDao;
import com.maistruks.portfolio.gallery.mapper.PaintingMapper;
import com.maistruks.portfolio.model.gallery.Painter;
import com.maistruks.portfolio.model.gallery.Painting;
import com.maistruks.portfolio.model.gallery.Style;
import com.maistruks.portfolio.model.gallery.dto.PaintingDto;

@Service
public class PaintingService {

    @Autowired
    private PaintingDao paintingDao;
    
    @Autowired
    private PainterService painterService;
    
    @Autowired
    private PaintingMapper paintingMapper;

    public void create(PaintingDto paintingDto, Integer painterId) throws PaintingException {
        if(painterId == null) {
            throw new PaintingException("Painter not exist");
        }
        if(paintingDto.getName() == null || paintingDto.getName().isEmpty()) {
            throw new PaintingException("Painting Name is invalid");
        }
        if(paintingDto.getYear() == null) {
            throw new PaintingException("Year is invalid");
        }
        Painting painting = paintingMapper.mapPaintingDtoToPainting(paintingDto);
        paintingDao.create(painting, Integer.valueOf(painterId));
    }

    public void updateFullPaintingInfo(PaintingDto paintingDto, Integer painterId) throws PaintingException {
        if(painterId == null) {
            throw new PaintingException("Painter not exist");
        }
        if(paintingDto.getId() == null) {
            throw new PaintingException("Painting not exist");
        }
        if(paintingDto.getName() == null || paintingDto.getName().isEmpty()) {
            throw new PaintingException("Painting Name is invalid");
        }
        if(paintingDto.getYear() == null) {
            throw new PaintingException("Year is invalid");
        }
        Painting painting = paintingMapper.mapPaintingDtoToPainting(paintingDto);
        paintingDao.updateFullPaintingInfo(painting, Integer.valueOf(painterId));
    }
    
    public void updatePaintingInfo(PaintingDto paintingDto) throws PaintingException {
        if(paintingDto.getId() == null) {
            throw new PaintingException("Painting not exist");
        }
        if(paintingDto.getName() == null || paintingDto.getName().isEmpty()) {
            throw new PaintingException("Painting Name is invalid");
        }
        if(paintingDto.getYear() == null) {
            throw new PaintingException("Year is invalid");
        }
        Painting painting = paintingMapper.mapPaintingDtoToPainting(paintingDto);
        paintingDao.updatePaintingInfo(painting);
    }

    public List<Painting> getAll()  {
        List<Painting> paintings = paintingDao.getAll();
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }

    public Painting getByName(String name) throws PaintingException {
        if(name == null || name.isEmpty()) {
            throw new PaintingException("Painting not exist");
        }
        Painting painting = paintingDao.getByName(name);
        byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
        try {
            painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return painting;
    }
    
    public List<Painting> getByPainterId(Integer painterId) throws PaintingException, PainterException{
        if(painterId == null) {
            throw new PaintingException("Painter not exist");
        }
        Painter painter = painterService.getById(painterId);
        List<Painting> paintings = paintingDao.getByPainter(painter);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }
    
    public List<Painting> getByStyle(Style style){
        List<Painting> paintings = paintingDao.getByStyle(style);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }
    

    public void delete(Integer id) throws PaintingException {
        if (id == null) {
            throw new PaintingException("Painting not exist");
        }
        paintingDao.delete(Integer.valueOf(id));
    }

    public List<Painting> getAllSortedByNameAsc() {
        List<Painting> paintings = getAll();
        Collections.sort(paintings, Comparator.comparing(Painting::getName));
        return paintings;
    }
    
    public List<Painting> getAllSortedByNameDsc() {
        List<Painting> paintings = getAll();
        paintings.sort((Painting one, Painting two) ->two.getName().compareTo(one.getName()));
        return paintings;
    }

    public List<Painting> getAllSortedByYearAsc() {
        List<Painting> paintings = getAll();
        Collections.sort(paintings, Comparator.comparing(Painting::getYear));
        return paintings;
    }
    
    public List<Painting> getAllSortedByYearDsc() {
        List<Painting> paintings = getAll();
        paintings.sort((Painting one, Painting two) ->two.getYear().compareTo(one.getYear()));
        return paintings;
    }
    
    public Integer getRowsAmount() {
        return paintingDao.getRowsAmount();
    }
    
    public List<Painting> getPaintingsInRange(Integer offset, Integer limit) {
        List<Painting> paintings = paintingDao.getPaintingsInRange(offset, limit);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }
    
    
    public List<Painting> getSortedByNameAsc(Integer offset, Integer limit) {
        List<Painting> paintings = paintingDao.getSortedByNameAsc(offset, limit);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;    
        }

    
    public List<Painting> getSortedByNameDesc(Integer offset, Integer limit) {
        List<Painting> paintings = paintingDao.getSortedByNameDesc(offset, limit);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings; 
        }

    
    public List<Painting> getSortedByYearAsc(Integer offset, Integer limit) {
        List<Painting> paintings = paintingDao.getSortedByYearAsc(offset, limit);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings; 
        }

    
    public List<Painting> getSortedByYearDesc(Integer offset, Integer limit) {
        List<Painting> paintings = paintingDao.getSortedByYearDesc(offset, limit);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings; 
        }

}
