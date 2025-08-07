package quest2;

import java.util.ArrayList;

//interface ?
public abstract class Cliente {
    private final String nome;
    private int pontos;
    private ArrayList<Integer> compras;
    private final int mesCadastro;

    public Cliente(String nome, int mes) {
        this.nome = nome;
        pontos = 0;
        compras = new ArrayList<Integer>();
        mesCadastro = mes;
    }

}
