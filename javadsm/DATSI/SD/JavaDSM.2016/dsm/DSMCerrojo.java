package dsm;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

//public class DSMCerrojo implements Cerrojo{

public class DSMCerrojo {
	
    private List<ObjetoCompartido> loc = new ArrayList<ObjetoCompartido> ();//lista de objetos compartidos

    private String   nombre;        //nombre del cerrojo
    private Almacen  almacen;       //almacen del cerrojo
    private Cerrojo  cerrojo;       //el DSM utiliza este cerrojo por debajo.
        
    
    public DSMCerrojo (String nom) throws RemoteException {
    	this.nombre = nom;
    	String servidor = System.getenv("SERVIDOR");
    	String puerto = System.getenv("PUERTO");
	
	FabricaCerrojos fabrica = null;
    	this.almacen = (Almacen) Naming.lookup("rmi://" + servidor + ":" + puerto + "/DSM_almacen");
	//DSM_almacen porque en el servidor:  Naming.rebind("rmi://localhost:" + args[0] + "/DSM_almacen", srv_alm);
	fabrica = (FabricaCerrojos) Naming.lookup("rmi://" + servidor + ":" + puerto + "/DSM_cerrojos");
	//DSM_cerrojos porque en el servidor: Naming.rebind("rmi://localhost:" + args[0] + "/DSM_cerrojos", srv_cerr);
	this.cerrojo = fabrica.iniciar(nom);
	
    }

    public void asociar(ObjetoCompartido o) {
    	listaDeObjetos.add(o);
    }
    public void desasociar(ObjetoCompartido o) {
	boolean encontrado = false;
	int i = 0;
	//o esta en loc?
	//vamos a buscar por el nombre al objeto que deberia estar en loc=listadeobjetoscompartidos
	while (i=0; !encontrado && i < loc.size();i++ ){
	    if (o.getCabecera().getNombre().equals( loc.get(i).getCabecera().getNombre() )){
		loc.remove(j);
		entontrado=true;
	    }
	}
    }
    public boolean adquirir(boolean exc) throws RemoteException {
	
	this.cerrojo.adquirir(exc);
	List<CabeceraObjetoCompartido> lcoc = new ArrayList<CabeceraObjetoCompartido>();
	List<ObjetoCompartido> loca = new ArrayList<ObjetoCompartido>();//loc actualizado.
	int i;
	boolean adquirir=true;
	for(i= 0; i < this.loc.size()){
	    lcoc.add( this.loc.get(i).getCabecera() );	    
	}
	if ( lcoc.isEmpty() ){
	    return false;
	}
	else{
	    //tengo que meter a loca los objetos actualizados de loc
	    loca= almacen.leerObjetos(lcoc); //leerObjetos(): Solo se leeran si la version pasada es mayor que la del contenedor del Almacen
	}
	//este while va a dar problemas
	while(i= 0;!adquirir && loca!=null && i<loca.size();i++){
	    ObjetoCompartido oc = loca.get(i).getCabecera().getNombre();//loca.get(i)
	    if (oc==null){
		adquirir = false;
	    }
	    else{
		if ( oc.setObjecto( loca.get(i).getObjeto() ){
			oc.setVersion( loca.get(i).getCabecera().getVersion() );//otra vez loca.get(i)
		    }
	        else{
			adquirir = false;
		    }
	    }
	    
	}//supuesto while
	return adquirir;
    }
    
    public boolean liberar() throws RemoteException {
	return this.cerrojo.liberar();
   }
}
