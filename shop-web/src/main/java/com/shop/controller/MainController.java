package com.shop.controller;

import com.shop.domain.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {


	@Autowired
	private UserService userService;
	public UserService getUserService(){
		return this.userService;
	}



    public void setUserService(UserService userService){
		this.userService=userService;
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
//        User user = new User();
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("index");
		return model;

	}
	@RequestMapping(value = { "/searchpage" }, method = RequestMethod.GET)
	public ModelAndView searchpage() {
//        User user = new User();
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("searchpage");
		return model;

	}
	@RequestMapping(value = { "/user_personal_page" }, method = RequestMethod.GET)
	public ModelAndView user_personal_page() {
//        User user = new User();
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("user_personal_page");
		return model;

	}
	@RequestMapping(value = { "/user_info" }, method = RequestMethod.GET)
	public ModelAndView user_info() {
//        User user = new User();
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("user_info");
		return model;

	}
	@RequestMapping(value = { "/user_bought" }, method = RequestMethod.GET)
	public ModelAndView user_bought() {
//        User user = new User();
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("user_bought");
		return model;

	}
	@RequestMapping(value = { "/user_favorites" }, method = RequestMethod.GET)
	public ModelAndView user_favorites() {
//        User user = new User();
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("user_favorites");
		return model;

	}
	@RequestMapping(value = { "/user_shoppingcart" }, method = RequestMethod.GET)
	public ModelAndView user_shoppingcart() {
//        User user = new User();user_info. Reason:
//        user.setUserName("donahue");
//        user.setRealName("ldz");
//        System.err.println(userService.createUser(user)+"sssssssssssssss");
		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security Remember Me");
		model.addObject("message", "This is default page!");*/
		model.setViewName("user_shoppingcart");
		return model;

	}

	/**
	 * both "normal login" and "login for update" shared this form.
	 * 
	 */
/*	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			
			//login form for update, if login error, get the targetUrl from session again.
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
				model.addObject("loginUpdate", true);
			}
			
		}
alert("现在显示的是"+i);
				$("#tab"+i).css("display","none");
				$(tab).css("display","block");
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}alert("现在显示的是"+i);
				$("#tab"+i).css("display","none");
				$(tab).css("display","block");
		model.setViewName("login");

		return model;
alert("现在显示的是"+i);
				$("#tab"+i).css("display","none");
				$(tab).css("display","block");
	}*/
    @RequestMapping(value = "/login" ,method=RequestMethod.GET)
    public String login(){
        if(isRememberMeAuthenticated())
            return "redirect:/welcome";
        return "login1";
    }
    @RequestMapping(value = "/register" ,method=RequestMethod.GET)
    public String register(){
        if(isRememberMeAuthenticated())
            return "redirect:/welcome";
        return "register";
    }


    @RequestMapping(value = "/seller/seller" ,method=RequestMethod.GET)
    public String seller(){
        /*if(isRememberMeAuthenticated())
            return "redirect:/welcome";*/
        return "seller";
    }
    @RequestMapping(value = "/seller/login" ,method=RequestMethod.GET)
    public String sellerLogin(){
        /*if(isRememberMeAuthenticated())
            return "redirect:/welcome";*/
        return "sellerLogin";
    }
	/**
	 *
	 */
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		// 检查是否空
		String userName="";
		String password="";
		if(request.getParameter("userName")!=null){
			userName=request.getParameter("userName");
		}else{
			model.addObject("message","请输入用户名！");
			return model;
		}
		if (request.getParameter("password")!=null){
			password=request.getParameter("password");
		}else{
			model.addObject("message","请输入密码！");
			return model;
		}

		//check if the user is exists
		User user=(userService.findUserByUserName(userName)!=null)?userService.findUserByUserName(userName)
				:userService.findUserByEmail(userName);

		if(user==null||user.getPassword()!=password){
			model.addObject("message","用户名或密码错误！");
			return model;
		}

		String status=user.getUserStatus();
		switch(Integer.parseInt(status)){
			case 0://预启用
				model.addObject("message","请激活链接后登录！");
				break;
			case 1:// 启用（激活）
				model.setViewName("");// 视图名称未知!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				break;
			case 2://首次登录
				model.setViewName("");
				break;
			case 3://资料未完善
				model.setViewName("");
				break;
			case 4://正常
				model.setViewName("");
				break;
			case 5://禁用
				model.addObject("message","该账户已被禁用！");
				break;
			case 6:// 注销
				model.addObject("message","该账户已被注销！");
				break;

		}

		return model;

	}*/


        /**
         * If the login in from remember me cookie, refer
         * org.springframework.security.authentication.AuthenticationTrustResolverImpl
         */
	private boolean isRememberMeAuthenticated() {
        System.err.println("this is rememberMeAuthenticated");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
            System.err.println("authentication == null");
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	/**
	 * save targetURL in session
	 */
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}

	/**
	 * get targetURL from session
	 */
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?"":session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
	
}