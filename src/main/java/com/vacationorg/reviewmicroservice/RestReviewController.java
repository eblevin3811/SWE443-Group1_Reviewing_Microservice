package com.vacationorg.reviewmicroservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestReviewController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public RestReview greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new RestReview(counter.incrementAndGet(), String.format(template, name));
	}
}
