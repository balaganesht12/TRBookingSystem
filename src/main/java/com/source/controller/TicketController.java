package com.source.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.source.entity.Ticket;
import com.source.model.TicketRequest;
import com.source.model.TicketResponse;
import com.source.service.TicketService;

@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	TicketService ticketService;
	
	@PostMapping("/bookTicket")
	public TicketResponse bookTicket(@RequestBody TicketRequest request) {
		return ticketService.bookTicket(request);
	}
	
	@GetMapping("/getTicket")
	public TicketResponse getReceiptByUserId(@RequestParam Long userId) {
		return ticketService.getReceiptByUserId(userId);
	}
	
	@GetMapping("/getTicketBySection")
	public List<Ticket> findBySection(@RequestParam String section) {
		System.out.println("findBy Section");
		return ticketService.findBySection(section);
	}
	
	@DeleteMapping("/cancelTicketByUserId")
	public String cancelTicketByUserId(@RequestParam Long userId) {
		System.out.println("cancel ticket by userid");
		return ticketService.cancelTicketByUserId(userId);
	}
	
	@PostMapping("/modifyTicket")
	public String modifyTicket(@RequestBody TicketRequest request, @RequestParam Long ticketId) {
		return ticketService.modifyTicket(request, ticketId);
	}
}
