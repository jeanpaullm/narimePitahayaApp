package com.example.jeanpaul.hylointerface;

public class SharedValues {

    private static final SharedValues ourInstance = new SharedValues();

    private static String tempPlantId;

    public static SharedValues getInstance() {
        return ourInstance;
    }

    private SharedValues() {
    }

    public static String getTempPlantId() {
        return tempPlantId;
    }

    public static void setTempPlantId(String tempPlantId) {
        SharedValues.tempPlantId = tempPlantId;
    }
}
