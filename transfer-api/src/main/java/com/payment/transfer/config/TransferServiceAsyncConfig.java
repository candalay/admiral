package com.payment.transfer.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.payment.transfer.exception.AsyncUncaughtException;

@Configuration
@EnableAsync
public class TransferServiceAsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(15);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("transfer-thread-");
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				throw new AsyncUncaughtException("Async exception is occured");
			}
		};
	}

}
