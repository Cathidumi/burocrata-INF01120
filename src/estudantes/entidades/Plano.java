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
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Plano outro = (Plano) o;

        return this.responsavel.equals(outro.responsavel) && Arrays.equals(this.planejamento, outro.planejamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.responsavel, Arrays.hashCode(this.planejamento));
    }
}