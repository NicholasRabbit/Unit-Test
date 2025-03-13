### 1,  TestCase in JUnit

path: `junit.framework.TestCase;`

What is "TestCase" used for? 

### 2,  expect(), replay(), verify()

The four steps mentioned above relate to methods in [*org.easymock.EasyMock*](http://easymock.org/api/org/easymock/EasyMock.html):

1. [mock(…)](http://easymock.org/api/org/easymock/EasyMock.html#mock-java.lang.Class-): generates a mock of the target class, be it a concrete class or an interface. Once created, a mock is in “recording” mode, meaning that EasyMock will record any action the Mock Object takes, and replay them in the “replay” mode
2. [expect(…)](http://easymock.org/api/org/easymock/EasyMock.html#expect-T-): with this method, we can set expectations, including calls, results, and exceptions, for associated recording actions
3. [replay(…)](http://easymock.org/api/org/easymock/EasyMock.html#replay-java.lang.Object...-): switches a given mock to “replay” mode. Then, any action triggering previously recorded method calls will replay “recorded results”
4. [verify(…)](http://easymock.org/api/org/easymock/EasyMock.html#verify-java.lang.Object...-): verifies that all expectations were met and that no unexpected call was performed on a mock

[replay](https://stackoverflow.com/questions/5987149/what-is-easymock-replay-used-for) 

[easymock introduction](https://www.baeldung.com/easymock)

### 3, *createMock*(..)

Don't forget to receive the return value of  `createMock(...)`

```java
// createMock(SessionFactory.class);  // Wrong!!
factory = createMock(SessionFactory.class);
```

### 4, mock entity

1. You can only get the mock value when **the getters of a mock object is called**. These mock values won't be displayed when debugging. If you didn't mock, all the values of fields would be default. Hence the default value of primitive data type is 0. 

   **Caution:**  Don't use `assertNotNull` for primitive data type and its package class such as Integer, Long and so on, because 0 is not null and is the default value of a mock Long, Integer and so forth(Why? It should be null for these package classes.).

```java
		when(compInfo.getSortOrder()).thenReturn(1);
		when(compInfo.getId()).thenReturn(1693803568366178306L);
		when(compInfo.getSuperiorFlag()).thenReturn("0");
```

2. When you mock a Page in a controller layer, not only should you mock the method in a service but also mock the `getter` of the Page instance. If you don't do so, the page is null thus a `NullPointerException` is presumably thrown. 

   ```java
   // mock the getter of a page instance
   when(mockPage.getRecords()).thenReturn(resourceList);
   // then mock the result of the method in the service
   when(teachingResourceService.findResourceByTeacherId(page, ownerId)).thenReturn(mockPage);
   ```

3. Use `@Mock` and `when(...)` to return mock values of getters in which there are dependencies which is impossible to mock.

   ```java
   private void getName(){
    return        RedisCacheUmpsHelper.getInstance().getDeptById(d.getParentId()).getName();
   }
   ```

   The `RedisCacheUmpsHelper` is so hard to mock that we use `when(entity.getName()).then("Name")` to get a mock `deptName`. Otherwise there will be a `NullPointerException` throwed out because `RedisCacheUmpsHelper.getInstance()`  is null.

### 5, assertThrows(...)

Mockito:` assertThrows(...)` is as same as `fail(...)`;  See `BooleanParserRefactoredTest`

```java
@Test    
public void shouldThrowTooManyArgumentsException() throws Exception{
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class,
                () -> {
                    new BooleanParserRefactored().parse(asList("-l", "abc"), option("l"));
                });
        assertEquals("l", e.getOption());
    }
```

### 6, @ParameterizedTest

This annotation is used in tests where only a few inputs are different but the logic is essentially same. In those situations, we might want to consider turning our test class into a Parameterized test.

See: `SingleValueOptionParserTest` of TDD in Practice

​        4.5.3 Test Patterns of the book name "TDD"

```java
    @ParameterizedTest
    @ValueSource(strings = {"-p", "-p -x"})
    public void shouldThrowInsufficientArgumentsException(String params) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class,
                () -> {
                    new SingleValueOptionParser<>(Integer::parseInt).parse(asList(params.split(" ")), option("p"));
                });
        assertEquals("p", e.getOption());
    }

```

### 7, given(...)

`given(...)` is as same as `when(...)` in Mockito. The `given(...)` is commonly used in BDD(Behaviour-Driven Development) while the latter is mostly used in traditional unit test.

```java
	@Test
	public void testGiven() throws Exception {
		RegulationRelease mockEntity = new RegulationRelease();
		mockEntity.setId(100L);
		mockEntity.setTitle("测试安全章程");

		RegulationRelease mockEntity2 = new RegulationRelease();
		mockEntity.setId(200L);
		mockEntity.setTitle("测试生产章程");

		given(mapper.selectById(100L)).willReturn(mockEntity);
		RegulationRelease actual = regulationReleaseService.getById(100L);
		assertEquals(mockEntity.getId(), actual.getId());

		given(mapper.selectById(200L)).willReturn(mockEntity2);
		RegulationRelease actual2 = regulationReleaseService.getById(200L);
		assertEquals(mockEntity2.getId(), actual2.getId());

	}
```







### 8, eq()

```java
import static org.mockito.ArgumentMatchers.eq;

when(teachingResourceService.findResourceByTeacherId(eq(page),                                                       eq(ownerId))).thenReturn(mockPage);
```

"eq(...)" is used for flexible verification or stubbing.  See the document in `ArgumentMatchers`	

### 9, @Nest

This annotation is used when you want to integrate test classes which has the same purpose to a enclosing class.

From the official document.

> @Nested is used to signal that the annotated class is a nested, non-static test class (i.e., an inner class) that can share setup and state with an instance of its enclosing class. The enclosing class may be a top-level test class or another @Nested test class, and nesting can be arbitrarily deep.
> @Nested test classes may be ordered via @TestClassOrder or a global ClassOrderer.
> 
Test Instance Lifecycle.
### 10, MockMvc

[Demos of Using MockMvc](https://github.com/spring-projects/spring-framework/tree/main/spring-test/src/test/java/org/springframework/test/web/servlet/samples)

#### 10.1, How to use MockMvc?

[MockMvc](https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework.html) is a Spring Test framework which provides solid support for testing Spring MVC applications.

A instruction of using MockMvc.

1, First of all, Import necessary static class.

- `MockMvcBuilders.*`
- `MockMvcRequestBuilders.*`
- `MockMvcResultMatchers.*`
- `MockMvcResultHandlers.*`

2, to be continue

### 11, Spring Testing Annotations

#### (1) @Sql

`@Sql` is an annotation used in integration to initialise a database.  It can be annotated to a test class or a test method. 

(1) What should be noticed is it also start transaction and it roll backs after test.

(2) SQL scripts are located in `test/resource/*.sql` . For more elaboration, see "Other Demos".

(3) The SQL scripts are executed before test by default, even before `@BeforeEach`.  So any code in the set up method `@BeforeEach`  is executed after the SQL scripts. 

```java
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    
@Sql(scripts = "/regulation_release.sql")
public class RegulationReleaseMapperTest {}
```

```java
	@Test
	@Sql(scripts = "/regulation_release.sql")
	public void testSQLAnnotation() throws Exception {
		List<RegulationRelease> list = regulationReleaseMapper.selectList(null);
		assertEquals(1, list.size());
	}
```

[@Sql](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-sql.html)

#### (2) @Commit

[@Commit](https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-commit.html)

You can use `@Commit`if you want to commit transaction after a test method.

```java
	@Test
	@Commit
	public void shouldSave100Rows() throws Exception {
		List<RegulationRelease> beforeAddList = regulationReleaseMapper.selectList(null);
		int expected = 100;
		populateTestData();  // add 100 rows
		int actual = regulationReleaseMapper.selectList(null).size() - beforeAddList.size();
		assertEquals(expected, actual);
	}

```



12, Use @Mock When 