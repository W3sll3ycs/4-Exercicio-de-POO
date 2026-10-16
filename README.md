# Herança em Java: respostas

## 1. Verdadeiro ou falso

**1. Os construtores de superclasse não são herdados por subclasses.**
**Verdadeira.** Construtores não são membros herdáveis. A subclasse precisa declarar os seus próprios construtores e, dentro deles, chamar um construtor da superclasse (com `super(...)`) para inicializar a parte herdada do objeto.

**2. Um relacionamento *tem um* é implementado via herança.**
**Falsa.** Herança implementa o relacionamento **"é um"** (um `Guerreiro` *é um* `Personagem`). O relacionamento **"tem um"** é implementado por **composição** (ou agregação): a classe possui um atributo que referencia outro objeto (um `Carro` *tem um* `Motor`).

**3. Quando uma subclasse redefine um método da superclasse com a mesma assinatura, diz-se que ela *sobrecarrega* o método.**
**Falsa.** Redefinir um método com a mesma assinatura é **sobrescrita** (*override*). **Sobrecarga** (*overload*) é ter métodos com o mesmo nome e **assinaturas diferentes** (parâmetros distintos), na mesma classe ou em classes relacionadas.

---

## 2. `protected` versus `private` em superclasses

### Argumentos a favor de `private`
- **Encapsulamento forte:** só a própria superclasse acessa o atributo, então ela controla totalmente como o estado é lido e alterado.
- **Invariantes protegidas:** validações (ex.: `vida` nunca negativa) ficam em um único lugar, nos métodos da superclasse. Uma subclasse não consegue deixar o objeto num estado inválido.
- **Liberdade para mudar a implementação:** a superclasse pode trocar a representação interna (de `int` para `double`, por exemplo) sem quebrar nenhuma subclasse.
- **Menor acoplamento:** as subclasses dependem só da interface pública, não dos detalhes internos.

### Argumentos a favor de `protected`
- **Conveniência e desempenho:** a subclasse acessa o dado direto, sem a chamada de um getter/setter.
- **Extensibilidade:** facilita a criação de subclasses que precisam de acesso privilegiado ao estado herdado.
- **Menos código repetitivo** em hierarquias pequenas e bem controladas.

### Os problemas de `protected`
- Quem escreve uma subclasse passa a depender da **implementação** da superclasse, não só da sua interface. Isso gera o chamado problema da "classe base frágil": mudar a superclasse pode quebrar subclasses.
- Qualquer subclasse pode **ignorar validações** e gravar valores inválidos diretamente.
- Em Java, `protected` também dá acesso a **qualquer classe do mesmo pacote**, o que alarga o acesso além das subclasses.

### Conclusão
A prática mais recomendada é manter os **atributos `private`** e expor o que as subclasses precisam por **métodos `protected`** (ou públicos), como `getVida()` ou `receberDano()`. Assim a subclasse tem o acesso necessário, mas a superclasse continua dona do seu estado e das suas regras. `protected` em atributos só se justifica em casos específicos, como hierarquias pequenas, no mesmo pacote e sob controle de quem as mantém.

```java
public abstract class Personagem {
    private int vida;                      // estado protegido

    protected void alterarVida(int delta) { // acesso controlado para subclasses
        vida = Math.max(0, vida + delta);
    }

    public int getVida() { return vida; }
}
```

---

## 3. Chamada de construtores em hierarquias de herança

### Como funciona
Ao executar `new Subclasse(...)`, a construção acontece **de cima para baixo**: primeiro o construtor da superclasse mais distante (`Object`), depois cada classe intermediária, e por fim o da própria subclasse.

1. A primeira instrução de qualquer construtor é uma chamada a outro construtor: `this(...)` (da mesma classe) ou `super(...)` (da superclasse).
2. Se o programador **não escreve** nenhuma das duas, o compilador insere **implicitamente** `super();` (sem argumentos).
3. A cadeia sobe até `Object`. Só então os construtores vão terminando na ordem inversa, até chegar ao da subclasse.

```java
class Animal {
    Animal() { System.out.println("1. Animal"); }
}

class Mamifero extends Animal {
    Mamifero() { System.out.println("2. Mamifero"); } // super() implícito
}

class Cachorro extends Mamifero {
    Cachorro() { System.out.println("3. Cachorro"); } // super() implícito
}

// new Cachorro() imprime: 1. Animal, 2. Mamifero, 3. Cachorro
```

### Quando a chamada precisa ser explícita
Se a superclasse **não tem construtor sem argumentos**, o `super()` implícito falha na compilação. Nesse caso, a subclasse deve chamar explicitamente um construtor existente:

```java
class Personagem {
    protected String nome;
    Personagem(String nome) { this.nome = nome; }   // não existe Personagem()
}

class Guerreiro extends Personagem {
    Guerreiro(String nome) {
        super(nome);   // obrigatório: sem isso, o código não compila
    }
}
```

### Por que a chamada ao construtor da superclasse é obrigatória
- **Um objeto da subclasse contém a parte da superclasse.** Um `Guerreiro` é também um `Personagem`, e os atributos herdados (`nome`, `vida`) fazem parte do mesmo objeto. Eles precisam ser inicializados antes de qualquer código da subclasse usá-los.
- **A superclasse é a única que sabe inicializar o próprio estado**, principalmente quando esses atributos são `private`, validados ou dependem de parâmetros. A subclasse não pode (nem deveria) fazer isso por ela.
- **Garante a ordem correta e consistente:** se a subclasse rodasse antes, poderia usar campos ainda não inicializados (com valores padrão como `0` ou `null`), gerando bugs difíceis de achar.
- **Mantém as invariantes da hierarquia:** cada nível da cadeia assegura que a sua parte do objeto está válida antes de o nível seguinte começar.

Por isso o Java **não permite pular essa etapa**: ou o programador chama `super(...)` explicitamente, ou o compilador faz a chamada implícita (e só funciona se existir um construtor sem argumentos na superclasse).
