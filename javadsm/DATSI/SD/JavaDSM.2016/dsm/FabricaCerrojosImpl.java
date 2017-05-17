package dsm;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.HashMap;

public class FabricaCerrojosImpl extends UnicastRemoteObject implements FabricaCerrojos {
    static Map<String,Cerrojo> contenedorMap = new HashMap<>(); 
    static Cerrojo instancia = null;
    
    public FabricaCerrojosImpl() throws RemoteException {
	if (instancia == null){
	    instancia = new Cerrojo();
	}
	return instancia;
    }
    public synchronized	Cerrojo iniciar(String s) throws RemoteException {
	//creo que quiere que usemos la fabrica como singleton con contenedores
	   //Cerrojo myreturn = null;
	if (contenedorMap.contains(s)){
	    return contenedorMap.get();
	}
	else{
	    return FabricaCerrojosImpl();
	}
    }
}

