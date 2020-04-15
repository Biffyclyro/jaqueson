public class Teste {
    private String nome;
    private double numero;
    private boolean ligado;
    private int array[] = {1,2,3};
    private Outro outro = new Outro();


    public Teste(String nome, int numero, boolean ligado) {
        this.nome = nome;
        this.numero = numero;
        this.ligado = ligado;

    }

    @JSON
    public int[] getArray() {
        return array;
    }

    @JSON
    public Outro getOutro() {
        return outro;
    }

    @JSON
    public String getNome() {
        return nome;
    }

    @JSON
    public double getNumero() {
        return numero;
    }

    @JSON
    public boolean isLigado() {
        return ligado;
    }


}

