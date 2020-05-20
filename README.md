# Accelerometer:
This app explain how to use accelerometer sensor

# Common Uses:
Motion detection (shake, tilt, etc.).

# Description:
Measures the acceleration force in m/s2 that is applied to a device on 
all three physical axes (x, y, and z), including the force of gravity.

# Use the accelerometer:
An acceleration sensor measures the acceleration applied to the device, 
including the force of gravity. The following code shows you how to get an instance of the default acceleration sensor:

# This is the code:
private SensorManager sensorManager;
private Sensor sensor;

sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

# Implementation: 
"implements SensorEventListener" in your Class 
when we implement SensorEventListener in our class, then automatically created two methods 
1.  public void onSensorChanged(SensorEvent event){}
2.  public void onAccuracyChanged(Sensor sensor, int accuracy) {}
