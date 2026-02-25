#!/bin/bash

#file path
echo "Enter file path"
read filepath

#checking

if [ -f "$filepath" ];
then
echo "File exists in the path $filepath "
else
echo "File doesnt Not Exist"
fi

