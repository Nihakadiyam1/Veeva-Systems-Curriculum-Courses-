#!/bin/bash

# Check if correct number of arguments are provided
if [ $# -ne 2 ]; then
    echo "Usage: $0 <html_file> <tag_name>"
    exit 1
fi

html_file=$1
tag=$2

# Check if file exists
if [ ! -f "$html_file" ]; then
    echo "File '$html_file' does not exist."
    exit 2
fi

# Extract text between the specified HTML tags
echo "Extracting text between <$tag> and </$tag>..."
grep -oP "(?<=<$tag>).*?(?=</$tag>)" "$html_file"

