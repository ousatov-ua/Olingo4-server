# nnrmls

## Tomcat config

Please edit `/conf/context.xml` in Tomcat home directory and add Database pool:

```xml
    <Resource name="jdbc/nnmrlsDb" auth="Container" type="javax.sql.DataSource"
               maxTotal="25" maxIdle="5" maxWaitMillis="5000"
               username="user" password="password" driverClassName="com.mysql.cj.jdbc.Driver"
               url="jdbc:mysql://server:3306/schema"/>

```

Additionally, you will need to add that driver lib to `$APACHE_HOME/lib`

Next is added to catalina.sh to have tomcat running on IP V4:

```shell
JAVA_OPTS="$JAVA_OPTS -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true "

```
right before sentence:

```shell
JAVA_OPTS="$JAVA_OPTS $JSSE_OPTS"
```

Also, in `server.xml` connector is configured to have next attributes:

```xml
 <Connector port="8080"
            
    address="0.0.0.0"
    useIPVHosts="true"/>        
    
```