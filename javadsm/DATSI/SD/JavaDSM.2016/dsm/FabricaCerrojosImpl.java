package dsm;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class FabricaCerrojosImpl extends UnicastRemoteObject implements FabricaCerrojos {

    public FabricaCerrojosImpl() throws RemoteException {
    }
    public synchronized	Cerrojo iniciar(String s) throws RemoteException {
	//habra que rellenar el metodo iniciar con una llamada al constructor.
	//el constructor tendra que guardar su instancia en algun lado, lo mas tipico es guardarla en MAP 
	return null;
    }
}

