package dsm;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


public class AlmacenImpl extends UnicastRemoteObject implements Almacen {
    //Almacen de Objetos Compartidos con este formato <id, objeto>;
    private Map<String, ObjetoCompartido> contenedor;
    //constructor
    public AlmacenImpl() throws RemoteException {
	contenedor = new HashMap<String,ObjetoCompartido>();
    }

    //solo se leeran si la version pasada es mayor que la del contenedor del Almacen
    public synchronized	List<ObjetoCompartido> leerObjetos(List<CabeceraObjetoCompartido> lcab)
	throws RemoteException {
	List<ObjetoCompartido> loc = new LinkedList<ObjetoCompartido>();
	loc = null;	
	Iterator<CabeceraObjetoCompartido> i = lcab.iterator();//iterador para el parametro
	if( ! contenedor.isEmpty() ){
	    while(i.hasNext()){
		CabeceraObjetoCompartido coc = i.next();
		if ( contenedor.containsKey(coc.getNombre()) ){
		    ObjetoCompartido oc = contenedor.get(coc.getNombre());
		    int versionO = coc.getVersion();
		    int versionC = oc.getCabecera().getVersion();
			if ( versionC > versionO  ){
			    loc.add(oc);
			}
		}	      
	    }//while		    
	}
	return loc;
    }
    //se añaden al contenedor del Almacen
    public synchronized void escribirObjetos(List<ObjetoCompartido> loc)
     throws RemoteException  {
	Iterator<ObjetoCompartido> i = loc.iterator();
	while (i.hasNext()){
	    ObjetoCompartido oc = i.next();
	    contenedor.put(oc.getCabecera().getNombre(), oc);
	}
	
    }
}

