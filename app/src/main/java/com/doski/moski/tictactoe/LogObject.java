package com.doski.moski.tictactoe;

/**
 * Created by SamerGigaByte on 4/8/2017.
 */

class LogObject {

    public String ExecutionTime;
    public String Throughput;
    public String MethodName;
    public String PowerConsumption="0";

    public LogObject(String name, String param) {
        this.ExecutionTime = param;
        this.MethodName = name;
    }
}
