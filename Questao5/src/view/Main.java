import model.Personagem;
import model.Guerreiro;
import model.Arqueiro;
import model.Mago;

void main(String[] args){

    Personagem[] personagens = {
            new Guerreiro("Thorin"),
            new Mago("Merlin"),
            new Arqueiro("Legolas")
    };

    System.out.println("=== Início do turno ===");
    for (Personagem p : personagens) {
        System.out.println(p);
    }
    System.out.println();

    for (int i = 0; i < personagens.length; i++) {
        Personagem atacante = personagens[i];
        Personagem alvo = personagens[(i + 1) % personagens.length];

        if (!atacante.estaVivo()) continue;

        atacante.atacar(alvo);
        System.out.println();
    }

    System.out.println("=== Fim do turno ===");
    for (Personagem p : personagens) {
        System.out.println(p + (p.estaVivo() ? "" : " - DERROTADO"));
    }
}

