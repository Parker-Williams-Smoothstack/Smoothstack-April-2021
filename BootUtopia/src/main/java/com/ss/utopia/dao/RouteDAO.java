package com.ss.utopia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ss.utopia.de.Airport;
import com.ss.utopia.de.Route;

@Repository
public class RouteDAO extends AbstractDAO<Route> implements ResultSetExtractor<List<Route>> {

	@Autowired
	AirportDAO adao;

	@Override
	public Integer create(Route obj) {
		String query = "insert into route (origin_id, destination_id) VALUES (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setString(1, obj.getOrigin().getCode());
			ps.setString(2, obj.getDestination().getCode());
			return ps;
		}, keyHolder);
		Integer key = keyHolder.getKey().intValue();
		obj.setId(key);
		return key;
	}

	@Override
	public void update(Route obj) {
		jdbcTemplate.update("update route set origin_id = ?, destination_id = ? where id = ?",
				obj.getOrigin().getCode(), obj.getDestination().getCode(), obj.getId());

	}

	@Override
	public void delete(Route obj) {
		jdbcTemplate.update("delete from route where id = ?", obj.getId());

	}

	@Override
	public List<Route> getAll() {
		return jdbcTemplate.query("select * from route", this);
	}

	@Override
	public List<Route> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Route> list = new ArrayList<>();
		while (rs.next()) {
			Route obj = new Route();
			Airport orig = new Airport();
			Airport dest = new Airport();

			obj.setId(rs.getInt("id"));
			orig.setCode(rs.getString("origin_id"));
			dest.setCode(rs.getString("destination_id"));
			orig.setCityName(
					adao.getData("select * from airport where iata_id = ?", orig.getCode()).get(0).getCityName());
			dest.setCityName(
					adao.getData("select * from airport where iata_id = ?", dest.getCode()).get(0).getCityName());
			obj.setOrigin(orig);
			obj.setDestination(dest);

			list.add(obj);
		}
		return list;
	}

	@Override
	public List<Route> getData(String query, Object... params) {
		return jdbcTemplate.query(query, this, params);
	}

}
