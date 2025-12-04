# Funcionalidade

## Como Funciona

### O Loop do Jogo
O jogo roda em um loop de passo de tempo fixo controlado por um `javax.swing.Timer`.
1. **Entrada**: Entradas são verificadas/tratadas.
2. **Atualização**: A lógica do jogo é atualizada (movimento, física).
3. **Renderização**: A tela é repintada.

### Controles
*A ser implementado/documentado.*
- **WASD / Setas**: Mover
- **Espaço**: Pular/Ação
- **Mouse**: Interagir

## Mecânicas
- **Colisão**: Detecção de colisão AABB (Axis-Aligned Bounding Box).
- **Sistema de Tiles**: Mundo baseado em grade.
