package tools.classFinder2;

import java.lang.reflect.Modifier;

/**
 *
 * @author Andrew Vitkus
 */
public enum ClassType {

    CLASS, ABSTRACT, INTERFACE, LOCAL, MEMBER, PRIMITIVE, SYNTHETIC, ANNOTATION, ANONYMOUS, ARRAY, ENUM, UNDEFINED;

    public static ClassType getClassType(Class<?> _class) {
        if (_class.isInterface()) {
            return INTERFACE;
        } else if (_class.isEnum()) {
            return ENUM;
        } else if (_class.isAnonymousClass()) {
            return ANONYMOUS;
        } else if (_class.isMemberClass()) {
            return MEMBER;
        } else if (_class.isLocalClass()) {
            return LOCAL;
        } else if (_class.isAnnotation()) {
            return ANNOTATION;
        } else if (_class.isArray()) {
            return ARRAY;
        } else if (_class.isPrimitive()) {
            return PRIMITIVE;
        } else if (_class.isSynthetic()) {
            return SYNTHETIC;
        } else if (_class.getEnclosingClass() == null && Object.class.isAssignableFrom(_class)) {
            if (Modifier.isAbstract(_class.getModifiers())) {
                return ABSTRACT;
            } else {
                return CLASS;
            }
        } else {
            return UNDEFINED;
        }
    }
}
