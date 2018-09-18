package com.seriousmonkey.realestateinvestmentsimulator.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seriousmonkey.realestateinvestmentsimulator.R;
import com.seriousmonkey.realestateinvestmentsimulator.assets.DetailItem;

import java.util.List;

/**
 * Created by Daniel on 2017-09-03.
 */

public class DetailItemAdapter extends BaseAdapter {

    private Context context;
    private List<DetailItem> detailItem;

    public DetailItemAdapter(Context context, List<DetailItem> detailItem) {
        this.context = context;
        this.detailItem = detailItem;
    }

    @Override
    public int getCount() {
        return detailItem.size();
    }

    @Override
    public Object getItem(int position) {
        return detailItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_details_layout, null);
        }

        LinearLayout detailItemLayout = (LinearLayout) convertView.findViewById(R.id.DetailItemLayout);
        TextView description = (TextView) convertView.findViewById(R.id.DescriptionTextView);
        TextView amount = (TextView) convertView.findViewById(R.id.AmountTextView);

        detailItemLayout.setBackgroundColor(Color.parseColor(detailItem.get(position).getBackgroundColor()));

        description.setText(detailItem.get(position).getDescription());
        amount.setText(detailItem.get(position).getAmount());

        amount.setTextColor(Color.parseColor(detailItem.get(position).getColor()));

        return convertView;
    }
}
