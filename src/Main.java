public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();
        for(int i=0;i<10;i++){
            lista.inserirInicio(i);
        }
        lista.exibir();
        for(int i=0;i<10;i++){
            lista.remover(i);
        }
        //lista.exibir();
        //lista.remover(5);
        lista.exibir();
    }
}