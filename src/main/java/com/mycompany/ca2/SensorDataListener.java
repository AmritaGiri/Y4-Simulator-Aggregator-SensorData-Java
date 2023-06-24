import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface for receiving data from sensors
public interface SensorDataListener extends Remote {
// Method to update the data received from a sensor
void updateSensorData(String sensorId, double temperature, double pressure) throws RemoteException;
}
// This interface extends the Remote interface, which indicates that this interface is intended to be used for remote communication.
// It declares a single method updateSensorData() which is used to update the data received from a sensor.
// The method signature includes the parameter types and the exception RemoteException, which is used to handle remote communication errors that might occur during method invocation.
