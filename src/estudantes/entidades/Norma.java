package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

public class Norma extends DocumentoAdministrativo {
    private int numero;
    private boolean valido;
    private String texto;

    public Norma(String criador, CodigoCurso codigoCurso, int paginas, int numero, boolean valido, String texto) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;

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
        Norma outro = (Norma) o;
        if (this.numero != outro.numero || this.valido != outro.valido || !this.texto.equals(outro.texto)) {
            return false;
        }
        return true;
    }

    @Override 
    public int hashCode() {
        return Objects.hash(super.hashCode(), numero, valido, texto);
    }
}
