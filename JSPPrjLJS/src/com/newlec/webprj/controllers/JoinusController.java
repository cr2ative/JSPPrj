package com.newlec.webprj.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinusController {
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value = "login", method=RequestMethod.GET)
	public String login(){
		return "joinus/login";
	}
	
	/*@RequestMapping(value = "login", method=RequestMethod.POST)
	public String login(@Param("user-name") String userName, String password,
			Model model, HttpSession session) throws SQLException{
		Member m = memberDao.getMember(userName);
		
		String error = null;
		
		if(m==null) //회원이 존재하지 않을 때
			error = "사용자 아이디 또는 비밀번호가 올바르지 않습니다.";
		else if(!m.getPwd().equals(password)) //회원이 존재하지만 비밀번호가 다를때
			error = "사용자 아이디 또는 비밀번호가 올바르지 않습니다.";
		
		if(error !=null)
		{
			model.addAttribute("error",error); //에러가 발생했으니 해당 메세지를 담아가랏!
			return "joinus/login";
		}
		
		//인증 성공
		//인증 정보를 저장 어디에?
		session.setAttribute("mid",userName);
		return "redirect:customer/notice";
	}*/
}
