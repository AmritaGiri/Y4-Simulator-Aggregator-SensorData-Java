// Importing necessary packages

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;

// Class to aggregate data from various sensors and calculate the average
public class Aggregator extends UnicastRemoteObject implements SensorDataListener, SensorDataRetriever {

    private static final int TEMP_WINDOW = 50; // Constant for size of temperature window
    private static final int PRESSURE_WINDOW = 100; // Constant for size of pressure window
    private final Map<String, LinkedList<Double>> tempData; // Map to store temperature data for each sensor
    private final Map<String, LinkedList<Double>> pressureData; // Map to store pressure data for each sensor

// Constructor to initialize the Maps
    public Aggregator() throws RemoteException {
        tempData = new HashMap<>();
        pressureData = new HashMap<>();
    }

// Method to update the data received from a sensor
    @Override
    public void updateSensorData(String sensorId, double temperature, double pressure) {
        // Calls updateData() method for temperature and pressure data separately
        updateData(tempData, sensorId, temperature, TEMP_WINDOW);
        updateData(pressureData, sensorId, pressure, PRESSURE_WINDOW);
    }

// Private method to update the data in a Map for a particular sensor
    private void updateData(Map<String, LinkedList<Double>> dataMap, String sensorId, double value, int windowSize) {
        // Adds an empty LinkedList for a new sensor to the Map
        dataMap.putIfAbsent(sensorId, new LinkedList<>());
        LinkedList<Double> dataList = dataMap.get(sensorId);
        // Adds new data received from the sensor to the end of the LinkedList
        dataList.addLast(value);
        // If the LinkedList exceeds the window size, removes the oldest data from the front of the LinkedList
        if (dataList.size() > windowSize) {
            dataList.removeFirst();
        }
    }

// Method to get the latest averages of temperature and pressure data
    @Override
    public String getLatestAverages() {
        StringBuilder result = new StringBuilder();
        result.append("Temperature Averages:\n");
        // Calls appendAverages() method for temperature and pressure data separately
        appendAverages(result, tempData, TEMP_WINDOW);
        result.append("Pressure Averages:\n");
        appendAverages(result, pressureData, PRESSURE_WINDOW);
        return result.toString();
    }

// Private method to calculate the average of the data for each sensor and append it to the StringBuilder
    private void appendAverages(StringBuilder result, Map<String, LinkedList<Double>> dataMap, int windowSize) {
        double totalAverage = 0; // Variable to store the sum of averages of all sensors
        int count = 0; // Variable to store the count of data points from all sensors
        for (Map.Entry<String, LinkedList<Double>> entry : dataMap.entrySet()) {
            String sensorId = entry.getKey();
            LinkedList<Double> dataList = entry.getValue();
            double sum = dataList.stream().mapToDouble(Double::doubleValue).sum();
            double average = sum / dataList.size(); // Calculates the average of the data for the current sensor
            totalAverage += sum;
            count += dataList.size();
            result.append(sensorId).append(": ").append(average).append("\n"); // Appends the average of the current sensor to the StringBuilder
        }
        if (count > 0) {
            result.append("Total Average: ").append(totalAverage / count).append("\n"); // Appends the total average to the StringBuilder
        }
    }
}
