package br.com.pedrohscramos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;


import static br.com.pedrohscramos.Analise.analyzePhrase;

public class Main {
    private static long loadTime;
    private static long phraseCheckTime;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        if (args.length < 3) {
            System.out.println("Uso: java -jar cli.jar analyze --depth <n> \"{frase}\"");
            return;
        }

        String command = args[0];
        if (!command.equals("analyze")) {
            System.out.println("Comando não encontrado: " + command);
            return;
        }

        // Argumentos
        int depth = Integer.parseInt(args[2]);
        boolean verbose = args.length == 5 && args[3].equals("--verbose");
        String frase = args[verbose ? 4 : 3];

        // Carregar o arquivo JSON a partir de resources/dicts/palavras.json
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream jsonFile = classLoader.getResourceAsStream("dicts/palavras.json");

        if (jsonFile == null) {
            System.err.println("Arquivo palavras.json não encontrado em resources/dicts.");
            return;
        }

        JsonNode arvore = mapper.readTree(jsonFile);

        loadTime = System.currentTimeMillis() - startTime;

        long phraseStartTime = System.currentTimeMillis();

        // Analisar a frase
        analyzePhrase(arvore, frase, depth);

        phraseCheckTime = System.currentTimeMillis() - phraseStartTime;

        // Exibir métricas se verbose for passado
        if (verbose) {
            System.out.println("Métricas:");
            System.out.println("Tempo de carregamento dos parâmetros (ms): " + loadTime);
            System.out.println("Tempo de verificação da frase (ms): " + phraseCheckTime);
        }
    }

}