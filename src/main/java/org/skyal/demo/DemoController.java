package org.skyal.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {
	@RequestMapping("/api")
	public String home(){
		return DemoApplication.page.getText();
	}

}