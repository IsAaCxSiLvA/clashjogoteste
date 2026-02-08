#!/bin/bash
echo "=== Clash Game - Syntax Verification ==="
echo ""
echo "Checking all Java files for syntax errors..."
echo ""

error_count=0
total_files=0

for file in $(find . -name "*.java" -not -path "./.gradle/*"); do
    total_files=$((total_files + 1))
    echo "Checking: $file"
    javac -cp "." "$file" 2>/dev/null
    if [ $? -ne 0 ]; then
        error_count=$((error_count + 1))
    fi
done

echo ""
echo "=== Results ==="
echo "Total Java files: $total_files"
echo "Files with errors: $error_count"
echo ""

if [ $error_count -eq 0 ]; then
    echo "✓ All files have valid Java syntax!"
else
    echo "✗ Some files have syntax errors"
fi
