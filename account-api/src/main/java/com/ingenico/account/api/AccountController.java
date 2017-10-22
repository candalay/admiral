package com.ingenico.account.api;

import static org.springframework.http.ResponseEntity.created;

import java.net.URI;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenico.account.engine.AccountEngine;
import com.ingenico.account.model.Account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/account", produces = "application/json")
@Api(value = "/account", description = "Account Operations")
public class AccountController {

	@Inject
	AccountEngine accountEngine;

	@PostMapping
	@ApiOperation(value = "Create a new account", notes = "This will create a new account by sending (accountNumber, balance (0.0 as default))")
	@ApiResponses({ 
		    @ApiResponse(code = 400, message = "An issue has been found with an input"),
			@ApiResponse(code = 201, message = "Created", response = AccountEngine.class) })
	public ResponseEntity<AccountEngine> newAccount(@RequestBody Account account) {

		AccountEngine result = accountEngine.createAccount(account.getAccountNumber(), account.getBalance());
		return created(URI.create("/account/" + result.id())).body(result);

	}
}
