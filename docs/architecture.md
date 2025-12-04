# Arquitetura

O projeto é um motor de jogo 2D construído usando Java Swing e AWT. Ele segue uma arquitetura padrão de loop de jogo com separação de responsabilidades entre lógica, renderização e entrada.

## Componentes Principais

### Loop do Jogo (`src/Game.java`)
A classe `Game` estende `JPanel` e implementa `ActionListener`. Ela serve como o ponto de entrada principal e orquestra o loop do jogo.
- **Responsabilidades**:
  - Gerenciar o temporizador do jogo.
  - Atualizar o estado do jogo (`update()`).
  - Renderizar o quadro (`paintComponent()`).
  - Lidar com entrada de alto nível.

### Sistema de Entidades (`src/entity/`)
Entidades representam objetos dinâmicos no mundo do jogo.
- **`Entity`**: Classe base para todos os objetos do jogo.
- **`Player`**: Implementação específica para o personagem controlado pelo usuário.
- **`Item`**: Representa itens interativos.

### Mundo e Tiles (`src/tiles/`)
O mundo do jogo é construído usando um sistema baseado em tiles.
- **`TileMap`**: Gerencia a grade de tiles que compõem o nível.

### Sistema de Mapas e Áreas
O jogo utiliza um sistema de áreas interconectadas (estilo Zelda clássico ou Stardew Valley).
- **Estrutura**: Cada área é um arquivo de texto separado (ex: `vila.txt`, `floresta.txt`).
- **Warps (Transições)**: As conexões entre áreas são definidas dentro do próprio arquivo da área.
  - Um Warp define: Coordenada de origem (X, Y), Arquivo de destino, e Coordenada de destino na nova área.
- **Funcionamento**:
  1. O `TileMap` carrega o arquivo `.txt`.
  2. O jogo verifica se o jogador pisou em um tile de Warp.
  3. Se sim, o mapa atual é descarregado e o novo mapa é carregado, posicionando o jogador na coordenada de destino.

### Física e Colisão (`src/collision/`)
- **`CollisionSystem`**: Lida com detecção e resolução de colisão entre entidades e o mapa de tiles.

### Tratamento de Entrada (`src/input/`)
- **`InputHandler`**: Captura eventos de teclado e mouse para controlar o jogador e o estado do jogo.

## Estrutura de Diretórios
- `src/core`: Utilitários principais e constantes (`GameConstants`, `Vector2`).
- `src/entity`: Entidades do jogo.
- `src/tiles`: Gerenciamento de mapa e tiles.
- `src/collision`: Motor de física.
- `src/input`: Processamento de entrada.
- `src/geometry`: Primitivas geométricas.
