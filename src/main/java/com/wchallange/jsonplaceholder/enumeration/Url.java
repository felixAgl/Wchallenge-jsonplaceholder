package com.wchallange.jsonplaceholder.enumeration;


public enum Url {
    URL("https://jsonplaceholder.typicode.com"),
    USERS("/users"), PHOTOS("/photos");

    private final String url;

    public String getUrl() {
        return url;
    }

    Url(String url) {
        this.url = url;
    }
}
