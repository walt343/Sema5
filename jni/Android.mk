LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS := -llog

LOCAL_MODULE		:= Sema4
LOCAL_SRC_FILES := \
	acars_message.c 

LOCAL_C_INCLLUDES	:
	acars.h

include $(BUILD_SHARED_LIBRARY)
