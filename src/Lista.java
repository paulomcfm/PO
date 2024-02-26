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

    public void exibir(){
        No aux = inicio;
        while(aux!=null){
            System.out.println(aux.getInfo());
            aux=aux.getProx();
        }
    }

    public No buscaExaustiva(int info){
        No aux = inicio;
        while(aux!=null && aux.getInfo()!=info)
            aux=aux.getProx();
        return aux;
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

    public void insercaoDireta(){
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

    public void selecaoDireta(){
        int posmenor,aux;
        for(int i=0; i<tl-1;i++){
            posmenor=i;
            for(int j=i+1;j<tl;j++)
                if(vet[j]<vet[posmenor])
                    posmenor=j;
            aux=vet[posmenor];
            vet[posmenor]=vet[i];
            vet[i]=aux;
        }
    }
}
