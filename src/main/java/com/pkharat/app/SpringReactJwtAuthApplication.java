package com.pkharat.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pkharat.app.entities.Authority;
import com.pkharat.app.entities.User;
import com.pkharat.app.repository.UserDetailsRepository;

@SpringBootApplication
public class SpringReactJwtAuthApplication {
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringReactJwtAuthApplication.class, args);
	}
	
	@PostConstruct
	protected void init() {
		List<Authority> authorityList=new ArrayList<>();
		
		authorityList.add(createAuthority("USER","User role"));
		//authorityList.add(createAuthority("ADMIN","Admin role"));
		
		User user=new User();
		
		user.setUserName("purab11");
		user.setFirstName("purab");
		user.setLastName("K");
		
		user.setPassword(passwordEncoder.encode("123456"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		
		userDetailsRepository.save(user);
	}
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}

}
