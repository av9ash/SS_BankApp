mvn clean install -Dmaven.test.skip=true
cd ~
echo 'Messi!Man6' | sudo -S rm /opt/tomcat/webapps/packaging-1.0-SNAPSHOT.war
echo 'Messi!Man6' | sudo -S cp CSE_SOFTWARE_SECURITY/SS_BankApp/warPackaging/target/packaging-1.0-SNAPSHOT.war /opt/tomcat/webapps/
echo 'Messi!Man6' | sudo -S systemctl restart tomcat
