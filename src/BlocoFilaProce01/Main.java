package BlocoFilaProce01;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String [] args) {
        List<Solicitacao> fila = new ArrayList<>();
        fila.add(new Solicitacao(1, "NORMAL", 10));
        fila.add(new Solicitacao(2, "URGENTE", 20));
        fila.add(new Solicitacao(3, "NORMAL", 15));
        fila.add(new Solicitacao(4, "URGENTE", 5));


        System.out.println("Como chegou");
        fila.forEach(System.out::println);

        ProcessadorFila processador = new ProcessadorFila();
        processador.ordenar(fila);

        System.out.println("Fila pronta pra começar");
        fila.forEach(System.out::println);


    }
}
