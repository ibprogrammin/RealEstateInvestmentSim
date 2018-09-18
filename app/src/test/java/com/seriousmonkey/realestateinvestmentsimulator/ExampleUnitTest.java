package com.seriousmonkey.realestateinvestmentsimulator;

import com.seriousmonkey.realestateinvestmentsimulator.assets.CashflowItem;
import com.seriousmonkey.realestateinvestmentsimulator.assets.InvestmentSimulationCalculator;
import com.seriousmonkey.realestateinvestmentsimulator.assets.PropertyValueItem;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void test_mortgagePayment() throws Exception {
        assertEquals(1523.75, InvestmentSimulationCalculator.calculateMortgagePayment(399900.0, 39990.0, 25, 1.99), 0.01);
    }

    @Test
    public void test_calculateMaxRecommendedOffer() throws Exception {
        assertEquals(364692.0, InvestmentSimulationCalculator.calculateMaxRecommendedOffer(399900.0, 2997.99, 696.67, 25, 1.99, 10.0, 30.0), 0.01);
    }

    @Test
    public void test_calculateDownpayment() throws Exception {
        assertEquals(39990.0, InvestmentSimulationCalculator.calculateDownPayment(399900.0, 10.0), 0.01);
    }

    @Test
    public void test_calculateNetPresentValue() throws Exception {
        double cashflow[] = {100000.0, 150000.0, 200000.0, 250000.0, 300000.0};
        assertEquals(472168.75, InvestmentSimulationCalculator.calculateNetPresentValue(250000.0, 0.1, cashflow),  0.01);
    }

    @Test
    public void test_calculateInteralRateOfReturn() throws Exception {
        double cashflow[] = {100000.0, 150000.0, 200000.0, 250000.0, 300000.0};
        assertEquals(0.57, InvestmentSimulationCalculator.calculateInteralRateOfReturn(250000.0, cashflow),  0.01);
    }

    @Test
    public void test_calculateNetPresentValueB() throws Exception {
        double cashflow[] = { 9330.97, 9870.61, 10418.34, 10974.29, 11538.58};
        assertEquals(9066.91, InvestmentSimulationCalculator.calculateNetPresentValue(39990.0, 0.0199, cashflow),  0.01);
    }

    @Test
    public void test_calculateInteralRateOfReturnB() throws Exception {
        double cashflow[] = { 9330.97, 9870.61, 10418.34, 10974.29, 11538.58 };
        assertEquals(0.0917, InvestmentSimulationCalculator.calculateInteralRateOfReturn(39990.0, cashflow),  0.01);
    }

    @Test
    public void test_getPropertyValueList() throws Exception {
        double cashflow[] = { 9330.97, 9870.61, 10418.34, 10974.29, 11538.58 };
        assertEquals(135256.41, PropertyValueItem.getPropertyValueList(399900.0, 39990.0, cashflow, 0.06, 5).get(4).getTotalIncrease(),  0.01);
        assertEquals(0.7146, PropertyValueItem.getPropertyValueList(399900.0, 39990.0, cashflow, 0.06, 5).get(3).getROI(),  0.0001);
    }

    @Test
    public void test_getCashflowItems() throws Exception {
        assertEquals(11538.57, CashflowItem.getCashflowItems(39990.0, 1523.74, 2997.99, 696.67, 0.015, 5).get(4).getYearlyCashflow(),  0.01);
    }

}