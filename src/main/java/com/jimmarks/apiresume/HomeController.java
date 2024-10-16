/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.apiresume;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jimmarks.model.DdlMethods;
import com.jimmarks.model.ModelProvider;

//@Controller
@RestController
@RequestMapping("restapi")
public class HomeController {

	public static final String APPLICATION_VERSION = ResourceHelper.getConstantFromConfig("application.version", App.APP_CONFIG);
	public static final String ENVIRONMENT = ResourceHelper.getConstantFromConfig("environment", App.DEPLOYMENT_CONFIG);

	@RequestMapping("/ping")
	public String restapi() {
		App.APPLOGGER.info("called: ping");
		return String.format("apiresume service online (version %s / environment: %s)", APPLICATION_VERSION, ENVIRONMENT);
	}

	@RequestMapping(value = "/checkfortable/{name}", method = RequestMethod.GET)
    	public ResponseEntity<Object> checkForTable(@PathVariable String name) {
		App.APPLOGGER.info("called: checkfortable");
		ModelProvider modelProvider = new ModelProvider("sandbox");
		modelProvider.getOpenMySqlConnection();
		boolean tableExists = modelProvider.checkTableExists(name, modelProvider.connection);
		String response = String.format("Table %s exists: %b", name, tableExists);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(response, HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping("/initialize")
	public String initialize() throws SQLException {
		App.APPLOGGER.info("called: initialize");
		DdlMethods ddl = new DdlMethods();
		ddl.initializeEnvironment();
		ddl.loadData();
		return "done";
	}
	
	@RequestMapping("/getall")
	public String getAll() throws SQLException {
		App.APPLOGGER.info("called: getall");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getAll(false);
		return result;
	}
	@RequestMapping(value = "/getid/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getById(@PathVariable("id") long id) throws SQLException {
		App.APPLOGGER.info("called: getid");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getById(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return responseEntity;
	}
	@RequestMapping(value = "/gettype/{type}", method = RequestMethod.GET)
	public ResponseEntity<String> getByType(@PathVariable("type") String type) throws SQLException {
		App.APPLOGGER.info("called: gettype");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getByType(type);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return responseEntity;
	}
	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
	public ResponseEntity<String> getBySearch(@PathVariable("term") String term) throws SQLException {
		App.APPLOGGER.info("called: search");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getBySearch(term);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping("/listall")
	public String listAll() throws SQLException {
		App.APPLOGGER.info("called: listall");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getAll(true);
		return result;
	}
	
	@RequestMapping("/testmodelprovider")
	public String testModelProvider() {
		App.APPLOGGER.info("called: testmodelprovider");
		ModelProvider modelProvider = new ModelProvider("sandbox");
		return modelProvider.testMySqlConnection();
	}

	
}