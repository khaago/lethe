#!/bin/sh

while : 
do
rsync -au --delete ./ ../lethe2
sleep 10
echo "~~~~SYNCED~~~~~"
done
