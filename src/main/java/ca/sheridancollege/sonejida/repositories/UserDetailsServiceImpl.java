package ca.sheridancollege.sonejida.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl  implements UserDetailsService {
	
	@Autowired
	private SecurityRepository secRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		//Find the user based on the user name
		ca.sheridancollege.sonejida.beans.User user = 
				secRepo.findUserByUsername(username);
		//If the user doesn't exist, throw exception
		if(user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Could not find user");
		}
			
		//Find the roles connected to that user
		List<String> roles = secRepo.getRolesById(user.getUserId());
		//Convert the roles into a list of Granted Authorities
		List<GrantedAuthority> grantList = new ArrayList <GrantedAuthority> ();
		for (String r : roles) {
			grantList.add(new SimpleGrantedAuthority(r));
		}
		//Create a Spring user based on our information
		//import org.springframework.security.core.userdetails.UserDetails.User;
		User springUser = new User(user.getUserName(),
				user.getEncryptedPassword(), grantList);
		return (UserDetails)springUser;
	}
}