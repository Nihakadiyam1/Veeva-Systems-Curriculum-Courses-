#!/bin/bash

# Check if correct number of arguments are provided
if [ $# -ne 2 ]; then
    echo "Usage: $0 <filename> <search_string>"
    exit 1
fi

filename=$1
search_string=$2

# Check if file exists
if [ ! -f "$filename" ]; then
    echo "File '$filename' does not exist."
    exit 2
fi

# Search for string in the file
echo "Searching for '$search_string' in $filename..."
grep -n "$search_string" "$filename"

# Check if string was found
if [ $? -ne 0 ]; then
    echo "String not found."
fi
