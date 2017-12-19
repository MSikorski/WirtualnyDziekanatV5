package org.mateuszsikorski.wirtualnydziekanat.aspect;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.Log;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {
	
	@Autowired
	LogDAO logDAO;
	
	@Transactional
	public void saveLog(Log log) {
		logDAO.saveLog(log);
	}

	@Transactional
	public List<Log> getLogs() {
		return logDAO.getLogs();
	}
}
