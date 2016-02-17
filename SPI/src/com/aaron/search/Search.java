package com.aaron.search;

import java.util.List;

import javax.print.Doc;  

public interface Search {
	List<Doc> search(String keyword);  
}
