package com.maistruks.portfolio.gallery.mapper;

import org.springframework.stereotype.Component;

import com.maistruks.portfolio.model.gallery.Painter;
import com.maistruks.portfolio.model.gallery.dto.PainterDto;

@Component
public class PainterMapper {
    
    public Painter mapPainterDtoToPainter(PainterDto painterDto) {
        Painter painter = new Painter();
        painter.setId(painterDto.getId());
        painter.setFirstName(painterDto.getFirstName());
        painter.setLastName(painterDto.getLastName());
        painter.setInfo(painterDto.getPainterInfo());
        return painter;
    }

}
