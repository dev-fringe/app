package dev.fringe.entity.support;

import lombok.Data;

@Data
public class Attachment {
	public Attachment(String filename, String pathfilename) {
		this.filename = filename;
		this.pathfilename = pathfilename;
	}
	private String filename;
	private String pathfilename;
}
