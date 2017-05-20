package dsm;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

//public class DSMCerrojo implements Cerrojo{

public class DSMCerrojo {
	
    private List<ObjetoCompartido> loc = new ArrayList<ObjetoCompartido> ();//lista de objetos compartidos

    private String   nombre;        //nombre del cerrojo
    private Almacen  almacen;       //almacen del cerrojo

        
    
    public DSMCerrojo (String nom) throws RemoteException {
    	this.nombre = nom;
    	String servidor = System.getenv("SERVIDOR");
    	String puerto = System.getenv("PUERTO");
	Cerrojo  cerrojo;       //el DSM utiliza este cerrojo por debajo.
	FabricaCerrojos fabrica = null;
    	this.almacen = (Almacen) Naming.lookup("rmi://" + servidor + ":" + puerto + "/DSM_almacen");
	//DSM_almacen porque en el servidor:  Naming.rebind("rmi://localhost:" + args[0] + "/DSM_almacen", srv_alm);
	fabrica = (FabricaCerrojos) Naming.lookup("rmi://" + servidor + ":" + puerto + "/DSM_cerrojos");
	//DSM_cerrojos porque en el servidor: Naming.rebind("rmi://localhost:" + args[0] + "/DSM_cerrojos", srv_cerr);
	cerrojo = fabrica.iniciar(nom);
	
    }

    public void asociar(ObjetoCompartido o) {
    	listaDeObjetos.add(o);
    }
    public void desasociar(ObjetoCompartido o) {
	boolean encontrado = false;
	int i = 0;
	while ( !encontrado && i < loc.size(),i++ ){
	    if (o.get(i).getCabecera().getNombre().equals( loc.get(i).getCabecera().getNombre() )){
		loc.remove(j);
		entontrado=true;
	    }
	}
    }
    public boolean adquirir(boolean exc) throws RemoteException {
        return true;
    }
    
    public boolean liberar() throws RemoteException {
        return true;
   }
}
