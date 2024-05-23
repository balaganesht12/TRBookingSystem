package com.source.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.source.entity.Ticket;
import com.source.entity.User;
	
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	public Ticket findByUserId(Long userId);
	public List<Ticket> findBySection(String section);
	public List<Ticket> deleteByUserId(Long userId);
}