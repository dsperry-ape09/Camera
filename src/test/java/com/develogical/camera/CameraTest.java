package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {

    Sensor sensor = mock(Sensor.class);
    MemoryCard memoryCard = mock(MemoryCard.class);
    Camera camera = new Camera(sensor, memoryCard);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        camera.powerOn();
        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        camera.powerOff();
        verify(sensor).powerDown();
    }

    @Test
    public void pressingTheShutterWithThePowerOnCopiesDataFromSensorToMemoryCard() {
        when(sensor.readData()).thenReturn(new byte[]{42});
        camera.powerOn();
        camera.pressShutter();
        verify(sensor).readData();
        verify(memoryCard).write(eq(new byte[]{42}), any(WriteCompleteListener.class));
    }

    @Test
    public void pressingTheShutterWhenIsPowerOffDoesNothing() {
        when(sensor.readData()).thenReturn(new byte[0]);
        camera.pressShutter();
        verifyZeroInteractions(sensor);
        verifyZeroInteractions(memoryCard);
    }

    @Test
    public void ifDataBeingWrittenDoNotSwitchOff() {
        when(sensor.readData()).thenReturn(new byte[0]);
        camera.powerOn();
        camera.pressShutter();
        camera.powerOff();
        verify(sensor, times(0)).powerDown();
    }
}
