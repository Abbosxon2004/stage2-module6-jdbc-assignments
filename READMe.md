### Java Database Connectivity assignment

1. - You need to create a postgresql database called "myfirstdb" binding on port 5432.
   - The database should contain information about users: 
     - unique identifier(Id), 
     - user first name, 
     - user last name
     - user age.

     (Table name - myusers, with the column: id, firstname, lastname, age)
   - For config datasource use app.properties file.


2. Implement class _CustomConnector_ to get connection with "myfirstdb" database.
```java
public class CustomConnector {
    public Connection getConnection(String url) {
    }

    public Connection getConnection(String url, String user, String password)  {
    }
}
```
3. Implement class _CustomDataSource_ using singleton pattern to get connection with "myfirstdb" database:
```java

@Getter
@Setter
public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;
    //  code

    private CustomDataSource(String driver, String url, String password, String name) {
        //  code
    }


    public static CustomDataSource getInstance() {
        //  code
        return instance;
    }
//  code
```
4. - Implement class _SimpleJDBCRepository_, resolve all class methods for create, update, delete and search Users. 
   - Use _CustomDataSource_ and _CustomConnector_
   for performing statement or prepareStatement to database. 

```java

public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "";
    private static final String updateUserSQL = "";
    private static final String deleteUser = "";
    private static final String findUserByIdSQL = "";
    private static final String findUserByNameSQL = "";
    private static final String findAllUserSQL = "";

    public Long createUser() {
        //  code
    }

    public User findUserById(Long userId) {
        //  code
    }

    public User findUserByName(String userName) {
        //  code
    }

    public List<User> findAllUser() {
        //  code
    }

    public User updateUser() {
        //  code
    }

    private void deleteUser(Long userId) {
        //  code
    }
}
```