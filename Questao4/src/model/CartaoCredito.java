package model;

public class CartaoCredito extends Pagamento {

    private static final double TAXA = 0.035; // 3,5% de taxa

    @Override
    public void processar(double valor) {
        if (!valorValido(valor)) return;

        double taxa = valor * TAXA;
        double total = valor + taxa;

        System.out.println("=== Cartão de Crédito ===");
        System.out.println("Valor original: " + moeda(valor));
        System.out.println("Taxa (3,5%):    " + moeda(taxa));
        System.out.println("Total a pagar:  " + moeda(total));
        System.out.println();
    }
}
