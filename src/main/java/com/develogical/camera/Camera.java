package com.develogical.camera;

public class Camera implements WriteCompleteListener {


    private Sensor sensor;
    private MemoryCard memoryCard;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        // not implemented
        byte[] data = sensor.readData();
        memoryCard.write(data, this);
    }

    public void powerOn() {
        sensor.powerUp();
    }

    public void powerOff() {
        sensor.powerDown();
    }

    @Override
    public void writeComplete() {

    }
}

