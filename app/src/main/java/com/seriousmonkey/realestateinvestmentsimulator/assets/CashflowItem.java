package com.seriousmonkey.realestateinvestmentsimulator.assets;

import java.util.ArrayList;

/**
 * Created by Daniel on 2017-09-07.
 */

public class CashflowItem {
    private int mYear;
    private double mCashflow;
    private double mROI;

    public CashflowItem(int year, double cashflow, double roi) {
        mYear = year;
        mCashflow = cashflow;
        mROI = roi;
    }

    public int getYear() { return mYear; }
    public double getCashflow() { return mCashflow; }
    public double getYearlyCashflow() { return mCashflow * Preferences.MONTHS_IN_YEAR; }
    public double getROI() { return mROI; }

    public static ArrayList<CashflowItem> getCashflowItems(double downpayment, double mortgagePayment, double income, double expenses, double annualRentIncrease, int years) {
        ArrayList<CashflowItem> cashflowItems = new ArrayList<>();

        // Cashflow = (Income * (1 + AnnualRentIncrease) * (1 + AnnualRentIncrease)) - MortgagePayment - Expenses
        for(int year = 1; year <= years; year++) {
            double currentCashflow;

            if(year == 1) {
                currentCashflow = income - mortgagePayment - expenses;
            }
            else {
                currentCashflow = income;
                for (int i = 1; i < years - (years - year); i++) {

                    currentCashflow *= (1 + annualRentIncrease);
                }
                currentCashflow = currentCashflow - mortgagePayment - expenses;
            }

            cashflowItems.add(new CashflowItem(year, currentCashflow, currentCashflow / downpayment ));

        }

        return cashflowItems;
    }
}
