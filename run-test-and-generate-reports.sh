#!/usr/bin/env bash

# Usage : source run-tests-and-generate-reports.sh "<SPRING_PROFILE>" "<CUCUMBER_TAG>" "<EXCLUDED_CUCUMBER_TAG>"
# SPRING_PROFILE        - this parameter is for the env for e.g. local/dev/qa/uat/prod
# CUCUMBER_TAG          - this parameter can contain a single tag or multiple tags separated by "|" symbol
# EXCLUDED_CUCUMBER_TAG - this parameter can contain a single tag or multiple tags separated by "," symbol

SPRING_PROFILE=$1
CUCUMBER_TAGS=$2
EXCLUDED_GROUPS=$3

MAVEN_CMD="mvn clean verify -U"

if [ -n "$SPRING_PROFILE" ]; then
    MAVEN_CMD="${MAVEN_CMD} -Dspring.profiles.active=\"${SPRING_PROFILE}\""
fi

if [ -n "$CUCUMBER_TAGS" ]; then
    MAVEN_CMD="${MAVEN_CMD} -Dgroups=\"${CUCUMBER_TAGS}\""
fi

if [ -n "$EXCLUDED_GROUPS" ]; then
    MAVEN_CMD="${MAVEN_CMD} -DexcludedGroups=\"${EXCLUDED_GROUPS}\""
fi

source bin/codeartifact.sh

rm -rf .allure
mvn allure:install


echo "Executing maven command : " $MAVEN_CMD

eval $MAVEN_CMD


if [ "$SPRING_PROFILE" == "dev" ]; then
    cp src/main/resources/reporting-dev.properties target/allure-results/environment.properties
elif [ "$SPRING_PROFILE" == "qa" ]; then
    cp src/main/resources/reporting-qa.properties target/allure-results/environment.properties
elif [ "$SPRING_PROFILE" == "uat" ]; then
    cp src/main/resources/reporting-uat.properties target/allure-results/environment.properties
elif [ -z "$SPRING_PROFILE" ] || [ "$SPRING_PROFILE" == "default" ]; then
    cp src/main/resources/reporting-local.properties target/allure-results/environment.properties
fi

cd .allure/allure-2.27.0/bin/
./allure generate ../../../target/allure-results/ -c --single-file -o ../../../target/allure-report/
cd -