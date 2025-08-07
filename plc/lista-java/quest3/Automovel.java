package quest3;

public class Automovel {
    private double CombustivelAtual;
    private final double capacidadeMaximaTanque;
    private final TipoMotor motor;

    public Automovel(double combAtual, double capMax, TipoMotor motor){
        this.CombustivelAtual = combAtual;
        this.capacidadeMaximaTanque = capMax;
        this.motor = motor;
    }

    public double getCombustivelAtual() {
        return CombustivelAtual;
    }
    public void setCombustivelAtual(double combustivelAtual) {
        CombustivelAtual += combustivelAtual;
    }

    public double getCapacidadeMaximaTanque() {
        return capacidadeMaximaTanque;
    }

    public TipoMotor getMotor() {
        return motor;
    }
}
