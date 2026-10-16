package model;
import java.util.Random;

public abstract class Personagem {

    protected static final Random SORTEIO = new Random(42);

    protected String nome;
    protected int vida;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public abstract void atacar(Personagem alvo);

    public void receberDano(int dano) {
        vida = Math.max(0, vida - dano);
        System.out.println("  -> " + nome + " sofre " + dano + " de dano (vida: " + vida + ")");
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " [vida: " + vida + "]";
    }
}
