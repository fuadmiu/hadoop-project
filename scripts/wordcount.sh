echo "WordCount Program ...."
hadoop fs -mkdir /user/wordcount /user/wordcount/input
hadoop fs -put input/part1/wordcount/file* /user/wordcount/input
hadoop jar HadoopProject.jar part1.wordcount.WordCountMain /user/wordcount/input /user/wordcount/output
echo "Output for WordCount...."
hadoop fs -cat /user/wordcount/output/*