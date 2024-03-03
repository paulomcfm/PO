import java.util.Random;

public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }

    public void inicializa(){
        inicio = null;
        fim = null;
    }

    public void inserirInicio(int info){
        No nova = new No(null, info, inicio);
        if(inicio==null){
            inicio = fim =  nova;
        }
        else{
            inicio.setAnt(nova);
            inicio = nova;
        }
    }

    public void insercaoFinal(int info){
        No nova = new No(fim, info, null);
        if(fim==null){
            inicio = fim = null;
        }
        else{
            fim.setProx(nova);
            fim = nova;
        }
    }

    public void insercaoAleatoria(int qtd){
        Random random = new Random();
        for (int i = 0; i < qtd; i++) {
            No nova = new No(null, random.nextInt(100)+1, inicio);
            if(inicio==null){
                inicio = fim =  nova;
            }
            else{
                inicio.setAnt(nova);
                inicio = nova;
            }
        }
    }

    public void exibir(){
        No aux = inicio;
        while(aux!=null){
            System.out.print("| " + aux.getInfo()+" ");
            aux=aux.getProx();
        }
        System.out.println("|");
    }

    public No buscaExaustiva(int info){
        No aux = inicio;
        while(aux!=null && aux.getInfo()!=info)
            aux=aux.getProx();
        return aux;
    }

    public No buscaBinaria(int chave, No fim){

        return inicio;
    }

    public void remover(int info){
        No aux = buscaExaustiva(info);
        if(aux!=null){
            if(inicio==fim){
                inicio=fim=null;
            }else if(aux==inicio){
                inicio = aux.getProx();
                inicio.setAnt(null);
            }else if(aux==fim){
                fim = aux.getAnt();
                fim.setProx(null);
            }else{
                aux.getAnt().setProx(aux.getProx());
                aux.getProx().setAnt(aux.getAnt());
            }
        }
    }

    public void insercaoDireta(){ //inicia i no segundo elemento, enquanto i>0 e aux maior que vet[i-1] vai trocando, dps incrementa i ate tl
        int aux;
        No pos, i=inicio.getProx();
        while (i!=null) {
            aux=i.getInfo();
            pos=i;
            while(pos!=inicio && aux<pos.getAnt().getInfo()){
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }

    public void insercaoBinaria(){
        int aux;
        No i=inicio.getProx(),pos,j;
        while(i!=null){
            aux=i.getInfo();
            pos = buscaBinaria(i.getInfo(),i.getAnt());
            j=i;
            if(j!=pos) {
                while (j.getAnt() != pos) {
                    j.setInfo(j.getAnt().getInfo());
                    j=j.getAnt();
                }
            }
            j.setInfo(aux);
            i=i.getProx();
        }
    }
}
