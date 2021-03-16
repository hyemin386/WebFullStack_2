package com.iu.s1.member;

import javax.servlet.http.HttpServletRequest;

import com.iu.s1.util.ActionForward;

public class MemberService {

	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
 
	public ActionForward memberLogin (HttpServletRequest request) throws Exception {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();
		actionForward.setPath("../WEB-INF/member/memberLogin.jsp");	
		if(method.toUpperCase().equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setPw(request.getParameter("pw"));
			int result = memberDAO.memberJoin(memberDTO);
			actionForward.setPath("../index.jsp");
		}
		return actionForward;
	}
	
	public ActionForward memberJoin(HttpServletRequest request) throws Exception {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod();		
		actionForward.setPath("../WEB-INF/member/memberJoin.jsp");	
		if(method.toUpperCase().equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setPw(request.getParameter("pw"));
			memberDTO.setName(request.getParameter("name"));
			memberDTO.setEmail(request.getParameter("email"));
			memberDTO.setPhone(request.getParameter("phone"));
			int result = memberDAO.memberJoin(memberDTO);
			actionForward.setPath("../index.jsp");
		}
		
		return actionForward;
	}
}
