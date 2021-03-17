package com.iu.s1.bankBook;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.s1.util.ActionForward;

/**
 * Servlet implementation class BankBookController
 */
@WebServlet("/BankBookController")
public class BankBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BankBookService bankBookService; //서비스 객체 선언

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BankBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		//Controller 객체 생성 후 자동 호출되는 초기화 메서드
		bankBookService = new BankBookService();
		BankBookDAO bankBookDAO = new BankBookDAO();
		bankBookService.setBankBookDAO(bankBookDAO);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 Encoding처리 모든 Controller에 다 작성 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// /WebFullStack_2/bankBook/bankBookList.do 의 경로에서 bankBookList.do만 자르기
		String uri = request.getRequestURI();
		int index = uri.lastIndexOf("/");
		uri = uri.substring(index+1);
		
		ActionForward actionForward = null; //actionForward안에는 path정보와 true, false 정보가 있음
		
		try {
			if(uri.equals("bankBookList.do")) {
			 actionForward = bankBookService.getList(request);
			} else if (uri.equals("bankBookSelect.do")) {
				actionForward = bankBookService.getSelect(request);
			} else if (uri.equals("bankBookWrite.do")) {
				actionForward = bankBookService.setWrite(request);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
			e.printStackTrace();
		}
		
		//true라면 forward false라면 redirect
		if(actionForward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionForward.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(actionForward.getPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
