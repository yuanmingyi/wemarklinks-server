package com.wemarklinks.apiserver.controller;

import com.wemarklinks.factory.data.model.CodeInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wemarklinks.factory.data.service.ICodeInfoService;

import java.util.Date;

@RestController
public class FactoryControllers {

	@Autowired
	private ICodeInfoService codeInfoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> test(
			@RequestParam(defaultValue="WemarkLinks") String name) {
		String text = String.format("hello %s!", StringEscapeUtils.escapeHtml4(name));
		return new ResponseEntity<String>(text, HttpStatus.OK);
	}

	@RequestMapping(value = "/{category}/{code}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> consume(@PathVariable String category, @PathVariable int code, @RequestParam String location) {
		CodeInfo info = codeInfoService.getCodeInfoById(code);
		info.setDiscovered_loc(location);
		info.setDiscovered_time(new Date(System.currentTimeMillis()));
		codeInfoService.updateCodeInfo(info);
		return new ResponseEntity<>(info, HttpStatus.OK);
	}

	@RequestMapping(value = "/scan", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> scan(@RequestParam String code, @RequestBody CodeInfo info) {
		info.setCode(code);
		codeInfoService.insertCodeInfo(info);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
