#Originally from the intersystems repository
## List of the modifications:
For the working in SQL in the Object Oriented Database, we need a sequence id generator.
In my modificated driver,
```java
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SomeGener")
@SequenceGenerator(name = "SomeGener", sequenceName = "SomeSeqName", allocationSize = 1)
Means, that i have created Stored procedure with the name GETSOMESEQSERIAL or if it doesn't exist, then automatically will be created.

My idea was about to create a stored procedure which works with the globals with the unique name:
```objectscript
Class User.procSOMESERIAL Extends %Library.RegisteredObject [ ClassType = "", DdlAllowed, Owner = {_SYSTEM}, Not ProcedureBlock ]
{

	ClassMethod GETSOMESERIAL() As %Library.Integer(MAXVAL=2147483647,MINVAL=-2147483648) [ SqlName = GETSOMESERIAL, SqlProc ]
	{
		quit $SEQ(^SOMEGENER)
	}

}
Then, we can work with the stored procedure in Entity like with Sequence.
when we need a new id, then stored procedure will be called:
 "select  GET".concat(sequenceName).concat("SERIAL").concat("()");" and expression on line quit $SEQ(^SOMEGENER) will return a number
 It is working like if it is a real sequence.
  