package com.iu.s1.test;

import java.util.List;

import com.iu.s1.bankBook.BankBookDAO;
import com.iu.s1.bankBook.BankBookDTO;

public class BankBookDAOTest {
	
	public static void main(String[] args) {
		BankBookDAO bankBookDAO = new BankBookDAO();
		BankBookDTO bankBookDTO = new BankBookDTO();
		try  {
			int result = bankBookDAO.setWrite(bankBookDTO);
			System.out.println(result != 0);
			/*
			 * BankBookDTO bankBookDTO = bankBookDAO.getSelect(1);
			 * System.out.println(bankBookDTO != null );
			 */
			/*
			 * List<BankBookDTO> ar = bankBookDAO.getList();
			 * System.out.println(ar.size()==4);
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
