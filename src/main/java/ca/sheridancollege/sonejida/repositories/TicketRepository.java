package ca.sheridancollege.sonejida.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.sheridancollege.sonejida.beans.Ticket;
import ca.sheridancollege.sonejida.beans.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
@AllArgsConstructor
public class TicketRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addTicket() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO tickets"
				+ " (userName, name, price, age, gender, email, phone_Num) VALUES ('Dars', 'Darshit Soneji', '35.00', '19', 'male', '1darshitsoneji@gmail.com', '991707975')";
		jdbc.update(query, parameters);
	}

	public void addTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO tickets "
				+ "(userName, name, price, age, gender, email, phone_Num) VALUES"
				+ "(:usn, :na, :pr, :ag, :ge, :em, :ph)";
		parameters.addValue("usn", ticket.getUserName());
		parameters.addValue("na", ticket.getName());
		parameters.addValue("pr", ticket.getPrice());
		parameters.addValue("ag", ticket.getAge());
		parameters.addValue("ge", ticket.getGender());
		parameters.addValue("em", ticket.getEmail());
		parameters.addValue("ph", ticket.getPhoneNum());
		jdbc.update(query, parameters);
		
	}
		public ArrayList<Ticket> getTickets() {
			ArrayList<Ticket> tickets = new ArrayList<Ticket>();
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM tickets";
			List<Map<String, Object>> rows =
					jdbc.queryForList(query, parameters);
			for(Map<String, Object> row : rows) {
				Ticket t = new Ticket();
				t.setName((String)row.get("userName"));
				t.setName((String)row.get("name"));
				t.setPrice((Double)row.get("price"));
				t.setAge((Integer)row.get("age"));
				t.setGender((String)row.get("gender"));
				t.setEmail((String)row.get("email"));
				t.setPhoneNum((String)row.get("phone_Num"));
				
				tickets.add(t); 
			}
			return tickets;
		}
		
		public ArrayList<Ticket> getTickets2() {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM tickets";
			
			ArrayList<Ticket> tickets = 
					(ArrayList<Ticket>) jdbc.query(query, parameters,
							
			new BeanPropertyRowMapper<Ticket>(Ticket.class));
			return tickets;
			}

		public Ticket getTicketById(int id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM tickets WHERE id=:id";
			parameters.addValue("id", id);
			ArrayList<Ticket> tickets = 
					(ArrayList<Ticket>) jdbc.query(query, parameters,
			new BeanPropertyRowMapper<Ticket>(Ticket.class));
			
			if(tickets.size()>0)
				return tickets.get(0);
			else
				return null;
		}
		
		
		public void editTicket(Ticket ticket) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "UPDATE tickets SET userName=:usn, name=:na, price=:pr, age=:ag, gender=:ge, email=:em, phone_Num=:ph WHERE id=:id";
			
			parameters.addValue("usn", ticket.getUserName());
			parameters.addValue("id", ticket.getId());
			parameters.addValue("na", ticket.getName());
			parameters.addValue("pr", ticket.getPrice());
			parameters.addValue("ag", ticket.getAge());
			parameters.addValue("ge", ticket.getGender());
			parameters.addValue("em", ticket.getEmail());
			parameters.addValue("ph", ticket.getPhoneNum());
			
			jdbc.update(query, parameters);
		}

		public void deleteTicketById(int id) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "DELETE FROM tickets WHERE id=:dars";
			parameters.addValue("dars", id);
			jdbc.update(query, parameters);
		}

		public List<User> getAllUsernames() {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM SEC_USER";
			return jdbc.query(query,  parameters, new BeanPropertyRowMapper<User>(User.class));
		}
	}
