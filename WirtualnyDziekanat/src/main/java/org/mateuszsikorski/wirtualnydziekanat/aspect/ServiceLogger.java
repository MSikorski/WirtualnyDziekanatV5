package org.mateuszsikorski.wirtualnydziekanat.aspect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mateuszsikorski.wirtualnydziekanat.entity.Log;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogger {
	
	@Autowired
	LogService logService;
	
	final String type="Service";

	@Pointcut("execution (* org.mateuszsikorski.wirtualnydziekanat.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* org.mateuszsikorski.wirtualnydziekanat.service.interfaces.LoginService.*(..))")
	public void excludedLoginService() {}
	
	@Pointcut("excludedLoginService()")
	public void excludedServices() {}
	
	
	@After("forServicePackage() && !excludedServices()")
	private void servicePackageLogger(JoinPoint theJoinPoint) {
		
		UserDetail executioner = new UserDetail();
		
		String method = theJoinPoint.getSignature().toShortString();
		String methodArg = "";
		Object[] args = theJoinPoint.getArgs();
		
		for(Object tempArg : args) {
			if(tempArg instanceof UserDetail) {
				executioner = (UserDetail) tempArg;
			} else if(tempArg instanceof Integer) {
				methodArg += tempArg;
			}else continue;
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		
		String logMessage = date + " Method: " + method + " called with method argument: [" + methodArg
				+ "] by User with userDetail: " + executioner.getShortInfo();
		
		Log log = new Log(executioner, type, logMessage);
		
		logService.saveLog(log);
		
	}
		
}
	

