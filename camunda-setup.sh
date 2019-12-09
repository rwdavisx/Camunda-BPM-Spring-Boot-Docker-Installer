#!/bin/bash

if [ -z "${ARTIFACT_ID}" ]; then
  echo "Provide an artifactId with -e ARTIFACT_ID=yourArtifactId"
  exit 1
fi

if [ -z "${EE_USERNAME}" ]; then
  echo "Provide the Camunda EE username with -e EE_USERNAME=validUsername"
  exit 1
fi

if [ -z "${ARTIFACT_ID}" ]; then
  echo "Provide the Camunda EE password with -e EE_PASSWORD=validPassword"
  exit 1
fi

mkdir -p /app/src/"$ARTIFACT_ID"

mvn archetype:generate \
  -DarchetypeGroupId=com.infarmbureau \
  -DarchetypeArtifactId=camunda-starter \
  -DgroupId=com.infarmbureau \
  -DartifactId="$ARTIFACT_ID" \
  -DinteractiveMode=false

cd "$ARTIFACT_ID"
mvn clean install -s settings.xml -Dee_username=$EE_USERNAME -Dee_password=$EE_PASSWORD && \
cp -R * /app/src/"$ARTIFACT_ID"