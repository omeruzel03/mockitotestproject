package com.ceng557.assignment.modules.dao;

import com.ceng557.assignment.rest.util.DaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LectureDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private void setDataSource(final DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<String> getTeacherLecturesByTeacherNumber(String teacherNumber) {
        String sql = DaoUtil.getQuery("getTeacherLecturesByTeacherNumber");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("teacher_number", teacherNumber);
        return namedParameterJdbcTemplate.queryForList(sql, mapSqlParameterSource, String.class);
    }
}
