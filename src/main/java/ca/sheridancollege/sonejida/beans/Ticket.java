package ca.sheridancollege.sonejida.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Ticket {
	private int id;
	private String userName;
	private String name;
	private double price;
	private int age;
	private String gender;
	private String email;
	private String phoneNum;
}
