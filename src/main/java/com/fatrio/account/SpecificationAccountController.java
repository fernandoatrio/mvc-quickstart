package com.fatrio.account;

import static org.springframework.data.jpa.domain.Specifications.where;

import static com.fatrio.account.AccountSpecs.hasEmail;
import static com.fatrio.account.AccountSpecs.hasRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Test the Specification API.
 * <p>
 * <a href='http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#specifications'>Spring Data JPA Specifications</a>
 * </p>
 * 
 * @author fatrio
 *
 */
@Controller
public class SpecificationAccountController {
	
	@Autowired
	private AccountService accountService;

    @RequestMapping(value = "account/current/specifications", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account currentAccountSpecifications(@RequestParam String email) {
        Specification<Account> hasEmail = hasEmail(email);
        return this.accountService.findOne(hasEmail);
    }

    @RequestMapping(value = "account/current/specifications/all/page", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Page<Account> currentAccountSpecificationsAll(Pageable page) {
        return this.accountService.findAll(page);
    }

    @RequestMapping(value = "account/current/specifications/all", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account currentAccountSpecificationsAll(@RequestParam(required=false, defaultValue="") String email, 
    											   @RequestParam(required=false, defaultValue="") String role) {
    	// Roles: "ROLE_USER", "ROLE_ADMIN"
        Specification<Account> hasEmail = hasEmail(email);
        Specification<Account> hasRole = hasRole(role);
        return this.accountService.findOne(where(hasEmail).or(hasRole));
    }
}
