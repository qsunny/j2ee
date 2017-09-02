@echo off
echo "generate dao,service mapping"
gradle clean build installDist
echo "==========================="
cd %CD%\build\install\mybatisgenerator\bin\
#mybatisgenerator.bat
@echo on
