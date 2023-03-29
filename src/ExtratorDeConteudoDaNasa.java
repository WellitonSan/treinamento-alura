import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {
    
    public List<Conteudo> extraiConteudos (String json) {

        //Extraindo dados
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        //return listaDeAtributos.stream()
            //.map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url")))
            //.toList();

        List<Conteudo> listaconteudos = new ArrayList<>();
        
        //popular lista
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");            
            var conteudo = new Conteudo (titulo, urlImagem);

            listaconteudos.add(conteudo);
        }
        return listaconteudos;
    }    
}
