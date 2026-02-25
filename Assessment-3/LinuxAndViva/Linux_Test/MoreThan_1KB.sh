
#!/bin/bash

RESULT=$(find . -type f -size +1k)

if [ -n "$RESULT" ];
then
echo " Files larger than 1KB are : "
echo " $RESULT"
else
echo " No Files Larger than 1KB"
fi

