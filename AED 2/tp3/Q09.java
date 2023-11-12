class Celula{
    public int elemento;
    public Celula esq;
    public Celula dir;
    public Celula sup;
    public Celula inf;
    
    Celula(){
        elemento = 0;
        esq = dir = sup = inf = null;
    }

    Celula (Celula esq, Celula dir, Celula sup, Celula inf){
         this.elemento = 0;
         this.esq = esq;
         this.dir = dir;
         this.sup = sup;
         this.inf = inf;
    }


    Celula (int elemento, Celula esq, Celula dir, Celula sup, Celula inf){
         this.elemento = elemento;
         this.esq = esq;
         this.dir = dir;
         this.sup = sup;
         this.inf = inf;
    }
}

class Matriz{
    public Celula inicio;
    public int lin, col;

    //construtores
    Matriz(){
        this(3,3);
    }

    Matriz(int lin, int col){
        this.lin = lin;
        this.col = col;
        inicio = new Celula();
        Celula i = inicio;
        
        //criacao primeira linha
        for(int l=1; l<lin; l++){
            i.inf = new Celula(null, null, i, null);
            i = i.inf;
        }

        i = inicio;
        //criacao proximas linhas
        for (int c=1; c<col; c++){
            Celula iTmp = i;
            i.dir = new Celula(i, null, null, null);
            Celula jTmp = i.dir;
            for(int l=1; l<lin; l++){
                iTmp = iTmp.inf;
                jTmp.inf = new Celula(iTmp, null, jTmp, null);
                jTmp = jTmp.inf;
                iTmp.dir = jTmp;
            } 
            i = i.dir;
        }
    }
    
    //Operacoes entre matrizes
    public Matriz soma(Matriz mat) throws Exception{
        if(mat.lin != this.lin || mat.col != this.col)
            throw new Exception("Matriz incompativel (linha ou coluna diferente)");
        Matriz resultado = new Matriz(lin, col);
        for(Celula i1 = inicio, i2 = mat.inicio, iF = resultado.inicio; i1 != null; i1 = i1.dir, i2 = i2.dir, iF = iF.dir){
            for(Celula j1 = i1, j2 = i2, jF = iF; j1 != null; j1 = j1.inf, j2 = j2.inf, jF = jF.inf){
                jF.elemento = j1.elemento + j2.elemento;
            }
        }
        return resultado;
    }
    public Matriz multiplicacao(Matriz mat) throws Exception{
        if(mat.lin != this.lin || mat.col != this.col)
            throw new Exception("Matriz incompativel (linha ou coluna diferente)");
        Matriz resultado = new Matriz(lin, col);
        for(Celula i1 = inicio, i2 = mat.inicio, iF = resultado.inicio; i1 != null; i1 = i1.dir, i2 = i2.dir, iF = iF.dir){
            for(Celula j1 = i1, j2 = i2, jF = iF; j1 != null; j1 = j1.inf, j2 = j2.inf, jF = jF.inf){
                jF.elemento = j1.elemento * j2.elemento;
            }
        }
        return resultado;
    }

    //Mostrar na tela
    public void print(){
        for(Celula l = inicio; l != null; l = l.inf){
            for(Celula c = l; c != null; c = c.dir){
                MyIO.print(c.elemento + " ");
            }
            MyIO.print("\n");
        }
    }
    public void printDiagonalPrincipal(){
        for(Celula i = inicio; i != null; i = i.inf){
            MyIO.print(i.elemento + " ");
            if(i.dir != null){
                i = i.dir;
            } else {
                i = null;
            }
        }
    }
    public void printDiagonalSecundaria(){
        Celula i = inicio;
        for(i = i; i.dir != null; i = i.dir);
        for(i = i; i != null; i = i.inf){
            MyIO.print(i.elemento + " ");
            if(i.esq != null){
                i = i.esq;
            } else {
                i = null;
            }
        }

    }
}
