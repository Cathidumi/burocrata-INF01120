package estudantes.entidades;

import professor.entidades.CodigoCurso; //obrigatório para o construtor
import java.util.Objects;               // OBRIGATÓRIO PARA O HASHCODE

//extensão de certificado
public class Diploma extends Certificado{
    private String habilitacao;

    //construtor
    public Diploma(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao, String habilitacao){
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula, descricao);   //herança de certificado
        this.habilitacao = habilitacao;
    }

//getters
    public String getHabilitacao(){
        return this.habilitacao;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        // testar o objeto
        if (this == o) {
            return true;
        }

        // testa se é da mesma classe
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        // verifica se os atributos da classe mãe são iguais
        if (!super.equals(o)) {
            return false;
        }

        // verifica se Diploma é um Diploma valido
        Diploma outro = (Diploma) o;

        return this.habilitacao.equals(outro.habilitacao);
    }

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.habilitacao);
    }
}
