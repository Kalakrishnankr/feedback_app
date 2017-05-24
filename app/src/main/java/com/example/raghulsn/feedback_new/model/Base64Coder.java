 package com.example.raghulsn.feedback_new.model;

 /**
  * @author Created Intamac Systems
  * <Summary></h1> Class to encode credentials
  */
 public class Base64Coder
 {
private static char[] map1 = new char[64];

 
   static
   {
	   	int i = 0;
     	for (char c = 'A'; c <= 'Z'; c = (char)(c + '\001'))
     		map1[(i++)] = c;
     	for (char c = 'a'; c <= 'z'; c = (char)(c + '\001'))
     		map1[(i++)] = c;
     	for (char c = '0'; c <= '9'; c = (char)(c + '\001'))
     		map1[(i++)] = c;
     		map1[(i++)] = '+';
     		map1[(i++)] = '/'; 
   }
 
   public static String encodeString(String s)
   {
    return new String(encode(s.getBytes()));
   }
 
   private static char[] encode(byte[] in)
   {
     return encode(in, in.length);
   }
 
   private static char[] encode(byte[] in, int iLen)
   {
	    int oDataLen = (iLen * 4 + 2) / 3;
	    int oLen = (iLen + 2) / 3 * 4;
	    char[] out = new char[oLen];
	    int ip = 0;
	    int op = 0;
	    while (ip < iLen) {
	    	int i0 = in[(ip++)] & 0xFF;
	    	int i1 = ip < iLen ? in[(ip++)] & 0xFF : 0;
	    	int i2 = ip < iLen ? in[(ip++)] & 0xFF : 0;
	    	int o0 = i0 >>> 2;
	    	int o1 = (i0 & 0x3) << 4 | i1 >>> 4;
	    	int o2 = (i1 & 0xF) << 2 | i2 >>> 6;
	      	int o3 = i2 & 0x3F;
	      	out[(op++)] = map1[o0];
	      	out[(op++)] = map1[o1];
	     	out[op] = (op < oDataLen ? map1[o2] : '=');
	     	op++;
	     	out[op] = (op < oDataLen ? map1[o3] : '=');
	     	op++;
     }
   return out;
   }
 
  
 }

