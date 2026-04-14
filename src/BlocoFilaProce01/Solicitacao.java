package BlocoFilaProce01;

public class Solicitacao {
    public int id;
    public String tipo;
    public int timestamp;

    public Solicitacao(int id, String tipo, int timestamp) {
        this.id = id;
        this.tipo = tipo;
        this.timestamp = timestamp;
    }

    public String toString() {
        return "Solicitação [ID: " + id + " Tipo: " + tipo + " Chegada: " + timestamp + "]";
    }
}
