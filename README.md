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