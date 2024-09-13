package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IO.createTXT();
        String url = "https://stackoverflow.com/";
        Crawler.crawl(1, url, new ArrayList<String>());
    }
}