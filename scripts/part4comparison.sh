hadoop fs -mkdir /user/pair
FACTOR=1000
for i in {1..10}
do
  PREFIX=$((i * FACTOR))
  echo "PROCESSING $PREFIX INPUT FILE"
  hadoop fs -mkdir /user/pair/$PREFIX
  hadoop fs -put part4/$PREFIX/file-$PREFIX /user/pair/$PREFIX
  hadoop jar HadoopProject.jar part2.PairMain /user/pair/$PREFIX /user/pair/output/$PREFIX 
  hadoop fs -cat /user/pair/output/$PREFIX/*
  echo "$PREFIX COMPLETED"
done