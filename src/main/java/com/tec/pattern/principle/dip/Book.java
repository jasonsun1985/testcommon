package com.tec.pattern.principle.dip;

public class Book implements IReader {
    @Override
    public String getContent() {
        return "很久很久以前……";
    }

}
