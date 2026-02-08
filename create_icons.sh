#!/bin/bash
# Create simple PNG icons using ImageMagick if available, or placeholder otherwise

# Check if ImageMagick is available
if command -v convert &> /dev/null; then
    echo "Creating app icons with ImageMagick..."
    
    # Create icons at different resolutions
    convert -size 48x48 xc:#FFA500 -fill white -pointsize 24 -gravity center -draw "text 0,0 'C'" android/src/main/res/mipmap-mdpi/ic_launcher.png
    convert -size 72x72 xc:#FFA500 -fill white -pointsize 36 -gravity center -draw "text 0,0 'C'" android/src/main/res/mipmap-hdpi/ic_launcher.png
    convert -size 96x96 xc:#FFA500 -fill white -pointsize 48 -gravity center -draw "text 0,0 'C'" android/src/main/res/mipmap-xhdpi/ic_launcher.png
    convert -size 144x144 xc:#FFA500 -fill white -pointsize 72 -gravity center -draw "text 0,0 'C'" android/src/main/res/mipmap-xxhdpi/ic_launcher.png
    convert -size 192x192 xc:#FFA500 -fill white -pointsize 96 -gravity center -draw "text 0,0 'C'" android/src/main/res/mipmap-xxxhdpi/ic_launcher.png
    
    echo "Icons created successfully!"
else
    echo "ImageMagick not available, keeping placeholder icons"
fi
