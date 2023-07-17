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

## Functional tests

Currently, we are using embedded DB. It needs ncurses lib installed (maybe)

For Ubuntu:

```shell
sudo apt-get install libncurses5
```

## Generation of code

<span style="color: red;font-weight: bold"> 
Please pay attention 
</span>
that code for `*CsdlEntityTypeProvider` is generated using `com.olus.olingo2.nmrls.util.DdlUtil`

## Mapping new fields/tables

<span style="color: red;font-weight: bold"> 
!!! Please pay attention
</span>
 that currently all `CsdlEntityType`s have properties with the same name as database columns

<span style="color: red; font-weight: bold"> 
!!!
</span>

This is very important because all queries are generated based on those properties.

You'll need to implement mapper if you need custom mappings between CsdlEntityType and Database columns

### New table

1) Add appropriate ET_$name_NAME, ET_$name_FQN, ES_$name_NAME in `NnmrlsEdmProvider`
2) Get DDL of your new table (just use generate SQL tool if you have just a table without its creation script)
3) Use `com.olus.olingo4.nmrls.util.DdlUtil` to generate java code for all columns
4) Create CsdlEntityTypeProvider - refer to existing ones as example. Put generated java code inside it.
5) Put call to your CsdlEntityProvider from `NnmrlsEdmProvider.getCsdlEntityType`

### Add fields to a table

1) Update appropriate CsdlEntityTypeProvider.

## Olingo4 operations

### Get one property only

Optimized in db terms too. So only specific column will be fetched on DB level

[http://localhost:8080/.../OfficeData('1')/OfficeAddress1]()

### Top and Skip

Optimized in db terms too. So pagination will work on db side as well.

[http://localhost:8080/.../OfficeData?$top=5&$skip=1]()

### Select

Select is optimized in db terms too. So only specific columns will be fetched on DB level.

[http://localhost:8080/.../OfficeData('1')?$select=OfficeAddress1,OfficeAddress2]()

### Filter

Next current filter operations are supported

#### Comparison operators

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId eq 1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId ne 1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId gt 2]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId ge 2]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId le 2]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId lt 2]()

#### Unary operators

[http://localhost:8080/.../PropertyDataLocation?$filter=-ListingId eq -1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=not (ListingId eq 42)]()

#### Method calls and strong binding unary not

[http://localhost:8080/.../PropertyDataLocation?$filter=contains(UnparsedAddress,'ROSECREST DRIVE')]()

[http://localhost:8080/.../PropertyDataLocation?$filter=not contains(UnparsedAddress,'ROSECREST DRIVE')]()

#### Arithmetic operators

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId add 1 eq 2]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId sub 1 eq 1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId div 2 eq 1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId mul 2 eq 6]()

[http://localhost:8080/.../PropertyDataLocation?$filter=ListingId mod 2 eq 1]()

#### String literal

[http://localhost:8080/.../PropertyDataLocation?$filter=UnparsedAddress eq '000 ROSECREST DRIVE 1']()

#### Boolean operators

[http://localhost:8080/.../PropertyDataLocation?$filter=contains(UnparsedAddress,'ROSECREST DRIVE 1') or ListingId eq 1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=contains(UnparsedAddress,'ROSECREST DRIVE 1') and ListingId eq 1]()

[http://localhost:8080/.../PropertyDataLocation?$filter=contains(UnparsedAddress,'ROSECREST DRIVE 1') and ListingId eq 3]()
