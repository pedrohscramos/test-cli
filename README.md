# CLI Application

## Descrição
Esta aplicação de linha de comando carrega uma estrutura hierárquica de palavras a partir de um arquivo JSON e analisa uma frase fornecida pelo usuário para identificar palavras e profundidades.

## Uso
Para analisar uma frase, execute:

`java -jar cli.jar analyze --depth <n> [--verbose] "{phrase}"`

## Parâmetros
- `--depth <n>`: Nível de profundidade da árvore.
- `--verbose` (opcional): Exibe informações detalhadas sobre o tempo de carregamento e análise.
- `"{phrase}"`: Texto a ser analisado.

## Exemplo
`java -jar cli.jar analyze --depth 2 "Eu amo papagaios" --verbose`

## Estrutura do Projeto
- `src/main/java/br/com/pedrohscramos/`: Código-fonte principal.
- `src/main/resources/dicts/`: Arquivo JSON com a estrutura hierárquica.
- `pom.xml`: Configuração do Maven.

