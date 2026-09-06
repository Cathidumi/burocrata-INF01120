package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;


/**
 * Classe que representa um documento acadêmico
 * @author Cauã Miranda
 */
public abstract class DocumentoAcademico extends Documento {
    private long autenticacao;

    public DocumentoAcademico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao) {
        super(criador, codigoCurso, paginas);
        this.autenticacao = autenticacao;
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

        DocumentoAcademico outro = (DocumentoAcademico) o;

        return this.autenticacao == outro.autenticacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.autenticacao);
    }
}


