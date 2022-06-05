package com.altafjava.service;

import java.util.List;
import com.altafjava.model.KnowledgePackage;

public interface KpacService {

	public List<KnowledgePackage> getAllKpacs();

	public List<KnowledgePackage> getKpacs(List<Integer> ids);

	public void deleteKpac(int id);

	public void saveKpac(KnowledgePackage knowledgePackage);

}
