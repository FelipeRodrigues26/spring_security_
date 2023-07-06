package br.com.security.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/work")
public class WorkResource {

	
	@RequestMapping(method=RequestMethod.GET)
	public String findAll() {
		return "working...";
	}

	
	
	
	
}