package com.tec.method;


import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ServiceLoader;

public class TestClassLoader {
    public static void main(String[] args) throws IOException {
        // Array.class的完整路径
        String name = "java/sql/Array.class";
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(name);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url.toString());
        }
        ServiceLoader<Something> test8 = ServiceLoader.load(Something.class);
        for (Something t : test8) {
            System.out.println(t.startsWith("a"));
        }


    }
}
