# Building Clash Game APK

## Prerequisites

Before building the APK, ensure you have the following installed:

### 1. Java Development Kit (JDK)
- **Required**: JDK 8 or higher (JDK 17 recommended)
- **Download**: https://adoptium.net/

Check if installed:
```bash
java -version
```

### 2. Android SDK
- **Required**: Android SDK with API level 23 (minimum) and 33 (target)
- **Download**: https://developer.android.com/studio

Options:
- Install Android Studio (includes SDK)
- Or install command-line tools only

### 3. Environment Variables
Set the `ANDROID_HOME` environment variable:

**Linux/Mac:**
```bash
export ANDROID_HOME=/path/to/android/sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

Add to `~/.bashrc` or `~/.zshrc` to make permanent.

**Windows:**
```cmd
setx ANDROID_HOME "C:\Users\YourUser\AppData\Local\Android\Sdk"
```

### 4. Accept Android SDK Licenses
```bash
$ANDROID_HOME/tools/bin/sdkmanager --licenses
```

## Building the APK

### Debug Build (for testing)
This build is for development and testing purposes:

```bash
./gradlew assembleDebug
```

Output location:
```
android/build/outputs/apk/debug/android-debug.apk
```

### Release Build (for distribution)
This build is optimized for distribution:

```bash
./gradlew assembleRelease
```

Output location:
```
android/build/outputs/apk/release/android-release-unsigned.apk
```

**Note**: Release APKs need to be signed before distribution.

## Installing on Device

### Via USB
1. Enable USB Debugging on your Android device:
   - Go to Settings > About Phone
   - Tap "Build Number" 7 times to enable Developer Mode
   - Go to Settings > Developer Options
   - Enable "USB Debugging"

2. Connect device via USB

3. Install the APK:
```bash
./gradlew installDebug
```

Or manually:
```bash
adb install android/build/outputs/apk/debug/android-debug.apk
```

### Via File Transfer
1. Copy the APK to your device
2. Open the APK file on your device
3. Allow installation from unknown sources if prompted
4. Install the app

## Signing Release APKs

For distribution, you need to sign the release APK:

### 1. Generate a Keystore
```bash
keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-alias
```

### 2. Sign the APK
```bash
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 -keystore my-release-key.jks android/build/outputs/apk/release/android-release-unsigned.apk my-alias
```

### 3. Align the APK (optional but recommended)
```bash
zipalign -v 4 android-release-unsigned.apk clash-game-release.apk
```

## Troubleshooting

### Build Fails with "SDK not found"
- Ensure ANDROID_HOME is set correctly
- Verify SDK is installed at that location
- Accept SDK licenses

### Build Fails with Network Errors
- Check internet connection
- Gradle needs to download dependencies on first build
- Try again with: `./gradlew clean build --refresh-dependencies`

### Gradle Daemon Issues
```bash
./gradlew --stop
./gradlew clean
```

### Out of Memory
Increase Gradle memory in `gradle.properties`:
```properties
org.gradle.jvmargs=-Xms512M -Xmx4G
```

## Build Commands Reference

| Command | Description |
|---------|-------------|
| `./gradlew tasks` | List all available tasks |
| `./gradlew assembleDebug` | Build debug APK |
| `./gradlew assembleRelease` | Build release APK |
| `./gradlew installDebug` | Build and install debug APK |
| `./gradlew clean` | Clean build artifacts |
| `./gradlew build` | Build all variants |
| `./gradlew --refresh-dependencies` | Refresh dependencies |

## Project Structure

```
clashjogoteste/
├── android/                    # Android-specific code
│   ├── build.gradle           # Android build configuration
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/clashgame/
│   │   │   └── AndroidLauncher.java
│   │   └── res/              # Android resources
│   └── build/                # Build output (gitignored)
│       └── outputs/apk/
├── core/                     # Game logic (platform-independent)
│   ├── build.gradle
│   └── src/com/clashgame/
├── build.gradle              # Root build configuration
├── settings.gradle           # Project modules
└── gradle.properties         # Gradle settings
```

## Dependencies

The project uses:
- **LibGDX 1.11.0**: Game framework
- **Android SDK 33**: Target platform
- **Gradle 7.6**: Build system

Dependencies are automatically downloaded by Gradle on first build.

## APK Size

Expected APK sizes:
- Debug: ~15-20 MB
- Release (unsigned): ~10-15 MB
- Release (signed & aligned): ~10-15 MB

## Testing

After installation, test all features:
1. ✅ Main menu navigation
2. ✅ Building placement
3. ✅ Resource collection
4. ✅ Troop training
5. ✅ Combat system
6. ✅ Save/load functionality

## Distribution

For public distribution:
1. Sign the release APK with your keystore
2. Test on multiple devices
3. Upload to Google Play Console
4. Follow Google Play policies

## Support

For issues with:
- **LibGDX**: https://libgdx.com/
- **Android**: https://developer.android.com/
- **Gradle**: https://gradle.org/

## Version Information

- **App Version**: 1.0.0
- **Min SDK**: 23 (Android 6.0)
- **Target SDK**: 33 (Android 13)
- **Package**: com.clashgame
