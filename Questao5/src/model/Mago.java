package model;

public class Mago extends Personagem {

    private static final int CUSTO_MAGIA = 15;
    private static final int DANO_MAGIA = 30;
    private static final int DANO_CAJADO = 5;

    private int mana;

    public Mago(String nome) {
        super(nome, 80);
        this.mana = 40;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (mana >= CUSTO_MAGIA) {
            mana -= CUSTO_MAGIA;
            System.out.println(nome + " (Mago) lança uma bola de fogo em " + alvo.getNome()
                    + "! (mana restante: " + mana + ")");
            alvo.receberDano(DANO_MAGIA);
        } else {
            System.out.println(nome + " (Mago) está sem mana e bate em " + alvo.getNome() + " com o cajado.");
            alvo.receberDano(DANO_CAJADO);
        }
    }
}
