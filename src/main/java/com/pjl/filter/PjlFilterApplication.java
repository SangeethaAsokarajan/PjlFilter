package com.pjl.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.pjl.filter.processor.FileProcessor;

@SpringBootApplication
@ComponentScan
public class PjlFilterApplication {

	@Autowired
	FileProcessor fileProcessor;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PjlFilterApplication.class, args);
		PjlFilterApplication mainObj = ctx.getBean(PjlFilterApplication.class);
		if (args.length > 0)
			mainObj.fileProcessor.process(args[0],args[1]);
	}
}
