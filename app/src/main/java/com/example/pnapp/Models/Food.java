package com.example.pnapp.Models;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Serving;

import java.util.List;

public class Food  extends CompactFood{

    /** A list of all servings for this food */
    private List<com.fatsecret.platform.model.Serving> servings;

    /**
     * Returns the list of all servings for this food
     *
     * @return		the list of all servings for this food
     */
    public List<Serving> getServings() {
        return servings;
    }

    /**
     * Sets the list of all servings for this food
     *
     * @param		servings the list of all servings for this food
     */
    public void setServings(List<Serving> servings) {
        this.servings = servings;
    }
}
