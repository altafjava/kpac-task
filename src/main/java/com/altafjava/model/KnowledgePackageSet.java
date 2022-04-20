package com.altafjava.model;

import java.util.List;
import lombok.Data;

@Data
public class KnowledgePackageSet {
	private Integer id;
	private String title;
	private List<Integer> kpacIds;
}
