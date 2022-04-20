package com.altafjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.model.KpsKp;

@Repository
public class KpsKpDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<KnowledgePackage> findAllKpacs() {
		List<KnowledgePackage> knowledgePackages = jdbcTemplate.query("select * FROM KnowledgePackage",
				new RowMapper<KnowledgePackage>() {
					@Override
					public KnowledgePackage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						return bindKnowledgePackage(resultSet);
					}
				});
		return knowledgePackages;
	}

	public int saveKpac(KnowledgePackage knowledgePackage) {
		String sql = "insert into KnowledgePackage(title, description, date) values('" + knowledgePackage.getTitle()
				+ "','" + knowledgePackage.getDescription() + "','" + knowledgePackage.getCreationDate() + "')";
		return jdbcTemplate.update(sql);
	}

	public int deleteKpacById(int id) {
		String sql = "delete from knowledgepackage where id=" + id + "";
		return jdbcTemplate.update(sql);
	}

	public List<KnowledgePackage> findKpacsById(String ids) {
		return jdbcTemplate.query("select * from KnowledgePackage where id in " + ids + "",
				new RowMapper<KnowledgePackage>() {
					public KnowledgePackage mapRow(ResultSet resultSet, int row) throws SQLException {
						return bindKnowledgePackage(resultSet);
					}
				});
	}

	private KnowledgePackage bindKnowledgePackage(ResultSet resultSet) throws SQLException {
		KnowledgePackage knowledgePackage = new KnowledgePackage();
		knowledgePackage.setId(resultSet.getInt("id"));
		knowledgePackage.setTitle(resultSet.getString("title"));
		knowledgePackage.setDescription(resultSet.getString("description"));
		knowledgePackage.setCreationDate(resultSet.getString("creationDate"));
		return knowledgePackage;
	}

	public int removeKpacById(int id) {
		String sql = "delete from KnowledgePackage where id=" + id + "";
		return jdbcTemplate.update(sql);
	}

	public int save(KnowledgePackage knowledgePackage) {
		String sql = "insert into KnowledgePackage(title, description, creationDate) values('"
				+ knowledgePackage.getTitle() + "','" + knowledgePackage.getDescription() + "','"
				+ knowledgePackage.getCreationDate() + "')";
		return jdbcTemplate.update(sql);
	}

	public List<KpsKp> findByKpacSetId(int id) {
		return jdbcTemplate.query("select * from KpsKp where kpacSetId = " + id + "", new RowMapper<KpsKp>() {
			public KpsKp mapRow(ResultSet resultSet, int row) throws SQLException {
				KpsKp kpsKp = new KpsKp();
				kpsKp.setKpacId(resultSet.getInt("kpacSetId"));
				kpsKp.setKpacId(resultSet.getInt("kpacId"));
				return kpsKp;
			}
		});
	}
}
