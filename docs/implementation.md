# Guia de Implementação

## Adicionando uma Nova Entidade
1. Crie uma nova classe em `src/entity/` estendendo `Entity`.
2. Implemente os métodos `update()` e `render()`.
3. Registre a entidade em `Game.java` usando `addEntity()`.

## Criando um Mapa
O sistema de mapas utiliza arquivos de texto para definir o layout e as conexões entre áreas.

### Formato do Arquivo de Mapa (.txt)
O arquivo é dividido em cabeçalho (configurações) e o layout visual.

```text
NAME: Nome da Área
WIDTH: 20
HEIGHT: 15

# SAÍDAS (WARPS)
# Formato: WARP [X] [Y] [ARQUIVO_DESTINO] [X_DESTINO] [Y_DESTINO]
WARP 19 7 floresta.txt 1 7   <-- Exemplo: Warp na posição (19,7) leva para floresta.txt na posição (1,7)

# TILES
# Legenda:
# # = Parede
# . = Chão
# > = Saída Visual (apenas visual, a lógica está no WARP acima)
####################
#..................#
#..................#
#.......P..........#
#..................#
####################
```

### Passos para Criar
1. Crie um novo arquivo `.txt` na pasta de recursos de mapas.
2. Defina o cabeçalho com `NAME`, `WIDTH`, `HEIGHT`.
3. Adicione os `WARP`s necessários para conectar com outras áreas.
4. Desenhe o mapa usando os caracteres definidos na legenda de tiles.

## Padrões de Código
- **Linguagem**: Java
- **Estilo**: Convenções de nomenclatura padrão Java (CamelCase para classes, camelCase para métodos/variáveis).
- **Comentários**: Javadoc para métodos públicos e classes.
