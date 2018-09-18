package com.seriousmonkey.realestateinvestmentsimulator.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.seriousmonkey.realestateinvestmentsimulator.R;
import com.seriousmonkey.realestateinvestmentsimulator.assets.Room;

import java.util.List;

/**
 * Created by Daniel on 2017-09-01.
 */

public class RoomArrayAdapter extends BaseAdapter {

    private Context context;
    private List<Room> rooms;

    public RoomArrayAdapter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.room_entry_layout, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.ListTitle);
        TextView summary = (TextView) convertView.findViewById(R.id.ListDescription);

        name.setText(rooms.get(position).getRoomLabel());
        summary.setText(String.format("%o x $%,.2f = $%,.2f", rooms.get(position).getNumberOfRooms(), rooms.get(position).getAssumedRentalRate(), rooms.get(position).getTotalIncomeFromRoom()));

        return convertView;
    }

}
