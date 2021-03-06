package com.example.pnapp.Models;

import java.util.List;

public class CompactRecipe {

    /** The name of the recipe */
    protected String name;

    /** URL of this recipe item on <a href="http://www.fatsecret.com">Fatsecret website</a> */
    protected String url;

    /** The unique recipe identifier */
    protected Long id;

    /** A short description of the recipe */
    protected String description;

    /** A list of URLs of all the images for this recipe on <a href="www.fatsecret.com">Fatsecret website</a> */
    protected List<String> images;

    /**
     * Returns the name of the recipe
     *
     * @return		the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the recipe
     *
     * @param		name the name of the recipe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the URL for the recipe
     *
     * @return		the URL for the recipe
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for the recipe
     *
     * @param		url the URL for the recipe
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the unique identifier of the recipe
     *
     * @return		the unique identifier of the recipe
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the recipe
     *
     * @param		id the unique identifier of the recipe
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the short description of the recipe
     *
     * @return		the short description of the recipe
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the short description of the recipe
     *
     * @param		description the short description of the recipe
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the list of URLs of all the images for the recipe
     *
     * @return		the list of URLs of all the images for the recipe
     */
    public List<String> getImages() {
        return images;
    }

    /**
     * Sets the list of URLs of all the images for the recipe
     *
     * @param		images the list of URLs of all the images for the recipe
     */
    public void setImages(List<String> images) {
        this.images = images;
    }
}
