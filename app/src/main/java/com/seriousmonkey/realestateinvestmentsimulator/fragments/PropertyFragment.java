package com.seriousmonkey.realestateinvestmentsimulator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.seriousmonkey.realestateinvestmentsimulator.R;

public class PropertyFragment extends Fragment {

    private EditText mPurchasePriceInput;
    private EditText mInsuranceInput;
    private EditText mUtilitiesInput;
    private EditText mTaxesInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property, container, false);

        mPurchasePriceInput = (EditText) view.findViewById(R.id.PurchasePriceInput);
        mInsuranceInput = (EditText) view.findViewById(R.id.InsuranceInput);
        mUtilitiesInput = (EditText) view.findViewById(R.id.UtilitiesInput);
        mTaxesInput = (EditText) view.findViewById(R.id.TaxesInput);

        return view;
    }

    public double getPurchasePrice() {
        return Double.parseDouble(mPurchasePriceInput.getText().toString());
    }

    public double getInsurance() {
        return Double.parseDouble(mInsuranceInput.getText().toString());
    }

    public double getUtilities() {
        return Double.parseDouble(mUtilitiesInput.getText().toString());
    }

    public double getTaxes() {
        return Double.parseDouble(mTaxesInput.getText().toString());
    }
}
