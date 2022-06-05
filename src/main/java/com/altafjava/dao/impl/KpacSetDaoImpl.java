package com.altafjava.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.altafjava.constant.KpacSetConstant;
import com.altafjava.dao.KpacSetDao;
import com.altafjava.model.KnowledgePackageSet;

@Repository
public class KpacSetDaoImpl implements KpacSetDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<KnowledgePackageSet> getAllKpacSet() {
		return jdbcTemplate.query(KpacSetConstant.SELECT_KPAC_SET_QUERY, new RowMapper<KnowledgePackageSet>() {
			public KnowledgePackageSet mapRow(ResultSet rs, int row) throws SQLException {
				return buildKnowledgePackageSet(rs);
			}
		});
	}

	@Override
	public List<KnowledgePackageSet> getKpacSet(int id) {
		return jdbcTemplate.query(KpacSetConstant.SELECT_KPAC_SET_BY_ID_QUERY, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, id);
			}
		}, new RowMapper<KnowledgePackageSet>() {
			public KnowledgePackageSet mapRow(ResultSet rs, int row) throws SQLException {
				return buildKnowledgePackageSet(rs);
			}
		});
	}

	@Override
	public int[] saveKpacSet(KnowledgePackageSet knowledgePackageSet) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(KpacSetConstant.INSERT_KPAC_SET_QUERY, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, knowledgePackageSet.getTitle());
				return ps;
			}
		}, keyHolder);
		int generatedPrimaryKey = keyHolder.getKey().intValue();
		List<Integer> kpacIds = knowledgePackageSet.getKpacIds();
		if (kpacIds != null && !kpacIds.isEmpty()) {
			return jdbcTemplate.batchUpdate(KpacSetConstant.INSERT_KPS_KP_QUERY, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, knowledgePackageSet.getTitle());
					ps.setInt(1, generatedPrimaryKey);
					ps.setInt(2, kpacIds.get(i));
				}

				@Override
				public int getBatchSize() {
					return kpacIds.size();
				}
			});
		}
		return new int[0];
	}

	@Override
	public int deleteKpacSet(int id) {
		return jdbcTemplate.update(KpacSetConstant.DELETE_KPAC_SET_QUERY, id);
	}

	private KnowledgePackageSet buildKnowledgePackageSet(ResultSet resultSet) throws SQLException {
		KnowledgePackageSet knowledgePackageSet = new KnowledgePackageSet();
		knowledgePackageSet.setId(resultSet.getInt(KpacSetConstant.ID));
		knowledgePackageSet.setTitle(resultSet.getString(KpacSetConstant.TITLE));
		return knowledgePackageSet;
	}
}
