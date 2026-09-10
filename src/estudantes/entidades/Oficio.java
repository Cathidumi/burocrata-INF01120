package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

public class Oficio extends Deliberacao{
    private String destinatario;

    //construtor
    public Oficio(String criador, CodigoCurso codigoCurso, int paginas, String texto, String destinatario){
        super(criador, codigoCurso, paginas, texto);   //herança de todas as suas mães
        this.destinatario = destinatario;
    }

    //getter
    public String getDestinatario(){
        return this.destinatario;
    }

    //equals
    @Override
    public boolean equals(Object o){
        //verifica se é o mesmo objeto
        if(this == o){
            return true;
        }

        //verifica se é nulo ou da mesma classe
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        // verifica se os atributos da classe mãe são iguais
        if (!super.equals(o)) {
            return false;
        }

        //verifica se Oficio é valido
        Oficio outro = (Oficio) o;
        return this.destinatario.equals(outro.destinatario);  //retorna o texto comparando-os
    }

    //hashcode
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), this.destinatario);
    }
}
