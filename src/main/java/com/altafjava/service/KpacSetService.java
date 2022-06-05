package com.altafjava.service;

import java.util.List;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.model.KnowledgePackageSet;

public interface KpacSetService {

	public List<KnowledgePackageSet> getAllKpacSet();

	public void saveKpacSet(KnowledgePackageSet knowledgePackageSet);

	public void deleteKpacSet(int id);

	public List<KnowledgePackage> getKpacsFromSet(int id);

}
