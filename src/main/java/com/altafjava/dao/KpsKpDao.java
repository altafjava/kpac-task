package com.altafjava.dao;

import java.util.List;
import com.altafjava.model.KpsKp;

public interface KpsKpDao {

	public List<KpsKp> findByKpacSetId(int id);

}
