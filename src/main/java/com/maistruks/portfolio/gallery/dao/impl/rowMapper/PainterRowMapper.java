package com.maistruks.portfolio.gallery.dao.impl.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.maistruks.portfolio.gallery.model.Painter;

@Component
public class PainterRowMapper implements RowMapper<Painter> {

    @Override
    public Painter mapRow(ResultSet resultSet, int row) throws SQLException {
        Integer id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String info = resultSet.getString("info");
        return new Painter(id, firstName, lastName, info);
    }
}
