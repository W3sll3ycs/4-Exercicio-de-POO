package model;

public abstract class Pagamento {
    public abstract void processar(double valor);

    protected boolean valorValido(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido: o pagamento deve ser maior que zero.");
            return false;
        }
        return true;
    }

    protected String moeda(double valor) {
        return String.format("R$ %.2f", valor);
    }
}
