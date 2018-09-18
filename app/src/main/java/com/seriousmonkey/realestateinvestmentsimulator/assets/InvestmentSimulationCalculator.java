package com.seriousmonkey.realestateinvestmentsimulator.assets;

import java.util.List;

/**
 * Created by Daniel Sevitti on 2017-08-31.
 */

public class InvestmentSimulationCalculator {
    private double mPurchasePrice;
    private double mInsurance;
    private double mUtilities;
    private double mTaxes;
    private double mMortgageRate;
    private double mDownpaymentRate;
    private int mAmortization;
    private List<Room> mRooms;

    public InvestmentSimulationCalculator(double purchasePrice, double insurance, double utilities, double taxes, double mortgageRate, double downpaymentRate, int amortization) {
        mPurchasePrice = purchasePrice;
        mInsurance = insurance;
        mUtilities = utilities;
        mTaxes = taxes;
        mMortgageRate = mortgageRate;
        mDownpaymentRate = downpaymentRate;
        mAmortization = amortization;
    }

    public void setRooms(List<Room> rooms) {
        mRooms = rooms;
    }

    public double getDownPayment() {
        return calculateDownPayment(mPurchasePrice, mDownpaymentRate);
    }

    public double getExpenses() {
        return calculateExpenses(mInsurance, mTaxes, mUtilities);
    }

    public double getMortgagePayment() {
        return calculateMortgagePayment(mPurchasePrice, getDownPayment(), mAmortization, mMortgageRate);
    }

    public double getIncomeFromRooms() {
        return calculateIncomeFromRooms(mRooms);
    }

    public double getCashflow() {
        return calculateCashflow(getIncomeFromRooms(), getMortgagePayment(), getExpenses());
    }

    public double getAnnualCashflow() {
        return calculateAnnualCashflow(getCashflow());
    }

    public double getROI() {
        return calculateROI(getCashflow(), getDownPayment());
    }

    public double getCAPRate() {
        return calculateCAPRate(mPurchasePrice, getIncomeFromRooms());
    }

    public int getBreakEvenInMonths() {
        return calculateBreakEvenInMonths(getDownPayment(), getCashflow());
    }

    public static double calculateIncomeFromRooms(List<Room> rooms){
        double income = 0.0;
        for (Room item: rooms ) {
            income += item.getTotalIncomeFromRoom();
        }
        return income;
    }

    public static double calculatePotentialIncome(List<Room> rooms){
        double income = 0.0;
        for (Room item: rooms ) {
            income += item.getPotentialIncomeFromRoom();
        }
        return income;
    }

    public static double calculateMissingIncome(double monthlyIncome, double potentialMonthlyIncome){
        return potentialMonthlyIncome - monthlyIncome;
    }

    public static double calculatePotentialCashflow(double mortgagePayment, double expenses, double potentialIncome) {
        return potentialIncome - mortgagePayment - expenses;
    }

    public static double calculatePotentialROI(double potentialCashflow, double downpayment) {
        return calculateROI(potentialCashflow, downpayment);
    }

    public static double calculateAnnualCashflow(double cashflow) {
        return cashflow * (double) Preferences.MONTHS_IN_YEAR;
    }

    public static double calculateDownPayment(double purchasePrice, double downpaymentPercentage) {
        return purchasePrice * (downpaymentPercentage / (double) Preferences.NUMBER_TO_PERCENT);
    }

    public static double calculateCashflow(double monthlyIncome, double mortgagePayment, double expenses) {
        return monthlyIncome - mortgagePayment - expenses;
    }

    public static int calculateBreakEvenInMonths(double downpayment, double cashflow) {
        return (int) Math.ceil(downpayment / cashflow);
    }

    public static double calculateROI(double cashflow, double downpayment){
        return (cashflow * (double) Preferences.MONTHS_IN_YEAR) / downpayment;
    }

    public static double calculateCAPRate(double purchasePrice, double monthlyIncome) {
        return (monthlyIncome * (double) Preferences.MONTHS_IN_YEAR) / purchasePrice;
    }

    public static double calculateExpenses(double insurance, double taxes, double utilities) {
        return insurance + taxes + utilities;
    }

    public static double calculateAnnualMaintenanceFund(double purchasePrice, double maintenanceRate) {
        return purchasePrice * (maintenanceRate / (double) Preferences.NUMBER_TO_PERCENT);
    }

    public static double calculateMortgagePayment(double purchasePrice, double downpayment, int termInYears, double annualInterestRate) {
        double loanAmount = purchasePrice - downpayment;
        double monthlyInterest = (annualInterestRate / (double) Preferences.NUMBER_TO_PERCENT) / (double) Preferences.MONTHS_IN_YEAR;
        int termInMonths = termInYears * Preferences.MONTHS_IN_YEAR;
        double monthlyPayment = (loanAmount * monthlyInterest) / (1 - Math.pow(1 + monthlyInterest, -termInMonths));

        return monthlyPayment;
    }

    public static double calculateMaxRecommendedOffer(double purchasePrice, double income, double expenses, int termInYears, double annualInterestRate, double downpaymentRate, double targetROI ) {
        double ROI = 0.0;
        double currentOffer = purchasePrice;

        while (ROI < (targetROI / (double) Preferences.NUMBER_TO_PERCENT)) {
            double downpayment = calculateDownPayment(currentOffer, downpaymentRate);
            double mortgagePayment = calculateMortgagePayment(currentOffer, downpayment, termInYears, annualInterestRate);

            // Calculate ROI at the current offer
            ROI = calculateROI(calculateCashflow(income, mortgagePayment, expenses), downpayment);

            // Subtract 1 dollar from current offer
            currentOffer -= 1;
        }

        return currentOffer + 1;
    }

    public static double calculateNetPresentValue(double downPayment, double discountRate, double cashflow[]) {
        return calculatePresentValue(cashflow, discountRate, 1) - downPayment;
    }

    public static double calculatePresentValue(double cashflow[], double discountRate, int startTerm) {
        if (cashflow.length >= startTerm) return (cashflow[startTerm - 1] / Math.pow((1.0 + discountRate), startTerm)) + calculatePresentValue(cashflow, discountRate, startTerm + 1);
        else return 0;
    }

    public static double calculateInteralRateOfReturn(double downPayment, double cashflow[]) {
        double discountRate = 10; // An interest rate to start our calculation from
        double npv = calculatePresentValue(cashflow, discountRate, 1) - downPayment;

        if (npv > 0) {
            while (npv > 0.0) {
                npv = calculatePresentValue(cashflow, discountRate, 1) - downPayment;
                discountRate += 0.0001;
            }
        }
        else
        {
            while (npv < 0.0) {
                npv = calculatePresentValue(cashflow, discountRate, 1) - downPayment;
                discountRate -= 0.0001;
            }
        }

        return discountRate;
    }

}

