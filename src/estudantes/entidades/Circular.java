package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;
import java.util.Arrays;

/**
 * Classe que representa uma circular
 * @author Cauã Miranda
 */
public class Circular extends Deliberacao{
    private String[] destinatarios;

    public Circular(String criador, CodigoCurso codigoCurso, int paginas, String texto, String[] destinatarios) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }

    //getter
    public String[] getDestinatarios() {
        return destinatarios;
    }

    @Override
    public boolean equals(Object o) {
        //se for a mesma referência 
        if (this == o) {
            return true;
        }

        //se for nulo ou de classe diferente
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        //compara atributos herdados
        if (!super.equals(o)) {
            return false;
        }

        //converte o tipo do objeto
        Circular outro = (Circular) o;

        //compara atributos da subclasse
        return Arrays.equals(this.destinatarios, outro.destinatarios);
    }

    @Override 
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(this.destinatarios));
    }
    
}
