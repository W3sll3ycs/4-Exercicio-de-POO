package model;

public class Pix extends Pagamento {

    private static final double DESCONTO = 0.05; // 5% de desconto

    @Override
    public void processar(double valor) {
        if (!valorValido(valor)) return;

        double desconto = valor * DESCONTO;
        double total = valor - desconto;

        System.out.println("=== Pix ===");
        System.out.println("Valor original: " + moeda(valor));
        System.out.println("Desconto (5%):  " + moeda(desconto));
        System.out.println("Total a pagar:  " + moeda(total));
        System.out.println("Confirmação instantânea.");
        System.out.println();
    }
}
