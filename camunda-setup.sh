#!/bin/bash

if [ -z "${ARTIFACT_ID}" ]; then
  echo "Provide an artifactId with -e ARTIFACT_ID=yourArtifactId"
  exit 1
fi

mvn -B -s /usr/share/maven/ref/settings-docker.xml archetype:generate \
  -DarchetypeGroupId=com.infarmbureau \
  -DarchetypeArtifactId=camunda-starter \
  -DgroupId=com.infarmbureau \
  -DartifactId="$ARTIFACT_ID" \
  -DinteractiveMode=false

mkdir -p /dist/"$ARTIFACT_ID"
cp -R "$ARTIFACT_ID"/* /dist/"$ARTIFACT_ID"