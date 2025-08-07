package quest3;

public class Main {
    public static void main(String[] args){
        Automovel carro = new Automovel(19, 127, TipoMotor.FLEX);
        Automovel van = new Automovel(53,203,TipoMotor.GASOLINA);
        Automovel onibus = new Automovel(23,253,TipoMotor.ETANOL);

        Gasolina g = new Gasolina(7);
        Etanol e = new Etanol(5);

        //caso imcompativel
        e.calcularCusto(20);
        e.tryAbastecer(van,20);
        g.calcularCusto(20);
        g.tryAbastecer(onibus, 20);
        System.out.println(van.getCombustivelAtual()+"\n"+onibus.getCombustivelAtual());

        System.out.println(" ");

        //caso de abastecimento em excesso
        e.calcularCusto(110);
        e.tryAbastecer(carro, 110);
        System.out.println(carro.getCombustivelAtual());

        System.out.println(" ");

        //caso de abastecimento de gasolina bem sucedido mas com limite do tanque excedido
        g.calcularCusto(100);
        g.tryAbastecer(carro, 100);

        System.out.println(" ");
        
        //caso de abastecimento de gasolina após reajuste de preço
        e.ajustarPreco(13);
        e.calcularCusto(30);
        e.tryAbastecer(onibus, 30);

    }
}
