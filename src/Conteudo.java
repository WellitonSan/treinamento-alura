public class Conteudo {
    private final String titulo;
    private final String urlImagem;

    //Construtor
    public Conteudo(String titulo, String urlImagem) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
    }

    //Get
    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }    
}
