package ca.sheridancollege.sonejida.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sonejida.beans.User;
import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class SecurityRepository {
	
	private NamedParameterJdbcTemplate jdbc;
	
	public User findUserByUsername(String username) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM SEC_USER WHERE userName=:un";
			parameters.addValue("un",username);
			
			ArrayList<User> users = 
					(ArrayList<User>) jdbc.query(query, parameters,
			new BeanPropertyRowMapper<User>(User.class));
			if(users.size()>0)
				return users.get(0);
			return null;
	}
	
	public ArrayList<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "AND userId=:id";
		parameters.addValue("id", userId);
		List<Map<String, Object>> rows =
				jdbc.queryForList(query, parameters);
		for(Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}

	public void register(String username, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO SEC_User "
				+ "(userName, encryptedPassword, ENABLED) VALUES"
				+ "(:un,:pa, 1)";
		parameters.addValue("un", username);
		parameters.addValue("pa", encodedPassword);
		jdbc.update(query, parameters);
		
	}
	
	public void assignRoles(long userId, long roleId) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role "
				+ "(userId, roleId) VALUES"
				+ "(:uid,:rid)";
		parameters.addValue("uid", userId);
		parameters.addValue("rid", roleId);
		jdbc.update(query, parameters);
		
	}
}
