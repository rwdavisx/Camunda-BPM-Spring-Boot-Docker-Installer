echo "Define value for property 'artifactId': " && \
read input && \
echo "Enter Camunda EE Username: " && \
read username && \
echo "Enter Camunda EE Password: " && \
read password && \
git clone https://bitbucket.infarmbureau.com/scm/icaf/camunda-starter.git && \
cd camunda-starter && \
mvn clean install && \
cd .. &&
rm -rf camunda-starter && \
mvn archetype:generate \
  -DarchetypeGroupId=com.infarmbureau \
  -DarchetypeArtifactId=camunda-starter \
  -DgroupId=com.infarmbureau \
  -DartifactId=$input \
  -DinteractiveMode=false && \
cd $input && \
mvn clean install -s settings.xml -Dee_username=$username -Dee_password=$password