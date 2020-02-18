package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {
    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        // write your test here
        Sensor sensor = mock(Sensor.class);
        Camera camera = new Camera(sensor);
        camera.powerOn();
        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        // write your test here
        Sensor sensor = mock(Sensor.class);
        Camera camera = new Camera(sensor);
        camera.powerOff();
        verify(sensor).powerDown();
    }
}
