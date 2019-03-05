生成处在ch1和ch2之间的字符
(char)(ch1 +Math.Random()*(ch2 - ch1 +1))

二分法查找若找不到返回
return -low-1;
这样方便后续插入
