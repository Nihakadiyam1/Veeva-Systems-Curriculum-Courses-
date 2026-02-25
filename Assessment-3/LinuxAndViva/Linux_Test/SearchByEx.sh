
#!/bin/bash

# either extension given or not
if [ -z "$1" ]; then
    echo "Usage: $0 <extension>"
    exit 1
fi

extension=$1

echo "Files with extension: .$extension"

found=false

for file in *."$extension"; do
    if [ -e "$file" ]; then
        echo "$file"
        found=true
    fi
done

if [ "$found" = false ]; then
    echo "No files with .$extension"
fi
