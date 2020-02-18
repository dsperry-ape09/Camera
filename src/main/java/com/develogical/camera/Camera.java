package com.develogical.camera;

public class Camera {


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
            memoryCard.write(data, () -> writeComplete());
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
        onStatus = false;
    }

    private void writeComplete() {
        writing = false;
        if (!onStatus) {
            sensor.powerDown();
        }
    }
}

