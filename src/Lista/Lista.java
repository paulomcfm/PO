package Lista;

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

    public int conta(No primeira, No ultima){
        int i=0;
        No aux=primeira;
        while(aux!=ultima){
            aux = aux.getProx();
            i++;
        }
        return i;
    }
    public No posiciona(No inicio, int meio){
        for(int i=0; i<meio; i++){
            inicio=inicio.getProx();
        }
        return inicio;
    }

    public No buscaBinaria(int chave, No ultima){
        int intIni=0, intFim=conta(this.inicio,ultima.getAnt()), intMeio=intFim/2;
        No noIni=inicio, noFim=ultima.getAnt(), noMeio=posiciona(noIni,intMeio);
        while(intIni<intFim && chave!=noMeio.getInfo()){
            if(chave>noMeio.getInfo()){
                noIni=noMeio.getProx();
                intIni=intMeio+1;
            }
            else{
                noFim=noMeio.getAnt();
                intFim=intMeio-1;
            }
            noMeio=posiciona(noIni,conta(noIni,noFim)/2);
        }
        if(chave>noMeio.getInfo()){
            return noMeio.getProx();
        }
        return noMeio;
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
            pos = buscaBinaria(aux,i);
            j=i;
            while (j != pos) {
                j.setInfo(j.getAnt().getInfo());
                j=j.getAnt();
            }
            j.setInfo(aux);
            i=i.getProx();
        }
    }
}
