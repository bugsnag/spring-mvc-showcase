package org.springframework.samples.mvc.simple;

import com.bugsnag.Bugsnag;
import com.bugsnag.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	Logger logger = LoggerFactory.getLogger("Bugsnag");

	@Autowired
	Bugsnag bugsnag;

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		bugsnag.setEndpoint("http://localhost:8000");
		bugsnag.notify(new RuntimeException("Hello World"));
//		logger.warn("Error encountered", new RuntimeException("Hello World"));
		return "Hello world!";
	}

}
