# 第12章：异常处理和文本I/O

## 异常处理
* 不应该由方法来终止程序——应该由调用者决定是否可以终止一个程序
* 方法应当如何通知他的调用者一个异常产生了呢？   **Java**可以让方法抛出一个异常，该异常可以被调用者捕获和处理。
* throw new ArithmetricException("**Divisor cannot be zero**");   加粗部分为 Detail message（描述异常的消息），无实际功能。
* 异常（Exception）
* 调用方法的语句包含在一个try块和一个catch块中。try块中的代码包含了正常情况下执行的代码。异常被catch所捕获。catch中的代码被执行以处理异常。
* 异常就是一个从异常类所创建的对象。异常的根类是java.lang.Throwable.所有的Java异常类都直接或者间接地继承自Throwable。***可以通过继承Exception或者Exception的子类来创建自己的异常类***

