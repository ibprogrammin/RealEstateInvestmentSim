package com.seriousmonkey.realestateinvestmentsimulator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.seriousmonkey.realestateinvestmentsimulator.MainActivity;
import com.seriousmonkey.realestateinvestmentsimulator.R;
import com.seriousmonkey.realestateinvestmentsimulator.adapters.RoomArrayAdapter;
import com.seriousmonkey.realestateinvestmentsimulator.assets.Room;

import java.util.List;
import java.util.ArrayList;


public class RoomFragment extends Fragment {

    private Spinner mRoomSpinner;
    private EditText mRentalRateText;
    private EditText mNumberUnitsText;
    private Button mAddRoomButton;
    private ListView mRoomListView;

    private List<Room> mRooms;
    private RoomArrayAdapter mRoomArrayAdapter;

    public RoomFragment() {
        mRooms = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        mRoomSpinner = (Spinner) view.findViewById(R.id.RoomSpinner);
        mRentalRateText = (EditText) view.findViewById(R.id.RentalRateText);
        mNumberUnitsText = (EditText) view.findViewById(R.id.NumberUnitsText);
        mAddRoomButton = (Button) view.findViewById(R.id.AddRoomButton);
        mRoomListView = (ListView) view.findViewById(R.id.RoomListView);

        mRoomArrayAdapter = new RoomArrayAdapter(this.getContext(), mRooms);
        mRoomListView.setAdapter(mRoomArrayAdapter);

        mAddRoomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                addRoomToList();

                MainActivity root = (MainActivity) getActivity();
                root.hideSoftKeyboard();

                mNumberUnitsText.setText("");
            }
        });

        mRoomSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                mRentalRateText.setText(String.format("%.0f", Room.AverageRentalRates[Room.getRoomIndex(mRoomSpinner.getSelectedItem().toString())]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        mRoomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removeRoomFromList(position);
                }
        });

        loadRoomSpinnerItems();

        return view;
    }

    public void addRoomToList() {
        try {
            String room = mRoomSpinner.getSelectedItem().toString();
            double rentalRate = Double.parseDouble(mRentalRateText.getText().toString());
            int numberUnits = Integer.parseInt(mNumberUnitsText.getText().toString());
            int roomIndex = Room.getRoomIndex(room);
            double averageRentalRate = Room.AverageRentalRates[roomIndex];

            if ( room != null && rentalRate != 0.0 && numberUnits != 0 &&  averageRentalRate != 0.0) {
                mRooms.add(new Room(room, averageRentalRate, rentalRate, numberUnits));
            }
        }
        catch (Exception e) {

        }

        loadRoomListItems();
    }

    public void removeRoomFromList(int position) {
        mRooms.remove(position);
        loadRoomListItems();
    }

    public void loadRoomSpinnerItems() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.room_spinner_style, Room.RoomNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(dataAdapter);
    }

    public void loadRoomListItems() {
        mRoomArrayAdapter.notifyDataSetChanged();
    }

    public List<Room> getRooms() {
        return mRooms;
    }

}
