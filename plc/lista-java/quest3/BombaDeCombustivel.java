package quest3;

public interface BombaDeCombustivel {
    
    public void abastecer(Automovel automovel, double quantidadeLitros) throws CombustivelNaoCompativel, CombustivelOverflow;

    public void calcularCusto(double quantidadeLitros);

    public void ajustarPreco(double novoPreco);

    public void tryAbastecer(Automovel automovel, double quantidadeLitros);
    
}
