import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        

        //fazer uma conexão HTTP (protocolo) buscando top 250 filmes da API
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        // extrair só os dados necessarios (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.size());
        //System.out.println(listaDeFilmes.get(0));
     
        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        //criando diretorio se ele nao existe para depositar figura
        var diretorio = new File("saida");
        diretorio.mkdir();

        for (Map<String,String> filme : listaDeFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            
            String textoFigurinha;
            InputStream imagemAvaliativa;
            if (classificacao >= 9.0){
                textoFigurinha = "Top";
                imagemAvaliativa = new FileInputStream(new File("sobreposicao/joia.png"));
            } else {
                textoFigurinha = "Ruim";
                imagemAvaliativa = new FileInputStream(new File("sobreposicao/negativo.png"));
            }

            
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo, textoFigurinha, imagemAvaliativa);

            System.out.println(titulo);
            System.out.println(classificacao);
            System.out.println("\n"); 
            
        }

    }
}
