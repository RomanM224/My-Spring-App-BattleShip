package com.maistruks.portfolio.gallery.mapper;

import org.springframework.stereotype.Component;

import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.dto.PaintingDto;

@Component
public class PaintingMapper {
    
    public Painting mapPaintingDtoToPainting(PaintingDto paintingDto) {
        Painting painting = new Painting();
        painting.setId(paintingDto.getId());
        painting.setName(paintingDto.getName());
        painting.setStyle(paintingDto.getStyle());
        painting.setYear(paintingDto.getYear());
        painting.setImage(paintingDto.getImage());
        return painting;
    }

}
