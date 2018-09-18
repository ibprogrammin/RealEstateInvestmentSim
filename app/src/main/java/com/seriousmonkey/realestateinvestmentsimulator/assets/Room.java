package com.seriousmonkey.realestateinvestmentsimulator.assets;

public class Room {
    public static String RoomNames[] = {"Bachelor", "1 Bedroom", "2 Bedroom", "3 Bedroom", "Commercial Unit"};
    public static double AverageRentalRates[] = {820.0, 990.0, 1236.0, 1500.0, 1950.0};

    private String mRoomLabel;
    private double mAverageRentalRate;
    private double mAssumedRentalRate;
    private int mNumberOfRooms;

    public Room(String roomLabel, double averageRentalRate, double assumedRentalRate, int numberOfRooms) {
        mRoomLabel = roomLabel;
        mAverageRentalRate = averageRentalRate;
        mAssumedRentalRate = assumedRentalRate;
        mNumberOfRooms = numberOfRooms;
    }

    public double getTotalIncomeFromRoom(){
        return mAssumedRentalRate * (double) mNumberOfRooms;
    }

    public double getPotentialIncomeFromRoom() {
        return mAverageRentalRate * (double) mNumberOfRooms;
    }

    public String getRoomLabel() {
        return mRoomLabel;
    }

    public double getAverageRentalRate() {
        return mAverageRentalRate;
    }

    public double getAssumedRentalRate() {
        return mAssumedRentalRate;
    }

    public int getNumberOfRooms() {
        return mNumberOfRooms;
    }

    public void setRoomLabel(String roomLabel) {
        mRoomLabel = roomLabel;
    }

    public void setAverageRentalRate(double rentalRate) {
        mAverageRentalRate = rentalRate;
    }

    public void setAssumedRentalRate(double assumedRentalRate) {
        mAssumedRentalRate = assumedRentalRate;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        mNumberOfRooms = numberOfRooms;
    }

    public static int getRoomIndex(String roomName) {
        int index = -1;

        for (int i = 0; (i < RoomNames.length) && (index == -1); i++) {
            if (RoomNames[i] == roomName) {
                index = i;
            }
        }

        return index;
    }

}