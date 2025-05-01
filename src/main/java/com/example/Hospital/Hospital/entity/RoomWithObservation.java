package com.example.Hospital.Hospital.entity;

import com.example.Hospital.Hospital.entity.Room;

public class RoomWithObservation {
    private Room room;
    private String lastObservation;
    
    public RoomWithObservation() {
        super();
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public String getLastObservation() {
        return lastObservation;
    }
    
    public void setLastObservation(String lastObservation) {
        this.lastObservation = lastObservation;
    }
}