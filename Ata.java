package estudantes.entidades;

import professor.entidades.CodigoCurso; //obrigatório para o construtor
import java.util.Arrays;                //compara vetores
import java.util.Objects;               // OBRIGATÓRIO PARA O HASHCODE

//extensão de documento
public class Ata extends Documento{
    private int numero;
    private String texto;
    private String presentes[]; //array

    //construtor
    public Ata(String criador, CodigoCurso codigoCurso, int paginas, int numero, String texto, String presentes[]){
        super(criador, codigoCurso, paginas);   //herança de documento
        this.numero = numero;
        this.texto = texto;
        this.presentes = presentes;
    }   

//getters
    public int getNumero(){
        return this.numero;
    }

    public String getTexto(){
        return this.texto;
    }

    public String[] getPresente(){
        return this.presentes;
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
        
        // verifica se Ata é uma Ata valida
        Ata outro = (Ata) o;

        //FEITO COM AJUDA DE IA *********************************
        // compara os 3 atributos de Ata em um único retorno usando &&
        return this.numero == outro.numero && 
            this.texto.equals(outro.texto) && 
            Arrays.equals(this.presentes, outro.presentes);
    }//******************************************************

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.numero, this.texto, Arrays.hashCode(this.presentes)); //FEITO COM AJUDA DE IA
    }
}
