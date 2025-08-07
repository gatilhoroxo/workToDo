package quest2;
import java.util.ArrayList;

class Produto {
    private final String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
}


// sistema para ter Hamburguer Batata e Refri (e o Bonus do VIP)
public class Menu {
    private ArrayList<Produto> cardapio;

    public Menu(){}
    public Menu(Produto[] coisas){
        for(Produto i : coisas)
            cardapio.add(i);
    }
    public void printCardapio(){
        System.out.print("----: menu :----\n");
        for(int i=0; i<cardapio.size(); i++){
            Produto p = cardapio.get(i);
            System.out.println(p.getNome() + ": R$ " + p.getPreco());
        }
        System.out.print("----------------\n");
    }

    public Double procuraItem(String nome){
        for(int i=0; i < cardapio.size(); i++){
            Produto p = cardapio.get(i);
            if(p.getNome().equals(nome))
                return p.getPreco();
        }
        return null;
    }
}
