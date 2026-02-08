# Clash Game - Jogo Estilo Clash of Clans

🎮 Jogo mobile Android no estilo Clash of Clans desenvolvido com LibGDX

## 📋 Sobre o Projeto
Este é um jogo de estratégia mobile onde você constrói sua base, gerencia recursos, treina tropas e ataca bases inimigas.

## ✨ Features Implementadas

### Sistema de Recursos
- ✅ **Ouro**: recurso principal para construções (inicia com 1000)
- ✅ **Elixir**: recurso para treinar tropas (inicia com 1000)
- ✅ **Gemas**: moeda premium (inicia com 50)
- ✅ Geração automática de recursos ao longo do tempo
- ✅ Capacidade máxima de armazenamento

### Sistema de Construção de Base
Edifícios disponíveis:
- ✅ **Prefeitura (Town Hall)**: edifício principal (nível 1-5)
- ✅ **Minas de Ouro**: geram ouro automaticamente
- ✅ **Coletores de Elixir**: geram elixir automaticamente
- ✅ **Depósitos**: aumentam capacidade de armazenamento
- ✅ **Quartéis**: interface para treinar tropas
- ✅ **Muralhas**: defesa básica
- ✅ **Torres de Arqueiro**: defesa que ataca invasores

Funcionalidades:
- ✅ Sistema de grid 40x22 para posicionamento
- ✅ Sistema de posicionamento com validação
- ✅ Sistema de upgrade de edifícios (5 níveis)
- ✅ Tempo de construção/upgrade
- ✅ Custos em recursos para cada ação
- ✅ Visualização de HP e níveis

### Sistema de Tropas
Tropas disponíveis:
- ✅ **Bárbaro**: tropa corpo a corpo (HP: 100, Dano: 15)
- ✅ **Arqueira**: tropa de ataque à distância (HP: 60, Dano: 12, Alcance: 5)
- ✅ **Gigante**: tropa tanque (HP: 500, Dano: 25)

Funcionalidades:
- ✅ Treinar tropas no quartel (custo em elixir)
- ✅ Tempo de treinamento (5 segundos por tropa)
- ✅ Capacidade máxima de tropas (20)
- ✅ Estatísticas detalhadas por tipo

### Sistema de Combate
- ✅ Modo de ataque contra base NPC
- ✅ IA básica para tropas (pathfinding para edifício mais próximo)
- ✅ Sistema de dano e HP para tropas e edifícios
- ✅ Tropas atacam edifícios automaticamente
- ✅ Condições de vitória: destruir prefeitura ou 50%+ da base
- ✅ Recompensas: 500 ouro e 300 elixir ao vencer
- ✅ Visualização de porcentagem de destruição

### Interface do Usuário
Telas implementadas:
- ✅ **Menu Principal**: novo jogo, continuar, sair
- ✅ **Tela Principal**: visualização e gerenciamento da base
- ✅ **Menu de Construção**: lista de edifícios com custos
- ✅ **HUD**: exibição de recursos em tempo real
- ✅ **Tela de Quartéis**: treinar tropas com interface visual
- ✅ **Tela de Ataque**: batalha contra base inimiga
- ✅ Botões: construir, atacar, salvar, quartéis

### Sistema de Salvamento
- ✅ Salvamento automático a cada 30 segundos
- ✅ Salvamento usando SharedPreferences
- ✅ Dados salvos:
  - Posição e nível de todos os edifícios
  - HP de edifícios
  - Quantidade de recursos
  - Capacidade de armazenamento
  - Estado de construções em andamento

## 🎮 Como Jogar

### Menu Principal
- **New Game**: inicia um novo jogo com base inicial
- **Continue**: continua jogo salvo (se disponível)
- **Exit**: sai do jogo

### Tela Principal (Base)
1. **Build**: abre menu de construção
   - Selecione um edifício
   - Clique no grid para posicionar
   - Requer ouro suficiente
2. **Attack**: inicia batalha contra base inimiga
3. **Save**: salva o jogo manualmente
4. **Barracks**: abre tela de treinamento de tropas

### Quartéis
- Clique em Barbarian, Archer ou Giant para treinar
- Aguarde 5 segundos por treinamento
- Requer elixir suficiente
- Capacidade máxima: 20 tropas

### Batalha
- Clique nos botões para selecionar tipo de tropa
- Clique no mapa para posicionar tropas
- Tropas atacam automaticamente
- Vitória: destruir prefeitura ou 50%+ dos edifícios
- Retreat: retorna à base (derrota)

