package quest1;

//conceitos de generics ??
public class Queue<T> {
    private T pilha[];
    private int tam;
    private int last;

    public Queue(){
        last = 0;
        this.tam = 10;
    }
    public Queue(int tam){
        last = 0;
        this.tam = tam;
    }
    
    public void push(T coisa) {
        if(last == tam) {
            System.out.println("tamanho maximo da fila");
            return;
        }
        //colocar as pessoas mais velhas antes das mais novas
        pilha[last] = coisa;
        last++;
        return;
    }
    public T pop(){ 
        T coisa = pilha[last];
        last--;
        return coisa;
    }
    public Boolean isEmpty(){
        return (last == 0);
    }
}
