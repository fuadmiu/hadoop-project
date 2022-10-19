echo "Computing Average using in-mapper ....."
hadoop fs -mkdir /user/averageinmapper /user/averageinmapper/input
hadoop fs -put input/part1/average/access_log /user/averageinmapper/input
hadoop jar HadoopProject.jar part1.averageinmapper.LogAverageInMapperMain /user/averageinmapper/input /user/averageinmapper/output
echo "Output for Average using in-mapper...."
hadoop fs -cat /user/averageinmapper/output/*