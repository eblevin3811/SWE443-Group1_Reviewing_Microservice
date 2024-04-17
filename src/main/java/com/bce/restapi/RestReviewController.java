package com.bce.restapi;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestGreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public RestGreeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new RestGreeting(counter.incrementAndGet(), String.format(template, name));
	}
}
