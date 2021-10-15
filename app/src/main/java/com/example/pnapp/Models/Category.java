package com.example.pnapp.Models;

public class Category {

    /** Name of the Category */
    private String name;

    /** URL for the Category */
    private String url;

    /**
     * Returns the name of the category
     *
     * @return		name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category
     *
     * @param		name the name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the URL for the category
     *
     * @return		URL for the category
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for the category
     *
     * @param		url the URL for the category
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
