package mkcoding.services.dynamicproxy;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

/**
 * Created by mx on 16/7/25.
 */
public class Client {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();

        InvocationHandler handler = new DynamicProxy(realSubject);

        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

        System.out.println(subject.getClass().getName());

        subject.rent();
        subject.hello("World");
    }
}
