# Clash Game - Jogo Estilo Clash of Clans

🎮 Jogo mobile Android no estilo Clash of Clans

## Sobre o Projeto
Este é um jogo de estratégia mobile onde você constrói sua base, treina tropas e ataca outros jogadores!

## ✨ Features Implementadas
- ⚒️ Sistema de construção de base
  - 🏰 Centro da Vila com sistema de upgrade
  - ⚔️ Quartéis para treinar tropas
  - 🎯 Canhões para defesa
- 💰 Gerenciamento de recursos
  - Ouro para construções
  - Elixir para tropas
  - Sistema de coleta
- 🗡️ Sistema de tropas e combate
  - Treinamento de tropas
  - Sistema de ataque
  - Saque de recursos
- 📊 Sistema de níveis e progressão

## 🛠️ Tecnologias
- Java
- Android SDK 34
- Gradle 8.2
- Material Design

## 📋 Pré-requisitos

Para compilar o APK, você precisa ter instalado:

1. **Java JDK 17+**
   - Download: https://adoptium.net/

2. **Android SDK** (ou Android Studio)
   - Download: https://developer.android.com/studio

## 🚀 Como Compilar

### Método 1: Linha de Comando (Recomendado)

```bash
# 1. Clone o repositório (se ainda não clonou)
git clone https://github.com/IsAaCxSiLvA/clashjogoteste.git
cd clashjogoteste

# 2. Compile o APK
gradlew.bat assembleRelease

# 3. O APK estará em: app/build/outputs/apk/release/app-release.apk
```

### Método 2: Android Studio

1. Abra o Android Studio
2. File > Open > Selecione a pasta `clashjogoteste`
3. Build > Build Bundle(s) / APK(s) > Build APK(s)
4. O APK estará em `app/build/outputs/apk/debug/`

## 📱 Instalar no Android

Após compilar:

```bash
# Conecte seu celular Android via USB com Depuração USB habilitada
adb install app/build/outputs/apk/release/app-release.apk
```

Ou transfira o APK para o celular e instale manualmente.

## 🎮 Como Jogar

1. **Colete Ouro**: Use para construir estruturas
2. **Melhore o Centro da Vila**: Desbloqueia mais construções
3. **Construa Quartéis**: Necessário para treinar tropas
4. **Treine Tropas**: Usa Elixir
5. **Ataque**: Use suas tropas para saquear recursos!
6. **Construa Defesas**: Canhões protegem sua base

## 🎯 Dicas

- Sempre melhore o Centro da Vila primeiro
- Construa vários quartéis para treinar mais tropas
- Ataque regularmente para ganhar recursos
- Balance investimento entre construções e tropas

## 📝 Estrutura do Projeto

```
clashjogoteste/
├── app/
│   ├── src/main/
│   │   ├── java/com/clashgame/
│   │   │   ├── MainActivity.java      # Tela principal
│   │   │   └── GameManager.java       # Lógica do jogo
│   │   ├── res/
│   │   │   ├── layout/                # Interface
│   │   │   └── values/                # Cores, strings, temas
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle/
├── build.gradle
└── settings.gradle
```

## 🔧 Troubleshooting

**Erro: JAVA_HOME não configurado**
```bash
# Configure a variável de ambiente JAVA_HOME apontando para seu JDK
```

**Erro: SDK não encontrado**
- Instale o Android SDK ou Android Studio
- Configure ANDROID_HOME nas variáveis de ambiente

**APK não instala**
- Habilite "Instalar apps de fontes desconhecidas" no Android
- Verifique se tem espaço suficiente

## 📄 Licença

Projeto livre para uso educacional.

---

# 👨‍💻 Créditos

<div align="center">

Projeto desenvolvido por **Snepdog** ⚡

💻 Full Stack Developer  
🎮 SA-MP Developer  
🛡️ Cybersecurity Enthusiast  

</div>

---

<div align="center">

⭐ Caso goste do projeto, deixe uma estrela no repositório.

</div>
