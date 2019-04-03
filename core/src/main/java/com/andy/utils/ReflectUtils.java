package com.andy.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
    /**
     * Create an instance of a class using the constructor matching
     * the given arguments.
     *
     * @param <T>      desired type
     * @param ofClass  Class&lt;T&gt;
     * @param argTypes Class&lt;?&gt;[]
     * @param args     Object[]
     * @return class instance
     * @since Ant 1.8.0
     */
    public static <T> T newInstance(Class<T> ofClass,
                                    Class<?>[] argTypes,
                                    Object[] args) throws Exception {
        Constructor<T> con = ofClass.getConstructor(argTypes);
        return con.newInstance(args);
    }

    /**
     * Call a method on the object with no parameters.
     *
     * @param <T>        desired type
     * @param obj        the object to invoke the method on.
     * @param methodName the name of the method to call
     * @return the object returned by the method
     */
    @SuppressWarnings("unchecked")
    public static <T> T invoke(Object obj, String methodName) throws Exception {
        Method method = obj.getClass().getMethod(methodName);
        return (T) method.invoke(obj);
    }

    /**
     * Call a method on the object with no parameters.
     * Note: Unlike the invoke method above, this
     * calls class or static methods, not instance methods.
     *
     * @param <T>        desired type
     * @param obj        the object to invoke the method on.
     * @param methodName the name of the method to call
     * @return the object returned by the method
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeStatic(Object obj, String methodName) throws Exception {
        Method method = ((Class<?>) obj).getMethod(methodName);
        return (T) method.invoke(obj);
    }

    /**
     * Call a method on the object with one argument.
     *
     * @param <T>        desired type
     * @param obj        the object to invoke the method on.
     * @param methodName the name of the method to call
     * @param argType    the type of argument.
     * @param arg        the value of the argument.
     * @return the object returned by the method
     */
    @SuppressWarnings("unchecked")
    public static <T> T invoke(
            Object obj, String methodName, Class<?> argType, Object arg) throws Exception {
        Method method = obj.getClass().getMethod(methodName, argType);
        return (T) method.invoke(obj, arg);
    }

    /**
     * Call a method on the object with two argument.
     *
     * @param <T>        desired type
     * @param obj        the object to invoke the method on.
     * @param methodName the name of the method to call
     * @param argType1   the type of the first argument.
     * @param arg1       the value of the first argument.
     * @param argType2   the type of the second argument.
     * @param arg2       the value of the second argument.
     * @return the object returned by the method
     */
    @SuppressWarnings("unchecked")
    public static <T> T invoke(
            Object obj, String methodName, Class<?> argType1, Object arg1,
            Class<?> argType2, Object arg2) throws Exception {
        Method method =
                obj.getClass().getMethod(methodName, argType1, argType2);
        return (T) method.invoke(obj, arg1, arg2);
    }

    /**
     * Get the value of a field in an object.
     *
     * @param <T>       desired type
     * @param obj       the object to look at.
     * @param fieldName the name of the field in the object.
     * @return the value of the field.
     * @throws Exception if there is an error.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getField(Object obj, String fieldName)
            throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }
}