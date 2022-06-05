package com.altafjava.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.altafjava.constant.AppConstant;
import com.altafjava.dao.KpacDao;
import com.altafjava.model.KnowledgePackage;
import com.altafjava.service.KpacService;

@Service
public class KpacServiceImpl implements KpacService {

	@Autowired
	private KpacDao kpacDao;

	@Override
	public List<KnowledgePackage> getAllKpacs() {
		return kpacDao.getAllKpacs();
	}

	@Override
	public List<KnowledgePackage> getKpacs(List<Integer> ids) {
		return kpacDao.getKpacs(ids);
	}

	@Override
	public void deleteKpac(int id) {
		kpacDao.deleteKpac(id);
	}

	@Override
	public void saveKpac(KnowledgePackage knowledgePackage) {
		if (null != knowledgePackage) {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT);
			String creationDate = simpleDateFormat.format(date);
			knowledgePackage.setCreationDate(creationDate);
			kpacDao.saveKpac(knowledgePackage);
		}
	}
}
