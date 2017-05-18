package dsm;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class DSMCerrojo {
	
	private List<ObjetoCompartido> listaDeObjetos = new ArrayList<ObjetoCompartido> ();
	private String nombre;
	private Almacen almacen;

    public DSMCerrojo (String nom) throws RemoteException {
    	this.nombre = nom;
    	String servidor = System.getenv("SERVIDOR");
    	String puerto = System.getenv("PUERTO");
    	this.almacen = (Almacen) Naming.lookup("rmi://" + servidor + ":" + puerto + "/Almacen");
    	
    }

    public void asociar(ObjetoCompartido o) {
    	listaDeObjetos.add(o);
    }
    public void desasociar(ObjetoCompartido o) {
    }
    public boolean adquirir(boolean exc) throws RemoteException {
        return true;
    }
    
    public boolean liberar() throws RemoteException {
        return true;
   }
}
