package estudo.uanderson.javadevdojo.javacore.Gassociacao.dominio.Execicio;

public class Aluno {
    String nome;
    int idade;
    private Seminario seminario; //-UM ALUNO PODERÁ ESTAR EM APENAS UM SEMINÁRIO(associação)

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
