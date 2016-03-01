#define _GNU_SOURCE
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <math.h>
#include <jni.h>

JNIEXPORT jstring JNICALL Java_ACARSActivity_getText(JNIEnv *env, jobject obj)
{
	char msg[60] = "Hello there!";

	jstring result;

	result = (*env)->NewStringUTF(env, msg);
	return result;
}
