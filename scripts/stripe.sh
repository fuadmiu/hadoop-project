echo "Stripe algorithm for computing relative frequency...."
hadoop fs -mkdir /user/stripe /user/stripe/input
hadoop fs -put input/part3/stripe /user/stripe/input
hadoop jar HadoopProject.jar part3.StripeMain /user/stripe/input /user/stripe/output
echo "Output for relative frequency (Stripe Approach)"
hadoop fs -cat /user/stripe/output/*