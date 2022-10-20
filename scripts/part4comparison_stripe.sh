hadoop fs -rm -r /user/stripe
hadoop fs -mkdir /user/stripe
FACTOR=1000
for i in {1..10}
do
  PREFIX=$((i * FACTOR))
  echo "PROCESSING $PREFIX INPUT FILE"
  hadoop fs -mkdir /user/stripe/$PREFIX
  hadoop fs -put input/part4/$PREFIX/file* /user/stripe/$PREFIX
  hadoop jar HadoopProject-1.0.jar part3.StripeMain /user/stripe/$PREFIX /user/stripe/output/$PREFIX
  #hadoop fs -cat /user/stripe/output/$PREFIX/*
  echo "$PREFIX COMPLETED"
done