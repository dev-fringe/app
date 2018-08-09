package dev.fringe.entity.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import lombok.Data;

@Data
public abstract class Page {
	private int offset;
	private int size = 50;
	private int page = 1;
	private int num;
	private int from;
	private Long rowcount;
	private boolean firstPage;
	private boolean lastPage;
	private String previousUrl;
	private String nextUrl;
	private List<Url> urls;
	
	public void setRowcount(Long rowcount) {
		this.rowcount = rowcount;
		this.from = (page -1) * size;
		if(page == 1) {
			this.firstPage=true;
		}else {
			this.firstPage=false;
		}
		if(page == rowcount/size || 0 == rowcount/size) {
			this.lastPage=true;
		}else {
			this.lastPage=false;
		}
		this.setUrls();
	}
	public void setUrls() {
		String path = (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		this.setUrls(path);
	}

	public void setUrls(String path) {
		this.previousUrl = path + "?page=" + (page -1);
		this.nextUrl = path + "?page=" + (page + 1);
		List<Url> urls = new ArrayList<>();
		for (int i = 0; i < rowcount/size ; i++) {
			Url p= new Url();
			int temppage = i + 1;
			p.setUrl(path + "?page=" + temppage);
			p.setNum(temppage);
			urls.add(p);
		}
		this.urls = urls;
	}
}
