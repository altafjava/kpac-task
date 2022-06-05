package com.altafjava.dao;

import java.util.List;
import com.altafjava.model.KnowledgePackageSet;

public interface KpacSetDao {

	public List<KnowledgePackageSet> getAllKpacSet();

	public List<KnowledgePackageSet> getKpacSet(int id);

	public int[] saveKpacSet(KnowledgePackageSet knowledgePackageSet);

	public int deleteKpacSet(int id);

}
