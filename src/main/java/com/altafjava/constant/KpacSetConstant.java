package com.altafjava.constant;

public class KpacSetConstant {

	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String SELECT_KPAC_SET_QUERY = "SELECT * FROM KnowledgePackageSet";
	public static final String SELECT_KPAC_SET_BY_ID_QUERY = "SELECT * FROM KnowledgePackageSet  where id = ?";
	public static final String INSERT_KPAC_SET_QUERY = "INSERT into KnowledgePackageSet(title) values(?)";
	public static final String INSERT_KPS_KP_QUERY = "INSERT into KpsKp(kpacSetId, kpacId) values(?,?)";
	public static final String DELETE_KPAC_SET_QUERY = "DELETE from KnowledgePackageSet where id = ?";

}
