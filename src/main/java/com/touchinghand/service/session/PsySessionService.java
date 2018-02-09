package com.touchinghand.service.session;

import java.time.LocalDate;
import java.util.List;

import com.touchinghand.dto.PsySession;

public interface PsySessionService {

	public List<PsySession> getSessionOfClient(int clientId);
	
	public List<PsySession> getSessionBetween(LocalDate start, LocalDate end);
	
	public boolean createSession(PsySession s);
	
	public boolean updateSession(PsySession s);
	
	public boolean createTreatmentData(PsySession s);
	
	public boolean updateTreatmentData(PsySession s);
	
}
