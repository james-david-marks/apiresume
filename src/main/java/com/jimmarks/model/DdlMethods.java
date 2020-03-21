/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jimmarks.apiresume.App;
import com.jimmarks.apiresume.HtmlFormatter;
import com.jimmarks.apiresume.Json;

public class DdlMethods {
	
	private final String MASTER_SCHEMA_NAME = "sandbox";
	private final String SCHEMA_NAME = "apiresume";
	private final String TABLE_NAME = "resumeline";
	
	public void initializeEnvironment() throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(MASTER_SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlCheckForSchema(SCHEMA_NAME));
		if(!result.next())
		{
			App.applogger.info("creating schema");
			modelProvider.mySqlExecuteNonQuery(sqlCreateSchema(SCHEMA_NAME));
		}
		else
		{
			App.applogger.info("schema exists");
		}
		modelProvider = new ModelProvider(SCHEMA_NAME);
		if(!modelProvider.checkTableExists(TABLE_NAME, modelProvider.connection))
		{
			App.applogger.info("creating table");
			modelProvider.mySqlExecuteNonQuery(sqlCreateTableResumeLine());
		}
		else
		{
			App.applogger.info("table exists");
		}
	}
	public String buildHtmlDocument(ResultSet result, boolean includeDetail) throws SQLException
	{
		return buildHtmlDocument(result, includeDetail, HtmlFormatter.NO_ID, "", "");
	}
	public String buildHtmlDocument(ResultSet result, boolean includeDetail, long id, String type, String term) throws SQLException
	{
		StringBuffer buf = new StringBuffer();
		HtmlFormatter html = new HtmlFormatter();
		buf.append(ResumeLine.getAsDivs_header());
		while(result.next())
		{
			ResumeLine line = new ResumeLine();
			line.id = result.getLong("id");
			line.type = result.getString("type");
			line.header = result.getString("header");
			line.body = result.getString("body");
			buf.append(line.getAsDivs(includeDetail));
		}
		return html.getLandingPage_build(buf.toString(), id, type, term);		
	}
	public JsonObject buildJsonDocument(ResultSet result) throws SQLException
	{
		return buildJsonDocument(result, true);
	}
	public JsonObject buildJsonDocument(ResultSet result, boolean includeDetail) throws SQLException
	{
		ResumeLines lines = new ResumeLines();
		while(result.next())
		{
			ResumeLine line = new ResumeLine();
			line.id = result.getLong("id");
			line.type = result.getString("type");
			line.header = result.getString("header");
			line.body = includeDetail ? result.getString("body") : null;
			lines.add(line);
		}
		ResumeDto.ComponentDto dto = new ResumeDto.ComponentDto();
		ResumeDto.map(lines, dto.ResumeLines);
		return Json.toJsonObject(dto);
	}
	public String getAll(boolean list) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectAll());
		StringBuffer buf = new StringBuffer();
		buf.append(buildHtmlDocument(result, !list));
		return buf.toString();
	}
	public String getById(long id) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectById(id));
		return buildHtmlDocument(result, true, id, "", "");
	}
	public String getByType(String type) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectByType(type));
		return buildHtmlDocument(result, true, HtmlFormatter.NO_ID, type, "");
	}
	public String getBySearch(String term) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectBySearch(term));
		return buildHtmlDocument(result, true, HtmlFormatter.NO_ID, "", term);
	}
	public JsonObject getJsonAll(boolean list) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectAll());
		return buildJsonDocument(result, !list);
	}
	public JsonObject getJsonById(long id) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectById(id));
		return buildJsonDocument(result);
	}
	public JsonObject getJsonByType(String type) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectByType(type));
		return buildJsonDocument(result);
	}
	public JsonObject getJsonBySearch(String term) throws SQLException
	{
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectBySearch(term));
		return buildJsonDocument(result);
	}
	public void loadData() throws SQLException
	{
		final String RESUME_NAME = "data";
		ModelProvider modelProvider = new ModelProvider(SCHEMA_NAME);
		ResultSet result = modelProvider.mySqlQuery(sqlSelectName(RESUME_NAME));
		if(!result.next())
		{
			App.applogger.info(String.format("loading %s", RESUME_NAME));
			String insertSql = sqlInsert_testData();
			App.debuglogger.info(insertSql);
			modelProvider.mySqlExecuteNonQuery(insertSql);
		}
		else
		{
			App.applogger.info(String.format("found %s", RESUME_NAME));
		}
	}
	public String sqlCheckForSchema(String schemaName)
	{
		String sql = String.format("select schema_name from information_schema.schemata where schema_name = '%s'", schemaName);
		return sql;
	}
	public String sqlCreateTableResumeLine(){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("create table %s(", TABLE_NAME));
		buf.append("id bigint PRIMARY KEY AUTO_INCREMENT NOT NULL");
		buf.append(",type varchar(100)");
		buf.append(",header varchar(500)");
		buf.append(",body varchar(2000)");
		buf.append(");");
		return buf.toString();
	}
	public String sqlCreateSchema(String database){
		return String.format("create database %s", database);
	}
	public String sqlDropSchema(String database){
		return String.format("drop database %s", database);
	}
	public String sqlInsert_testData(){
		StringBuffer buf = new StringBuffer();
		buf.append("insert into resumeline (type, header, body) values");
		buf.append("('head','data', 'data')");
		buf.append(",('subhead', 'data', 'data')");
		buf.append(",('body', 'data', 'data')");
		buf.append(",('experience', 'data', 'data')");
		buf.append(",('education', 'data', 'data')");
		buf.append(",('community', 'data', 'data')");
		buf.append(";");
		return buf.toString();
	}
	public String sqlSelectAll(){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("select * from resumeline"));
		buf.append(";");
		return buf.toString();
	}
	public String sqlSelectById(long id){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("select * from resumeline where id = %d", id));
		buf.append(";");
		return buf.toString();
	}
	public String sqlSelectByType(String type){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("select * from resumeline where type = '%s'", type));
		buf.append(";");
		return buf.toString();
	}
	public String sqlSelectBySearch(String term){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("select * from resumeline where header	like '%%%s%%' or body like '%%%s%%'", term, term));
		buf.append(";");
		return buf.toString();
	}
	public String sqlSelectName(String name){
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("select * from resumeline where header = '%s'", name));
		buf.append(";");
		return buf.toString();
	}
}
