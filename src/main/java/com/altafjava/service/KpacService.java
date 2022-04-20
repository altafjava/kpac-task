package com.altafjava.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.altafjava.dao.KpacDao;
import com.altafjava.model.KnowledgePackage;

@Service
public class KpacService {

	@Autowired
	private KpacDao kpacDao;

	public List<KnowledgePackage> getAllKpacs() {
		return kpacDao.findAllKpacs();
	}

	public List<KnowledgePackage> getKpacsByIds(List<Integer> ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		ids.forEach(id -> sb.append(id).append(","));
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return kpacDao.findKpacsById(sb.toString());
	}

	public void deleteKpac(int id) {
		kpacDao.removeKpacById(id);
	}

	public void save(KnowledgePackage knowledgePackage) {
		if (null != knowledgePackage) {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String creationDate = simpleDateFormat.format(date);
			knowledgePackage.setCreationDate(creationDate);
			kpacDao.save(knowledgePackage);
		}
	}
}
