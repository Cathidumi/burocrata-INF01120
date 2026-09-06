package estudantes.entidades;

import professor.entidades.CodigoCurso; //obrigatório para o construtor
import java.util.Objects;               // OBRIGATÓRIO PARA O HASHCODE

//extensão de registro
public class Atestado extends Registro{
    private String descricao;
    private String categoria;

    //construtor
    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, String descricao, String categoria){
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);   //herança de registro
        this.descricao = descricao;
        this.categoria = categoria;
    }

//getters
    public String getDescricao(){
        return this.descricao;
    }

    public String getCategoria(){
        return this.categoria;
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

        // verifica se Atestado é um Atestado valido
        Atestado outro = (Atestado) o;

        return this.descricao.equals(outro.descricao) &&
            this.categoria.equals(outro.categoria);
    }

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.descricao, this.categoria);
    }
}
