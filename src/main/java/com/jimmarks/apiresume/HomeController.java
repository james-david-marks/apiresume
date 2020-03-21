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

/**
 * @author dderose
 *
 */
//@Controller
@RestController
@RequestMapping("restapi")
public class HomeController {
	
	@RequestMapping("/")
	public String restapi() {
		App.applogger.info("called: restapi");
		return "restapi";
	}

	@RequestMapping(value = "/checkfortable/{name}", method = RequestMethod.GET)
    	public ResponseEntity<Object> checkForTable(@PathVariable String name) {
		App.applogger.info("called: checkfortable");
		ModelProvider modelProvider = new ModelProvider("sandbox");
		modelProvider.getOpenMySqlConnection();
		boolean tableExists = modelProvider.checkTableExists(name, modelProvider.connection);
		String response = String.format("Table %s exists: %b", name, tableExists);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(response, HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping("/initialize")
	public String initialize() throws SQLException {
		App.applogger.info("called: initialize");
		DdlMethods ddl = new DdlMethods();
		ddl.initializeEnvironment();
		ddl.loadData();
		return "done";
	}
	
	@RequestMapping("/getall")
	public String getAll() throws SQLException {
		App.applogger.info("called: getall");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getAll(false);
		return result;
	}
	@RequestMapping(value = "/getid/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getById(@PathVariable long id) throws SQLException {
		App.applogger.info("called: getid");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getById(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return responseEntity;
	}
	@RequestMapping(value = "/gettype/{type}", method = RequestMethod.GET)
	public ResponseEntity<String> getByType(@PathVariable String type) throws SQLException {
		App.applogger.info("called: gettype");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getByType(type);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return responseEntity;
	}
	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
	public ResponseEntity<String> getBySearch(@PathVariable String term) throws SQLException {
		App.applogger.info("called: search");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getBySearch(term);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(result, HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping("/listall")
	public String listAll() throws SQLException {
		App.applogger.info("called: listall");
		DdlMethods ddl = new DdlMethods();
		String result = ddl.getAll(true);
		return result;
	}
	
	@RequestMapping("/testmodelprovider")
	public String testModelProvider() {
		App.applogger.info("called: testmodelprovider");
		ModelProvider modelProvider = new ModelProvider("sandbox");
		return modelProvider.testMySqlConnection();
	}

	
}