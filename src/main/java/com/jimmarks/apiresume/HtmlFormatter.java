/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.apiresume;

import com.jimmarks.model.ResumeLine;

public class HtmlFormatter {
	public final String SERVLET_CONTEXT = "/apiresume/";
	public static final long NO_ID = -1;
	
 	public String closeContainer()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</div>");
		return buf.toString();
	}
	public String closeDiv()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</div>");
		return buf.toString();
	}
	public String closeDocument()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</body>");
		buf.append("</html>");
		return buf.toString();
	}
	public String closeTable()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("</table>");
		return buf.toString();
	}
	public String openContainer(String classes)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("<div class=\"container %s\">", classes));
		return buf.toString();
	}
	public String openDiv(String classes)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("<div class=\"%s\">", classes));
		return buf.toString();
	}
	public String openTable()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<table>");
		buf.append("<th>");
		buf.append("</th>");
		return buf.toString();
	}
	public String openDocument()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<html>");
		buf.append("<head>");
		buf.append(scripts());
		buf.append(styleSheet());
		buf.append("</head>");
		buf.append("<body>");
		return buf.toString();
	}
	public String getErrorDivs(String status, String message, String context)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row border_bottom\">");
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "Status"));
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "Message"));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row\">");
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", status));
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", message));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row\">");
		buf.append(String.format("<DIV class=\"col-lg-12  bold_alert\"><label >%s</label></DIV>", "Requested resource unknown."));
		buf.append(String.format("<DIV class=\"column_image\"><img src=\"images/panda404.png\" height=\"300px\" ></DIV>", context));
		buf.append("</DIV>");
		return buf.toString();
	}
	public String getLandingPage()
	{
		return getLandingPage_build("");
	}
	public String getLandingPage_build(String pretty)
	{
		return getLandingPage_build(pretty, NO_ID, "", "");
	}
	public String getLandingPage_build(String pretty, long id, String type, String term)
	{
		StringBuffer buf = new StringBuffer();
		HtmlFormatter html = new HtmlFormatter();
		buf.append(html.openDocument());
		buf.append(html.openContainer("full"));
		buf.append(html.openDiv("row"));
		buf.append(html.openDiv("column_blank"));
		buf.append(String.format("<span class=\"font_head\">jimmarks API Resume&emsp;</span><span class=\"font_subhead\">Landing Page&emsp;</span><span class=\"base_link\"><a href=\"%s\">home</a>", SERVLET_CONTEXT));
		buf.append(html.closeDiv());
		buf.append(html.closeDiv());
		buf.append(html.openDiv("row flexdisplay"));
		buf.append(html.openDiv("column basic"));
		buf.append(getLandingPage_copy(id, type, term));
		buf.append(html.closeDiv());
		buf.append(html.openDiv("column pretty"));
		buf.append(pretty);
		buf.append(html.closeDiv());
		buf.append(html.closeDiv());
		buf.append(html.closeContainer());
		buf.append(html.closeDocument());
		return buf.toString();		
	}
	public String getLandingPage_copy()
	{
		return getLandingPage_copy(NO_ID, "", "");
	}
	public String getLandingPage_copy(long id, String type, String term)
	{
		String goodId = (id > NO_ID) ? Long.toString(id) : "";
		
		String copy_1 = "The jimmarks API Resume demonstrates basic RestAPI service methods. This service is built with the spring framework in java using the JAX-RS 2.0 protocol. To demonstrate api calls, I have loaded my resume contents into a simple MySql data model. The api calls presented below return both JSON formatted results and HTML formatted results for easy reading. The JSON results represent a more traditional api solution because typical api transactions are machine-to-machine.";
		String copy_2 = "<b>Services:</b> listall, getall, getid, gettype, search";
		String copy_3 = "getall&emsp;<span class=\"font10\">click on the links below to return a complete set of resume details</span>";
		String copy_4 = "/restapi-json/getall";
		String copy_5 = "/restapi/getall";
		String copy_6 = "listall&emsp;<span class=\"font10\">click on the links below to return a complete set of resume summary rows</span>";
		String copy_7 = "/restapi-json/listall";
		String copy_8 = "/restapi/listall";
		String copy_9 = "getid/{id}&emsp;<span class=\"font10\">click on the links below to return a resume line by row id.  Enter your own row id before clicking.</span>";
		String copy_10 = "/restapi-json/getid/1";
		String copy_11 = "/restapi/getid/1";
		String copy_12 = "/restapi-json/getid/{id}";
		String copy_13 = "/restapi/getid/{id}";
		String copy_14 = "gettype/{type}&emsp;<span class=\"font10\">click on the links below to return resume lines by row type.  Enter your own row type before clicking.</span>";
		String copy_15 = "/restapi-json/gettype/education";
		String copy_16 = "/restapi/gettype/education";
		String copy_17 = "/restapi-json/gettype/{type}";
		String copy_18 = "/restapi/gettype/{type}";
		String copy_19 = "search/{term}&emsp;<span class=\"font10\">click on the links below to return resume lines by a full text search.  Enter your own search term before clicking.</span>";
		String copy_20 = "/restapi-json/search/java";
		String copy_21 = "/restapi/search/java";
		String copy_22 = "/restapi-json/search/{term}";
		String copy_23 = "/restapi/search/{term}";
		String copy_24 = "Advanced RestAPI Topics";
		String copy_25 = "The Restful API requests demonstrated on this page are all GET requests. This illustrates a basic API model. Some more advanced topics in Rest API solutions are not covered here for simplicity. In particular, POST requests and OAUTH2 authentication and security are not addressed. POST requests require preparation of a post body. A good tool for exploring post requests is Google Postman. Especially in an implementation of POST requests it is important to follow good security protocols. In such an environment HTTPS secure sockets provide encrypted communications to shield communications and OAUTH2 authentication protocols assure that only privileged POST requests will be accepted.";
		String copy_26 = "Source Code";
		String copy_27 = "The source code for this project may be found at: <a href=\"https://github.com/james-david-marks/apiresume\">https://github.com/james-david-marks/apiresume</a>";
		
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_normal\"><span>%s</span></DIV>",copy_1));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_normal\"><span>%s</span></DIV>",copy_2));
		buf.append("</DIV>");
		
		buf.append(getLandingPage_tableHeader());
		
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_6));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi-json/listall\" target=\"_blank\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_7));
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi/listall\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_8));
		buf.append("</DIV>");
		
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_3));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi-json/getall\" target=\"_blank\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_4));
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi/getall\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_5));
		buf.append("</DIV>");
		
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_9));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi-json/getid/1\" target=\"_blank\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_10));
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi/getid/1\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_11));
		buf.append("</DIV>");
		getLandingPage_tryIt(buf, "Row Id");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-4 basic_link\"><span><a href=\"javascript:doJsonLink('getid');\">%s</a></span></DIV>",copy_12));
		buf.append(String.format("<DIV class=\"col-lg-2 basic_input\"><input type=\"text\" onchange=\"javascript:doJsonLink('getid')\" id=\"idjson\" name=\"idjson\" size=\"10\" /></DIV>"));
		buf.append(String.format("<DIV class=\"col-lg-4 basic_link\"><span><a href=\"javascript:doHtmlLink('getid');\">%s</a></span></DIV>",copy_13));
		buf.append(String.format("<DIV class=\"col-lg-2 basic_input\"><input type=\"text\" onchange=\"javascript:doHtmlLink('getid');\" id=\"idhtml\" name=\"idhtml\" size=\"10\" value=\"%s\" /></DIV>", goodId));
		buf.append("</DIV>");
		
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_14));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi-json/gettype/education\" target=\"_blank\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_15));
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi/gettype/education\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_16));
		buf.append("</DIV>");
		getLandingPage_tryIt(buf, "Row Type");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-4 basic_link\"><span><a href=\"javascript:doJsonLink('gettype');\">%s</a></span></DIV>",copy_17));
		//buf.append(String.format("<DIV class=\"col-lg-2 basic_link\"><input type=\"text\" id=\"typejson\" name=\"typejson\" size=\"10\" /></DIV>"));
		buf.append(String.format("<DIV class=\"col-lg-2 basic_input\">%s</DIV>", getLandingPage_select("typejson", type)));
		buf.append(String.format("<DIV class=\"col-lg-4 basic_link\"><span><a href=\"javascript:doHtmlLink('gettype');\">%s</a></span></DIV>",copy_18));
