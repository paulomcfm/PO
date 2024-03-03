public class Main {
    public static void listas(){
        Lista lista = new Lista();
        lista.insercaoAleatoria(10);
        lista.exibir();
        lista.insercaoDireta();
        lista.exibir();
    }
    public static void vetores(){
        Vetor vetor = new Vetor(10);
        vetor.preencher();
        vetor.exibir();
        vetor.insercaoBinaria();
        vetor.exibir();
    }
    public static void main(String[] args) {
        //listas();
        vetores();

    }
}