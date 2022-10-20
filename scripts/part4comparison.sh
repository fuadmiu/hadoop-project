hadoop fs -rm -r /user/pair
hadoop fs -mkdir /user/pair
FACTOR=1000
for i in {1..10}
do
  PREFIX=$((i * FACTOR))
  echo "PROCESSING $PREFIX INPUT FILE"
  hadoop fs -mkdir /user/pair/$PREFIX
  hadoop fs -put input/part4/$PREFIX/file* /user/pair/$PREFIX
  hadoop jar HadoopProject-1.0.jar part2.PairMain /user/pair/$PREFIX /user/pair/output/$PREFIX
  #hadoop fs -cat /user/pair/output/$PREFIX/*
  echo "$PREFIX COMPLETED"
done