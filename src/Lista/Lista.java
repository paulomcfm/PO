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

    public void preencher(int qtd){
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

    public No posicionaMeio(No ini, No fim){
        No aux=ini;
        int i;
        for(i=1;aux!=fim;i++)
            aux=aux.getProx();
        aux=ini;
        i=(i-1)/2;
        for(int j=0;j<i;j++){
            aux=aux.getProx();
        }
        return aux;
    }

    public No buscaBinaria(int chave, No ultima){
        No ini=inicio, fim=ultima.getAnt(), meio=posicionaMeio(ini,fim);
        while(ini.getInfo()<fim.getInfo() && chave!=meio.getInfo()){
            if(chave>meio.getInfo())
                ini=meio.getProx();
            else
                fim=meio.getAnt();
            meio=posicionaMeio(ini, fim);
        }
        if(chave>meio.getInfo())
            return meio.getProx();
        return meio;
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

    public void selecaoDireta() {
        No inicio = this.inicio;
        No aux=inicio;
        No menor;
        int troca;
        while(inicio!=fim){
            menor=inicio;
            aux=inicio.getProx();
            while(aux!=null){
                if(aux.getInfo()<menor.getInfo()){
                    menor=aux;
                }
                aux=aux.getProx();
            }
            troca=inicio.getInfo();
            inicio.setInfo(menor.getInfo());
            menor.setInfo(troca);
            inicio=inicio.getProx();
        }
    }

    public void bubble() {
        No ultima = fim;
        No aux;
        int auxi;
        boolean troca=true;
        while(ultima!=inicio.getProx() && troca){
            troca=false;
            aux=inicio;
            while(aux!=ultima){
                if(aux.getInfo()>aux.getProx().getInfo()){
                    troca=true;
                    auxi=aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(auxi);
                }
                aux=aux.getProx();
            }
        }
        ultima=ultima.getAnt();
    }

    public void shake() {
        No ultima= fim;
        No ini = inicio;
        No aux;
        int auxi;
        boolean troca=true;
        while(ini!=ultima && troca){
            troca=false;
            aux=ini;
            while(aux!=ultima){
                if(aux.getInfo()>aux.getProx().getInfo()){
                    troca=true;
                    auxi=aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(auxi);
                }
                aux=aux.getProx();
            }
            ultima=ultima.getAnt();
            if(troca){
                aux=ultima;
                while(aux!=ini){
                    if(aux.getInfo()<aux.getAnt().getInfo()){
                        troca=true;
                        auxi=aux.getInfo();
                        aux.setInfo(aux.getAnt().getInfo());
                        aux.getAnt().setInfo(auxi);
                    }
                    aux=aux.getAnt();
                }
            }
            ini=ini.getProx();
        }
    }
    private No posicionaPai(No ult) {
        No aux=inicio;
        int i;
        for(i=1;aux!=ult;i++)
            aux=aux.getProx();
        i=i/2-1;
        aux=inicio;
        for(int j=0;j<i;j++){
            aux=aux.getProx();
        }
        return aux;
    }
    private No posicionaFilho(No pai, No ult) {
        No aux=inicio;
        int i;
        for(i=0;aux!=pai;i++)
            aux=aux.getProx();
        aux=inicio;
        for(int j=0;j<i*2+1;j++)
            aux=aux.getProx();
        return aux;
    }
    private boolean filhoDireitaDentro(No FE, No ultima) {
        No aux;
        aux=inicio;
        if(FE!=null){
            while(aux!=FE && aux!=ultima)
                aux=aux.getProx();
            if(aux==FE)
                return true;
        }
        return false;
    }
    public void heap() {
        No ult = fim;
        No FE, FD;
        No pai;
        No maiorF;
        int aux;
        while(ult!=inicio){
            pai = posicionaPai(ult);
            while(pai!=null){
                FE=posicionaFilho(pai, ult);
                FD=FE.getProx();
                maiorF=FE;
                if(filhoDireitaDentro(FD,ult) && FD.getInfo()>FE.getInfo())
                    maiorF=FD;
                if(maiorF.getInfo()>pai.getInfo()){
                    aux=pai.getInfo();
                    pai.setInfo(maiorF.getInfo());
                    maiorF.setInfo(aux);
                }
                pai=pai.getAnt();
            }
            aux=ult.getInfo();
            ult.setInfo(inicio.getInfo());
            inicio.setInfo(aux);
            ult=ult.getAnt();
        }
    }

    private int calculaDist() {
        No aux = inicio;
        int i;
        for(i=0; aux!=null;i++)
            aux=aux.getProx();
        int dist=1;
        while(dist<i)
            dist=dist*3+1;
        dist=dist/3;
        return dist;
    }
    private No posicionaPont(int pos) {
        No aux=inicio;
        for(int i=0;i<pos;i++)
            aux=aux.getProx();
        return aux;
    }
    private No posicionaJDist(No no, int dist){
        No aux=no;
        for(int i=0;aux!=null && i<dist;i++)
            aux=aux.getAnt();
        return aux;
    }
    public void shell() {
        int dist = calculaDist(), aux;
        No i,j,jdist;
        while(dist>0){
            i = posicionaPont(dist);
            while(i!=null){
                aux=i.getInfo();
                j=i;
                jdist = posicionaJDist(j,dist);
                while(jdist!=null && aux<jdist.getInfo()){
                    j.setInfo(jdist.getInfo());
                    j=jdist;
                    jdist = posicionaJDist(j,dist);
                }
                j.setInfo(aux);
                i=i.getProx();
            }
            dist=dist/3;
        }
    }
    private void incrementa(Lista cont, int info) {
        No aux = cont.inicio;
        for (int i = 0; i < info; i++) {
            aux=aux.getProx();
        }
        aux.setInfo(aux.getInfo()+1);
    }
    private int procuraPosicao(int info, Lista cont) {
        No aux = cont.inicio;
        for (int i = 0; i < info; i++)
            aux=aux.getProx();
        return aux.getInfo();
    }
    private void colocaValor(int possaida, int info, Lista saida) {
        No aux = saida.inicio;
        for (int i = 0; i < possaida; i++)
            aux=aux.getProx();
        aux.setInfo(info);
    }
    public void couting() {
        No aux = inicio, auxS;
        Lista cont = new Lista();
        cont.inicializa();
        Lista saida = new Lista();
        saida.inicializa();
        int maior = aux.getInfo(), info = 0, possaida;
        aux=aux.getProx();
        while(aux!=null) {
            if(aux.getInfo()>maior)
                maior= aux.getInfo();
            aux=aux.getProx();
        }
        for(int i=0;i<=maior;i++){
            cont.insercaoFinal(0);
        }
        aux=inicio;
        while(aux!=null){
            info = aux.getInfo();
            incrementa(cont,info);
            aux=aux.getProx();
        }
        aux=cont.inicio.getProx();
        while(aux!=null) {
            aux.setInfo(aux.getAnt().getInfo() + aux.getInfo());
            aux = aux.getProx();
        }
        aux=inicio;
        while(aux!=null)
            saida.insercaoFinal(0);
        aux=fim;
        while(aux!=null){
            possaida = procuraPosicao(aux.getInfo(),cont);
            colocaValor(possaida-1,aux.getInfo(),saida);
            aux=aux.getAnt();
        }
        aux=inicio;
        auxS=saida.inicio;
        while (aux!=null) {
            aux.setInfo(auxS.getInfo());
            aux=aux.getProx();
            auxS=auxS.getProx();
        }
    }
    //Counting Lista -> Para criar uma Lista dentro do obeto Lista, devo criar novos métodos de insercao e inicializacao?
    //Bucket Sort -> Como determinar a quantidade de baldes e sua range? Procuro o maior?
    //Tim Sort -> Como determinar a quantidade de grupos e o tamanho deles?
}
