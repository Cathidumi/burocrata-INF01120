package estudantes.entidades;

import professor.entidades.CodigoCurso;
import java.util.Objects;

//extensão de DocumentoAdministrativo
public abstract class Deliberacao extends DocumentoAdministrativo{
    private String texto;

    //construtor
    public Deliberacao(String criador, CodigoCurso codigoCurso, int paginas, String texto){
        super(criador, codigoCurso, paginas);   //herança de documento
        this.texto = texto;
    } 

    //getter
    public String getTexto(){
        return this.texto;
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

        //verifica se Deliberacao é valido
        Deliberacao outra = (Deliberacao) o;
        return this.texto.equals(outro.texto);  //retorna o texto comparando-os
    }
    
    //hashcode
    @Override   
    public int hashCode(){
        return Object.hash(super.hashCode(), this.texto);
     }
}
