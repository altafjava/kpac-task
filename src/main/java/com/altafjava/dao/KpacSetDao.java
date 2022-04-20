package com.altafjava.dao;

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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.altafjava.model.KnowledgePackageSet;

@Repository
public class KpacSetDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<KnowledgePackageSet> findAllKpacSet() {
		return jdbcTemplate.query("select * from KnowledgePackageSet", new RowMapper<KnowledgePackageSet>() {
			public KnowledgePackageSet mapRow(ResultSet rs, int row) throws SQLException {
				return bindKnowledgePackageSet(rs);
			}
		});
	}

	public List<KnowledgePackageSet> findKpacSetById(int id) {
		return jdbcTemplate.query("select * from KnowledgePackageSet where id=" + id + "",
				new RowMapper<KnowledgePackageSet>() {
					public KnowledgePackageSet mapRow(ResultSet rs, int row) throws SQLException {
						return bindKnowledgePackageSet(rs);
					}
				});

	}

	private KnowledgePackageSet bindKnowledgePackageSet(ResultSet resultSet) throws SQLException {
		KnowledgePackageSet knowledgePackageSet = new KnowledgePackageSet();
		knowledgePackageSet.setId(resultSet.getInt("id"));
		knowledgePackageSet.setTitle(resultSet.getString("title"));
		return knowledgePackageSet;
	}

	public int[] save(KnowledgePackageSet knowledgePackageSet) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			String sql = "insert into KnowledgePackageSet(title) values(?)";

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, knowledgePackageSet.getTitle());
				return ps;
			}
		}, keyHolder);
		int generatedPrimaryKey = keyHolder.getKey().intValue();
		List<Integer> kpacIds = knowledgePackageSet.getKpacIds();
		if (kpacIds != null && !kpacIds.isEmpty()) {
			String sql = "insert into KpsKp(kpacSetId, kpacId) values(?,?)";
			return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
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

	public int removeById(int id) {
		String sql = "delete from KnowledgePackageSet where id = " + id + "";
		return jdbcTemplate.update(sql);
	}

}
