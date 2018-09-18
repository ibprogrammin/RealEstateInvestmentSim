package com.seriousmonkey.realestateinvestmentsimulator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Button;

import com.seriousmonkey.realestateinvestmentsimulator.adapters.DetailItemAdapter;
import com.seriousmonkey.realestateinvestmentsimulator.MainActivity;
import com.seriousmonkey.realestateinvestmentsimulator.R;
import com.seriousmonkey.realestateinvestmentsimulator.assets.DetailItem;
import com.seriousmonkey.realestateinvestmentsimulator.assets.Preferences;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    public Button CalculateButton;

    private GridView mDetailsGridView;
    private DetailItemAdapter mDetailItemAdapter;

    private List<DetailItem> mDetailItems;

    public DetailsFragment() {
        mDetailItems = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        CalculateButton = (Button) view.findViewById(R.id.CalculateButton);
        mDetailsGridView = (GridView) view.findViewById(R.id.DetailsItemGrid);

        mDetailItemAdapter = new DetailItemAdapter(this.getContext(), mDetailItems);
        mDetailsGridView.setAdapter(mDetailItemAdapter);

        CalculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                try {
                    MainActivity root = (MainActivity) getActivity();
                    root.processCalculations();
                }
                catch(Exception e) {

                }
            }
        });

        RefreshView();

        return view;
    }

    public void RefreshView() {
        if (mPurchasePrice != 0.0 && mDownPayment != 0.0 && mIncome != 0.0 && mExpenses != 0.0 && mMortgagePayment != 0.0 && mCashflow != 0.0 && mROI != 0.0) {

            mDetailItems.clear();

            mDetailItems.add(new DetailItem("Purchase Price", String.format("$%,.2f", mPurchasePrice), "#24b955"));
            mDetailItems.add(new DetailItem("Down Payment", String.format("$%,.2f", mDownPayment), "#24b955"));
            mDetailItems.add(new DetailItem("Income", String.format("$%,.2f", mIncome), "#24b955"));
            mDetailItems.add(new DetailItem("Expenses", String.format("$%,.2f", mExpenses), "#b92455"));
            mDetailItems.add(new DetailItem("Mortgage Payment", String.format("$%,.2f", mMortgagePayment),"#b99124"));
            mDetailItems.add(new DetailItem(String.format("Cashflow ($%,.2f yr.)", mCashflow * Preferences.MONTHS_IN_YEAR), String.format("$%,.2f", mCashflow), getCashflowColor(mCashflow)));
            mDetailItems.add(new DetailItem("ROI", String.format("%.2f%%", mROI * 100), getROIColor(mROI * Preferences.NUMBER_TO_PERCENT)));
            mDetailItems.add(new DetailItem("CAP Rate", String.format("%.2f%%", mCAPRate * 100), getCAPRateColor(mCAPRate * 100)));

            loadDetailListItems();
        }
    }

    public static String getCashflowColor(double cashflow) {
        if (cashflow <= 0.0) return "#b92455";
        else return "#24b955";
    }

    public static String getROIColor(double ROI) {
        if (ROI <= 0.0) return "#b92455";
        else if (ROI <= 5.0) return "#b99124";
        else if (ROI <= 10.0) return "#b4b924";
        else if (ROI <= 15.0) return "#adb924";
        else if (ROI > 15.0) return "#24b955";
        else return "#aaaaaa";
    }

    public static String getCAPRateColor(double CAPRate) {
        if (CAPRate <= 0.0) return "#b92455";
        else if (CAPRate <= 2.5) return "#b99124";
        else if (CAPRate <= 5) return "#b4b924";
        else if (CAPRate <= 7.5) return "#adb924";
        else if (CAPRate > 7.5) return "#24b955";
        else return "#aaaaaa";
    }

    public void loadDetailListItems() {
        mDetailItemAdapter.notifyDataSetChanged();
    }

    private double mPurchasePrice;
    private double mDownPayment;
    private double mIncome;
    private double mExpenses;
    private double mMortgagePayment;
    private double mCashflow;
    private double mROI;
    private double mCAPRate;

    public void setPurchasePrice(double purchasePrice) {
        mPurchasePrice = purchasePrice;
    }

    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    public void setIncome(double income) {
        mIncome = income;
    }

    public void setExpenses(double expenses) {
        mExpenses = expenses;
    }

    public void setMortgagePayment(double mortgagePayment) {
        mMortgagePayment = mortgagePayment;
    }

    public void setCashflow(double cashflow) {
        mCashflow = cashflow;
    }

    public void setROI(double roi) {
        mROI = roi;
    }

    public void setCAPRate(double capRate) {
        mCAPRate = capRate;
    }

}
