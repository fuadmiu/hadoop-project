hadoop fs -rm -r /user/pair
echo "Pair algorithm for calculating relative frequency...."
hadoop fs -mkdir /user/pair /user/pair/input
hadoop fs -put input/part2/pair /user/pair/input
hadoop jar HadoopProject-1.0.jar part2.PairMain /user/pair/input /user/pair/output
echo "Output for relative frequency (Pair Approach)"
hadoop fs -cat /user/pair/output/*