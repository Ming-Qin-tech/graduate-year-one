# 第12章：异常处理和文本I/O

## 异常处理
* 不应该由方法来终止程序——应该由调用者决定是否可以终止一个程序
* 方法应当如何通知他的调用者一个异常产生了呢？   **Java**可以让方法抛出一个异常，该异常可以被调用者捕获和处理。
* throw new ArithmetricException("**Divisor cannot be zero**");   加粗部分为 Detail message（描述异常的消息），无实际功能。
* 异常（Exception）
* 调用方法的语句包含在一个try块和一个catch块中。try块中的代码包含了正常情况下执行的代码。异常被catch所捕获。catch中的代码被执行以处理异常。
* 异常就是一个从异常类所创建的对象。异常的根类是java.lang.Throwable.所有的Java异常类都直接或者间接地继承自Throwable。***可以通过继承Exception或者Exception的子类来创建自己的异常类***
* 分为免检异常和必检异常，必检异常是说编译器会强制程序员检查并通过catch块处理他们，或者在方法头进行声明。
* 捕获异常即try-catch语句   
* 声明异常 `puvlic void method2() throws Exception{ any code  }`   其中的throw**s**即用于声明异常，声明异常来表明方法**可能会**抛出的异常。如果方法可能会抛出多个异常，就可以在关键字throws后添加一个用逗号分隔的异常列表  
`public void myMethod() throws Exception1,Exception2,Exception3,....,ExceptionN` 
* **如果父类中的方法没有声明异常，那么在子类重写时就不能声明异常**
* 抛出异常 `throw new Exception（）`     其中throw即用于抛出异常,抛出异常有两种表达形式：
```
 IllegalArgumentException ex = 
  new IllegalArgumentException("Wrong Argument");
 throw ex;
 
```
 `throw new IllegalArgumentException("Wrong Argument");`
 * ***JavaAPI中的每个异常类至少有两个构造方法： 一个无参构造方法和一个带有可以描述这个异常的String参数的构造方法***
 * 捕获异常  利用**调用栈**   一直向上查，**上层方法引用下层方法**，如果最终异常类型没有被捕获，则程序终止。
 * 各种异常类可以从一个共同的父类中派生。如果一个catch块可以捕获一个父类的异常对象，它就能捕获那个父类的所有子类的异常对象。只是判断应先判断是否是子类专属的异常类型。
 * 从JDK7开始一个catch就可以捕获多种异常
 ```
 catch (Exception1|Exception2|Exception3|...|Exceptionk ex){
      some code for handling these Exception
 }
 ```
* 1.try-catch:可以对异常进行处理，提高软件的健壮性  2.JVM：运行错误则直接崩溃
* 异常处理器使用System.out.println(ex)打印一个有关异常的短消息ex.toString().
* 如果try模块中发生异常，try模块剩余代码将不会被执行；如果catch模块中发生了异常，catch模块剩余代码将不会被执行，而是跳过catch模块去执行后面的部分。
* **finally语句** 在任何情况下，finally块中的代码都会执行，不论try块中死否出现异常或者被捕获。***即使在到达finally块之前有一个return语句，finally块还是会执行***   **使用finally块时可以略去catch块。
* 在代码中，应该在什么时候使用try-catch语句呢？ 当必须处理不可预料的错误状况时应该使用它。不要使用try-catch语句做简单的逻辑测试，不要用于处理简单可预料的情况。
* **重新抛出异常**如果异常处理器并不能处理一个异常，或者只是简单地希望它的调用者注意到该异常，Java允许该异常处理器重新抛出异常。 如下面的语句throw ex重新抛出异常给调用者，以便调用者的其他处理器获得处理异常ex的机会。
```
try{
 statements;
 }
 catch (TheException ex){
  perform operations before exists;
  throw ex;
 }
 ```
 *  **链式异常**：与另一个异常一起抛出一个异常，构成了链式异常。上面的代码块例子**只是**重新抛出了最初的异常。链式异常是将新异常（带有附加信息）和最初异常一起抛出。
 * 程序闪退，就是异常处理做的不好。
 ### HOW2J.com  异常
 * try-catch异常捕捉机制提高了程序的健壮性，以前所见过的程序闪退即是这方面做的不好。
 * 步骤二：罗列到目前为止所接触过的所有异常(面试常问)： 数组超限，输入类型不匹配，待打开的文件不存在，做除法除数为零。
 1. OutOfIndexException 数组下标越界异常
 2. OutOfMemoryError  内存不足
 3. ClassCastException 类型转换异常
 4. ArithmeticException 除数为零
 5. NullPointerException 空指针异常
 * try块里面都是可能会出错，需要加强健壮性的代码，通过catch捕捉。
 * **instanceof**是和**catch (FileNotFoundException | ParseException e)** 这类在一个catch中捕捉多种异常使用的，见代码如下

```
//未使用instanceof
catch (FileNotFoundException e) {
            System.out.println("d:/LOL.exe不存在");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("日期格式解析错误");
            e.printStackTrace();
```

```
//使用instanceof
 catch (FileNotFoundException | ParseException e) {
            if (e instanceof FileNotFoundException)
                System.out.println("d:/LOL.exe不存在");
            if (e instanceof ParseException)
                System.out.println("日期格式解析错误");
            e.printStackTrace();
 ```
 * throws与throw这两个关键字接近，不过意义不一样，有如下区别：
>> 1. throws 出现在方法声明上，而throw通常都出现在方法体内。
>> 2. throws 表示出现异常的一种可能性，并不一定会发生这些异常；throw则是抛出了异常，执行throw则一定抛出了某个异常对象。
 
 
 
 
 
 
 
 
 
 
 
 
 
 
