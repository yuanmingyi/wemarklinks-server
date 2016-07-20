package com.wemarklinks.apiserver.controller;

import com.wemarklinks.factory.data.model.CodeInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wemarklinks.factory.data.service.ICodeInfoService;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping(value = "/{category}/{batch}/{code}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> scan(HttpServletRequest request,
			@PathVariable String category, @PathVariable String batch, @PathVariable String code,
			@RequestParam(required=false) String action, @RequestParam(required=false) String location, @RequestParam(required=false) String userid, @RequestParam(required=false) String signature) {
		CodeInfo info = codeInfoService.getCodeInfoById(category, batch, code);
		if (info == null) {
			return new ResponseEntity<>("code not found", HttpStatus.BAD_REQUEST);
		}
		if (action == null && location == null && userid == null && signature == null) {
			String ip = request.getRemoteAddr();
			info.setDiscovered_loc(ip2loc(ip));
			info.setDiscovered_time(new Date(System.currentTimeMillis()));
			codeInfoService.updateCodeInfo(info);			
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} else {
			// specific scan
			return new ResponseEntity<CodeInfo>(info, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{category}/{batch}/{code}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> assign(@PathVariable String category, @PathVariable String batch, @PathVariable String code,
			@RequestParam String location, @RequestParam String signature) {
		CodeInfo info = codeInfoService.getCodeInfoById(category, batch, code);
		if (info == null) {
			return new ResponseEntity<>("code not found", HttpStatus.BAD_REQUEST);
		}
		info.setAssigned_time(new Date(System.currentTimeMillis()));
		info.setTo_loc(location);
		codeInfoService.updateCodeInfo(info);
		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

	private String ip2loc(String ip) {
		return "sq";
	}

}
