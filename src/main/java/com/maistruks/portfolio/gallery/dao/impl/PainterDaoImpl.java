package com.maistruks.portfolio.gallery.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.maistruks.portfolio.gallery.dao.PainterDao;
import com.maistruks.portfolio.gallery.dao.impl.rowMapper.PainterRowMapper;
import com.maistruks.portfolio.gallery.model.Painter;

@Repository
public class PainterDaoImpl implements PainterDao {

    private static final String ADD_PAINTER = "INSERT INTO painters (id, first_name, last_name, info) VALUES (DEFAULT, ?, ?, ?)";
    private static final String UPDATE_PAINTER = "UPDATE painters SET first_name = ?, last_name = ?, info = ? WHERE id = ?";
    private static final String GET_ALL_PAINTERS = "SELECT * FROM painters";
    private static final String GET_PAINTER_BY_ID = "SELECT * FROM painters WHERE id = ?";
    private static final String GET_PAINTER_BY_PAINTING_ID = "SELECT painters.id, painters.first_name, painters.last_name, painters.info "
            + "FROM painters_paintings INNER JOIN painters ON painters_paintings.painter_id = painters.id WHERE painters_paintings.painting_id = ?";
    private static final String DELETE_PAINTING_RELAETED_TO_PAINTER = "DELETE FROM painters_paintings WHERE painter_id = ?";
    private static final String DELETE_PAINTER = "DELETE FROM painters WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PainterRowMapper painterRowMapper;

    @Override
    public void create(Painter painter) {
        jdbcTemplate.update(ADD_PAINTER, painter.getFirstName(), painter.getLastName(), painter.getInfo());
    }

    @Override
    public void update(Painter painter) {
        jdbcTemplate.update(UPDATE_PAINTER, painter.getFirstName(), painter.getLastName(), painter.getInfo(),
                painter.getId());
    }

    @Override
    public List<Painter> getAll() {
        return jdbcTemplate.query(GET_ALL_PAINTERS, painterRowMapper);
    }

    @Override
    public Painter getById(Integer id) {
        List<Painter> painters = jdbcTemplate.query(GET_PAINTER_BY_ID, painterRowMapper, id);
        if (painters.isEmpty()) {
            return null;
        }
        return painters.get(0);
    }

    @Override
    public Painter getByPaintingId(Integer paintingId) {
        List<Painter> painters = jdbcTemplate.query(GET_PAINTER_BY_PAINTING_ID, painterRowMapper, paintingId);
        if (painters.isEmpty()) {
            return null;
        }
        return painters.get(0);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_PAINTING_RELAETED_TO_PAINTER, id);
        jdbcTemplate.update(DELETE_PAINTER, id);
    }

}
