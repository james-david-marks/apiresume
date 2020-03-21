/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.model;

import java.util.ArrayList;

public class ResumeDto {
	public static class ResumeLineDto{
		public long id;
		public String type;
		public String header;
		public String body;
	}
	public static class ComponentDto{
		public ArrayList<ResumeLineDto> ResumeLines = new ArrayList<ResumeLineDto>();
	}
	public static void map(ResumeLines lines, ArrayList<ResumeLineDto> dtos)
	{
		for(ResumeLine line : lines)
		{
			ResumeLineDto dto = new ResumeLineDto();
			dto.id = line.id;
			dto.type = line.type;
			dto.header = line.header;
			dto.body = line.body;
			dtos.add(dto);
		}
	}
}