## 🛠️ Tecnologias Utilizadas
- **LibGDX 1.11.0**: engine de jogo para renderização 2D
- **Java 8**: linguagem de programação
- **Android SDK**: plataforma mobile
  - SDK mínimo: Android 6.0 (API 23)
  - SDK alvo: Android 13+ (API 33)
- **Gradle 7.6**: sistema de build

## 📁 Estrutura do Projeto
```
clashjogoteste/
├── android/                    # Módulo Android
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/clashgame/
│   │   │   └── AndroidLauncher.java
│   │   └── res/               # Resources (ícones, etc)
│   └── build.gradle
├── core/                      # Lógica principal do jogo
│   └── src/com/clashgame/
│       ├── ClashGame.java     # Classe principal
│       ├── screens/           # Telas do jogo
│       │   ├── MainMenuScreen.java
│       │   ├── GameScreen.java
│       │   ├── BarracksScreen.java
│       │   └── BattleScreen.java
│       ├── entities/          # Entidades do jogo
│       │   ├── Building.java
│       │   ├── TownHall.java
│       │   ├── GoldMine.java
│       │   ├── ElixirCollector.java
│       │   ├── Storage.java
│       │   ├── Barracks.java
│       │   ├── Wall.java
│       │   ├── ArcherTower.java
│       │   ├── Resource.java
│       │   ├── Troop.java
│       │   ├── Barbarian.java
│       │   ├── Archer.java
│       │   └── Giant.java
│       ├── systems/           # Sistemas do jogo
│       │   ├── ResourceSystem.java
│       │   ├── BuildingSystem.java
│       │   ├── CombatSystem.java
│       │   └── SaveSystem.java
│       └── utils/
│           └── Constants.java
├── build.gradle               # Build raiz
├── settings.gradle
├── gradle.properties
└── README.md
```

## 🔨 Como Compilar

### Pré-requisitos
- Java JDK 8 ou superior
- Android SDK instalado
- Variável de ambiente ANDROID_HOME configurada

### Build do APK de Debug
```bash
./gradlew assembleDebug
```

APK gerado em: `android/build/outputs/apk/debug/android-debug.apk`

### Build do APK de Release
```bash
./gradlew assembleRelease
```

APK gerado em: `android/build/outputs/apk/release/android-release.apk`

### Instalar no Dispositivo
```bash
# Conecte o dispositivo Android via USB e execute:
./gradlew installDebug
```

### Limpar Build
```bash
./gradlew clean
```

## 🎯 Custos de Edifícios

| Edifício | Custo (Ouro) |
|----------|--------------|
| Prefeitura | 0 (grátis) |
| Mina de Ouro | 200 |
| Coletor de Elixir | 200 |
| Depósito | 300 |
| Quartéis | 500 |
| Muralha | 50 |
| Torre de Arqueiro | 400 |

## 💪 Custos de Tropas

| Tropa | Custo (Elixir) | HP | Dano | Velocidade | Alcance |
|-------|----------------|-----|------|------------|---------|
| Bárbaro | 50 | 100 | 15 | 40 | 1 |
| Arqueira | 80 | 60 | 12 | 35 | 5 |
| Gigante | 200 | 500 | 25 | 20 | 1 |

## 🚀 Próximas Melhorias

### Gameplay
- [ ] Mais tipos de tropas
- [ ] Mais tipos de defesas
- [ ] Sistema de feitiços
- [ ] Multiplayer (atacar bases de outros jogadores)
- [ ] Clãs e guerras de clãs
- [ ] Eventos e missões

### Técnico
- [ ] Sprites e assets gráficos customizados
- [ ] Efeitos sonoros e música
- [ ] Animações de combate
- [ ] Melhor IA para tropas (pathfinding A*)
- [ ] Tutorial interativo
- [ ] Conquistas e progressão

### UI/UX
- [ ] Melhorias visuais na interface
- [ ] Transições de tela animadas
- [ ] Feedback visual para ações
- [ ] Sistema de notificações

## 📄 Licença
Este é um projeto educacional desenvolvido para demonstrar desenvolvimento de jogos mobile com LibGDX.

## 👨‍💻 Desenvolvimento
- Engine: LibGDX
- Linguagem: Java
- Plataforma: Android
- Versão: 1.0.0