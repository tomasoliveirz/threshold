# Threshold

**Threshold** é um motor/framework de jogo 2D em Java (Swing/AWT) focado em performance e escalabilidade.

## 🚀 Como Executar

Para compilar e rodar o projeto, use o seguinte comando no terminal na raiz do projeto:

```powershell
javac -d bin -sourcepath src src/Game.java; java -cp bin Game
```

*Nota: Certifique-se de ter o JDK instalado e configurado no seu PATH.*

---

## ✨ Funcionalidades e Mudanças Recentes

O projeto passou por melhorias significativas para garantir robustez e facilidade de expansão:

1.  **Sistema de Física e Colisão (`CollisionSystem`)**:
    *   Implementação de colisão AABB (Axis-Aligned Bounding Box) com resolução de "slide" (deslizar nas paredes).
    *   Suporte a gravidade e pulo com detecção precisa de chão e teto.
    *   Correção de offsets de colisão para sprites que não ocupam todo o tile.

2.  **Carregamento de Mapas (`LevelLoader`)**:
    *   Separação da lógica de carregamento da classe `Game`.
    *   Mapas agora são carregados de arquivos de texto (`res/maps/`), permitindo edição fácil do layout sem recompilar o código.
    *   Formato simples: `#` para paredes, `P` para o jogador.

3.  **Organização de Código**:
    *   Estrutura modular: `core`, `entity`, `tiles`, `collision`, `input`, `level`.
    *   Uso de `Vector2` imutável para operações matemáticas seguras.

---

## 🛠️ Guia de Implementação

### 1. Criando um Novo Mapa

Vá até a pasta `res/maps/` e crie um arquivo `.txt` (ex: `nivel2.txt`).

O formato é visual, onde cada caractere representa um tile:
```text
####################
#..................#
#...P..............#
#.......#####......#
#..................#
####################
```
*   `#`: Parede (Vermelha)
*   `P`: Ponto de Início do Jogador
*   `.`: Espaço vazio (Ar)

Para carregar este mapa, edite o método `createTestMap` em `src/Game.java`:
```java
level.LevelLoader.LevelData data = level.LevelLoader.loadLevel("res/maps/nivel2.txt");
```

### 2. Criando uma Nova Entidade

Para adicionar um inimigo ou objeto:

1.  Crie uma nova classe em `src/entity/` estendendo `Entity`.
2.  Implemente o método `update()` (lógica) e `render(Graphics2D g)` (desenho).

**Exemplo (`Inimigo.java`):**
```java
package entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Inimigo extends Entity {

    public Inimigo(double x, double y) {
        // x, y, largura, altura
        super(x, y, 32, 32); 
    }

    @Override
    public void update() {
        // Lógica simples de movimento
        move(1, 0); // Move para a direita
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect((int)getX(), (int)getY(), width, height);
    }
}
```

### 3. Registrando a Entidade

No método `init` de `Game.java` ou no `LevelLoader`, instancie e adicione sua entidade:

```java
Inimigo inimigo = new Inimigo(200, 300);
addEntity(inimigo);
```

---

## 📂 Estrutura do Projeto

*   `src/Game.java`: Ponto de entrada e loop principal.
*   `src/core/`: Constantes e utilitários (`Vector2`).
*   `src/entity/`: Classes de objetos do jogo (`Entity`, `Player`).
*   `src/collision/`: Lógica de física (`CollisionSystem`).
*   `src/tiles/`: Gerenciamento de mapa e blocos (`TileMap`).
*   `src/level/`: Carregamento de arquivos (`LevelLoader`).
*   `src/input/`: Controle de teclado (`InputHandler`).
