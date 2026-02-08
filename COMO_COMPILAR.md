# 🚀 GUIA DE COMPILAÇÃO - CLASH GAME APK

## ⚠️ PRÉ-REQUISITOS NECESSÁRIOS

### 1. Java JDK (OBRIGATÓRIO)
✅ Você já tem: OpenJDK 11 instalado

### 2. Android SDK (OBRIGATÓRIO)

#### Opção A: Instalar Android Studio (RECOMENDADO)
1. Baixe em: https://developer.android.com/studio
2. Instale o Android Studio
3. Abra o Android Studio
4. Vá em: Tools > SDK Manager
5. Instale:
   - Android SDK Platform 34
   - Android SDK Build-Tools 34.0.0
   - Android SDK Platform-Tools

#### Opção B: Apenas SDK (Command Line Tools)
1. Baixe em: https://developer.android.com/studio#command-tools
2. Extraia em: `C:\Android\sdk`
3. Configure variável de ambiente:
   ```
   ANDROID_HOME=C:\Android\sdk
   ```

## 🔧 CONFIGURAÇÃO

### 1. Configure o Android SDK no projeto

Edite o arquivo `local.properties` e coloque o caminho correto do seu Android SDK:

```properties
sdk.dir=C\:\\Users\\isaac\\AppData\\Local\\Android\\Sdk
```

Ou se instalou em outro lugar:
```properties
sdk.dir=C\:\\Android\\sdk
```

### 2. Baixe o Gradle Wrapper (se necessário)

Execute no PowerShell:
```powershell
# Instale o Gradle manualmente
choco install gradle
# OU baixe de: https://gradle.org/releases/
```

Ou use o Gradle do Android Studio.

## 📦 COMPILAR O APK

### Método 1: Via Android Studio (MAIS FÁCIL)

1. Abra o Android Studio
2. File > Open > Selecione a pasta `clashjogoteste`
3. Aguarde o Gradle sincronizar (pode demorar)
4. Build > Build Bundle(s) / APK(s) > Build APK(s)
5. Clique em "locate" quando aparecer a notificação
6. O APK estará em: `app/build/outputs/apk/debug/app-debug.apk`

### Método 2: Linha de Comando

```powershell
# No diretório do projeto
cd C:\Users\isaac\clashjogoteste

# Compile o APK
.\gradlew.bat assembleDebug

# Se der erro, tente:
gradle assembleDebug

# O APK ficará em:
# app\build\outputs\apk\debug\app-debug.apk
```

### Método 3: Sem Gradle (usando Android Studio CLI)

```powershell
# Configure o PATH (ajuste o caminho conforme sua instalação)
$env:PATH += ";C:\Program Files\Android\Android Studio\jbr\bin"
$env:ANDROID_HOME = "C:\Users\isaac\AppData\Local\Android\Sdk"

# Compile direto
cd app\src\main\java
javac -d ..\..\..\..\build\classes com\clashgame\*.java
```

## 📱 INSTALAR NO CELULAR

### Pré-requisito: Habilitar Depuração USB

1. No celular Android:
   - Configurações > Sobre o telefone
   - Toque 7x em "Número da compilação"
   - Volte e entre em "Opções do desenvolvedor"
   - Ative "Depuração USB"

### Método 1: Via ADB (Cabo USB)

```powershell
# Conecte o celular via USB
# Verifique se o celular foi detectado:
adb devices

# Instale o APK:
adb install app\build\outputs\apk\debug\app-debug.apk

# Se já estiver instalado:
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

### Método 2: Manualmente

1. Copie o APK para o celular via cabo USB
2. No celular, abra o gerenciador de arquivos
3. Toque no APK
4. Permita "Instalar apps de fontes desconhecidas" se pedido
5. Instale

### Método 3: Via Android Studio

1. Com o celular conectado via USB
2. No Android Studio: Run > Run 'app'
3. Selecione seu celular na lista
4. O app será instalado e aberto automaticamente

## ❌ SOLUÇÕES DE PROBLEMAS

### Erro: "ANDROID_HOME não configurado"

```powershell
# Configure permanentemente:
[System.Environment]::SetEnvironmentVariable("ANDROID_HOME", "C:\Users\isaac\AppData\Local\Android\Sdk", "User")

# Configure apenas para a sessão atual:
$env:ANDROID_HOME = "C:\Users\isaac\AppData\Local\Android\Sdk"
$env:PATH += ";$env:ANDROID_HOME\platform-tools;$env:ANDROID_HOME\tools"
```

### Erro: "SDK location not found"

Crie/edite o arquivo `local.properties` com o caminho correto do SDK.

### Erro: "Gradle wrapper not found"

Instale o Android Studio ou Gradle manualmente.

### Erro: "Build Tools version 34.0.0 not found"

No Android Studio:
- Tools > SDK Manager
- SDK Tools tab
- Marque "Show Package Details"
- Instale: Android SDK Build-Tools 34.0.0

### APK não instala no celular

1. Verifique se permitiu "Fontes desconhecidas"
2. Tente desinstalar versão antiga primeiro
3. Verifique se tem espaço suficiente (mínimo 50MB)

## 🎯 CHECKLIST RÁPIDO

- [ ] Java JDK instalado e no PATH
- [ ] Android SDK instalado
- [ ] ANDROID_HOME configurado
- [ ] local.properties com caminho correto
- [ ] Gradle instalado (ou use Android Studio)
- [ ] Celular em modo desenvolvedor
- [ ] Depuração USB habilitada

## 📞 COMANDOS ÚTEIS

```powershell
# Verificar versão do Java
java -version

# Verificar ADB
adb version

# Listar dispositivos conectados
adb devices

# Ver logs do app em tempo real
adb logcat | Select-String "ClashGame"

# Desinstalar o app
adb uninstall com.clashgame

# Limpar build
.\gradlew.bat clean
```

## 🎮 APÓS INSTALAR

1. Abra o app "Clash Game" no celular
2. Comece coletando ouro
3. Melhore o Centro da Vila
4. Construa quartéis
5. Treine tropas
6. Ataque e conquiste!

---

💡 **DICA**: Se tiver dificuldades, use o Android Studio. É a forma mais fácil e completa!

🔗 **Links Úteis**:
- Android Studio: https://developer.android.com/studio
- JDK: https://adoptium.net/
- ADB: https://developer.android.com/tools/adb

⚔️ Boa sorte compilando seu jogo! ⚔️
