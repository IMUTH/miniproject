package egovframework.ojm.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.ojm.service.*;

@Controller
public class OjmController {

	@Resource(name = "OjmService")
	private OjmService OjmService;
	
	
	@RequestMapping(value = "/ojmLogin.do")
	public String ojmLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "ojm/ojmLogin";
	}
	
	@RequestMapping(value = "/ojmLoginAction.do")
	public String ojmLoginAction(HttpServletRequest request, LoginVO LoginVO , HttpServletResponse response) throws Exception{
		String user_id= request.getParameter("user_id");
		String password= request.getParameter("password");
		LoginVO.setUser_id(user_id);
		LoginVO.setPassword(password);
		OjmService.loginAction(LoginVO);
		String result = LoginVO.getUser_id();
		HttpSession session = request.getSession();
		if(result.isEmpty()) {
			return "redirect:/ojmLogin.do";
		}else {
			session.setAttribute("user_id", result);
			return "redirect:/ojmMain.do";
		}
		
	}
	
	
	@RequestMapping(value = "/ojmMain.do")
	public String ojmMain(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "ojm/ojmMain";
	}
	
	
	
	
	
}