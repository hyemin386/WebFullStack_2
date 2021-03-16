package com.iu.s1.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.s1.util.ActionForward;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private MemberService memberService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

    //tomcat의 초기화 메서드
    @Override
    public void init() throws ServletException {
    	memberService = new MemberService();
    	MemberDAO memberDAO = new MemberDAO();
    	memberService.setMemberDAO(memberDAO);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Member Controller!");
		
		String path = request.getServletPath();
		String uri = request.getRequestURI();
		System.out.println(path);
		System.out.println(uri);
		String result = "";
		//subString으로 마지막 주소 
		//1. 자르려고하는 시작 인덱스 번호 찾기
		int index = uri.lastIndexOf("/");
		//2. 해당 인덱스부터 잘라오기
		result = uri.substring(index+1);
		System.out.println(result);
		String pathInfo = "";
		ActionForward actionforward = null;
	
		if(result.equals("memberLogin.do")) {
			System.out.println("로그인 처리");
			
			try {
				actionforward = memberService.memberLogin(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (result.equals("memberJoin.do")) {
			
			try {
				//actionforward안에 이동하고자하는 jsp경로가 존재함
				actionforward = memberService.memberJoin(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("그 외 다른 처리");
		}
		
		//forward
		RequestDispatcher view = request.getRequestDispatcher(actionforward.getPath());
		view.forward(request, response);
		
		// TODO Auto-generated method stub
		/*
		 * String id = request.getParameter("id"); String pw =
		 * request.getParameter("pw");
		 * 
		 * System.out.println("Id: "+id); System.out.println("Pw: "+pw);
		 * 
		 * MemberDAO memberDAO = new MemberDAO(); MemberDTO memberDTO = new MemberDTO();
		 * memberDTO.setId(id); memberDTO.setPw(pw); String result ="";
		 * 
		 * try { memberDTO = memberDAO.login(memberDTO);
		 * 
		 * if(memberDTO != null) { result = "로그인 성공"; } else { result = "로그인 실패"; }
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } //attribute request.setAttribute("r", result);
		 * 
		 * //attribute에 로그인한 MemberDTO request.setAttribute("id", memberDTO.getId());
		 * request.setAttribute("pw", memberDTO.getPw()); request.setAttribute("name",
		 * memberDTO.getName()); request.setAttribute("email", memberDTO.getEmail());
		 * request.setAttribute("phone", memberDTO.getPhone());
		 * 
		 * //forward RequestDispatcher view =
		 * request.getRequestDispatcher("./memberResult.jsp"); view.forward(request,
		 * response); }
		 */
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
