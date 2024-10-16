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

import com.google.gson.JsonObject;
import com.jimmarks.model.DdlMethods;
import com.jimmarks.model.ModelProvider;

/**
 * @author dderose
 *
 */
//@Controller
@RestController
@RequestMapping("restapi-json")
public class JsonController {
	
	@RequestMapping("/")
	public String restapi() {
		App.APPLOGGER.info("called: restapi-json");
		return "restapi/json";
	}

	@RequestMapping("/getall")
	public ResponseEntity<String> getAll() throws SQLException {
		App.APPLOGGER.info("called: getall");
		DdlMethods ddl = new DdlMethods();
		JsonObject json = ddl.getJsonAll(false);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value = "/getid/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("id") long id) throws SQLException {
		App.APPLOGGER.info("called: getid");
		DdlMethods ddl = new DdlMethods();
		JsonObject json = ddl.getJsonById(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = "/gettype/{type}", method = RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("type") String type) throws SQLException {
		App.APPLOGGER.info("called: gettype");
		DdlMethods ddl = new DdlMethods();
		JsonObject json = ddl.getJsonByType(type);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping("/listall")
	public ResponseEntity<String> listAll() throws SQLException {
		App.APPLOGGER.info("called: listall");
		DdlMethods ddl = new DdlMethods();
		JsonObject json = ddl.getJsonAll(true);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
	public ResponseEntity<String> getSearch(@PathVariable("term") String term) throws SQLException {
		App.APPLOGGER.info("called: search");
		DdlMethods ddl = new DdlMethods();
		JsonObject json = ddl.getJsonBySearch(term);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(json.toString(), HttpStatus.OK);
		return responseEntity;
	}

}