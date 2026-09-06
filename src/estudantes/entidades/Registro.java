package estudantes.entidades;

import professor.entidades.CodigoCurso; //obrigatório para o construtor
import java.util.Objects;               // OBRIGATÓRIO PARA O HASHCODE

//extensão de documentoAcademico
public abstract class Registro extends DocumentoAcademico{
    private String estudante;
    private long matricula;

    //construtor
    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula){
        super(criador, codigoCurso, paginas, autenticacao);   //herança de documentoAcademico
        this.estudante = estudante;
        this.matricula = matricula;
    }

//getters
    public String getEstudante(){
        return this.estudante;
    }

    public long getMatricula(){
        return this.matricula;
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

        // verifica se Registro é um Registro valido
        Registro outro = (Registro) o;

        return this.matricula == outro.matricula &&
            this.estudante.equals(outro.estudante);
    }

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.estudante, this.matricula);
    }
}
