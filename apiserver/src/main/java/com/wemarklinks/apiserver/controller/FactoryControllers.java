package com.wemarklinks.apiserver.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FactoryControllers {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> test(
			@RequestParam(defaultValue="WemarkLinks") String name) {
		String text = String.format("hello %s!", StringEscapeUtils.escapeHtml4(name));
		return new ResponseEntity<String>(text, HttpStatus.OK);
	}

}
