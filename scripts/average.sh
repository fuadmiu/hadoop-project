echo "Computing average...."
hadoop fs -mkdir /user/average /user/average/input
hadoop fs -put input/part1/average/access_log /user/average/input
hadoop jar HadoopProject.jar part1.average.LogAverageMain /user/average/input /user/average/output
echo "Output for average..."
hadoop fs -cat /user/average/output/*