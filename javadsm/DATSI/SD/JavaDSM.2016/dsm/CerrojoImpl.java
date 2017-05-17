package dsm;
import java.rmi.*;
import java.rmi.server.*;

class CerrojoImpl extends UnicastRemoteObject implements Cerrojo {
    private int numeroEscritores;
    private int numeroLectores;
    CerrojoImpl() throws RemoteException {
	this.numeroEscritores = 0;
	this.numeroEscritores = 0;
    }

    public synchronized void adquirir (boolean exc) throws RemoteException {
    }
    public synchronized boolean liberar() throws RemoteException {
        return true;
    }
}
