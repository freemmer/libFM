package com.tistory.freemmer.lib.libfm.util

import java.lang.ref.WeakReference
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Reflect Help Class
 * @warn
 * 	- obfuscator 사용 될 때 변경되지 않도록 obfuscator 옵션 설정 필수.
 * @code
 * Class<?> c = CReflect.findClassForName(
 * 		ApplicationManager.getInstance().getProtocolPackageName() +
 * 		"." +
 * 		ApplicationManager.getInstance().mMapProtocol.get(trCode));
 * Field fieldResultTag = CReflect.findFieldForName(c,"RESULT_CODE");
 * String rtnCodeTag = (String) CReflect.getObjectFromField(fieldResultTag);
 *
 * Created by freemmer on 01/02/2019.
 * History
 *    - 01/02/2019 Create file
 */
class FMReflectUtil {

    companion object {
        private var weakReference: WeakReference<FMReflectUtil>? = null
        fun instance(): FMReflectUtil {
            if (weakReference?.get() == null) {
                weakReference = WeakReference(FMReflectUtil())
            }
            return weakReference?.get()!!
        }
    }

    /**
     * 클래스 이름으로 클래스를 찾는다.
     * @param className    찾을 클래스 이름.
     * @return class
     */
    fun findClassForName(className: String): Class<*>? {
        val c: Class<*>?
        try {
            c = Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("CReflect error! ClassNotFoundException. $className")
        }
        return c
    }

    /**
     * 클래스내의 필드를 이름으로 찾는다.
     * @param c			Class
     * @param fieldName	필드 이름.
     * @return	field
     */
    fun findFieldForName(c:Class<*>, fieldName:String): Field? {
        val field: Field?
        try {
            field = c.getField(fieldName)
        } catch (e:SecurityException) {
            throw RuntimeException("CReflect error! SecurityException. Class: ${c.name}, Field:$fieldName")
        } catch (e:NoSuchFieldException) {
            throw RuntimeException("CReflect error! NoSuchFieldException. Class:${c.name}, Field:$fieldName")
        }
        return field
    }

    /**
     * 필드로부터 Object를 얻는다.
     * @param 	field	필드.
     * @return	Object
     */
    fun getObjectFromField(field: Field?):Any {
        if (field == null) throw RuntimeException("CReflect error! Field is NULL")
        try {
            return field.get(null)
        } catch (e:IllegalArgumentException) {
            throw RuntimeException("CReflect error! IllegalArgumentException. Field: ${field.name}")
        } catch (e:IllegalAccessException) {
            throw RuntimeException("CReflect error! IllegalAccessException. Field: ${field.name}")
        }
    }

    /**
     * 필드로부터 Int Object를 얻는다.
     * @param 	field	필드.
     * @return	Int Object
     */
    fun getIntFromField(field: Field):Int {
        try {
            return field.getInt(null)
        } catch (e:IllegalArgumentException) {
            throw RuntimeException("CReflect error! IllegalArgumentException. Field:${field.name}")
        } catch (e:IllegalAccessException) {
            throw RuntimeException("CReflect error! IllegalAccessException. Field:${field.name}")
        }
    }

    /**
     * 필드로부터 Boolean Object를 얻는다.
     * @param field	필드.
     * @return	Boolean Object
     */
    fun getBooleanfromField(field: Field):Boolean {
        try {
            return field.getBoolean(null)
        } catch (e:IllegalArgumentException) {
            throw RuntimeException("CReflect error! IllegalAccessException. Field:${field.name}")
        } catch (e:IllegalAccessException) {
            throw RuntimeException("CReflect error! IllegalAccessException. Field:${field.name}")
        }
    }

    /**
     * Class로 부터 인스턴스를 생성한다.
     * @param c	Class
     * @return	생성된 인스턴스.
     */
    fun createInstance(c:Class<*>):Any {
        try {
            return c.newInstance()
        } catch (e:InstantiationException) {
            throw RuntimeException("CReflect error! InstantiationException. Field:${c.name}")
        } catch (e:IllegalAccessException) {
            throw RuntimeException("CReflect error! IllegalAccessException. Field:${c.name}")
        }
    }

    /**
     * Class와 Parameter로 부터 인스턴스를 생성한다.
     * @param c            Class
     * @param params    Parameter Class 배열.
     * @param args        Parameter insance 배열.
     * @return    생성된 instance.
     *
     * @code
     * CReflect.createInstance(
     * * 				viewClassList.get(pos).getClassInfo(),
     * new Class[] {Context.class},
     * getApplicationContext());
     * @endcode
     */
    fun createInstance(c: Class<*>, params: Array<Class<*>>, vararg args: Any): Any {
        try {
            return c.getConstructor(*params).newInstance(*args)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("CReflect error! NoSuchMethodException.")
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("CReflect error! IllegalArgumentException.")
        } catch (e: InstantiationException) {
            throw RuntimeException("CReflect error! InstantiationException.")
        } catch (e: IllegalAccessException) {
            throw RuntimeException("CReflect error! IllegalAccessException.")
        } catch (e: InvocationTargetException) {
            throw RuntimeException("CReflect error! InvocationTargetException")
        }
    }

    fun methodInvoke(c: Class<*>, methodName: String, params: Array<Class<*>>, vararg args: Any): Any {
        val inst = createInstance(c)
        val method: Method?
        try {
            method = c.getMethod(methodName, *params)
            return method!!.invoke(inst, args)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("CReflect error! NoSuchMethodException")
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("CReflect error! IllegalArgumentException")
        } catch (e: IllegalAccessException) {
            throw RuntimeException("CReflect error! IllegalAccessException")
        } catch (e: InvocationTargetException) {
            throw RuntimeException("CReflect error! InvocationTargetException")
        }
    }

}

