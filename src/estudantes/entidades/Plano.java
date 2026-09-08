package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe que representa um plano
 * @author Cauã Miranda
 */
public class Plano extends DocumentoAcademico {
    private String responsavel;
    private String[] planejamento;
    
    public Plano(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String responsavel, String[] planejamento) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.responsavel = responsavel;
        this.planejamento = planejamento;
    }

    @Override
    public boolean equals(Object o) {
        // se for a mesma referência retorna true
        if (this == o) {
            return true;
        }

        // se for nulo, ou de classe diferente do objeto retorna false
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        //compara com os atributos herdados e retorno falso se não for equivalente
        if (!super.equals(o)) {
            return false;
        }

        //compara os 3 atributos da subclasse
        Plano outro = (Plano) o;

        return this.responsavel.equals(outro.responsavel) && Arrays.equals(this.planejamento, outro.planejamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.responsavel, Arrays.hashCode(this.planejamento));
    }
}