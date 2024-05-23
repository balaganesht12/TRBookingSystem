package com.source.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.source.entity.Ticket;
import com.source.entity.User;
import com.source.model.TicketRequest;
import com.source.model.TicketResponse;
import com.source.repository.TicketRepository;
import com.source.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserService userService;

	private static final int TICKET_PRICE = 5;
	private static final long TOTAL_SEAT = 5;

	@Override
	public TicketResponse bookTicket(TicketRequest request) {
		Ticket ticket = new Ticket();
		ticket.setTicketFrom(request.getTicketFrom());
		ticket.setTicketTo(request.getTicketTo());
		ticket.setUserId(request.getUserId());
		ticket.setSection(request.getSecion());
		ticket.setSeats(request.getSeats());
		ticket.setPrice(request.getSeats() * TICKET_PRICE);
		Ticket ticket1 = ticketRepository.save(ticket);

		TicketResponse ticketResponse = new TicketResponse();
		ticketResponse.setTicketFrom(ticket1.getTicketFrom());
		ticketResponse.setTicketTo(ticket1.getTicketTo());
		ticketResponse.setPrice(ticket1.getPrice());
		User user = userService.retrieveUserById(ticket1.getUserId());
		ticketResponse.setUser(user);

		return ticketResponse;
	}

	@Override
	public TicketResponse getReceiptByUserId(Long userId) {
		Ticket ticket = ticketRepository.findByUserId(userId);
		TicketResponse ticketResponse = new TicketResponse();
		ticketResponse.setTicketFrom(ticket.getTicketFrom());
		ticketResponse.setTicketTo(ticket.getTicketTo());
		ticketResponse.setPrice(ticket.getPrice());
		User user = userService.retrieveUserById(ticket.getUserId());
		ticketResponse.setUser(user);
		return ticketResponse;
	}

	@Override
	public List<Ticket> findBySection(String section) {
		List<Ticket> ticketList = ticketRepository.findBySection(section);
		System.out.println("findBySection:" + ticketList);
		return ticketList;
	}

	@Override
	@Transactional
	public String cancelTicketByUserId(Long userId) {
		try {
			ticketRepository.deleteByUserId(userId);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}

	@Override
	public String modifyTicket(TicketRequest request, Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId).get();
		ticket.setTicketId(ticketId);
		ticket.setTicketFrom(request.getTicketFrom());
		ticket.setTicketTo(request.getTicketTo());
		ticket.setUserId(request.getUserId());
		ticket.setSection(request.getSecion());
		ticket.setSeats(request.getSeats());
		ticket.setPrice(request.getSeats() * TICKET_PRICE);
		try {
			ticketRepository.save(ticket);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
}
