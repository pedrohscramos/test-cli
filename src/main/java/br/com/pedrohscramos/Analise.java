package br.com.pedrohscramos;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;


public class Analise {


    // Função que analisa a frase e busca o nível especificado na árvore
    static void analyzePhrase(JsonNode arvore, String frase, int depth) {
        String[] palavras = frase.split("\\s+");
        boolean found = false;

        for (String palavra : palavras) {
            if (buscarNaArvore(arvore, palavra, depth, 0)) {
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nenhuma palavra da frase foi encontrada no nível de profundidade especificado.");
        }
    }

    // Função que busca na árvore de palavras
    private static boolean buscarNaArvore(JsonNode node, String palavra, int targetDepth, int currentDepth) {
        if (currentDepth == targetDepth) {
            if (node.isArray()) {
                for (JsonNode item : node) {
                    if (item.asText().equalsIgnoreCase(palavra)) {
                        System.out.println("Palavra encontrada no nível " + currentDepth + ": " + item.asText());
                        return true;
                    }
                }
            }
            return false;
        }

        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String field = fieldNames.next();
                JsonNode childNode = node.get(field);
                if (buscarNaArvore(childNode, palavra, targetDepth, currentDepth + 1)) {
                    return true;
                }
            }
        } else if (node.isArray()) {
            for (JsonNode arrayItem : node) {
                if (buscarNaArvore(arrayItem, palavra, targetDepth, currentDepth)) {
                    return true;
                }
            }
        }

        return false;
    }
}
