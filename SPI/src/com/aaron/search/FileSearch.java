package com.aaron.search;

import java.util.List;

import javax.print.Doc;

public class FileSearch implements Search{

    @Override
    public List<Doc> search(String keyword) {
        System.out.println("now use file system search. keyword:" + keyword);
        return null;
    }

}
