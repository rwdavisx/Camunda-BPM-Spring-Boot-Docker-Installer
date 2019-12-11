#!/bin/bash

if [ -z "${ARTIFACT_ID}" ]; then
  echo "Provide an artifactId with -e ARTIFACT_ID=yourArtifactId"
  exit 1
fi

#if [ -z "${EE_USERNAME}" ]; then
#  echo "Provide the Camunda EE username with -e EE_USERNAME=validUsername"
#  exit 1
#fi
#
#if [ -z "${ARTIFACT_ID}" ]; then
#  echo "Provide the Camunda EE password with -e EE_PASSWORD=validPassword"
#  exit 1
#fi

mvn -B -s /usr/share/maven/ref/settings-docker.xml archetype:generate \
  -DarchetypeGroupId=com.infarmbureau \
  -DarchetypeArtifactId=camunda-starter \
  -DgroupId=com.infarmbureau \
  -DartifactId="$ARTIFACT_ID" \
  -DinteractiveMode=false

cd "$ARTIFACT_ID"
mvn install
mkdir -p /dist/"$ARTIFACT_ID"
cp -R * /dist/"$ARTIFACT_ID"