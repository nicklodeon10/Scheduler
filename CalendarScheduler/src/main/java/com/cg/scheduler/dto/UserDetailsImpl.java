/**
 * 
 */
package com.cg.scheduler.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: DEVANG
 * description: UserDetails Model (Required by Spring Security)
 * created date: 11/10/2019
 * modified: -
 */
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011366581940810649L;
	
	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl() {
		super();
	}

	public UserDetailsImpl(Employee user) {
		this.username = user.getUserName();
		this.password = user.getEmpPassword();
		this.active = true;
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [username=" + username + ", password=" + password + ", active=" + active
				+ ", authorities=" + authorities + "]";
	}

}
