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

    public void selecaoDireta(){
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

    public void shake(){
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
}
