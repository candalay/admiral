package com.ingenico.account.api;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.ingenico.account.engine.TransferEngine;
import com.ingenico.account.model.Transfer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/account", produces = "application/json")
@Api(value = "/account", description = "Transfer Operations")
public class TransferController {

	@Inject
	TransferEngine engine;

	@PostMapping("/transfer")
	@ApiOperation(value = "Make a transfer", notes = "")
	@ApiResponses({ @ApiResponse(code = 404, message = "Account not found"),
			@ApiResponse(code = 400, message = "An issue has been found with an input"),
			@ApiResponse(code = 403, message = "Not a valid amuont"),
			@ApiResponse(code = 409, message = "Your balance is insufficient to transfer the amount."),
			@ApiResponse(code = 200, message = "Successful", response = TransferEngine.class) })
	public DeferredResult<TransferEngine> transfer(@RequestBody Transfer transfer) throws InterruptedException, ExecutionException {

		UUID uuid = UUID.randomUUID();
		final DeferredResult<TransferEngine> result = new DeferredResult<>();

		  CompletableFuture<TransferEngine> future = CompletableFuture
			.supplyAsync(() -> engine.checkTransferRequest(uuid, transfer))
			.thenApply(a -> engine.completeTransfer(a))
		    .exceptionally(e -> { result.setErrorResult(e); return null; });
		  
		  result.setResult(future.get());
		  
		return result;

	}

	public TransferEngine getEngine() {
		return engine;
	}

	public void setEngine(TransferEngine engine) {
		this.engine = engine;
	}
}
