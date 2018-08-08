package dev.fringe.entity.support;

import java.io.IOException;
import java.io.Writer;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Compiler;
import com.samskivert.mustache.Template.Fragment;

public class Layout implements Mustache.Lambda {

	public String title = "Demo Application";

	String body;

	private Compiler compiler;

	public Layout(Compiler compiler) {
		this.compiler = compiler;
	}

	public void execute(Fragment frag, Writer out) throws IOException {
		body = frag.execute();
		compiler.compile("{{>layout}}").execute(frag.context(), out);
	}

}