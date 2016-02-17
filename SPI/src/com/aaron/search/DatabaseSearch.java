package com.aaron.search;

import java.util.List;

import javax.print.Doc;

public class DatabaseSearch implements Search {

    @Override
    public List<Doc> search(String keyword) {
        System.out.println("now use database search. keyword:" + keyword);
        return null;
    }

}

