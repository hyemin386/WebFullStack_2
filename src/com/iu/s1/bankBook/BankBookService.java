package com.iu.s1.bankBook;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.iu.s1.util.ActionForward;

public class BankBookService {

	private BankBookDAO bankBookDAO; //객체 미리 선언
		
	public void setBankBookDAO(BankBookDAO bankBookDAO) {
		this.bankBookDAO = bankBookDAO;
	}

	//메서드명 getList DAO의 getList를 호출 후 리턴
	public ActionForward getList(HttpServletRequest request) throws Exception {
		ActionForward actionForward = new ActionForward();
		List<BankBookDTO> ar = bankBookDAO.getList();
		
		request.setAttribute("list", ar);
		actionForward.setPath("../WEB-INF/bankBook/bankBookList.jsp");
		actionForward.setCheck(true);
		
		return actionForward;
	}
	
	
	public ActionForward getSelect (HttpServletRequest request) throws Exception {
		ActionForward actionForward = new ActionForward();
		
		long bookNumber = Long.parseLong(request.getParameter("bookNumber"));
		BankBookDTO bankBookDTO = bankBookDAO.getSelect(bookNumber);
		
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/bankBook/bankBookSelect.jsp");
		request.setAttribute("dto", bankBookDTO);
		
		return actionForward;
	}
	
	
	public ActionForward setWrite (HttpServletRequest request) throws Exception {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		
		//Get
		actionForward.setPath("../WEB-INF/bankBook/bankBookWrite.jsp");
		actionForward.setCheck(true);
		
		//Post
		if(request.getMethod().toUpperCase().equals("POST")) {
			BankBookDTO bankBookDTO = new BankBookDTO();
			bankBookDTO.setBookName(request.getParameter("bookName"));
			bankBookDTO.setBookRate(Double.parseDouble(request.getParameter("bookRate")));
			bankBookDTO.setBookSale(request.getParameter("bookSale"));
			//DAO 작업
			int result = bankBookDAO.setWrite(bankBookDTO);
			actionForward.setPath("./bankBookList.do");
			actionForward.setCheck(false);
		}
		return actionForward;
	}
	
}
