package dsm;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.HashMap;

public class FabricaCerrojosImpl extends UnicastRemoteObject implements FabricaCerrojos {
    static Cerrojo instancia = null;
    
    public FabricaCerrojosImpl() throws RemoteException {
	if (instancia == null){
	    instancia = new Cerrojo();
	}
	return instancia;
    }
    public synchronized	Cerrojo iniciar(String s) throws RemoteException {
	//no, ya no estoy seguro de si se trata de utilizar la fabrica como intermedio para crear varios tipos de cerrojo o como singleton
	return null;
    }
}

