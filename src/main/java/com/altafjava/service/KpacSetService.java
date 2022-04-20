package com.altafjava.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.altafjava.dao.KpacSetDao;
import com.altafjava.dao.KpsKpDao;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.model.KnowledgePackageSet;
import com.altafjava.model.KpsKp;

@Service
public class KpacSetService {

	@Autowired
	private KpacSetDao kpacSetDao;
	@Autowired
	private KpsKpDao kpsKpDao;
	@Autowired
	private KpacService kpacService;

	public List<KnowledgePackageSet> getAllKpacSet() {
		return kpacSetDao.findAllKpacSet();
	}

	public void saveKpacSet(KnowledgePackageSet knowledgePackageSet) {
		kpacSetDao.save(knowledgePackageSet);
	}

	public void deleteKpacSet(int id) {
		kpacSetDao.removeById(id);
	}

	public List<KnowledgePackage> getKpacsFromSet(int id) {
		List<KpsKp> kpsKpList = kpsKpDao.findByKpacSetId(id);
		if (kpsKpList.isEmpty()) {
			return Collections.emptyList();
		} else {
			List<Integer> kpacSetIds = kpsKpList.stream().map(KpsKp::getKpacId).collect(Collectors.toList());
			return kpacService.getKpacsByIds(kpacSetIds);
		}
	}
}
