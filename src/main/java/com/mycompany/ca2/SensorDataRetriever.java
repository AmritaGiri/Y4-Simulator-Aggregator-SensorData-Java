// Importing necessary packages
import java.rmi.Remote; // Used to create a remote interface
import java.rmi.RemoteException; // Used to handle remote exceptions

// Remote interface for retrieving the latest averages of sensor data
public interface SensorDataRetriever extends Remote {
// Method to get the latest averages of sensor data

    String getLatestAverages() throws RemoteException;
}
// This interface extends the Remote interface, which indicates that this interface is intended to be used for remote communication.
// It declares a single method getLatestAverages() which is used to retrieve the latest averages of sensor data.
// The method signature includes the exception RemoteException, which is used to handle remote communication errors that might occur during method invocation.
