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

You can only get the mock value when **the getter is called**. These mock values won't be displayed when debugging.

```java
		when(compInfo.getSortOrder()).thenReturn(1);
		when(compInfo.getId()).thenReturn(1693803568366178306L);
		when(compInfo.getSuperiorFlag()).thenReturn("0");
```

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

`given(...)` is as same as `when(...)` in Mockito. The `given(...)` is commonly used in BDD(Behaviour-Driven Development) while the later is mostly used in traditional unit test.

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







