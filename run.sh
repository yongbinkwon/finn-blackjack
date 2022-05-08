if [ $# -eq 0 ]
then
  ./gradlew run -q --console=plain
else
  ./gradlew run -q --console=plain --args "$1"
fi