package model;

public class Boleto extends Pagamento {
    private static final double TAXA_FIXA = 3.50; // tarifa de emissão

    @Override
    public void processar(double valor) {
        if (!valorValido(valor)) return;

        double total = valor + TAXA_FIXA;

        System.out.println("=== Boleto ===");
        System.out.println("Valor original:   " + moeda(valor));
        System.out.println("Tarifa de emissão: " + moeda(TAXA_FIXA));
        System.out.println("Total a pagar:    " + moeda(total));
        System.out.println("Compensação em até 3 dias úteis.");
        System.out.println();
    }
}
