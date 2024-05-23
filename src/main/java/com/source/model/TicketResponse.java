package com.source.model;

import com.source.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
	private String ticketFrom;
	private String ticketTo;
	private User user;
	private double price;
}
