main函数其实也是所在类的一个方法，就比如一个类是test，那么该类的main其实就是test.main(String[] args)，众所周知如果一个方法不是静态的，则要先实例化该类，比如要这样 test t=new test(); 然后才能调用 test.main(); 
	而这对于运行一个程序的主函数来说是不现实的，所以比如把main函数定义为static，使test.main()可以直接被调用。
--------------------- 
转载
原文：https://blog.csdn.net/xiao1ni1zi/article/details/12437653 
