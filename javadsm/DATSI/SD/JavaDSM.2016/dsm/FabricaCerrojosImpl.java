package dsm;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class FabricaCerrojosImpl extends UnicastRemoteObject implements FabricaCerrojos {
    protected FabricaCerrojosImpl() throws RemoteException {
		super();
		getInstance();
    }
	static Map<String,Cerrojo> contenedorMap = new HashMap<>(); 
    static Cerrojo instancia = null;
    
    public static Cerrojo getInstance() throws RemoteException {
	if (instancia == null){
	    instancia = new CerrojoImpl();
	}
	return instancia;
    }
    public synchronized	Cerrojo iniciar(String s) throws RemoteException {
	//creo que quiere que usemos la fabrica como singleton con contenedores
	   //Cerrojo myreturn = null;
	if (contenedorMap.containsKey(s)){
	    return contenedorMap.get(s);
	}
	else{
	    return getInstance();
	}
    }
}

