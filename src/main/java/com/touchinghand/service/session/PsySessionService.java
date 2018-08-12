package com.touchinghand.service.session;

import java.util.List;

import com.touchinghand.dto.PsySession;
import com.touchinghand.dto.TreatmentData;

public interface PsySessionService {

	public List<PsySession> getSessionOfClient(int clientId);
	
	public PsySession getSession(int sessionId);
	
	public List<PsySession> getSessionBetween(String start, String end);
	
	public List<PsySession> getSessionOfClientBetween(int clientId, String start, String end);
	
	public List<PsySession> getAllSessionFeedbackOfClient(int clientId);
	
	public List<PsySession> getAllSessionImpressionsOfClient(int clientId);
	
	public Integer createSession(PsySession s);
	
	public boolean updateSession(PsySession s);
	
	public boolean createTreatmentData(TreatmentData s);
	
	public boolean updateTreatmentData(TreatmentData s);
	
	public TreatmentData getTreatmentData(int clientId);
	
}
