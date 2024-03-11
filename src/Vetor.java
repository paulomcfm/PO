import java.util.Random;

public class Vetor {
    private int[] vet;
    private int TL;

    public Vetor(int TL){
        vet = new int[TL];
        this.TL = TL;
    }

    public void preencher() {
        Random random = new Random();
        for (int i = 0; i < TL; i++) {
            vet[i] = random.nextInt(100)+1;
        }
    }

    public void exibir(){
        for(int i=0; i<TL; i++){
            System.out.print("| "+ vet[i]+ " ");
        }
        System.out.println("|");
    }

    public int buscaBinaria(int chave, int TL){
        int inicio=0, fim=TL-1, meio=fim/2;
        while(inicio<fim && chave!=vet[meio]){
            if(chave<vet[meio]){
                fim=meio-1;
            }else{
                inicio=meio+1;
            }
            meio=(inicio+fim)/2;
        }
        if(chave>vet[meio]){
            return meio+1;
        }
        return meio;
    }

    public void insercaoDireta(){ //posiciona o i no segundo elemento e vai trocando pelos da esquerda enquanto > 0 e aux menor, depois incrementa o i
        int pos, aux;
        for(int i=1;i<TL;i++){
            aux=vet[i];
            pos=i;
            while(pos>0 && aux<vet[pos-1]){
                vet[pos]=vet[pos-1];
                pos--;
            }
            vet[pos]=aux;
        }
    }

    public void insercaoBinaria(){ //posiciona o i no segundo elemento e busca a pos, enquanto j>pos vai remanejando e coloca o aux, depois incrementa o i
        int pos, aux;
        for(int i=1;i<TL;i++){
            aux=vet[i];
            pos=buscaBinaria(aux, i);
            for(int j=i;j>pos;j--)
                vet[j]=vet[j-1];
            vet[pos]=aux;
        }
    }

    public void selecaoDireta(){ // posiciona no primeiro elemento e anda do segundo até o final salvando a pos do menor, dps troca
        int posmenor,aux;
        for(int i=0; i<TL-1;i++){
            posmenor=i;
            for(int j=i+1;j<TL;j++)
                if(vet[j]<vet[posmenor])
                    posmenor=j;
            aux=vet[posmenor];
            vet[posmenor]=vet[i];
            vet[i]=aux;
        }
    }

    public void bubble(){ //define um TL2=TL-1 e vai trocando i com os vizinhos até TL2=1 ou nao trocou
        int TL2=TL-1,aux;
        boolean troca=true;
        while(TL2>1 && troca){
            troca=false;
            for(int i=0;i<TL2;i++){
                if(vet[i]>vet[i+1]){
                    troca=true;
                    aux=vet[i];
                    vet[i]=vet[i+1];
                    vet[i+1]=aux;
                }
            }
            TL2--;
        }
    }

    public void shake(){ //define ini=0 e fim=tl-1, enquanto ini<fim anda jogando os maiores pra direta e decrementa o fim, dps anda jogando menores pra esquerda e decrementa ini
        int aux, inicio=0, fim=TL-1;
        boolean troca=true;
        while(inicio < fim && troca){
            troca=false;
            for(int i=inicio;i<fim;i++){
                if(vet[i]>vet[i+1]){
                    troca=true;
                    aux=vet[i];
                    vet[i]=vet[i+1];
                    vet[i+1]=aux;
                }
            }
            fim--;
            if(troca){
                for(int i=fim;i>inicio;i--){
                    if(vet[i]<vet[i-1]){
                        troca=true;
                        aux=vet[i];
                        vet[i]=vet[i-1];
                        vet[i-1]=aux;
                    }
                }
                inicio++;
            }
        }
    }

    public void heap(){ //TL2=TL, enquanto tl2>1, define pai = tl2/2-1, enquanto pai>=0, ve qual o maior filho, coloca no lugar do pai
        int TL2 = TL,pai,FE, FD, maiorF,aux;
        while(TL2>1){ //TL/2 = qtd de pais
            pai=TL2/2-1;
            while(pai>=0){
                FE=2*pai+1;
                FD=FE+1;
                maiorF=FE;
                if(FD<TL2 && vet[FD]>vet[FE])
                    maiorF=FD;
                if(vet[maiorF]>vet[pai]){
                    aux=vet[maiorF];
                    vet[maiorF]=vet[pai];
                    vet[pai]=aux;
                }
                pai--;
            }
            aux=vet[TL2-1];
            vet[TL2-1]=vet[0];
            vet[0]=aux;
            TL2--;
        }
    }

    public void shell(){ //calcula a distancia, enquanto for >0, compara j com j-dist, se for menor troca e j assume j-dist
        int i,j,aux,dist=1;
        while(dist<TL)
            dist=dist*3+1;
        dist=dist/3;
        while(dist>0){
            for(i=dist;i<TL;i++){
                aux=vet[i];
                j=i;
                while(j-dist>=0 && aux<vet[j-dist]){
                    vet[j]=vet[j-dist];
                    j=j-dist;
                }
                vet[j]=aux;
            }
            dist=dist/3;
        }
    }
}
