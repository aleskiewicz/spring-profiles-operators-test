# spring-profiles-operators-test
Possible bug in handling profiles info in Spring 5.1

[ProfilesTest](src/test/java/pl/aleskiewicz/ProfilesTest.java) contains several tests for the following combination of @Profiles values and active profiles:

| @Profile value | Active profiles |Test name |Expected result |Actual result|
|---|---|---|---|---|
|!a & b|b|notAandBShouldAcceptWhenBActive|true|true|
|b & !a|b|bAndNotAShouldAcceptWhenBActive|true|true|
|**!a & b**|a|**notAandBShouldNotAcceptWhenAActive**|false|**true**|
|b & !a|a|bAndNotAShouldNotAcceptWhenAActive|false|false|
|!a & b|ab|notAandBShouldNotAcceptWhenABActive|false|false|
|b & !a|ab|bAndNotAShouldNotAcceptWhenABActive|false|false|

As shown in the table above, test **notAandBShouldNotAcceptWhenAActive** should not accept the bean, but it does, and just different order of operations in @Profile is enoug to fil the case
