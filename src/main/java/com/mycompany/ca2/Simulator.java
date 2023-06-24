package com.mycompany.ca2;

/**
 * In this class, the main method generates random sensor data for the specified
 * number of sensors (SENSOR_COUNT) every 0.2 seconds. The random temperature
 * and pressure values are generated using the getRandomValue method, which
 * returns a random double value between the specified min and max values.
 */
/**
 * Class to simulate sensor data generation for a specified number of sensors.
 */
import java.util.Random;

public class Simulator {

// Constants for the simulation
    private static final Random RANDOM = new Random(); // Random object to generate random sensor data
    private static final int SENSOR_COUNT = 3; // Number of sensors to simulate
    private static final double TEMP_MIN = 0; // Minimum temperature value
    private static final double TEMP_MAX = 100; // Maximum temperature value
    private static final double PRESSURE_MIN = 900; // Minimum pressure value
    private static final double PRESSURE_MAX = 1100; // Maximum pressure value

// Main method to simulate sensor data generation
    public static void main(String[] args) {
        // Lambda expression to simulate sensor data generation every 0.2 seconds
        new Thread(() -> {
            while (true) {
                // Loop to generate random data for each sensor
                for (int i = 1; i <= SENSOR_COUNT; i++) {
                    String sensorId = "Sensor" + i;
                    double temperature = getRandomValue(TEMP_MIN, TEMP_MAX);
                    double pressure = getRandomValue(PRESSURE_MIN, PRESSURE_MAX);
                    System.out.printf("%s: Temperature=%.2f, Pressure=%.2f%n", sensorId, temperature, pressure);
                }

                try {
                    Thread.sleep(200); // Sleep for 200 ms (0.2 seconds) before generating data again
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }).start();
    }

// Private method to generate random sensor data
    private static double getRandomValue(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble(); // Returns a random double value between the specified min and max values
    }
}
