package com.mzelzoghbi.zgallery;

import java.io.Serializable;

public class ZImage implements Serializable {
    private String url;
    private String caption;

    public ZImage() {}
    public ZImage(String url, String caption) {
        this.url = url;
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
