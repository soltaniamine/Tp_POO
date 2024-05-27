package com.example.back;

public class AppointmentRecord {
    private String date;
    private String observation;

    public AppointmentRecord(String date, String observation) {
        this.date = date;
        this.observation = observation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
