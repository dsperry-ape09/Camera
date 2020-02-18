package com.develogical.camera;

public class Camera implements WriteCompleteListener {


    private Sensor sensor;
    private MemoryCard memoryCard;
    private boolean onStatus;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        if (onStatus) {
            byte[] data = sensor.readData();
            memoryCard.write(data, this);
        }
    }

    public void powerOn() {
        onStatus = true;
        sensor.powerUp();
    }

    public void powerOff() {
        sensor.powerDown();
    }

    @Override
    public void writeComplete() {

    }
}

