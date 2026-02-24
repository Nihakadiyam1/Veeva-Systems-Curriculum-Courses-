#!/bin/bash

echo "Files that are modified in the last 3 days"

find . -type f -mtime -3
