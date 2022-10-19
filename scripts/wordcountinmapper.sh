echo "WordCount (in-mapper version) ...."
hadoop fs -mkdir /user/wordcountinmapper /user/wordcountinmapper/input
hadoop fs -put input/part1/wordcount/file* /user/wordcountinmapper/input
hadoop jar HadoopProject.jar part1.wordcountinmapper.WordCountInMapperMain /user/wordcountinmapper/input /user/wordcountinmapper/output
echo "Output for WordCount (in-mapper)"
hadoop fs -cat /user/wordcountinmapper/output/*