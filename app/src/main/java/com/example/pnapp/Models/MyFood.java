package com.example.pnapp.Models;

import java.io.Serializable;

public class MyFood implements Serializable {

    /** Name of the food, not including the brand name */
    protected String name;

    /** URL of this food item on <a href="http://www.fatsecret.com">Fatsecret website</a> */
    protected String url;

    /** Type of the food - indicates whether the food is a brand or generic item */
    protected String type;

    /** The unique food identifier */
    protected Long id;

    /** A short description of the food */
    protected String description;

    /** The brand name, only when food_type is "Brand" */
    protected String brandName;

    /**
     * Returns the name of the food
     *
     * @return		the name of the food
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food
     *
     * @param		name the name of the food
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the URL for the food
     *
     * @return		the URL for the food
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for the food
     *
     * @param		url the URL for the food
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the type of the food
     *
     * @return		the type of the food
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the food
     *
     * @param		type the type of the food
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the unique food identifier
     *
     * @return		the unique food identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique food identifier
     *
     * @param		id the unique food identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the short description of the food
     *
     * @return		the short description of the food
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the short description of the food
     *
     * @param		description the short description of the food
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the brand name of the food
     *
     * @return		the brand name of the food
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the brand name of the food
     *
     * @param		brandName the brand name of the food
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return name;
    }
}
