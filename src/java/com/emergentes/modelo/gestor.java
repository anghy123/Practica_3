
package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class gestor {
    private ArrayList<productos> lista;
    
    public gestor()
    {
        lista = new ArrayList<productos>();
    }

    public ArrayList<productos> getLista() {
        return lista;
    }

    public void setLista(ArrayList<productos> lista) {
        this.lista = lista;
    }
    
     public void insertarP(productos item){
        lista.add(item);
    }
    public void modificarP(int pos,productos item){
        lista.set(pos, item);
    }
    public void eliminarP(int pos){
        lista.remove(pos);
    }
    public int obtieneId(){
        int idaux = 0;
        
        for (productos item : lista){
            idaux = item.getId();
        }
        return idaux +1 ;
    }
    public int ubicarP(int id){
        int pos = -1;
        Iterator<productos> it = lista.iterator();
        
        while (it.hasNext()){
            ++pos;
            productos aux = it.next();
            
            if(aux.getId() == id){
                break;
            }
        }
        return pos; 
    }
}