package com.altafjava.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.altafjava.constant.KpacConstant;
import com.altafjava.dao.KpacDao;
import com.altafjava.model.KnowledgePackage;

@Repository
public class KpacDaoImpl implements KpacDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<KnowledgePackage> getAllKpacs() {
		List<KnowledgePackage> knowledgePackages = jdbcTemplate.query(KpacConstant.SELECT_KPAC_QUERY, new RowMapper<KnowledgePackage>() {
			@Override
			public KnowledgePackage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return buildKnowledgePackage(resultSet);
			}
		});
		return knowledgePackages;
	}

	@Override
	public List<KnowledgePackage> getKpacs(List<Integer> ids) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(KpacConstant.IDS, ids);
		return namedParameterJdbcTemplate.query(KpacConstant.SELECT_KPACS_QUERY, parameters, new RowMapper<KnowledgePackage>() {
			public KnowledgePackage mapRow(ResultSet resultSet, int row) throws SQLException {
				return buildKnowledgePackage(resultSet);
			}
		});
	}

	@Override
	public int deleteKpac(int id) {
		return jdbcTemplate.update(KpacConstant.DELETE_KPAC_QUERY, id);
	}

	@Override
	public int saveKpac(KnowledgePackage knowledgePackage) {
		return jdbcTemplate.update(KpacConstant.INSERT_KPAC_QUERY, knowledgePackage.getTitle(), knowledgePackage.getDescription(),
				knowledgePackage.getCreationDate());
	}

	private KnowledgePackage buildKnowledgePackage(ResultSet resultSet) throws SQLException {
		KnowledgePackage knowledgePackage = new KnowledgePackage();
		knowledgePackage.setId(resultSet.getInt(KpacConstant.ID));
		knowledgePackage.setTitle(resultSet.getString(KpacConstant.TITLE));
		knowledgePackage.setDescription(resultSet.getString(KpacConstant.DESCRIPTION));
		knowledgePackage.setCreationDate(resultSet.getString(KpacConstant.CREATION_DATE));
		return knowledgePackage;
	}

}
