#!/bin/bash

# Error log file
error_log="error.txt"

# Clear previous error log
> "$error_log"

# Example commands
echo "Starting script..."

# Command 1 (successful)
ls /tmp

# Command 2 (intentional error for demonstration)
ls /nonexistent_directory 2>> "$error_log"

# Command 3 (another example)
cat /etc/passwd

# Command 4 (another intentional error)
grep "something" /nonexistent_file 2>> "$error_log"

echo "Script completed. Check '$error_log' for errors."
