# Hadoop Project
## Submitted By
1. Mohammad Hizbullah Fuad
2. Md Shafiqul Haque
3. Tharugasini Manickavasagar

## Create Jar
 In Project Directory, Run `mvn clean package`
 
We'll use the HadoopProject-1.0.jar from target folder

## Move files to Hadoop system
Using `docker cp` command, move HadoopProject-1.0.jar, input and scripts folder to hadoop

In Hadoop, make sure the script file with sh extension are in same level with the jar.
Also, input directory should be in same level with the jar.

# Sample Run Command
`./wordcount.sh`

`./wordcountinmapper.sh`

`./average.sh`

`./averageinmapper.sh`

`./pair.sh`

`./stripe.sh`

`./part4comparison.sh`

`./part4comparison_stripe.sh`