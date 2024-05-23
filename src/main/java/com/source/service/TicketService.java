package com.source.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.source.entity.Ticket;
import com.source.model.TicketRequest;
import com.source.model.TicketResponse;

public interface TicketService {
	public TicketResponse bookTicket(TicketRequest request);
	
	public TicketResponse getReceiptByUserId(Long userId);
	
	public List<Ticket> findBySection(String section);
	
	public String cancelTicketByUserId(Long userId);
	
	public String modifyTicket(TicketRequest request, Long ticketId);
	
	
}
