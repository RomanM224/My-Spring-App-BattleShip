package com.maistruks.portfolio.gallery.dao.impl.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.maistruks.portfolio.gallery.model.Painting;
import com.maistruks.portfolio.gallery.model.Style;

@Component
public class PaintingRowMapper implements RowMapper<Painting> {

    @Override
    public Painting mapRow(ResultSet resultSet, int row) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer year = resultSet.getInt("year");
        String name = resultSet.getString("name");
        Style style = Style.valueOf(resultSet.getString("style"));
        byte[] file = resultSet.getBytes("image");
        Painting painting = new Painting(id, year, name, style);
        painting.setImage(file);
        return painting;
    }

}
