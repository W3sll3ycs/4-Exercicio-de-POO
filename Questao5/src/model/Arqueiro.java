package model;

public class Arqueiro extends Personagem {

    private static final int DANO_FLECHA = 10;
    private static final double CHANCE_ACERTO = 0.75;

    public Arqueiro(String nome) {
        super(nome, 90);
    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println(nome + " (Arqueiro) dispara duas flechas em " + alvo.getNome() + "!");

        // Duas flechas por turno, cada uma com 75% de chance de acertar
        for (int i = 1; i <= 2; i++) {
            if (SORTEIO.nextDouble() < CHANCE_ACERTO) {
                alvo.receberDano(DANO_FLECHA);
            } else {
                System.out.println("  -> A flecha " + i + " erra o alvo.");
            }
        }
    }
}
