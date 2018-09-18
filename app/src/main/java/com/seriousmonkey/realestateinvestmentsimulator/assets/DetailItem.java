package com.seriousmonkey.realestateinvestmentsimulator.assets;

/**
 * Created by Daniel on 2017-09-03.
 */

public class DetailItem {
    private String mDescription;
    private String mAmount;
    private String mColor;
    private String mBackgroundColor;

    public DetailItem(String description, String amount) {
        this(description, amount, "#333333");
    }

    public DetailItem(String description, String amount, String color) {
        this(description, amount, color, "#9fffffff");
    }

    public DetailItem(String description, String amount, String color, String backgroundColor) {
        mDescription = description;
        mAmount = amount;
        mColor = color;
        mBackgroundColor = backgroundColor;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAmount() {
        return mAmount;
    }

    public String getColor() {
        return mColor;
    }

    public String getBackgroundColor() {
        return mBackgroundColor;
    }

}
