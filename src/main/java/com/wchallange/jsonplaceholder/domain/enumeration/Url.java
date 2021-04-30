package com.wchallange.jsonplaceholder.domain.enumeration;


public enum Url {
    API("https://jsonplaceholder.typicode.com"),
    USERS("/users"), PHOTOS("/photos"), ALBUMS("/albums");

    private final String url;

    public String getUrl() {
        return url;
    }

    Url(String url) {
        this.url = url;
    }
}
