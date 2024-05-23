package com.source.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
	private String ticketFrom;
	private String ticketTo;
	private int seats;
	private String secion;
	private Long userId;
}
