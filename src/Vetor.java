import java.util.Random;
import Lista.Lista;
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

    public void insercaoDireta(){
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

    public void insercaoBinaria(){
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

    public void bubble(){
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

    public void heap(){
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

    public void shell(){
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

    public void counting(){
        int maior=vet[0];
        for(int i=1;i<TL;i++)
            if(vet[i]>maior)
                maior=vet[i];
        int cont[]= new int[maior+1];
        for(int i=0;i<TL;i++)
            cont[vet[i]]++;
        for(int i=1;i<=maior;i++)
            cont[i]=cont[i-1]+cont[i];
        int saida[] =new int[TL];
        for(int i=TL-1;i>=0;i--){
            saida[cont[vet[i]]-1]=vet[i];
            cont[vet[i]]--;
        }
        for(int i=0;i<TL;i++)
            vet[i]=saida[i];
    }

    public void quickSPivo(){
        quickSP(0,TL-1);
    }

    private void quickSP(int ini, int fim) {
        int i=ini, j=fim,aux;
        boolean flag=true;
        while(i<j){
            if(flag)
                while(i<j && vet[i]<=vet[j])
                    i++;
            else
                while(i<j && vet[i]<=vet[j])
                    j--;
            aux=vet[i];
            vet[i]=vet[j];
            vet[j]=aux;
            flag=!flag;
        }
        if(ini<i-1)
            quickSP(ini,i-1);
        if(j+1<fim)
            quickSP(j+1,fim);
    }

    public void quickCPivo(){
        quickCP(0,TL-1);
    }

    private void quickCP(int ini, int fim){
        int i=ini, j=fim, aux;
        int pivo=vet[(ini+fim)/2];
        while(i<j){
            while(vet[i]<pivo)
                i++;
            while(vet[j]>pivo)
                j--;
            if(i<=j){
                aux=vet[i];
                vet[i]=vet[j];
                vet[j]=aux;
                i++;
                j--;
            }
        }
        if(ini<j)
            quickCP(ini,j);
        if(i<fim)
            quickCP(i, fim);
    }

    public void bucket(int qtd) {
        int maior=vet[0],i,j;
        for (i = 1; i < TL; i++) {
            if(vet[i]>maior)
                maior=vet[i];
        }
        Lista baldes[] = new Lista[qtd];
        for (i = 0; i < qtd; i++) {
            baldes[i] = new Lista();
            baldes[i].inicializa();
        }
        for (i = 0; i < TL; i++)
            baldes[(vet[i]*qtd)/(maior+1)].insercaoFinal(vet[i]);
        for (i = 0; i < qtd; i++) {
            if(baldes[i].retornaPrimeiro()!=null)
                baldes[i].insercaoDireta();
        }
        i=0;
        j=0;
        while (i < TL) {
            while(j<qtd && baldes[j].retornaPrimeiro()!=null){
                vet[i]=baldes[j].removePrimeiro();
                i++;
            }
            j++;
        }
    }
    private void countingRadix(int chave){
        int maior=vet[0], index;
        for(int i=1;i<TL;i++)
            if(vet[i]>maior)
                maior=vet[i];
        int cont[]= new int[maior+1];
        for(int i=0;i<TL;i++) {
            index = (vet[i]/chave)%10;
            cont[index]++;
        }
        for(int i=1;i<=maior;i++)
            cont[i]=cont[i-1]+cont[i];
        int saida[] =new int[TL];
        for(int i=TL-1;i>=0;i--){
            index = (vet[i]/chave)%10;
            saida[cont[index]-1]=vet[i];
            cont[index]--;
        }
        for(int i=0;i<TL;i++)
            vet[i]=saida[i];
    }
    public void radix() {
        int maior=vet[0], chave;
        for (int i = 1; i < TL; i++)
            if(vet[i]>maior)
                maior=vet[i];
        for (chave=1; maior/chave>0; chave*=10)
            countingRadix(chave);
    }

    public void comb() {
        int gap = TL;
        boolean troca = true;
        while (gap > 1 || troca) {
            gap = (int) (gap/1.3);
            if(gap<1)
                gap=1;
            troca = false;
            for (int i = 0; i + gap < TL; i++) {
                if (vet[i] > vet[i + gap]) {
                    int temp = vet[i];
                    vet[i] = vet[i + gap];
                    vet[i + gap] = temp;
                    troca = true;
                }
            }
        }
    }

    public void gnome() {
        int i=0,aux;
        while(i<TL){
            if (i==0)
                i++;
            if(vet[i]>=vet[i-1])
                i++;
            else{
                aux=vet[i];
                vet[i]=vet[i-1];
                vet[i-1]=aux;
                i--;
            }
        }
    }

    private void particao(int[] vet1, int[] vet2) {
        for (int i = 0; i < TL/2; i++) {
            vet1[i]=vet[i];
            vet2[i]=vet[i+(TL/2)];
        }
    }

    private void fusao(int[] vet1, int[] vet2, int seq) {
        int i=0,j=0,k=0,aux=seq;
        while(k<TL){
            while (i < seq && j < seq) {
                if (vet1[i] > vet2[j])
                    vet[k++] = vet2[j++];
                else
                    vet[k++] = vet1[i++];
            }
            while(i<seq)
                vet[k++]=vet1[i++];
            while(j<seq)
                vet[k++]=vet2[j++];
            seq=seq+aux;
        }
    }

    public void merge(){
        int vet1[] = new int[TL/2];
        int vet2[] = new int[TL/2];
        int seq=1;
        while(seq<TL){
            particao(vet1,vet2);
            fusao(vet1,vet2,seq);
            seq=seq*2;
        }
    }

    private void fusaoM(int[] aux, int ini1, int fim1, int ini2, int fim2) {
        int i=ini1, j=ini2, k=0;
        while(i<=fim1 && j<=fim2){
            if (vet[i] > vet[j])
                aux[k++] = vet[j++];
            else
                aux[k++] = vet[i++];
        }
        while(i<=fim1)
            aux[k++]=vet[i++];
        while(j<=fim2)
            aux[k++]=vet[j++];
        for(i=0;i<k;i++)
            vet[i+ini1]=aux[i];
    }

    private void mergeDiv(int[] aux, int esq, int dir) {
        int meio;
        if(esq<dir){
            meio=(esq+dir)/2;
            mergeDiv(aux, esq, meio);
            mergeDiv(aux, meio+1, dir);
            fusaoM(aux, esq, meio, meio+1, dir);
        }
    }

    public void mergeSort(){
        int aux[]=new int[TL];
        mergeDiv(aux,0,TL-1);
    }

    private int tamMin(int runs) {
        int tam=runs;
        int i = 0;
        while (tam >= runs) {
            tam /= runs;
            i++;
        }
        return tam + i;
    }

    private void insercaoDiretaTim(int esq, int dir){
        int pos, aux;
        for (int i = esq + 1; i <= dir; i++) {
            aux = vet[i];
            pos = i;
            while (pos > esq && vet[pos-1] > aux) {
                vet[pos] = vet[pos-1];
                pos--;
            }
            vet[pos] = aux;
        }
    }

    private void mergeTim(int esq, int meio, int dir){
        int tam1 = meio - esq + 1, tam2 = dir - meio;
        int[] vet1 = new int[tam1];
        int[] vet2 = new int[tam2];
        for (int pos = 0; pos < tam1; pos++)
            vet1[pos] = vet[esq + pos];

        for (int pos = 0; pos < tam2; pos++)
            vet2[pos] = vet[meio + 1 + pos];

        int i = 0,j = 0,k = esq;
        while (i < tam1 && j < tam2) {
            if (vet1[i] <= vet2[j])
                vet[k++] = vet1[i++];
            else
                vet[k++] = vet2[j++];
        }
        while (i < tam1)
            vet[k++] = vet1[i++];
        while (j < tam2)
            vet[k++] = vet2[j++];
    }
    private int min(int i, int j) {
        return (i <= j) ? i : j;
    }
    public void tim(int runs){
        int run = tamMin(runs);

        for (int i = 0; i < TL; i += run) {
            insercaoDiretaTim(i,min((i + runs - 1), (TL - 1)));
        }

        for (int tam = run; tam < TL; tam = 2 * tam) {
            for (int esq = 0; esq < TL; esq += 2 * tam) {
                int meio = esq + tam - 1;
                int dir = min((esq + 2 * tam - 1),(TL - 1));
                if (meio < dir)
                    mergeTim(esq, meio, dir);
            }
        }
    }
}
