package com.touchinghand.service.client;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.touchinghand.entity.client.ClientEntity;
import com.touchinghand.entity.session.PsySessionEntity;


public class PsyClientServiceImplTest {

	
	List<ClientEntity> activeClients = new ArrayList<>();
	
	LocalDate today = LocalDate.now();
	
	@Before
	public void setUp() throws Exception {
		
		LocalDate twoDaysFromNow = LocalDate.now().plus(2, ChronoUnit.DAYS);
		LocalDate threeDaysFromNow = LocalDate.now().plus(3, ChronoUnit.DAYS);
		LocalDate fiveDaysOld = LocalDate.now().minus(5, ChronoUnit.DAYS);
		
		//Active client 1
		ClientEntity c1 = new ClientEntity();
		c1.setFirstName("Imran");
		c1.setLastName("Kazi");
		c1.setClientId(1);
		c1.setFollowupDate(twoDaysFromNow);
		c1.setStatus("Y");

		//Active client 2
		ClientEntity c2 = new ClientEntity();
		c2.setFirstName("Sahana");
		c2.setLastName("Ray");
		c2.setClientId(2);
		c2.setFollowupDate(threeDaysFromNow);
		c2.setStatus("Y");
		
		//Inactive client 3 with sessions
		ClientEntity c3 = new ClientEntity();
		c3.setFirstName("Pinaki");
		c3.setLastName("Ray");
		c3.setClientId(3);
		//LocalDate threeDaysFromNow = LocalDate.now().plus(3, ChronoUnit.DAYS);
		c3.setFollowupDate(today);
		c3.setStatus("Y");

		//Active client 4 with sessions - follow up date five days ago
		ClientEntity c4 = new ClientEntity();
		c4.setFirstName("Notun");
		c4.setLastName("Pal");
		c4.setClientId(4);
		c4.setFollowupDate(fiveDaysOld);
		c4.setStatus("Y");
		
		PsySessionEntity session1 = new PsySessionEntity();
		session1.setSessionDate(LocalDate.now());
		
		PsySessionEntity session2 = new PsySessionEntity();
		session2.setSessionDate(fiveDaysOld);
		
		PsySessionEntity session3 = new PsySessionEntity();
		session3.setSessionDate(twoDaysFromNow);
		
		List<PsySessionEntity> sessions = new ArrayList<>();
		sessions.add(session1);
		sessions.add(session2);
		
		List<PsySessionEntity> sessions2 = new ArrayList<>();
		sessions2.add(session1);
		sessions2.add(session3);
		
		c2.setSessionEntities(sessions);
		//adding sessions to client 3 - left status
		c3.setSessionEntities(sessions);
		//adding sessions to client 4 - session dates today and two days later
		c4.setSessionEntities(sessions2);
		
		activeClients.add(c1);
		activeClients.add(c2);
		activeClients.add(c3);
		activeClients.add(c4);
		
	}

	@Test
	public void testFindActiveClientsWithNoSession() {

		List<ClientEntity> activeClientsWithNoSession = activeClients
				.stream()
				.filter(activeClientEntity -> CollectionUtils.isEmpty(activeClientEntity.getSessionEntities()))
				.collect(Collectors.toList());
		
		activeClientsWithNoSession.forEach(System.out::println);
		assertEquals(1, activeClientsWithNoSession.size());
	
	}

	@Test
	public void testFindActiveClientsCrossedFollowupDate() {
		List<ClientEntity> clientEntities = new ArrayList<>();
		
		List<ClientEntity> clientsWithPastOrEqualFollowupDate = activeClients
		.stream()
		.filter(activeClientEntity -> 
			!CollectionUtils.isEmpty(activeClientEntity.getSessionEntities())).filter(
					ac -> 
					 (today.isEqual(ac.getFollowupDate()) || 
						today.isAfter(ac.getFollowupDate()))).collect(Collectors.toList());
		
		for(ClientEntity c : clientsWithPastOrEqualFollowupDate) {
			List<PsySessionEntity> sessions = c.getSessionEntities();
			boolean foundOnDateSession = false;
			for(PsySessionEntity session : sessions) {
				if(session.getSessionDate().isEqual(c.getFollowupDate())) {
					foundOnDateSession = true;
					break;
				}
			}
			if(!foundOnDateSession) {
				clientEntities.add(c);
			}
		}
		
		List<ClientEntity> filter = clientsWithPastOrEqualFollowupDate.stream()
		.filter(filteredClient -> filteredClient.getSessionEntities().stream()
			.noneMatch(session -> 
						session.getSessionDate().isEqual(filteredClient.getFollowupDate()))
			).collect(Collectors.toList());
		
		//clientEntities.forEach(System.out::println);
		filter.forEach(System.out::println);
		assertEquals(1, filter.size());
	}
	
	
	public void testActiveClientEntities () {
		
	}

}

