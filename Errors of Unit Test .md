#### 1, Run an individual test in Idea.

If you don't want to all the tests run in a class when you are trying to run a single test of a method. You should select "Skip Tests".

<img src="note-images/1712645962279.png" alt="1712645962279" style="zoom:80%;" />

**Or** cancel "Delegate IDE... to Maven "

<img src="note-images/1712646233825.png" alt="1712646233825" style="zoom:80%;" />

#### 2, JUnit is not available 

JUnit is not available in a project run by Idea.

Error: 

```txt
org.junit.platform.commons.JUnitException: TestEngine with ID 'junit-jupiter' failed to discover tests
```

Solution:

The reason is as same as it in the 1.  Just deselect "Delegate IDE build..."

#### 3,  Errors of Unit Test

1, An error occurs in a project of Idea. The exception is as follows.

```txt
command line is too long  also for junit
```

Solution:

In "Shorten command line" select JAR manifest.

<img src="note-images/1712796693495.png" alt="1712796693495" style="zoom: 80%;" />

#### 4, NoClassDefFoundError

`NoClassDefFoundError: org/springframework/core/CollectionFactory`

 * The following test throws "NoClassDefFoundError: org/springframework/core/CollectionFactory".

 * The reason, which I guess, is the version of servlet-api(3.1.0) is different from

   the one which is 2.4 in "spring-mock" dependency.

* **Reason:**

  How stupid I was! The exception indicated clearly that there is no "CollectionFactory" which is 

  a class of Spring Core. Just import the dependency!!


```xml
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <!--Write a proper version correspond to spring-mock-->
                <version>${spring-core.version}</version>  
                <scope>compile</scope>
             </dependency>
```

#### 5, incompatible version of Kotlin

Error: `module was compiled with an incompatible version of kotlin `

[A solution](https://stackoverflow.com/questions/71076748/errorkotlin-module-was-compiled-with-an-incompatible-version-of-kotlin-in-a-pr/71093698#71093698):

Try going to *Build* menu and choose *Rebuild project*. 

