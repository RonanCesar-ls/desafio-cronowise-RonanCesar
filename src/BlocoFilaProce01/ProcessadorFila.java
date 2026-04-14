package BlocoFilaProce01;
import java.util.List;

public class ProcessadorFila {

    public void ordenar (List<Solicitacao> fila){
        fila.sort((s1, s2) -> {

            if(!s1.tipo.equals(s2.tipo)) {
                if(s1.tipo.equals("URGENTE")) {
                    return -1;
                }else {
                    return 1;
                }
            }

            if(s1.timestamp < s2.timestamp) {
                return -1;

            }else if (s1.timestamp > s2.timestamp){
                return 1;
            }else {
                return 0;
            }
        });
    }
}
