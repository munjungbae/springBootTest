package com.zeus.common.intercepter;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

//IntercepterConfig에서 링크 인식 후 가로 채고 나면 아래 함수들 실행
@Slf4j
@Component
public class LoginIntercepter implements HandlerInterceptor {
	private static final String USER_INFO = "userInfo";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("---------------------------------------------");
		log.info("preHandle");
		
		String requestURL = request.getRequestURI();
		// resultURL : /login
		log.info("requestURL : " + requestURL);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		//Bean : com.zeus.controller.LoginController@15b1b890
		log.info("preHandle Bean: " + method.getBean());
		
		//Method : public void com.zeus.controller.LoginController.login();
		log.info("preHandle Method: " + methodObj);
		HttpSession session = request.getSession();
		if (session.getAttribute(USER_INFO) != null) {
			log.info("session.getAttribute(USER_INFO) != null");
			session.removeAttribute(USER_INFO);
		}
		log.info("---------------------------------------------");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		log.info("---------------------------------------------");
		log.info("postHandle");
		
		String requestURL = request.getRequestURI();
		// requestURL : /login
		log.info("requestURL : " + requestURL);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		// Bean: com.zeus.controller.Logincontroller@13ed2e22
		log.info("postHandle Bean: " + method.getBean());
		
		// Method: public void com.zeus.controller.Logincontroller.login()
		log.info("postHandle Method: " + methodObj);
		
		//세션생성
		HttpSession session = request.getSession();
		
		//modelAndView.getModelMap() => model내부에 있는 키/벨류 값을 받아옴
		// LoginController에서 model에 user이름(키)으로 member(벨류)를("user", member) 저장했기 때문에 해당 modelAndView에서 받을 수 있음
		ModelMap modelMap = modelAndView.getModelMap();
		
		//위에서 받은 modelMap 내 user키값에 상응하는 벨류값 불러오기
		Object member = modelMap.get("user");
		if (member != null) {
			log.info("member != null");
			
			// private static final String USER_INFO = "userInfo";
			// "user" 키 값으로 찾은 벨류 member가 비어있지 않다면 String USER_INFO를 키값을 가지는 member 벨류 생성
			session.setAttribute(USER_INFO, member);
			response.sendRedirect("/");
		}
		log.info("---------------------------------------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("---------------------------------------------");
		log.info("afterCompletion");
		
		String requestURL = request.getRequestURI();
		// requestURL : /login
		log.info("requestURL : " + requestURL);
		
		HandlerMethod method = (HandlerMethod) handler;
		
		Method methodObj = method.getMethod();
		
		// Bean: com.zeus.controller.Logincontroller@13ed2e22
		log.info("afterCompletion Bean: " + method.getBean());
		
		// Method: public void com.zeus.controller.Logincontroller.login()
		log.info("afterCompletion Method: " + methodObj);
		log.info("---------------------------------------------");
	}

}
