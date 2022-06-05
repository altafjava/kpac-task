package com.altafjava.constant;

public class KpacConstant {
	
	public static final String ID = "id";
	public static final String IDS = "ids";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String CREATION_DATE = "creationDate";
	public static final String SELECT_KPAC_QUERY = "SELECT * FROM KnowledgePackage";
	public static final String SELECT_KPACS_QUERY = "SELECT * from KnowledgePackage where id in (:ids)";
	public static final String INSERT_KPAC_QUERY = "INSERT into KnowledgePackage(title, description, creationDate) values(?, ?, ?)";
	public static final String DELETE_KPAC_QUERY = "DELETE from KnowledgePackage where id = ?";

}
