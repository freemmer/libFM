package com.tistory.freemmer.lib.libfm.platform

import java.util.*

/**
 * Created by freemmer on 22/01/2019.
 * History
 *    - 22/01/2019 Create file
 */
class FMBeanManager private constructor() {
    private val classMap = HashMap<Class<*>, Any>()

    companion object {
        private var thiz: FMBeanManager = FMBeanManager()

        fun init(classMap: HashMap<Class<*>, Any>): FMBeanManager {
            thiz.classMap.clear()
            thiz.classMap.putAll(classMap)
            return thiz
        }

        fun registerClass(beanClass: Class<*>, inst: Any): FMBeanManager {
            val obj = thiz.classMap[beanClass]
            if (obj != null) {
                thiz.classMap.remove(obj)
            }
            thiz.classMap.put(beanClass, inst)
            return thiz
        }

        fun unregisterClass(beanClass: Class<*>): FMBeanManager {
            val obj = thiz.classMap[beanClass]
            if (obj != null) {
                thiz.classMap.remove(obj)
            }
            return thiz
        }

        fun <T> getClass(beanClass: Class<T>): T? {
            val obj = thiz.classMap[beanClass]
            if (obj != null && beanClass.isInstance(obj)) {
                return beanClass.cast(obj)
            }
            return null
        }
    }

}

