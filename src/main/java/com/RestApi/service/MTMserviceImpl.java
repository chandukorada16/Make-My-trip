package com.RestApi.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.RestApi.binding.Passenger;
import com.RestApi.binding.Ticket;

@Service
public class MTMserviceImpl implements MTMServiceI {

	@Override
	public List<Ticket> getAllTickets() {
		String url = "http://localhost:5050/ticket/getAllTickets";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Ticket[]> forEntity = rt.getForEntity(url, Ticket[].class);
		Ticket[] body = forEntity.getBody();
		List<Ticket> list = Arrays.asList(body);

		return list;
	}

	@Override
	public Ticket bookTicket(Passenger p) {
		String ticketurl = "http://localhost:5050/ticket/bookTicket";

		RestTemplate rt = new RestTemplate();
		Passenger pass = new Passenger();
		pass.setName(p.getName());
		pass.setJourneryDate(p.getJourneryDate());
		pass.setSource(p.getSource());
		pass.setDestination(p.getDestination());
		pass.setFare(p.getFare());
		pass.setTrainNum(p.getTrainNum());
		ResponseEntity<Ticket> response = rt.postForEntity(ticketurl, pass, Ticket.class);
		Ticket ticket = response.getBody();
		return ticket;
	}

	@Override
	public Ticket getTicket(Integer ticketId) {
		String getUrl = "http://localhost:5050/ticket/getTicket/{id}";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Integer> param = new HashMap<>();
		param.put("id", ticketId);
		Ticket getTicket = restTemplate.getForObject(getUrl, Ticket.class, param);
		return getTicket;
	}

	@Override
	public String deleteTicket(Integer ticketId) {
		String url = "http://localhost:5050/ticket/deleteTicket/{ticketId}";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> responseEntity = rt.exchange(url, HttpMethod.DELETE, null, String.class, ticketId);
		String delete = responseEntity.getBody();
		return delete;
	}

	@Override
	public Ticket updateTicket(Integer ticketId, Passenger passenger) {
		String updateticturl = "http://localhost:5050/ticket/updateTicket/{ticketId}";
		RestTemplate rt = new RestTemplate();
		Map<String, Integer> param = new HashMap<>();
		param.put("id", ticketId);
		Passenger updatedPassenger = new Passenger();
		updatedPassenger.setName(passenger.getName());
		updatedPassenger.setPid(ticketId);
		updatedPassenger.setTrainNum(passenger.getTrainNum());
		updatedPassenger.setDestination(passenger.getName());
		updatedPassenger.setSource(passenger.getSource());
		updatedPassenger.setFare(passenger.getFare());
		HttpEntity<Passenger> requestEntity = new HttpEntity<>(passenger);
		ResponseEntity<Ticket> exchange = rt.exchange(updateticturl, HttpMethod.PUT, requestEntity, Ticket.class,
				ticketId);
		Ticket ticket = exchange.getBody();
		return ticket;

	}

}
