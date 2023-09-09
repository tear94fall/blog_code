#!/bin/bash

target_dir="demo demo2"

for dir in $target_dir; do
    cd $dir;

    ./gradlew clean build

    cd ..
done