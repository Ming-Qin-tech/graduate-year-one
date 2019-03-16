@[TOC](读书笔记《Java语言程序设计（11th）》)
# 二进制I/O
* 二进制文件不能使用文本编辑器读取，文本文件可以。
* 因文本文件在计算机中也是以二进制文件存储的，然后解码显示文本文件。所以直接处理二进制文件效率高。
*****
* 同样是存储199，文本文件使用三个Unicode码（0x31、0x39、0x39（在计算机内部再转码成二进制））表示它。
* 而二进制文件使用一个0xC7来表示199.
>  同样是存储199，二进制文件有两个优势：
>  1.无需编码解码，效率更高。
>  2.二进制文件与编码方案无关，是可移植的。
> 如window是用的ASCII码，Unix是使用Unicode码。
***
* 必须声明的异常两种声明方式：
> 1. 
> ```
> public static void main(String[] args)
> 				throws IOException{
> 			
> }
> ```
> //使用try  catch
> ```
> public static void main(String[] args){
>     try{
>     
>     }
>     catch(IOException ex) {
>         ex.printStackTrace();
>     }
## 文本I/O
*  写入三部曲：
> `PrintWriter output = new PrintWriter("temp.txt");`
> `output.print("Java 101 ");`
> `output.close();`
* ==输出对象（输出流）将数据流写入文件。==
* 





## 二进制 I/O
* InputStream是二进制输入类的根类，oututStream是二进制输出类的根类。
* 二进制I/O类中的方法都必须声明IO异常。 
* 如果试图为一个不存在的文件创建FileInputStream对象，将会发生java.io.FileNotFoundException 异常。
****
* 对于FileOutputStream（File：file）：
>  1.如果该文件不存在，则创建一个新文件。
>  2.如果该文件已存在，则会删除当前文件的内容再写入。
***
***
* 如果想保留原文件中的内容并且进行输入如何是好？ 用append！
* FileOutputStream（filename：String，append： boolean）；
* 只需将append的值设置为true即可。
***
* FilterInputStream和FilterOutputStream
* 要读取整数值、双精度值和字符串，就需要一个过滤器类来包装字节输入流。
* DataInputStream和DataOutputStream
* DataInputStream从数据流读取字节，将字节转化为对应的基本类型。DataOutputStream将基本类型的值或字符串转换为字节，并将字节输出为流。
* 由于ObjectInputStream类和ObjectOutputStream类包含DataInputStream和DataOutputStream类的所有功能，==所以完全可以用objectInputStream和ObjectOutputStream类代替DataInputStream和DataOutputStream类。==
* DataInputStream和DataOutputStream以一种平台无关的方式读写Java基本类型值和字符串。
* EOFException：如果到达InputStream的末尾之后还继续从中读取数据。
***
* BufferedInputStream和BufferedOutputStream
* input：磁盘》（一次性打包到）缓冲区buffer》程序
* output： 程序》buffer》（buffer满了）磁盘
* 好处：
> 1. 减少了磁盘的读取速度
> 2. 缓存的读取速度更快
> 对于小文件，好处不明显；对于大文件，你会看见缓冲IO带来的实质性性能提升。
> 另外：buffer多大，默认512字节，可以自定义。

* serializable接口
* 并不是每一个对象都可以写到输入流。可以写到输入流中的对象称作==可序列化==的对象。
* serializable接口是一个==标记接口==
* 如果一个对象是serializable的实例，但是它包含了不能被序列化的实例数据域，那么可以序列化这个对象吗？答案是否定的。
* 将不能序列化的数据域加上关键词transient，告诉java虚拟机不必序列化此数据域。静态变量也不能被序列化。
```
public class C implements java.io.Serializable{
	private int v1;
	private static double v2;
	private transient A v3 = new A();
}
```
上述代码只有v1可以被序列化，因为v2是静态变量，而v3是加了关键词trasient。
* 对象副本
> 如果一个对象不止一次写入对象流，会存储对象的多份副本吗？ 答案是不会，主会多存储一个序列号。机制：JVM对于每个新存入的数据都会有一个编号，以后再存同样的数据，只会将编号再存一次而已。
*****
* 之前的访问都只能叫做顺序访问，那么如何实现定点访问呢？
* RandomAccessFile类，语序在文件的任何位置进行数据的读写。
* 当创建一个RandomAccessFile时，可以指定两种模式（r（只读）或者”rw“（读写））之一。
`RandomAccessFile raf = new RandomAccessFile("test.dat", "rw");`
* 如果文件test.dat已经存在，则创建raf以便访问这个文件；如果test.dat不存在，则创建一个名为test.dat的新文件，再创建raf来访问这个文件。
* raf.length()返回再给定时刻test.dat的字节数。如果向文件中追加新数据，raf.length()就会增加。
* 如果不想改动文件，就将文件以”r“模式下打开，这样可以防止不经意间改动文件。
* java中  int :4 字节   byte：1； boolean : >=1; short:2; char:2; float:4; long:8; double:8;
