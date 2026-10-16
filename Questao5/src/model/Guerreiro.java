package model;

public class Guerreiro extends Personagem {

    private static final int DANO_BASE = 18;
    private static final double CHANCE_CRITICO = 0.30;

    public Guerreiro(String nome) {
        super(nome, 120);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = DANO_BASE;
        String golpe = "golpe de espada";

        // 30% de chance de acerto crítico (dano dobrado)
        if (SORTEIO.nextDouble() < CHANCE_CRITICO) {
            dano *= 2;
            golpe = "GOLPE CRÍTICO de espada";
        }

        System.out.println(nome + " (Guerreiro) acerta " + alvo.getNome() + " com um " + golpe + "!");
        alvo.receberDano(dano);
    }
}
