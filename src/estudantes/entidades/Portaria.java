package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

/**
 * Classe que representa uma Portaria.
 * @author Cauã Miranda
 */
public class Portaria extends Norma {
    private int anoInicio;

	public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto,
			int anoInicio) {
		super(criador, codigoCurso, paginas, numero, valido, texto);
		this.anoInicio = anoInicio;
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

        //compara os atributos da subclasse
        Portaria outro = (Portaria) o;
        if (this.anoInicio != outro.anoInicio) {
            return false;
        }
        return true;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(super.hashCode(), anoInicio);
    }
}
