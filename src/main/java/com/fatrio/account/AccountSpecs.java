package com.fatrio.account;

import org.springframework.data.jpa.domain.Specification;

public class AccountSpecs {
	
	public static Specification<Account> hasEmail(String email) {
		return (root, query, cb) -> cb.equal(root.get(Account_.email), email);
	}
	
	public static Specification<Account> hasRole(String role) {
		return (root, query, cb) -> cb.equal(root.get(Account_.role), role);
	}

}
