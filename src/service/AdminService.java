package service;

import dao.AdminDAO;

public class AdminService {
	
	private AdminDAO dao;
	
	public AdminService() {
		dao = new AdminDAO();
	}
	
	public String pass() {
		return dao.pass();
	}
	
}
