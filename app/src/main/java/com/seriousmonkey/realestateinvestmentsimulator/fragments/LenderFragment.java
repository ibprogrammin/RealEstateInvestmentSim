package com.seriousmonkey.realestateinvestmentsimulator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.seriousmonkey.realestateinvestmentsimulator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LenderFragment extends Fragment {

    private EditText mMortgageRateInput;
    private EditText mDownpaymentInput;
    private EditText mAmortizationInput;

    public LenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lender, container, false);

        mMortgageRateInput = (EditText) view.findViewById(R.id.MortgageRateInput);
        mDownpaymentInput = (EditText) view.findViewById(R.id.DownpaymentInput);
        mAmortizationInput = (EditText) view.findViewById(R.id.AmortizationInput);

        return view;
    }

    public double getMortgageRate() {
        return Double.parseDouble(mMortgageRateInput.getText().toString());
    }

    public double getDownpayment() {
        return Double.parseDouble(mDownpaymentInput.getText().toString());
    }

    public int getAmortization() {
        return Integer.parseInt(mAmortizationInput.getText().toString());
    }

}
