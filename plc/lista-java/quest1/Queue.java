package quest1;
import java.util.ArrayList;
import java.util.Collections;

//conceitos de generics ??
public class Queue<T extends Person> {
    private ArrayList<T> fila;

    public Queue(){
        fila = new ArrayList<>(10);
    }
    public Queue(int tam){
        fila = new ArrayList<>(tam);
    }
    
    public void push(T coisa) {
        fila.add(coisa);
        Collections.sort(fila);
        return;
    }
    public T pop(){ 
        if(isEmpty()){
            return null;
        }
        return fila.remove(0);
    }
    public Boolean isEmpty(){
        return fila.isEmpty();
    }
}
