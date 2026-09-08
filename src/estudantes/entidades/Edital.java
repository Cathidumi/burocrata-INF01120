package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe que representa um Edital.
 * @author Cauã Miranda
 */
public class Edital extends Norma {
    String[] responsaveis;

	public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto, String[] responsaveis) {
		
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
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
        Edital outro = (Edital) o;
        // usa arrays equals para comparar os arrays de responsaveis
        if (!Arrays.equals(this.responsaveis, outro.responsaveis)) {
            return false;
        }
        return true;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(responsaveis));
    }
}
