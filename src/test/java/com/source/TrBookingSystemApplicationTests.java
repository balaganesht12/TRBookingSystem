package com.source;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RequestBody;

import com.source.entity.Ticket;
import com.source.entity.User;
import com.source.model.TicketRequest;
import com.source.model.TicketResponse;
import com.source.service.TicketService;
import com.source.service.UserService;

@SpringBootTest
class TrBookingSystemApplicationTests {

	@Autowired
	TicketService ticketService;
	@Autowired
	UserService userService;

	private static final int TICKET_PRICE = 5;

	@Test
	void contextLoads() {
		// User user =
		// User.builder().firstName("fname").lastName("lname").email("flname*abc.com").build();
		// User user1 = userService.addUser(user);
	}

	@Test
	public void test_bookTicket() throws Exception {

		User user = User.builder().firstName("fname").lastName("lname").email("flname*abc.com").build();
		User user1 = userService.addUser(user);

		TicketRequest request = TicketRequest.builder().ticketFrom("London").ticketTo("Paris").secion("A").seats(1)
				.userId(new Long(1)).build();
		TicketResponse ticketResponse = ticketService.bookTicket(request);
		assertTrue(ticketResponse.getTicketFrom().equals(request.getTicketFrom())
				&& ticketResponse.getTicketTo().equals(request.getTicketTo())
				&& ticketResponse.getUser().getFirstName().equals(user.getFirstName())
				&& ticketResponse.getUser().getLastName().equals(user.getLastName())
				&& ticketResponse.getUser().getEmail().equals(user.getEmail())
				&& ticketResponse.getPrice() == Double.valueOf(request.getSeats() * TICKET_PRICE));
	}

	@Test	
	public void test_getReceiptByUserId() throws Exception {
		
		TicketRequest request = TicketRequest.builder().ticketFrom("London").ticketTo("Paris").secion("A").seats(1)
				.userId(new Long(1)).build();
		User user = User.builder().firstName("fname").lastName("lname").email("flname*abc.com").build();
		Long userId=new Long("1");
		TicketResponse ticketResponse = ticketService.getReceiptByUserId(userId);
		assertTrue(ticketResponse.getTicketFrom().equals(request.getTicketFrom())
				&& ticketResponse.getTicketTo().equals(request.getTicketTo())
				&& ticketResponse.getUser().getFirstName().equals(user.getFirstName())
				&& ticketResponse.getUser().getLastName().equals(user.getLastName())
				&& ticketResponse.getUser().getEmail().equals(user.getEmail())
				&& ticketResponse.getPrice() == Double.valueOf(request.getSeats() * TICKET_PRICE));
	}
	
	@Test	
	public void test_findBySection() throws Exception {
		String section="A";
		List<Ticket> tickets = ticketService.findBySection(section);
		assertTrue(tickets.stream().allMatch(t->t.getSection().equals("A")));
	}
	
	@Test
	public void test_modifyTicket() throws Exception {

		TicketRequest request = TicketRequest.builder().ticketFrom("London").ticketTo("Paris").secion("B").seats(2)
				.userId(new Long(1)).build();
		Long ticketId=new Long("1");
		String status = ticketService.modifyTicket(request, ticketId);
		assertTrue(status.equals("success"));
	}
	
	@Test
	public void test_cancelTicketByUserId() throws Exception {
		Long userId=new Long("1");
		String status = ticketService.cancelTicketByUserId(userId);
		assertTrue(status.equals("success"));
	}
}
