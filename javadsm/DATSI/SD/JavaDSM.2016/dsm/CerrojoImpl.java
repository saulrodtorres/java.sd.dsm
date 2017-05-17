package dsm;
import java.rmi.*;
import java.rmi.server.*;

class CerrojoImpl extends UnicastRemoteObject implements Cerrojo {
    private int numeroEscritores;
    private int numeroLectores;
    CerrojoImpl() throws RemoteException {
	this.numeroLectores = 0;
	this.numeroEscritores = 0;
    }

    public synchronized void adquirir (boolean exc) throws RemoteException {
	boolean noreason = true;
	while(noreason){
	    try{
		if(exc && numeroLectores <= 0 && numeroEscritores <= 0){
		    this.numeroEscritores = numeroEscritores +1;
		    noreason = false;
		}
		else{
		    noreason = true;
		    wait();
		}

		if(numeroEscritores > 0){
		    noreason = true;
		    wait();
		}
		if(numeroLectores >= 0){
		    this.numeroLectores = numeroLectores + 1;
		    noreason = false;		  
		}
	    }//try
	    catch(InterruptedExcepction e){
		e.printStackTrace();
	    }
	}//while
    }
	
    public synchronized boolean liberar() throws RemoteException {
	boolean resultado = true;
	if (numeroEsritores == 0 && numeroLectores == 0){
	    //no hay nada que liberar
	    resultado = false;	   
	}
	else if (numeroLectores > 0 ){
	    this.numeroLectores--;
	    if (numeroLectores == 0){
		notifyAll();
		resultado = true;
	    }
	}
	else{
	    this.numeroEscritores--;
	    notifyAll();
	    resultado = true;
	}
	
        return resultado;
    }
}
