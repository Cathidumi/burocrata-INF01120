package estudantes.entidades;

import professor.entidades.CodigoCurso; //obrigatório para o construtor
import java.util.Objects;               // OBRIGATÓRIO PARA O HASHCODE

//extensão de registro
public class Certificado extends Registro{
    private String descricao;

    //construtor
    public Certificado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao){
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);   //herança de registro
        this.descricao = descricao;
    }

//getters
    public String getDescricao(){
        return this.descricao;
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

        // verifica se Certificado é um Certificado valido
        Certificado outro = (Certificado) o;

        return this.descricao.equals(outro.descricao);
    }

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.descricao);
    }
}
