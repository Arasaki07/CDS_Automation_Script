@echo off
REM Usage: run-tests-and-generate-reports.bat "<SPRING_PROFILE>" "<CUCUMBER_TAG>" "<EXCLUDED_CUCUMBER_TAG>"
REM SPRING_PROFILE        - this parameter is for the environment (e.g., local/dev/qa/uat/prod)
REM CUCUMBER_TAG          - this parameter can contain a single tag or multiple tags separated by "^|" symbol
REM EXCLUDED_CUCUMBER_TAG - this parameter can contain a single tag or multiple tags separated by "," symbol

SET SPRING_PROFILE=%~1
SET CUCUMBER_TAGS=%~2
SET EXCLUDED_GROUPS=%~3

SET MAVEN_CMD=mvn clean verify -U

IF NOT "%SPRING_PROFILE%"=="" (
    SET MAVEN_CMD=%MAVEN_CMD% -Dspring.profiles.active="%SPRING_PROFILE%"
)

IF NOT "%CUCUMBER_TAGS%"=="" (
    SET MAVEN_CMD=%MAVEN_CMD% -Dgroups="%CUCUMBER_TAGS%"
)

IF NOT "%EXCLUDED_GROUPS%"=="" (
    SET MAVEN_CMD=%MAVEN_CMD% -DexcludedGroups="%EXCLUDED_GROUPS%"
)

call bin\codeartifact.bat

call rmdir /s /q .allure
call mvn allure:install


echo Executing Maven command: %MAVEN_CMD%
call %MAVEN_CMD%


if "%SPRING_PROFILE%"=="dev" (
    call xcopy /Y src\main\resources\reporting-dev.properties target\allure-results\environment.properties*
) else if "%SPRING_PROFILE%"=="qa" (
    call xcopy /Y src\main\resources\reporting-qa.properties target\allure-results\environment.properties*
) else if "%SPRING_PROFILE%"=="uat" (
     call xcopy /Y src\main\resources\reporting-uat.properties target\allure-results\environment.properties*
) else if "%SPRING_PROFILE%"=="" (
    call xcopy /Y src\main\resources\reporting-local.properties target\allure-results\environment.properties*
) else if "%SPRING_PROFILE%"=="default" (
    call xcopy /Y src\main\resources\reporting-local.properties target\allure-results\environment.properties*
)


call cd .allure\allure-2.27.0\bin\
call allure generate ..\..\..\target\allure-results\ -c --single-file -o ..\..\..\target\allure-report\
call cd ..\..\..\
