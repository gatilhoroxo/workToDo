package quest3;

public class Etanol implements BombaDeCombustivel{
    private double precoLitro;

    public Etanol(){}
    public Etanol(double precoLitro){
        this.precoLitro = precoLitro;
    }

    @Override
    public void abastecer(Automovel automovel, double quantidadeLitros) throws CombustivelNaoCompativel, CombustivelOverflow {
        if(automovel.getCapacidadeMaximaTanque() < quantidadeLitros + automovel.getCombustivelAtual())
            throw new CombustivelOverflow();
        else if(automovel.getMotor() != TipoMotor.ETANOL && automovel.getMotor() != TipoMotor.FLEX)
            throw new CombustivelNaoCompativel();
        else 
            automovel.setCombustivelAtual(quantidadeLitros);
    }

    @Override
    public void calcularCusto(double quantidadeLitros){
        System.out.println("Tipo do Combustivel: "+TipoMotor.ETANOL+"\nQuantidade abastecida: "+quantidadeLitros+"\nCusto do abastecimento: "+ quantidadeLitros*precoLitro);
    }

    @Override
    public void ajustarPreco(double novoPreco){
        precoLitro = novoPreco;
    }

    @Override
    public void tryAbastecer(Automovel automovel, double quantidadeLitros){
        try {
            this.abastecer(automovel, quantidadeLitros);
            System.out.println("Abasteceu etanol com sucesso! \n" 
            + automovel.getCombustivelAtual() + " / " + automovel.getCapacidadeMaximaTanque() 
            + "L no tanque!");
        }
        catch (CombustivelNaoCompativel | CombustivelOverflow e) {
            System.out.println(e.getMessage());
        }
    }
}
