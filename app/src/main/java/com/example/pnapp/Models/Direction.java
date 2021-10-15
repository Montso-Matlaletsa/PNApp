package com.example.pnapp.Models;

public class Direction {

    /** The order of this step */
    private Integer number;

    /** The instruction of this step */
    private String description;

    /**
     * Returns the order of this step
     *
     * @return		the order of this step
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the order of this step
     *
     * @param		number the order of this step
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Returns the instruction of this step
     *
     * @return		the instruction of this step
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the instruction of this step
     *
     * @param		description the instruction of this step
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