//		buf.append(String.format("<DIV class=\"col-lg-2 basic_link\"><input type=\"text\" id=\"typehtml\" name=\"typehtml\" size=\"10\" value=\"%s\" /></DIV>", type));
		buf.append(String.format("<DIV class=\"col-lg-2 basic_input\">%s</DIV>", getLandingPage_select("typehtml", type)));
		buf.append("</DIV>");

		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_19));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi-json/search/java\" target=\"_blank\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_20));
		buf.append(String.format("<DIV class=\"col-lg-6 basic_link\"><span><a href=\"%srestapi/search/java\">%s</a></span></DIV>",SERVLET_CONTEXT,copy_21));
		buf.append("</DIV>");
		getLandingPage_tryIt(buf, "Search Term");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-4 basic_link\"><span><a href=\"javascript:doJsonLink('search');\">%s</a></span></DIV>",copy_22));
		buf.append(String.format("<DIV class=\"col-lg-2 basic_input\"><input type=\"text\" onchange=\"javascript:doJsonLink('search');\" id=\"termjson\" name=\"termjson\" size=\"10\" /></DIV>"));
		buf.append(String.format("<DIV class=\"col-lg-4 basic_link\"><span><a href=\"javascript:doHtmlLink('search');\">%s</a></span></DIV>",copy_23));
		buf.append(String.format("<DIV class=\"col-lg-2 basic_input\"><input type=\"text\" onchange=\"javascript:doHtmlLink('search');\" id=\"termhtml\" name=\"termhtml\" size=\"10\" value=\"%s\" /></DIV>", term));
		buf.append("</DIV>");
		
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_24));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_normal\"><span>%s</span></DIV>",copy_25));
		buf.append("</DIV>");
		
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_head\"><span>%s</span></DIV>",copy_26));
		buf.append("</DIV>");
		buf.append("<DIV class=\"row gutters\">");
		buf.append(String.format("<DIV class=\"col-lg-12 basic_normal\"><span>%s</span></DIV>",copy_27));
		buf.append("</DIV>");
		return buf.toString();
	}
	public String getLandingPage_tableHeader()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<DIV class=\"row border_bottom_darkgray\">");
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "JSON"));
		buf.append(String.format("<DIV class=\"col-lg-6\"><label >%s</label></DIV>", "HTML"));
		buf.append("</DIV>");
		return buf.toString();
	}
	private void getLandingPage_tryIt(StringBuffer buf, String label) {
		buf.append("<DIV class=\"row try-it\">");
		buf.append(String.format("<DIV class=\"col-lg-4 try-it\"></DIV>"));
		buf.append(String.format("<DIV class=\"col-lg-2 try-it\"><label>%s</label></DIV>", label));
		buf.append(String.format("<DIV class=\"col-lg-4 try-it\"></DIV>"));
		buf.append(String.format("<DIV class=\"col-lg-2 try-it\"><label>%s</label></DIV>", label));
		buf.append("</DIV>");
	}
	private String getLandingPage_select(String tagId, String selected)
	{
		String onchangeEvent = String.format("javascript:do%s%sLink('gettype');", tagId.substring(4,5).toUpperCase(), tagId.substring(5));
		StringBuffer buf = new StringBuffer();
		buf.append(String.format("<select id=\"%s\" name=\"%s\" size=\"1\" width=\"8px\" onchange=\"%s\">", tagId, tagId, onchangeEvent));
		buf.append(String.format("<option value=\"\"%s></option>", selected.toLowerCase().equals("") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("<option value=\"head\"%s>Head</option>", selected.toLowerCase().equals("head") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("<option value=\"subhead\"%s>Subhead</option>", selected.toLowerCase().equals("subhead") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("<option value=\"body\"%s>Body</option>", selected.toLowerCase().equals("body") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("<option value=\"experience\"%s>Experience</option>", selected.toLowerCase().equals("experience") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("<option value=\"education\"%s>Education</option>", selected.toLowerCase().equals("education") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("<option value=\"community\"%s>Community</option>", selected.toLowerCase().equals("community") && tagId.equals("typehtml") ? " selected" : ""));
		buf.append(String.format("</select>"));
		App.DEBUGLOGGER.info(selected + " : " + buf.toString());
		return buf.toString();
	}
	public String scripts()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<script type=\"text/javascript\">");
		buf.append("function doHtmlLink(method){");
		buf.append("var id = document.getElementById(\"idhtml\").value;");
		buf.append("var type = document.getElementById(\"typehtml\").value;");
		buf.append("var term = document.getElementById(\"termhtml\").value;");
		buf.append("var param = method == \"getid\" ? id.toString(0) : (method == \"gettype\" ? type : term);");
		buf.append(String.format("window.location = \"%srestapi/\"+method+\"/\"+param",SERVLET_CONTEXT));
		buf.append("}");
		buf.append("function doJsonLink(method){");
		buf.append("var id = document.getElementById(\"idjson\").value;");
		buf.append("var type = document.getElementById(\"typejson\").value;");
		buf.append("var term = document.getElementById(\"termjson\").value;");
		buf.append("var param = method == \"getid\" ? id.toString(0) : (method == \"gettype\" ? type : term);");
		buf.append(String.format("window.open(\"%srestapi-json/\"+method+\"/\"+param, \"_blank\");",SERVLET_CONTEXT));
		buf.append("}");
		buf.append("</script>");
		return buf.toString();
	}
	public String styleSheet()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\"/>");
		buf.append("<style type=\"text/css\">");
		buf.append("html {"); 
		buf.append("width: 100%;");
		buf.append("height: 100%");
		buf.append("}");
		buf.append("body {"); 
		buf.append("background-color:white;");
		buf.append("width: 100%;");
		buf.append("height: 100%");
		buf.append("}");
		buf.append("select {"); 
		buf.append("padding: 3px 0px 3px 0px;");
		buf.append("width: 100%;");
		buf.append("vertical-align: middle");
		buf.append("}");
		buf.append("input {"); 
		buf.append("width: 100%;");
		buf.append("}");
		buf.append(".flexdisplay {"); 
		buf.append("display: flex;");
		buf.append("}");
		buf.append(".gutters {"); 
		buf.append("padding: 0px 30px 0px 30px;");
		buf.append("}");
		buf.append(".font_head {"); 
		buf.append("font-family: \"Verdana\";");
		buf.append("font-size: 36;");
		buf.append("}");
		buf.append(".font_subhead {"); 
		buf.append("font-family: \"Verdana\";");
		buf.append("font-size: 28;");
		buf.append("color: darkgray;");
		buf.append("}");
		buf.append(".basic {"); 
		buf.append("font-family: \"Georgia\";");
		buf.append("}");
		buf.append(".basic_head {"); 
		buf.append("font-size: 16px;");
		buf.append("font-weight: bold;");
		buf.append("margin-top: 10px;");
		buf.append("margin-bottom: 5px;");
		buf.append("}");
		buf.append(".basic_normal {"); 
		buf.append("font-size: 14px;");
		buf.append("font-weight: normal;");
		buf.append("margin-top: 10px;");
		buf.append("margin-bottom: 5px;");
		buf.append("}");
		buf.append(".basic_input {"); 
		buf.append("font-size: 12px;");
		buf.append("font-weight: bold;");
		buf.append("}");
		buf.append(".basic_link {"); 
		buf.append("font-size: 12px;");
		buf.append("font-weight: bold;");
		buf.append("}");
		buf.append(".pretty {"); 
		buf.append("font-family: \"Georgia\";");
		buf.append("background-color:#e7f4f9;");
		buf.append("}");
		buf.append(".half {"); 
		buf.append("width: 50%;");
		buf.append("}");
		buf.append(".full {"); 
		buf.append("width: 100%;");
		buf.append("height: 100%;");
		buf.append("}");
		buf.append(".column {"); 
		buf.append("float: left;");
		buf.append("width: 50%;");
		buf.append("padding: 20px;");
		buf.append("}");
		buf.append(".column_blank {"); 
		buf.append("padding: 10px;");
		buf.append("height: 100px;");
		buf.append("background-color:#fff2e6;");
		buf.append("}");
		buf.append(".column_image {"); 
		buf.append("text-align: center;");
		buf.append("}");
		buf.append(".border_bottom {");
		buf.append("border-bottom: 2px solid black;");
		buf.append("margin-bottom: 4px;");
		buf.append("}");
		buf.append(".border_bottom_darkgray {");
		buf.append("color: darkgray;");
		buf.append("border-bottom: 2px solid darkgray;");
		buf.append("margin-bottom: 4px;");
		buf.append("text-align: center;");
		buf.append("}");
		buf.append(".bold_alert {");
		buf.append("text-align: center;");
		buf.append("padding-top: 100px;");
		buf.append("padding-left: 40px;");
		buf.append("font-size: 36;");
		buf.append("color: #d84c7e;");
		buf.append("}");
		buf.append(".try-it {");
		buf.append("padding: 0px 0px 0px 0px;");
		buf.append("font-size: 10;");		
		buf.append("color: darkgray;");
		buf.append("text-align: center;");
		buf.append("vertical-align: text-bottom;");
		buf.append("}");
		buf.append(".font10 {");
		buf.append("font-size: 10;");		
		buf.append("color: darkgray;");
		buf.append("}");
		buf.append("</style>");
		return buf.toString();
	}
}
