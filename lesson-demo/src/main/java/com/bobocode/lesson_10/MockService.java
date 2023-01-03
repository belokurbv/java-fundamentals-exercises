package com.bobocode.lesson_10;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class MockService {
    void print(@NotNull String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MockService.class);
        MethodInterceptor interceptor = (obj, method, arguments, proxy) -> {
            var params = method.getParameters();
            for (int i = 0; i < params.length; i++) {
                if (params[i].isAnnotationPresent(NotNull.class) && args[i] == null) {
                    throw new RuntimeException(params[i].getName() + " should not be null in method "
                            + method.getName());
                }
            }
            return proxy.invokeSuper(obj, arguments);
        };
        enhancer.setCallback(interceptor);
        var service = (MockService) enhancer.create();
        service.print(null);
    }
}
