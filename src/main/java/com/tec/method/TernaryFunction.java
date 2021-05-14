package com.tec.method;

/**
 * @ClassName TernaryFunction
 * @Description TODO
 * @Author sunlei4
 * @Date 2021/5/12 23:26
 **/
public interface TernaryFunction<T,U,V,R>{
    R apply(T t,U u,V v);

//    default public BiFunction<T,U,R> get(V v){
//        return (t,u)->this.apply(t,u,v);
//    }
}