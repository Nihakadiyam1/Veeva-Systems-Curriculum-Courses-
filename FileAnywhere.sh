#!/bin/bash

echo "Enter file name"
read FILENAME

if find / -type f -name "$FILENAME" 2>/dev/null | grep -q .;
then 
echo "File exists"
else
echo "File not found anywhere"
fi

