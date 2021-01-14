#!/bin/bash
curPath=`pwd`
mkdir /data/detection/
cp ${curPath}/test/imagenet_slim_labels.txt /data/detection/
cp ${curPath}/src/inception_v3.dlc /data/detection/

cp ${curPath}/bin/smartcashier /data/
chmod 755 /data/smartcashier