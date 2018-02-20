package com.payment.transfer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;

public class TransferServiceUtil {

	private TransferServiceUtil() {

	}

	public static Timestamp getDateAsTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static UUID generateTransferID() {
		return UUID.randomUUID();
	}

	public static void randomSleep(int duration, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(ThreadLocalRandom.current().nextInt(duration));
		} catch (InterruptedException e) {
			Throwables.propagate(e);
		}
	}

	public static String getThreadName() {
		return Thread.currentThread().getName();
	}
}
