package com.develogical.camera;

public class Camera implements WriteCompleteListener {


    private Sensor sensor;
    private MemoryCard memoryCard;
    private boolean onStatus;
    private boolean writing;


    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        if (onStatus) {
            byte[] data = sensor.readData();
            writing = true;
            memoryCard.write(data, this);
        }
    }

    public void powerOn() {
        onStatus = true;
        sensor.powerUp();
    }

    public void powerOff() {
        if (!writing) {
            sensor.powerDown();
        }
    }

    @Override
    public void writeComplete() {
        writing = false;
    }
}

