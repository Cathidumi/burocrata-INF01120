package estudantes.entidades;

import professor.entidades.CodigoCurso; //obrigatório para o construtor
import java.util.Arrays;                //compara vetores
import java.util.Objects;               // OBRIGATÓRIO PARA O HASHCODE

//extensão de registro
public class Historico extends Registro{
    private double coeficiente;
    private String componentes[]; //array

    //construtor
    public Historico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula, double coeficiente, String componentes[]){
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);   //herança de registro
        this.coeficiente = coeficiente;
        this.componentes = componentes;
    }

//getters
    public double getCoeficiente(){
        return this.coeficiente;
    }

    public String[] getComponentes(){
        return this.componentes;
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

        // verifica se Historico é um Historico valido
        Historico outro = (Historico) o;

        return this.coeficiente == outro.coeficiente &&
            Arrays.equals(this.componentes, outro.componentes);
    }

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.coeficiente, Arrays.hashCode(this.componentes));
    }
}
