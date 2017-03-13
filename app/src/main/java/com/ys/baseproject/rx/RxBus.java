package com.ys.baseproject.rx;

import rx.Observable;
import rx.functions.Func1;
import rx.internal.util.ObjectPool;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yunshan on 17/3/13.
 */

public class RxBus {

    private static RxBus instance;

    /** Subject 既可以充当被观察者，可以充当观察者 */
    private Subject<Event,Event> publishSubject;

    class Event<T>{
        private String tag;
        private T obj;

        public Event(String tag,T obj){
            this.tag = tag;
            this.obj = obj;
        }

        public void setObj(T obj){
            this.obj = obj;
        }

        public T getObj(){
            return obj;
        }

        public void setTag(String tag){
            this.tag = tag;
        }

        public String getTag(){
            return tag;
        }
    }

    public static RxBus getRxBus(){
        synchronized(RxBus.class){
            if(instance == null){
                instance = new RxBus();
            }
        }
        return instance;
    }

   private RxBus (){
       //初始化Subject
       publishSubject = new SerializedSubject<>(PublishSubject.<Event>create());
   }

   //发送事件
   public void post(String tag, Object o){
       Event event = new Event(tag,o);
       publishSubject.onNext(event);
   }

    /**
     * 只能接受tag一样的事件.  eg:tag为空只能接收tag为空的
     * @param tag
     * @param eventType
     * @param <T>
     * @return
     */
    public <T extends Object> Observable<T> register(final String tag, final Class<T> eventType) {
        return publishSubject.filter(new Func1<Event, Boolean>() {
            @Override
            public Boolean call(Event event) {
                if (tag == null) {
                    return event.tag == null && eventType.isInstance(event.obj);
                } else {
                    return tag.equals(event.tag) && eventType.isInstance(event.obj);
                }
            }
        })
                .map(new Func1<Event, T>() {
                    @Override
                    public T call(Event event) {
                        return (T) event.obj;
                    }
                })
                .cast(eventType);
    }
}
