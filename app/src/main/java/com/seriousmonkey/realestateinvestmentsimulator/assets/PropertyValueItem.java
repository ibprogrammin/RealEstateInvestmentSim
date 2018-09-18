package com.seriousmonkey.realestateinvestmentsimulator.assets;

import java.util.ArrayList;

/**
 * Created by Daniel on 2017-09-06.
 */

public class PropertyValueItem {
    private int mYear;
    private double mCurrentValue;
    private double mIncreaseThisYear;
    private double mTotalIncrease;
    private double mYearlyPotentialEarnings;
    private double mROI;

    public PropertyValueItem(int year, double currentValue, double increaseThisYear, double totalIncrease, double yearlyPotentialEarnings, double roi) {
        mYear = year;
        mCurrentValue = currentValue;
        mIncreaseThisYear = increaseThisYear;
        mTotalIncrease = totalIncrease;
        mYearlyPotentialEarnings = yearlyPotentialEarnings;
        mROI = roi;
    }

    public double getYear() {
        return mYear;
    }

    public double getCurrentValue() {
        return mCurrentValue;
    }

    public double getIncreaseThisYear() {
        return mIncreaseThisYear;
    }

    public double getTotalIncrease() {
        return mTotalIncrease;
    }

    public double getYearlyPotentialEarnings() {
        return mYearlyPotentialEarnings;
    }

    public double getROI() {
        return mROI;
    }

    public static ArrayList<PropertyValueItem> getPropertyValueList(double purchasePrice, double downpayment, double[] cashflow, double annualProperyIncrease, int years) {
        ArrayList<PropertyValueItem> valueList = new ArrayList<>();

        double totalIncrease = 0.0;
        double currentValue = 0.0;

        for ( int year = 1; year <= years; year++){
            double increaseThisYear;
            double oldValue;

            if (year == 1) oldValue = purchasePrice;
            else oldValue = currentValue;

            currentValue = oldValue * (1 + annualProperyIncrease);
            increaseThisYear = currentValue - oldValue;

            double yearlyPotentialEarnings = increaseThisYear + (cashflow[year - 1]);
            double propertyRoi = increaseThisYear / downpayment;

            totalIncrease += increaseThisYear;

            valueList.add(new PropertyValueItem(year, currentValue, increaseThisYear, totalIncrease, yearlyPotentialEarnings, propertyRoi));
        }

        return valueList;
    }

}
