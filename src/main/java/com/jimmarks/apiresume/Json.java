/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.apiresume;

import com.google.gson.JsonObject;

public class Json
{
	private static final Gson2 _gson = new Gson2();

	public static JsonObject empty()
	{
		return new JsonObject();
	}
	public static JsonObject toJsonObject(Object obj)
	{
		return (JsonObject)_gson.toJsonTree(obj);
	}
	public static String serialize(Object obj)
	{
		return toJsonObject(obj).toString();
	}
}