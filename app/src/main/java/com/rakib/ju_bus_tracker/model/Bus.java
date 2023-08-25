package com.rakib.ju_bus_tracker.model;

public class Bus {
    private final String busNo;
    private final String regNo;
    private final String route;

    public Bus(String busNo, String regNo, String route) {
        this.busNo = busNo;
        this.regNo = regNo;
        this.route = route;
    }

    public String getBusNo() {
        return busNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getRoute() {
        return route;
    }
}
