# Guia de Implementação

## Adicionando uma Nova Entidade
1. Crie uma nova classe em `src/entity/` estendendo `Entity`.
2. Implemente os métodos `update()` e `render()`.
3. Registre a entidade em `Game.java` usando `addEntity()`.

## Criando um Mapa
Atualmente, os mapas são gerados via código em `Game.createTestMap()`.
*Futuro: Carregar mapas de arquivos de texto/JSON.*

## Padrões de Código
- **Linguagem**: Java
- **Estilo**: Convenções de nomenclatura padrão Java (CamelCase para classes, camelCase para métodos/variáveis).
- **Comentários**: Javadoc para métodos públicos e classes.
