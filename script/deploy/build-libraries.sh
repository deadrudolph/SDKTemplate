#!/usr/bin/env bash

env_name=$NAME
env_secret=$TEMP_TOKEN

for i in "$@"; do
  echo "Building $i"
  ./gradlew "$i" --no-daemon --no-configuration-cache -Dorg.gradle.internal.http.socketTimeout=60000 -Dorg.gradle.internal.http.connectionTimeout=60000 -Pgithub.name="$env_name" -Pgithub.token="$env_secret"
  
  if [ $? -ne 0 ]; then
    exit 1
  fi
done
