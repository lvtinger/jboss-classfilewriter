/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2012 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.classfilewriter.test.signature;

import org.jboss.classfilewriter.AccessFlag;
import org.jboss.classfilewriter.ClassFile;
import org.jboss.classfilewriter.ClassMethod;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class MethodSignatureTest<Y> {

    @Test
    public void methodSignatureTest() throws SecurityException, NoSuchFieldException, NoSuchMethodException {
        ClassFile test = new ClassFile(getClass().getName().replace('.', '/') + "GEN", "java/lang/Object", getClass().getClassLoader());
        ClassMethod cmth = test.addMethod(AccessFlag.PUBLIC | AccessFlag.ABSTRACT, "method1", "Ljava/util/List;");
        cmth.setSignature("()Ljava/util/List<Ljava/lang/Integer;>;");
        Class<?> clazz = test.define();
        Method method = clazz.getDeclaredMethod("method1");
        ParameterizedType fieldType = (ParameterizedType) method.getGenericReturnType();
        Assert.assertEquals(List.class, fieldType.getRawType());
        Assert.assertEquals(Integer.class, fieldType.getActualTypeArguments()[0]);

    }

}
